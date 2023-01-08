package com.api.agenda.service;

import com.api.agenda.exception.BusinessException;
import com.api.agenda.mapper.PacienteMapper;
import com.api.agenda.model.Paciente;
import com.api.agenda.repository.PacienteRepository;
import com.api.agenda.request.PacienteRequest;
import com.api.agenda.response.PacienteResponse;
import com.api.agenda.response.PageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository pacienteRepository;
    private final PacienteMapper pacienteMapper;


    public PacienteResponse salvar(PacienteRequest pacienteRequest) {
        Paciente pacienteEntity = pacienteMapper.toPaciente(pacienteRequest);
        if (pacienteRepository.existsByCpf(pacienteEntity.getCpf()) || pacienteRepository.existsByEmail(pacienteEntity.getEmail())) {
            throw new BusinessException("Cpf ou Email já consta no nosso sistema!");
        }

        return pacienteMapper.toPacienteResponse(pacienteRepository.save(pacienteEntity));
    }

    public List<PacienteResponse> list() {
        return pacienteMapper.pacienteResponseList(pacienteRepository.findAll());
    }

    public PageResponse<PacienteResponse> listPacientePagina(Integer page, Integer size, String nome, String email) {
        if (page < 0 || size < 0) {
            throw new BusinessException("Page ou Size não pode ser menor que zero!");
        }

        if (size > 0) {
            Page<Paciente> pacientePage = filtrosPaciente(page, size, nome, email);
            List<PacienteResponse> pacienteResponseList = pacientePage.getContent().stream()
                    .map(pacienteMapper::toPacienteResponse)
                    .toList();

            return new PageResponse<>(pacientePage.getTotalElements(),
                    pacientePage.getTotalPages(),
                    page, size, pacienteResponseList);
        }

        List<PacienteResponse> listEmpty = new ArrayList<>();
        return new PageResponse<>(0L, 0, 0, size, listEmpty);
    }

    public Page<Paciente> filtrosPaciente(Integer page, Integer size, String nome, String email) {
        PageRequest pageRequest = PageRequest.of(page, size);
        if (nome != null) {
            return pacienteRepository.findAllByNomeContainingIgnoreCase(nome, pageRequest);
        } else if (email != null) {
            return pacienteRepository.findAllByEmailIgnoreCase(email, pageRequest);
        }

        return pacienteRepository.findAll(pageRequest);
    }

    public Paciente buscarPorId(Long idPaciente) {
        return pacienteRepository.findById(idPaciente).orElseThrow(() -> new BusinessException("Paciente não encontrado"));
    }

    public PacienteResponse pegarPaciente(Long idPaciente) {
        return pacienteMapper.toPacienteResponse(buscarPorId(idPaciente));
    }

    public void deletar(Long idPaciente) {
        pacienteRepository.deleteById(idPaciente);
    }

    public PacienteResponse update(Long idPaciente, PacienteRequest pacienteRequest) {
        Paciente pacienteEncontrado = buscarPorId(idPaciente);
        pacienteEncontrado.setNome(pacienteRequest.getNome());
        pacienteEncontrado.setCpf(pacienteRequest.getCpf());
        pacienteEncontrado.setEmail(pacienteRequest.getEmail());
        pacienteEncontrado.setSobrenome(pacienteRequest.getSobrenome());

        return pacienteMapper.toPacienteResponse(pacienteRepository.save(pacienteEncontrado));
    }


}
