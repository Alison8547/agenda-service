package com.api.agenda.controller;

import com.api.agenda.request.EnderecoRequest;
import com.api.agenda.response.EnderecoResponse;
import com.api.agenda.service.EnderecoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Validated
@RequiredArgsConstructor
@RestController
@RequestMapping("/endereco")
public class EnderecoController {

    private final EnderecoService enderecoService;

    @Operation(summary = "Pegar um endereço por id", description = "Pegar um endereço no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200", description = "Resgatou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @GetMapping("/{idEndereco}")
    public ResponseEntity<EnderecoResponse> pegarAgenda(@PathVariable(name = "idEndereco") Long idEndereco) {
        return new ResponseEntity<>(enderecoService.pegarEndereco(idEndereco), HttpStatus.OK);
    }


    @Operation(summary = "Salvar um endereço", description = "Salvar um endereço no banco de dados")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201", description = "Salvou com sucesso"),
                    @ApiResponse(responseCode = "403", description = "Você não tem permissão para acessar este recurso"),
                    @ApiResponse(responseCode = "500", description = "Foi gerada uma exceção")
            }
    )
    @PostMapping("/salvar")
    public ResponseEntity<EnderecoResponse> salvarAgenda(@Valid @RequestBody EnderecoRequest enderecoRequest) {
        return new ResponseEntity<>(enderecoService.create(enderecoRequest), HttpStatus.CREATED);
    }
}
