package med.voll.api.medico.controller;

import jakarta.validation.Valid;
import med.voll.api.medico.dto.DadosListarMedicosDTO;
import med.voll.api.medico.entidade.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.medico.dto.DadosMedicoDTO;
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
    public void cadastrar(@RequestBody @Valid DadosMedicoDTO dadosMedicoDTO){
        Medico medico = new Medico(dadosMedicoDTO);
        repository.save(medico);
    }

    @GetMapping
    public List<DadosListarMedicosDTO> listarMedico(@PageableDefault(size = 5, sort = {"nome"} ) Pageable pageable){
        return repository.findAll(pageable).stream().map(DadosListarMedicosDTO::new).toList();
    }
}
