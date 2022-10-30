package com.api.agenda.service;

import com.api.agenda.exception.BusinessException;
import com.api.agenda.model.Paciente;
import com.api.agenda.repository.PacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class PacienteService {

    private final PacienteRepository repository;


    public Paciente salvar(Paciente paciente) {
        Optional<Paciente> optionalPaciente = repository.findByCpf(paciente.getCpf());
        boolean existeCpf = false;
        if (optionalPaciente.isPresent()) {
            if (!optionalPaciente.get().getId().equals(paciente.getId())) {
                existeCpf = true;
            }

        }

        if (existeCpf) {
            throw new BusinessException("Cpf já cadastrado!");
        }

        return repository.save(paciente);
    }

    public List<Paciente> list() {
        return repository.findAll();
    }

    public Optional<Paciente> buscarPorId(Long id) {
        return repository.findById(id);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }

    public Paciente update(Long id, Paciente paciente) {
        Optional<Paciente> optional = buscarPorId(id);

        if (optional.isEmpty()) {
            throw new BusinessException("Paciente não encontrado");
        }

        paciente.setId(id);

        return repository.save(paciente);


    }


}
