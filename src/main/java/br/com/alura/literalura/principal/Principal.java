package br.com.alura.literalura.principal;

import br.com.alura.literalura.model.*;
import br.com.alura.literalura.repository.AutorRepositorio;
import br.com.alura.literalura.repository.LivroRepositorio;
import br.com.alura.literalura.service.ConsumoApi;
import br.com.alura.literalura.service.ConverteDados;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

public class Principal {

    private Scanner leitura = new Scanner(System.in);
    private ConsumoApi consumo = new ConsumoApi();
    private ConverteDados conversor = new ConverteDados();

    @Autowired
    private LivroRepositorio livroRepositorio;

    @Autowired
    private AutorRepositorio autorRepositorio;

    private final String ENDERECO = "https://gutendex.com/books/?search=";

    public Principal(LivroRepositorio repositorioLivro, AutorRepositorio repositorioAutor) {
        this.livroRepositorio = repositorioLivro;
        this.autorRepositorio = repositorioAutor;
    }


    public void exibeMenu() {
        System.out.println("**** BEM VINDO AO LITERALURA ****\n");

        var opt = -1;

        while (opt != 9) {
            System.out.println("""
                    Escolha uma opção válida:
                    1- Adicionar livros
                    2- Listar livros registrados
                    3- Listar autores registrados
                    4- Listar autores vivos em determinado ano
                    5- Listar livros em um determinado idioma
                    
                    9- Finalizar aplicação
                    """);

            opt = leitura.nextInt();
            leitura.nextLine();

            switch (opt) {
                case 1 -> getDadosLivro();
                case 2 -> listarLivrosRegistrados();
                case 3 -> listarAutoresRegistrados();
                case 4 -> listarAutoresVivosPorAno();
                case 5 -> listarLivrosPorIdioma();
                case 9 -> System.out.println("Programa finalizado!");
                default -> System.out.println("Opção inválida");
            }
        }
    }

    private void adicionarLivro(DadosLivro dadosLivro) {
        Optional<Livro> verificarLivro = livroRepositorio.findBytituloEqualsIgnoreCase(dadosLivro.titulo().toString());

        if (verificarLivro.isEmpty()){

            Livro livro = new Livro(dadosLivro);

            try {
                livroRepositorio.save(livro);
                System.out.println(dadosLivro.titulo() +" inserido com sucesso!");
            }catch (Exception e) {
                System.out.println("Erro:" + e.getMessage());
            }
        }else{
            System.out.println("Livro já registrado");
        }
    }

    private void adicionarAutor(Integer id, String nome, Integer dataNasc, Integer dataFalec) {
        Autor autor = new Autor(id,nome,dataNasc,dataFalec);

        try {
            autorRepositorio.save(autor);
            System.out.println(autor.getNome()+" inserido com sucesso!");

        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

    }


    private void getDadosLivro() {
        System.out.println("Digite o nome do livro para busca");
        var nomeLivro = leitura.nextLine();

        String json = consumo.obterDados(ENDERECO + nomeLivro.replace(" ","%20").toLowerCase());

        var books = conversor.converterDados(json, DadosResponse.class);

        Optional<DadosLivro> livroSelecionado = books.livros().stream()
                .findFirst();

        if (livroSelecionado.isPresent()){
            DadosLivro livrodados = livroSelecionado.get();

            adicionarLivro(livrodados);
            adicionarAutor(livrodados.id(),
                    livrodados.autores().get(0).nome(),
                    livrodados.autores().get(0).anoNascimento(),
                    livrodados.autores().get(0).anoFalecimento());
        }
        else{
            System.out.println("Não foi possível encontrar o livro");
        }
    }


    private void listarLivrosRegistrados() {
        System.out.println("Livros registrados: ");
        try {
            List<Livro> livros = livroRepositorio.findAll();

            if (!livros.isEmpty()){
                livros.forEach(System.out::println);
            }else{
                System.out.println("Nenhum livro registrado");
            }

        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }

    }

    private void listarAutoresRegistrados() {
        System.out.println("Autores registrados: ");
        try {
            List<Autor> autores = autorRepositorio.findAll();

            if (autores.size() > 0){
                autores.forEach(System.out::println);
            }else{
                System.out.println("Nenhum autor registrado");
            }
        } catch (Exception e) {
            System.out.println("Erro:" + e.getMessage());
        }

    }

    private void listarAutoresVivosPorAno() {
        System.out.println("Digite o ano final do período para buscar autores falecidos até então");
        Integer anoFalec = leitura.nextInt(); leitura.nextLine();

        List<Autor> dadosAutorFalec = autorRepositorio.findByanoFalecimentoLessThan(anoFalec);

        if (!dadosAutorFalec.isEmpty()) {
            System.out.println(dadosAutorFalec);
            System.out.println("Foram registrados "+dadosAutorFalec.size() + " atores falecidos até " + anoFalec);
        } else {
            System.out.println("Nenhum autor falecido");
        }
    }

    private void listarLivrosPorIdioma(){
        var idioma = "";
        System.out.println("""
                Escolha o idioma:
                1-Português
                2-Inglês
                3-Francês
                4-Espanhol
                """);
        Integer idiomaSelecionado = leitura.nextInt(); leitura.nextLine();

        switch (idiomaSelecionado){
            case 1 -> idioma = "pt";
            case 2 -> idioma = "en";
            case 3 -> idioma = "fr";
            case 4 -> idioma = "es";
        }

        List<Livro> livroIdioma = livroRepositorio.findByidiomaContainingIgnoreCase(idioma);

        if (!livroIdioma.isEmpty()) {
            System.out.println(livroIdioma);
            System.out.println("Número de livros nesse idioma: " + livroIdioma.size());
        } else {
            System.out.println("Nenhum livro nesse idioma registrado");
        }

    }

}
