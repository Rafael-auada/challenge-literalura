package br.com.alura.literalura.repository;

import br.com.alura.literalura.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LivroRepositorio extends JpaRepository<Livro, Integer> {
        List<Livro> findByidiomaContainingIgnoreCase(String idioma);

        Optional<Livro> findBytituloEqualsIgnoreCase(String titulo);
}
