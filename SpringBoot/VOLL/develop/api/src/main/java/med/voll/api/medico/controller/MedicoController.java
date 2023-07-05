package med.voll.api.medico.controller;

import med.voll.api.medico.dto.DadosMedicoDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/medicos")
public class MedicoController {

    @PostMapping
    public void cadastrar(@RequestBody DadosMedicoDTO dadosMedicoDTO){
        System.out.println(dadosMedicoDTO);
    }
}
