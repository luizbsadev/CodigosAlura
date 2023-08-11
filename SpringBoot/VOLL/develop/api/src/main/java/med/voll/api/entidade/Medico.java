package med.voll.api.entidade;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import med.voll.api.dto.DadosMedicoDTO;

@Entity
@Table(name = "medicos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode(of = "id")
public class Medico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String crm;

    private String telefone;

    @Enumerated(EnumType.STRING)
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
