package med.voll.api.dto;

import org.springframework.validation.FieldError;

public record DadosMensagemErro400(String campo, String mensagem ) {
    public DadosMensagemErro400(FieldError erro){
        this(erro.getField(), erro.getDefaultMessage());
    }
}
