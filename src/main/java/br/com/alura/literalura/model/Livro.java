package br.com.alura.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "livros")
public class Livro {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        private String titulo;

        private Integer autor_id;

        private String idioma;

        private Integer numeroDownloads;



        public Livro() {
        }

        public Livro(DadosLivro livro) {
            this.titulo = livro.titulo();
            this.autor_id = livro.id();
            this.idioma = String.join(" ", livro.idioma());
            this.numeroDownloads = livro.numeroDownloads();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public Integer getAutor() {
            return autor_id;
        }

        public void setAutor(Integer autor) {
            this.autor_id = autor;
        }

        public String getIdioma() {
            return idioma;
        }

        public void setIdioma(String idioma) {
            this.idioma = idioma;
        }

        public Integer getNumeroDownloads() {
            return numeroDownloads;
        }

        public void setNumeroDownloads(Integer numeroDownloads) {
            this.numeroDownloads = numeroDownloads;
        }
    @Override
    public String toString() {
        return " Livro='" + titulo + '\'' +
                ", idiomas=" + idioma +
                ", numeroDownloads=" + numeroDownloads +
                ", id do autor=" + autor_id;
    }
}
