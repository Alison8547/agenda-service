package com.api.agenda.request;

import com.api.agenda.enums.TipoEndereco;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class EnderecoRequest {

    @NotNull
    @Schema(description = "Id do paciente", example = "1")
    private Long idPaciente;
    @NotNull
    @Schema(description = "Tipo do seu endereço", example = "Residencial")
    private String tipoEndereco;
    @NotBlank
    @Schema(description = "Logradouro do seu endereço", example = "Rua da liberdade")
    private String logradouro;
    @NotBlank
    @Schema(description = "Complemento do endereço", example = "Riva Padaria")
    private String complemento;
    @NotBlank
    @Pattern(regexp = "^[0-9]{5}-[0-9]{3}$",message = "Padrão do cep está incorreto!")
    @Schema(description = "Seu cep", example = "53033-123")
    private String cep;
    @NotBlank
    @Schema(description = "Sua cidade", example = "Recife")
    private String cidade;
    @NotBlank
    @Schema(description = "Seu estado", example = "Pernambuco")
    private String estado;
    @NotBlank
    @Schema(description = "Seu país", example = "Brasil")
    private String pais;
}
