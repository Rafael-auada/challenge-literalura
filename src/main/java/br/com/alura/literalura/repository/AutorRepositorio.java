package br.com.alura.literalura.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alura.literalura.model.Autor;


public interface AutorRepositorio extends JpaRepository<Autor,Integer> {

    List<Autor> findByanoFalecimentoLessThan(Integer anoFalec);
}