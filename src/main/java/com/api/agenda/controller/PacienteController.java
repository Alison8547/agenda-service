package com.api.agenda.controller;

import com.api.agenda.mapper.PacienteMapper;
import com.api.agenda.model.Paciente;
import com.api.agenda.request.PacienteRequest;
import com.api.agenda.response.PacienteResponse;
import com.api.agenda.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@RestController
@Validated
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService service;
    private final PacienteMapper mapper;

    @GetMapping
    public ResponseEntity<List<PacienteResponse>> list() {

        List<PacienteResponse> pacienteResponses = mapper.pacienteResponseList(service.list());
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
    }

    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest paciente) {

        Paciente toPaciente = mapper.toPaciente(paciente);
        Paciente pacienteSalvo = service.salvar(toPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPacienteResponse(pacienteSalvo));
    }

    @GetMapping("/{idPaciente}")
    public ResponseEntity<PacienteResponse> buscarByIdPaciente(@PathVariable(name = "idPaciente") Long id) {

        Optional<Paciente> paciente = service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(paciente.get()));
    }

    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteResponse> alterarPaciente(@PathVariable(name = "idPaciente") Long id, @Valid @RequestBody PacienteRequest paciente) {

        Paciente toPaciente = mapper.toPaciente(paciente);
        Paciente pacienteSalvo = service.update(id, toPaciente);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(pacienteSalvo));
    }

    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Void> deletePaciente(@PathVariable(name = "idPaciente") Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
