package com.api.agenda.service;

import com.api.agenda.exception.BusinessException;
import com.api.agenda.mapper.AgendaMapper;
import com.api.agenda.model.Agenda;
import com.api.agenda.model.Paciente;
import com.api.agenda.repository.AgendaRepository;
import com.api.agenda.request.AgendaRequest;
import com.api.agenda.response.AgendaResponse;
import com.api.agenda.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgendaService {
    private final AgendaRepository agendaRepository;
    private final PacienteService pacienteService;
    private final AgendaMapper agendaMapper;

    public AgendaResponse salvar(AgendaRequest agendaRequest) {
        Agenda agendaEntity = agendaMapper.toAgenda(agendaRequest);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        agendaEntity.setDataCriacao(now);

        if (!verificarDatas(agendaEntity.getDataCriacao(), agendaEntity.getHorario())) {
            throw new BusinessException("Horario marcado não poder ser antes do horario da criação!");
        }

        Paciente paciente = pacienteService.buscarPorId(agendaEntity.getIdPaciente());
        agendaEntity.setPaciente(paciente);

        return agendaMapper.toAgendaResponse(agendaRepository.save(agendaEntity));

    }

    public AgendaResponse pegarAgenda(Long idAgenda) {
        return agendaMapper.toAgendaResponse(findById(idAgenda));
    }

    public List<AgendaResponse> listAll() {
        return agendaMapper.agendaResponseList(agendaRepository.findAll());
    }

    public PageResponse<AgendaResponse> listAgendaPaginado(Integer page, Integer size, Long idPaciente) {
        if (page < 0 || size < 0) {
            throw new BusinessException("Page ou Size não pode ser menor que zero!");
        }

        if (size > 0) {
            Page<Agenda> pageAgenda = filtrosAgenda(page, size, idPaciente);
            List<AgendaResponse> agendaResponseList = pageAgenda.getContent().stream()
                    .map(agendaMapper::toAgendaResponse)
                    .toList();

            return new PageResponse<>(pageAgenda.getTotalElements(),
                    pageAgenda.getTotalPages(),
                    page,
                    size,
                    agendaResponseList);
        }
        List<AgendaResponse> listEmpty = new ArrayList<>();
        return new PageResponse<>(0L, 0, 0, size, listEmpty);
    }

    private Page<Agenda> filtrosAgenda(Integer page, Integer size, Long idPaciente) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (!(idPaciente == null)) {
            return agendaRepository.findAllByPaciente_Id(idPaciente, pageRequest);
        }
        return agendaRepository.findAll(pageRequest);
    }

    public boolean verificarDatas(LocalDateTime dataCriacao, LocalDateTime horario) {
        return !horario.isBefore(dataCriacao);
    }


    public Agenda findById(Long idAgenda) {
        return agendaRepository.findById(idAgenda)
                .orElseThrow(() -> new BusinessException("Agenda não encontrada!"));
    }
}
