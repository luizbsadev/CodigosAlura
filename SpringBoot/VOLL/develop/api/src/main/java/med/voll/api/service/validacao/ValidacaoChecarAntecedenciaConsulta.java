package med.voll.api.service.validacao;

import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;


@Component
public class ValidacaoChecarAntecedenciaConsulta implements ValidacaoConsultaInterface{

    @Override
    public void validar(DadosMarcarConsultaDTO dados) {
        LocalDateTime data = dados.data();
        LocalDateTime dataAgora = LocalDateTime.now();
        Long diferenca = Duration.between(dataAgora, data).toMinutes();

        if(diferenca < 30){
            throw new ValidacaoException("Não se pode marcar consultas sem 30 minutos de atencedencia");
        }

    }
}
