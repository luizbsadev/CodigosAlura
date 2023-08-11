package med.voll.api.dto;

import jakarta.validation.constraints.NotBlank;

public record EnderecoDTO(
        @NotBlank
        String logradouro,
        @NotBlank
        String bairro,
        @NotBlank
        String cep,
        @NotBlank
        String cidade,
        @NotBlank
        String uf,
        @NotBlank
        String numero,
        String complemento) {
}
