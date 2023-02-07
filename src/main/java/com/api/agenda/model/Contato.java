package com.api.agenda.model;

import com.api.agenda.enums.TipoContato;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity(name = "contato")
public class Contato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero")
    private String numero;

    @Column(name = "tipo_contato")
    @Enumerated(EnumType.ORDINAL)
    private TipoContato tipoContato;

    @Column(name = "paciente_id", insertable = false, updatable = false)
    private Long pacienteId;


    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    private Paciente paciente;

}
