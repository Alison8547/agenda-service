package com.api.agenda.response;

import lombok.Data;

import java.util.List;

@Data
public class PacienteResponsePersonalizado {
    private Long id;
    private String nome;
    private String sobrenome;
    private String email;
}
