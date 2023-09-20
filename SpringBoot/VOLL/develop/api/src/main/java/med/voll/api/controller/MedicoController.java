package med.voll.api.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosDetalhamentoMedicoDTO;
import med.voll.api.dto.DadosListarMedicosDTO;
import med.voll.api.dto.DadosAlterarMedicoDTO;
import med.voll.api.entidade.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.dto.DadosCadastroMedicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoRepository repository;
    @PostMapping
    public ResponseEntity cadastrarMedico(@RequestBody @Valid DadosCadastroMedicoDTO dadosMedicoDTO, UriComponentsBuilder uriBuilder){
        Medico medico = new Medico(dadosMedicoDTO);
        repository.save(medico);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(medico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoMedicoDTO(medico));
    }

    @GetMapping("/{id}")
    public ResponseEntity detalharMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListarMedicosDTO>> listarMedico(@PageableDefault(size = 5, sort = {"nome"} ) Pageable pageable){
        Page<DadosListarMedicosDTO> lista = repository.findAllByAtivoTrue(pageable).map(DadosListarMedicosDTO::new);
        return ResponseEntity.ok(lista);
    }

    @PutMapping
    @Transactional
    public ResponseEntity alterarMedico(@RequestBody DadosAlterarMedicoDTO dadosMedicoDTO){
        Medico medico = repository.getReferenceById(dadosMedicoDTO.id());
        medico.alterarMedico(dadosMedicoDTO);

        return ResponseEntity.ok(new DadosDetalhamentoMedicoDTO(medico));
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity excluirMedico(@PathVariable Long id){
        Medico medico = repository.getReferenceById(id);
        medico.excluir();

        return ResponseEntity.noContent().build();

    }
}
