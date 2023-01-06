package com.api.agenda.controller;

import com.api.agenda.request.AgendaRequest;
import com.api.agenda.response.AgendaResponse;
import com.api.agenda.service.AgendaService;
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

@Validated
@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {
    private final AgendaService agendaService;

    @Operation(summary = "Pegar uma agenda por id", description = "Pegar uma agenda no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Resgatou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idAgenda}")
    public ResponseEntity<AgendaResponse> pegarAgenda(@PathVariable(name = "idAgenda") Long idAgenda) {
        return new ResponseEntity<>(agendaService.pegarAgenda(idAgenda), HttpStatus.OK);
    }

    @Operation(summary = "Listar todas as agendas", description = "Listar agendas do banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Listou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/list-all")
    public ResponseEntity<List<AgendaResponse>> listAgenda() {
        return new ResponseEntity<>(agendaService.listAll(), HttpStatus.OK);
    }

    @Operation(summary = "Salvar uma agenda", description = "Salvar uma agenda no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Salvou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/salvar")
    public ResponseEntity<AgendaResponse> salvarAgenda(@Valid @RequestBody AgendaRequest agendaRequest) {
        return new ResponseEntity<>(agendaService.salvar(agendaRequest), HttpStatus.CREATED);
    }
}
