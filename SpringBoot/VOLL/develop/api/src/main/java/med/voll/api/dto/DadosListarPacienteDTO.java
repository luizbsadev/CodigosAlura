package med.voll.api.dto;

import med.voll.api.entidade.Paciente;

public record DadosListarPacienteDTO(String nome, String email, String cpf) {
    public DadosListarPacienteDTO(Paciente paciente){
        this(paciente.getNome(), paciente.getEmail(), paciente.getCpf());
    }

}
