package med.voll.api.service;

import med.voll.api.dto.DadosDetalhamentoConsultaDTO;
import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.entidade.Consulta;
import med.voll.api.entidade.Medico;
import med.voll.api.entidade.Paciente;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConsultaService {

    @Autowired
    MedicoRepository medicoRepository;

    @Autowired
    PacienteRepository pacienteRepository;

    public DadosDetalhamentoConsultaDTO agendar(DadosMarcarConsultaDTO dados) {
        if(!pacienteRepository.existsById(dados.idPaciente()))
            throw new ValidacaoException("id do paciente não existe");
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new ValidacaoException("id do medico não existe");

        Medico medico = escolherMedico(dados);
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        LocalDateTime data = dados.data();

        Consulta consulta = new Consulta(null, medico, paciente, data);

        return new DadosDetalhamentoConsultaDTO(consulta);
    }

    private Medico escolherMedico(DadosMarcarConsultaDTO dados) {
        if(dados.idMedico() != null)
            return medicoRepository.getReferenceById(dados.idMedico());
        if(dados.especialidade() == null)
            throw new ValidacaoException("Especialidade não pode ser nula caso o medico não tenha sido especificado");

        return medicoRepository.escolherMedicoAleatorioLivreNaDataPorEspecialidade(dados.especialidade(), dados.data());


    }
}
