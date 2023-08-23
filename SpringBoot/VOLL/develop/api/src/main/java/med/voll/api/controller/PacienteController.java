package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroPacienteDTO;
import med.voll.api.dto.DadosListarPacienteDTO;
import med.voll.api.entidade.Paciente;
import med.voll.api.medico.dto.DadosAlterarPacienteDTO;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    public void cadastrar(@RequestBody @Valid DadosCadastroPacienteDTO dados){
        repository.save(new Paciente(dados));
    }

    @GetMapping
    public Page<DadosListarPacienteDTO> listarPaciente(@PageableDefault(size = 5, sort = {"nome"} ) Pageable pageable){
    return repository.findAllByAtivoTrue(pageable).map(DadosListarPacienteDTO::new);
    }

    @Transactional
    @PutMapping
    public void alterarPaciente(@RequestBody DadosAlterarPacienteDTO dados){
    Paciente paciente = repository.getReferenceById(dados.id());
    paciente.alterar(dados);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public void deletarPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.excluir();
    }
}
