package com.api.agenda.mapper;

import com.api.agenda.model.Contato;
import com.api.agenda.request.ContatoRequest;
import com.api.agenda.response.ContatoResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ContatoMapper {

    private final ModelMapper mapper;


    public Contato toContato(ContatoRequest contatoRequest) {
        return mapper.map(contatoRequest, Contato.class);
    }

    public ContatoResponse toContatoResponse(Contato contato) {
        return mapper.map(contato, ContatoResponse.class);
    }

    public List<ContatoResponse> contatoResponseList(List<Contato> contatos) {
        return contatos.stream()
                .map(this::toContatoResponse)
                .toList();
    }

}
