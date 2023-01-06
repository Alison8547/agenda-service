package com.api.agenda.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paciente_id", insertable = false, updatable = false)
    private Long idPaciente;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_hora")
    private LocalDateTime horario;

    @Column(name = "data_criacao")
    private LocalDateTime dataCriacao;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;
}
