package com.api.agenda.request;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AgendaRequest {

    @NotNull
    private Long idPaciente;

    @NotBlank
    private String descricao;

    @DateTimeFormat(pattern = "yyyy-MM-ddTHH:mm:ss")
    private LocalDateTime horario;
}
