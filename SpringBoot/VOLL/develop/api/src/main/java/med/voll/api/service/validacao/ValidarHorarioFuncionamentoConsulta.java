package med.voll.api.service.validacao;

import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Component
public class ValidarHorarioFuncionamentoConsulta implements ValidacaoConsultaInterface {
    @Override
    public void validar(DadosMarcarConsultaDTO dados) {
        LocalDateTime data = dados.data();
        if(data.getDayOfWeek() == DayOfWeek.SUNDAY){
            throw new ValidacaoException("A clinica não funciona no domingo");
        }
        if(data.getHour() < 7 || data.getHour() > 19){
            throw new ValidacaoException("Fora do horario de funcionamento da clinica");
        }
    }
}
