package com.api.agenda.mapper;

import com.api.agenda.model.Agenda;
import com.api.agenda.request.AgendaRequest;
import com.api.agenda.response.AgendaResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class AgendaMapper {
    private final ModelMapper modelMapper;

    public Agenda toAgenda(AgendaRequest agendaRequest) {
        return modelMapper.map(agendaRequest, Agenda.class);
    }

    public AgendaResponse toAgendaResponse(Agenda agenda) {
        return modelMapper.map(agenda, AgendaResponse.class);
    }


    public List<AgendaResponse> agendaResponseList(List<Agenda> agendas) {
        return agendas.stream()
                .map(this::toAgendaResponse)
                .toList();
    }
}
