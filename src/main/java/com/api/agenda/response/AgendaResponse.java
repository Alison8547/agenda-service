package com.api.agenda.response;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AgendaResponse {

    private Long id;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime horario;
    private PacienteResponse paciente;

}
