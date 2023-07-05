package med.voll.api.medico.dto;

public record DadosMedicoDTO(String nome, String email, String crm, Especialidade especialidade, EnderecoDTO endereco) {
}
