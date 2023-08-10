package med.voll.api.medico.dto;

import med.voll.api.medico.entidade.Medico;

public record DadosListarMedicosDTO(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListarMedicosDTO(Medico medico){
       this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
