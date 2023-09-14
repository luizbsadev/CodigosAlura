package med.voll.api.infra;

import jakarta.persistence.EntityNotFoundException;
import med.voll.api.dto.DadosMensagemErro400DTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

@RestControllerAdvice
public class TratamentoDeErro {
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity tratarErro404(){
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity tratarErro400(MethodArgumentNotValidException exception){
        List<FieldError> fieldErros = exception.getFieldErrors();
        List listaErros = fieldErros.stream().map(DadosMensagemErro400DTO::new).toList();
        return ResponseEntity.badRequest().body(listaErros);

    }
}
