package com.api.agenda.response;

import com.api.agenda.enums.TipoEndereco;
import lombok.Data;

@Data
public class EnderecoResponse {

    private Long id;
    private Long pacienteId;
    private TipoEndereco tipoEndereco;
    private String logradouro;
    private String complemento;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;
}
