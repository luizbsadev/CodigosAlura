package med.voll.api.dto;

import org.springframework.validation.FieldError;

public record DadosMensagemErro400DTO(String campo, String mensagem ) {
    public DadosMensagemErro400DTO(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
