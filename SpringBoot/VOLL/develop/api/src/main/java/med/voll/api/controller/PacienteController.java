package med.voll.api.controller;

import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroPacienteDTO;
import med.voll.api.dto.DadosListarPacienteDTO;
import med.voll.api.entidade.Paciente;
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

}
