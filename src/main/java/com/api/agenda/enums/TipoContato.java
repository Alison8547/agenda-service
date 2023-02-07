package com.api.agenda.enums;

public enum TipoContato {
    RESIDENCIAL(0, "Residencial"), COMERCIAL(1, "Comercial"), WHATSAPP(2, "WhatsApp");

    private Integer valor;
    private String descricao;

    TipoContato(Integer valor, String descricao) {
        this.valor = valor;
        this.descricao = descricao;
    }

    public Integer getValor() {
        return valor;
    }

    public String getDescricao() {
        return descricao;
    }
}
