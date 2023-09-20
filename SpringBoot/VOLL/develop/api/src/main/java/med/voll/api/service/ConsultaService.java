package med.voll.api.service;

import med.voll.api.dto.DadosDetalhamentoConsultaDTO;
import med.voll.api.dto.DadosMarcarConsultaDTO;
import med.voll.api.entidade.Consulta;
import med.voll.api.entidade.Medico;
import med.voll.api.entidade.Paciente;
import med.voll.api.exception.ValidacaoException;
import med.voll.api.repository.ConsultaRepository;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.repository.PacienteRepository;
import med.voll.api.service.validacao.ValidacaoConsultaInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ConsultaService {

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ConsultaRepository consultaRepository;

    @Autowired
    private List<ValidacaoConsultaInterface> validadores;

    public DadosDetalhamentoConsultaDTO agendar(DadosMarcarConsultaDTO dados) {
        if(!pacienteRepository.existsById(dados.idPaciente()))
            throw new ValidacaoException("id do paciente não existe");
        if(dados.idMedico() != null && !medicoRepository.existsById(dados.idMedico()))
            throw new ValidacaoException("id do medico não existe");

        validadores.forEach(v -> v.validar(dados));

        Medico medico = escolherMedico(dados);
        Paciente paciente = pacienteRepository.getReferenceById(dados.idPaciente());
        LocalDateTime data = dados.data();

        Consulta consulta = new Consulta(null, medico, paciente, data);

        consultaRepository.save(consulta);

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
