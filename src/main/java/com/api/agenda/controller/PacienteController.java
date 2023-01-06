package com.api.agenda.controller;

import com.api.agenda.mapper.PacienteMapper;
import com.api.agenda.model.Paciente;
import com.api.agenda.request.PacienteRequest;
import com.api.agenda.response.PacienteResponse;
import com.api.agenda.service.PacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(summary = "Listar todos os pacientes", description = "Listar pacientes do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping
    public ResponseEntity<List<PacienteResponse>> list() {

        List<PacienteResponse> pacienteResponses = mapper.pacienteResponseList(service.list());
        return ResponseEntity.status(HttpStatus.OK).body(pacienteResponses);
    }

    @Operation(summary = "Salvar um paciente", description = "Salvar um paciente no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Salvou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping
    public ResponseEntity<PacienteResponse> salvar(@Valid @RequestBody PacienteRequest paciente) {

        Paciente toPaciente = mapper.toPaciente(paciente);
        Paciente pacienteSalvo = service.salvar(toPaciente);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPacienteResponse(pacienteSalvo));
    }

    @Operation(summary = "Pegar um paciente por id", description = "Pegar um paciente no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Resgatou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idPaciente}")
    public ResponseEntity<PacienteResponse> buscarByIdPaciente(@PathVariable(name = "idPaciente") Long id) {

        Optional<Paciente> paciente = service.buscarPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(paciente.get()));
    }

    @Operation(summary = "Atualizar um paciente", description = "Atualizar um paciente no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Atualizou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PutMapping("/{idPaciente}")
    public ResponseEntity<PacienteResponse> alterarPaciente(@PathVariable(name = "idPaciente") Long id, @Valid @RequestBody PacienteRequest paciente) {

        Paciente toPaciente = mapper.toPaciente(paciente);
        Paciente pacienteSalvo = service.update(id, toPaciente);
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPacienteResponse(pacienteSalvo));
    }

    @Operation(summary = "Apagar um paciente", description = "Apagar um paciente um paciente no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "204", description = "Apagou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @DeleteMapping("/{idPaciente}")
    public ResponseEntity<Void> deletePaciente(@PathVariable(name = "idPaciente") Long id) {
        service.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
