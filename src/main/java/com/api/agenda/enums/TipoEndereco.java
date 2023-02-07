package com.api.agenda.enums;

public enum TipoEndereco {
    COMERCIAL(0, "Comercial"), RESIDENCIAL(1, "Residencial");

    private final Integer tipo;
    private final String descricao;

    TipoEndereco(Integer tipo, String descricao) {
        this.tipo = tipo;
        this.descricao = descricao;
    }


    public Integer getTipo() {
        return tipo;
    }

    public String getDescricao() {
        return descricao;
    }
}
