package com.api.agenda.response;

import com.api.agenda.enums.TipoContato;
import lombok.Data;

@Data
public class ContatoResponse {

    private Long id;
    private String numero;
    private TipoContato tipoContato;
    private PacienteResponse paciente;
}
