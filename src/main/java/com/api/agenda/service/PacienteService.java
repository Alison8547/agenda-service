package com.api.agenda.service;

import com.api.agenda.exception.BusinessException;
import com.api.agenda.mapper.PacienteMapper;
import com.api.agenda.model.Paciente;
import com.api.agenda.repository.PacienteRepository;
import com.api.agenda.request.PacienteRequest;
import com.api.agenda.response.PacienteResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
