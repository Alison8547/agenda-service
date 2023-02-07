package com.api.agenda.mapper;

import com.api.agenda.model.Endereco;
import com.api.agenda.request.EnderecoRequest;
import com.api.agenda.response.EnderecoResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class EnderecoMapper {

    private final ModelMapper mapper;


    public Endereco toEndereco(EnderecoRequest enderecoRequest) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(enderecoRequest, Endereco.class);
    }

    public EnderecoResponse toEnderecoResponse(Endereco endereco) {
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        return mapper.map(endereco, EnderecoResponse.class);
    }

    public List<EnderecoResponse> enderecoResponseList(List<Endereco> enderecoList) {
        return enderecoList.stream()
                .map(this::toEnderecoResponse)
                .toList();
    }
}
