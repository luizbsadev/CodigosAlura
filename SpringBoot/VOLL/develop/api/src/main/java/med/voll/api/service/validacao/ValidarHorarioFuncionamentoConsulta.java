package med.voll.api.service.validacao;

import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.exception.ValidacaoException;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

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
