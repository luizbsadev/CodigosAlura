package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosCadastroPacienteDTO;
import med.voll.api.dto.DadosDetalhamentoPacienteDTO;
import med.voll.api.dto.DadosListarPacienteDTO;
import med.voll.api.entidade.Paciente;
import med.voll.api.dto.DadosAlterarPacienteDTO;
import med.voll.api.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    PacienteRepository repository;

    @PostMapping
    public ResponseEntity cadastrarPaciente(@RequestBody @Valid DadosCadastroPacienteDTO dados, UriComponentsBuilder uriBuilder){
        Paciente paciente = new Paciente(dados)       ;
        repository.save(paciente);
        URI uri = uriBuilder.path("/pacientes/{id}").buildAndExpand(paciente.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListarPacienteDTO>> listarPaciente(@PageableDefault(size = 5, sort = {"nome"} ) Pageable pageable){
         Page<DadosListarPacienteDTO> page = repository.findAllByAtivoTrue(pageable).map(DadosListarPacienteDTO::new);
         return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharPaciente(@PathVariable Long id){
        return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(repository.getReferenceById(id)));
        }

    @Transactional
    @PutMapping
    public ResponseEntity alterarPaciente(@RequestBody DadosAlterarPacienteDTO dados){
        Paciente paciente = repository.getReferenceById(dados.id());
        paciente.alterar(dados);
        return ResponseEntity.ok(new DadosDetalhamentoPacienteDTO(paciente));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity excluirPaciente(@PathVariable Long id){
        Paciente paciente = repository.getReferenceById(id);
        paciente.excluir();
        return ResponseEntity.noContent().build();
    }
}
