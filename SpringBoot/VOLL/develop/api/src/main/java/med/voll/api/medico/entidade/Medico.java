package med.voll.api.medico.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.medico.dto.DadosMedicoDTO;
import med.voll.api.medico.dto.EnderecoDTO;
import med.voll.api.medico.dto.Especialidade;

import java.util.Objects;

@Entity
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String crm;

    private String telefone;

    @Enumerated
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;


    public Medico(DadosMedicoDTO dadosMedicoDTO) {
        this.nome = dadosMedicoDTO.nome();
        this.email = dadosMedicoDTO.email();
        this.telefone = dadosMedicoDTO.telefone();
        this.crm = dadosMedicoDTO.crm();
        this.especialidade = dadosMedicoDTO.especialidade();
        this.endereco = new Endereco(dadosMedicoDTO.endereco());
    }
}
