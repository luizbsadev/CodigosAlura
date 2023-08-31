package med.voll.api.dto;

import jakarta.persistence.Embedded;
import med.voll.api.entidade.Endereco;
import med.voll.api.entidade.Paciente;

public record DadosDetalhamentoPacienteDTO(Long id, String nome, String email, String telefone, String cpf, Endereco endereco) {
    public DadosDetalhamentoPacienteDTO(Paciente paciente){
        this(paciente.getId(), paciente.getNome(), paciente.getEmail(), paciente.getTelefone(), paciente.getCpf(), paciente.getEndereco());
    }
}
