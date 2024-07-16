package br.com.alura.literalura.service;

public interface IConverteDados {
    // usando um generics pois assim passamos um tipo de dado, não sabendo qual, para a classe classe
    <T> T converterDados(String json, Class<T> classe);
}
