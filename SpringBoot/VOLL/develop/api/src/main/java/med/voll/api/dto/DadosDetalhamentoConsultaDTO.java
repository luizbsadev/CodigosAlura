package med.voll.api.dto;

import med.voll.api.entidade.Consulta;
import med.voll.api.entidade.Especialidade;

import java.time.LocalDateTime;

public record DadosDetalhamentoConsultaDTO(Long idMedico, Long idPaciente, LocalDateTime data) {
    public DadosDetalhamentoConsultaDTO(Consulta consulta) {
        this(consulta.getMedico().getId(), consulta.getPaciente().getId(), consulta.getData());
    }
}
