package com.api.agenda.response;

import lombok.Data;

@Data
public class PacienteResponse {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;
}
