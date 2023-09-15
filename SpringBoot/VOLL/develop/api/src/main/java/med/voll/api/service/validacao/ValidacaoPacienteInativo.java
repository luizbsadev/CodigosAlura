package med.voll.api.service.validacao;

import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.entidade.Paciente;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoPacienteInativo implements ValidacaoConsultaInterface {
    @Autowired
    PacienteRepository pacienteRepository;
    @Override
    public void validar(DadosMarcarConsultaDTO dados) {
        if(!pacienteRepository.findAtivoById(dados.idPaciente())){
            throw new ValidacaoException("Paciente está inativo");
        }
    }
}
