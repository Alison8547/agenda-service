package com.api.agenda.request;

import com.api.agenda.enums.TipoContato;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class ContatoRequest {
    @NotBlank
    @Schema(description = "Seu número de contato", example = "8199999999")
    private String numero;
    @NotNull
    @Schema(description = "Seu tipo de contato",example = "Residencial")
    private TipoContato tipoContato;
}
