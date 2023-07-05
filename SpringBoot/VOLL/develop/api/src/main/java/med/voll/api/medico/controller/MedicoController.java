package med.voll.api.medico.controller;

import med.voll.api.medico.entidade.Medico;
import med.voll.api.repository.MedicoRepository;
import med.voll.api.medico.dto.DadosMedicoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @Autowired
    MedicoRepository repository;
    @PostMapping
    public void cadastrar(@RequestBody DadosMedicoDTO dadosMedicoDTO){
        Medico medico = new Medico(dadosMedicoDTO);
        repository.save(medico);
        System.out.println("Cadastro completo");
    }
}
