package br.com.alura.screenmatch.model.filmes;

import jakarta.persistence.*;

@Entity
@Table(name = "filmes")
public class Filme {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private Integer duracao;
    private Integer ano;
    private String genero;

    public Filme(CadastroFilmeDTO dados){
        this.nome = dados.nome();
        this.ano = dados.ano();
        this.duracao = dados.duracao();
        this.genero = dados.genero();
    }

    public Filme() {

    }

    public Long getId() { return id; }

    public String getNome() {
        return nome;
    }

    public Integer getDuracao() {
        return duracao;
    }

    public Integer getAno() {
        return ano;
    }

    public String getGenero() {
        return genero;
    }

    @Override
    public String toString() {
        return "Filme{" +
                "nome='" + nome + '\'' +
                ", duracao=" + duracao +
                ", ano=" + ano +
                ", genero='" + genero + '\'' +
                '}';
    }

    public void atualizaDados(DadosAlteracaoFilme dadosNovos){
        this.nome = dadosNovos.nome();
        this.duracao = dadosNovos.duracao();
        this.ano = dadosNovos.ano();
        this.genero = dadosNovos.genero();
    }
}
