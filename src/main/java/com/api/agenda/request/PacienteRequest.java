package com.api.agenda.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class PacienteRequest {
    @NotBlank(message = "Nome do paciente é obrigatorio!")
    private String nome;

    @NotBlank(message = "Sobrenome do paciente é obrigatorio!")
    private String sobrenome;

    @NotBlank(message = "Cpf do paciente é obrigatorio!")
    private String cpf;

    private String email;

}
