package med.voll.api.dto;

import med.voll.api.entidade.Especialidade;
import med.voll.api.entidade.Medico;

public record DadosListarMedicosDTO(String nome, String email, String crm, Especialidade especialidade) {
    public DadosListarMedicosDTO(Medico medico){
       this(medico.getNome(), medico.getEmail(), medico.getCrm(), medico.getEspecialidade());
    }
}
