package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosListarMedicosDTO;
import med.voll.api.dto.DadosAlterarMedicoDTO;
import med.voll.api.entidade.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.dto.DadosCadastroMedicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoRepository repository;
    @PostMapping
    public void cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dadosMedicoDTO){
        Medico medico = new Medico(dadosMedicoDTO);
        repository.save(medico);
    }

    @GetMapping
    public List<DadosListarMedicosDTO> listarMedico(@PageableDefault(size = 5, sort = {"nome"} ) Pageable pageable){
        return repository.findAll(pageable).stream().map(DadosListarMedicosDTO::new).toList();
    }

    @PutMapping
    @Transactional
    public void alterarMedico(@RequestBody DadosAlterarMedicoDTO dadosMedicoDTO){
        Medico medico = repository.getReferenceById(dadosMedicoDTO.id());
        medico.alterarMedico(dadosMedicoDTO);

    }
}