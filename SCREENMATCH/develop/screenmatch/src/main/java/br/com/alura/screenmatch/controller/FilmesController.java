package br.com.alura.screenmatch.controller;

import br.com.alura.screenmatch.model.filmes.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/filmes")
public class FilmesController {
    @Autowired
    private RepositoryFilme repositoryFilme;

    @GetMapping("/formulario")
    public String carregaPaginaFormulario(Long id, Model model) {
        if (id != null) {
            var filme = repositoryFilme.getReferenceById(id);
            model.addAttribute("filme", filme);
        }
        return "filmes/formulario";
    }

    @PostMapping("/formulario")
    @Transactional
    public String criarFilme(CadastroFilmeDTO dados){
        Filme filme = new Filme(dados);
        repositoryFilme.save(filme);

        return "redirect:/filmes";
    }

    @GetMapping()
    public String listarFilmes(Model model){
        model.addAttribute("lista", repositoryFilme.findAll());
        return "filmes/listar";
    }

    @DeleteMapping()
    @Transactional
    public String apagarFilme(ApagarFilmeDTO dados){
        repositoryFilme.deleteById(dados.id());
        return "redirect:/filmes";
    }

    @PutMapping("/formulario")
    @Transactional
    public String alterarFilme(DadosAlteracaoFilme dados){
        Filme filme = repositoryFilme.getReferenceById(dados.id());
        filme.atualizaDados(dados);

        return "redirect:/filmes";
    }

}
