package com.api.agenda.controller;

import com.api.agenda.model.Paciente;
import com.api.agenda.service.PacienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/paciente")
public class PacienteController {

    private final PacienteService service;

    @GetMapping
    public ResponseEntity<List<Paciente>> list() {
        return ResponseEntity.status(HttpStatus.OK).body(service.list());
    }

    @PostMapping
    public ResponseEntity<Paciente> salvar(@RequestBody Paciente paciente) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.salvar(paciente));
    }
}
