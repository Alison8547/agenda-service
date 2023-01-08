package com.api.agenda.controller;

import com.api.agenda.model.Paciente;
import com.api.agenda.request.PacienteRequest;
import com.api.agenda.response.PacienteResponse;
import com.api.agenda.response.PageResponse;
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

    private final PacienteService pacienteService;

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
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.list());
    }

    @Operation(summary = "Listar todos os pacientes paginado", description = "Listar pacientes do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/list-paginado")
    public ResponseEntity<PageResponse<PacienteResponse>> listPaginadoPaciente(@RequestParam(required = false) Integer page, @RequestParam(required = false) Integer size, @RequestParam(required = false) String nome, @RequestParam(required = false) String email) {
        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.listPacientePagina(page, size, nome, email));
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

        return ResponseEntity.status(HttpStatus.CREATED).body(pacienteService.salvar(paciente));
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
    public ResponseEntity<PacienteResponse> buscarByIdPaciente(@PathVariable(name = "idPaciente") Long idPaciente) {

        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.pegarPaciente(idPaciente));
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
    public ResponseEntity<PacienteResponse> alterarPaciente(@PathVariable(name = "idPaciente") Long idPaciente, @Valid @RequestBody PacienteRequest pacienteRequest) {

        return ResponseEntity.status(HttpStatus.OK).body(pacienteService.update(idPaciente, pacienteRequest));
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
        pacienteService.deletar(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
