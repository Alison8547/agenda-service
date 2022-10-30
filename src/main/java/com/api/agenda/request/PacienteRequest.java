package com.api.agenda.request;

import lombok.Data;

@Data
public class PacienteRequest {
    private Long id;
    private String nome;
    private String sobrenome;
    private String cpf;
    private String email;

}
