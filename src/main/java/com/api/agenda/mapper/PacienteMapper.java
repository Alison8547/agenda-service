package com.api.agenda.mapper;

import com.api.agenda.model.Paciente;
import com.api.agenda.request.PacienteRequest;
import com.api.agenda.response.PacienteResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PacienteMapper {

    private final ModelMapper mapper;

    public Paciente toPaciente(PacienteRequest request) {
        return mapper.map(request, Paciente.class);
    }

    public PacienteResponse toPacienteResponse(Paciente paciente) {
        return mapper.map(paciente, PacienteResponse.class);
    }

    public List<PacienteResponse> pacienteResponseList(List<Paciente> paciente) {
        return paciente.stream()
                .map(this::toPacienteResponse)
                .toList();
    }
}
