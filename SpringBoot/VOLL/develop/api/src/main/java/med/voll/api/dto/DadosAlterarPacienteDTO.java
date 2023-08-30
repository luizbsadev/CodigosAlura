package med.voll.api.dto;

import med.voll.api.dto.EnderecoDTO;

public record DadosAlterarPacienteDTO(Long id, String nome, String telefone, EnderecoDTO enderecoDTO) {
}
