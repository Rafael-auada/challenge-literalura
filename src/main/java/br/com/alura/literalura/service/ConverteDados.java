package br.com.alura.literalura.service;
import br.com.alura.literalura.model.DadosLivro;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ConverteDados {

    private ObjectMapper mapper = new ObjectMapper();

    public <T> T  converterDados(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
