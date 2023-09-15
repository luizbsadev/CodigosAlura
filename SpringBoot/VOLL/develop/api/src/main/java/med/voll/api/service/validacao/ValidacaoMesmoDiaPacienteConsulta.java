package med.voll.api.service.validacao;

import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ValidacaoMesmoDiaPacienteConsulta implements ValidacaoConsultaInterface{

    @Autowired
    ConsultaRepository consultaRepository;

    @Override
    public void validar(DadosMarcarConsultaDTO dados) {
        if(consultaRepository.existsByMedicoIdAndData(dados.idMedico(), dados.data()))
            throw new ValidacaoException("Esse horario ja preenchido");

    }
}
