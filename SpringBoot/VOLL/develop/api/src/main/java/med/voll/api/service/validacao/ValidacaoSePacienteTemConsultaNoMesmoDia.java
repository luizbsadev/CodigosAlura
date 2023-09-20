package med.voll.api.service.validacao;

import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidacaoSePacienteTemConsultaNoMesmoDia implements ValidacaoConsultaInterface{

    @Autowired
    ConsultaRepository repository;
    @Override
    public void validar(DadosMarcarConsultaDTO dados) {
        LocalDateTime primeiroHorario = dados.data().withHour(7);
        LocalDateTime ultimoHorario = dados.data().withHour(19);


        if(repository.existsByPacienteIdAndDataBetween(dados.idPaciente(), primeiroHorario, ultimoHorario)){
            throw new ValidacaoException("Paciente ja possui consulta marcada nesse horario");
        }
    }
}
