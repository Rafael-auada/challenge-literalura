package br.com.alura.literalura.model;

import jakarta.persistence.*;

@Entity
@Table(name = "autores")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nome;

    private Integer anoNascimento;

    private Integer anoFalecimento;



    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Autor(){}

    public Autor(Integer autorid, String autor, Integer dtnascimento, Integer dtfalecimento) {
        this.id = autorid;
        this.nome = autor;
        this.anoNascimento = dtnascimento;
        this.anoFalecimento = dtfalecimento;
    }

    public Integer getanoNascimento() {
        return anoNascimento;
    }

    public void setanoNascimento(Integer ano) {
        this.anoNascimento = ano;
    }

    public Integer getanoFalecimento() {
        return anoFalecimento;
    }

    public void setanoFalecimento(Integer ano) {
        this.anoFalecimento = ano;
    }


    @Override
    public String toString() {
        return  "Autor='" + nome + '\'' +
                ", ano Nascimento=" + anoNascimento +
                ", ano Falecimento=" + anoFalecimento;
    }
}
