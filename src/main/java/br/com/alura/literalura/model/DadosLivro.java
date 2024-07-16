package br.com.alura.literalura.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosLivro(Integer id,
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DadosAutor> autores,
        @JsonAlias("languages") String[] idioma,
        @JsonAlias("download_count") Integer numeroDownloads
) {
}
