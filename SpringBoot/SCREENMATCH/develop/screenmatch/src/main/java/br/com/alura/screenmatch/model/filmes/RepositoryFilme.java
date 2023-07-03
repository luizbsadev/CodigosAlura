package br.com.alura.screenmatch.model.filmes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryFilme extends JpaRepository<Filme, Long> {
}
