package med.voll.api.service.validacao;

import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacaoMedicoInativo implements ValidacaoConsultaInterface{

    @Autowired
    MedicoRepository medicoRepository;

    @Override
    public void validar(DadosMarcarConsultaDTO dados) {
        if(!medicoRepository.findAtivoById(dados.idMedico())){
            throw new ValidacaoException("Medico está inativo");
        }
    }
}
