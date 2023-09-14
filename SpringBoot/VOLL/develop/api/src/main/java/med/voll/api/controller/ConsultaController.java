package med.voll.api.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import med.voll.api.dto.DadosDetalhamentoConsultaDTO;
import med.voll.api.dto.DadosMarcarConsultaDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {


    @PostMapping
    @Transactional
    public ResponseEntity marcarConsulta(@RequestBody @Valid DadosMarcarConsultaDTO dados){

        return ResponseEntity.ok().build();
    }
}
