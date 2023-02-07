package com.api.agenda.service;

import com.api.agenda.enums.TipoEndereco;
import com.api.agenda.exception.BusinessException;
import com.api.agenda.mapper.EnderecoMapper;
import com.api.agenda.model.Endereco;
import com.api.agenda.repository.EnderecoRepository;
import com.api.agenda.request.EnderecoRequest;
import com.api.agenda.response.EnderecoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EnderecoService {
    private final EnderecoMapper enderecoMapper;
    private final PacienteService pacienteService;
    private final EnderecoRepository enderecoRepository;


    public EnderecoResponse create(EnderecoRequest enderecoRequest) {
        Endereco enderecoEntity = enderecoMapper.toEndereco(enderecoRequest);
        enderecoEntity.setPaciente(pacienteService.buscarPorId(enderecoRequest.getIdPaciente()));
        enderecoEntity.setPacienteId(enderecoRequest.getIdPaciente());
        if (enderecoRequest.getTipoEndereco().equalsIgnoreCase("Residencial")) {
            enderecoEntity.setTipoEndereco(TipoEndereco.RESIDENCIAL);
        } else {
            enderecoEntity.setTipoEndereco(TipoEndereco.COMERCIAL);
        }
        return enderecoMapper.toEnderecoResponse(enderecoRepository.save(enderecoEntity));
    }

    public EnderecoResponse pegarEndereco(Long idEndereco) {
        return enderecoMapper.toEnderecoResponse(findById(idEndereco));
    }

    public void delete(Long idEndereco) {
        enderecoRepository.delete(findById(idEndereco));
    }


    public Endereco findById(Long idEndereco) {
        return enderecoRepository.findById(idEndereco).orElseThrow(() -> new BusinessException("Endereço não encontrado!"));
    }
}
