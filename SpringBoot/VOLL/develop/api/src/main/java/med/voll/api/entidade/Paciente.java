package med.voll.api.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DadosCadastroPacienteDTO;
import med.voll.api.dto.DadosAlterarPacienteDTO;

@EqualsAndHashCode(of = "id")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "pacientes")
public class Paciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String nome;

    String email;

    String telefone;

    String cpf;

    @Embedded
    Endereco endereco;

    Boolean ativo;


    public Paciente(DadosCadastroPacienteDTO dados){
        this.nome = dados.nome();
        this.cpf = dados.cpf();
        this.email = dados.email();
        this.telefone = dados.telefone();
        this.endereco = new Endereco(dados.endereco());
        this.ativo = true;

    }

    public void alterar(DadosAlterarPacienteDTO dados) {
        if(dados.nome() != null)
            this.nome = dados.nome();
        if(dados.telefone() != null)
            this.telefone = dados.telefone();
        if(dados.enderecoDTO() != null)
            this.endereco.alterarEndereco(dados.enderecoDTO());
    }

    public void excluir() {
        this.ativo = false;
    }
}