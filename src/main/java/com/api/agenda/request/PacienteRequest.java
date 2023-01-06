package com.api.agenda.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PacienteRequest {
    @NotBlank(message = "Nome do paciente é obrigatorio!")
    @Schema(description = "Nome do paciente",defaultValue = "José")
    private String nome;

    @NotBlank(message = "Sobrenome do paciente é obrigatorio!")
    @Schema(description = "Sobrenome do paciente",defaultValue = "Silva")
    private String sobrenome;

    @NotBlank(message = "Cpf do paciente é obrigatorio!")
    @Schema(description = "Cpf do paciente",defaultValue = "111-222-333-44")
    private String cpf;
    @Schema(description = "Email do paciente",defaultValue = "jose@hotmail.com")
    private String email;

}
