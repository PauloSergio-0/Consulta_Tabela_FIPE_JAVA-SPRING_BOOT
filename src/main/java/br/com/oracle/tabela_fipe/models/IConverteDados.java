package br.com.oracle.tabela_fipe.models;

public interface IConverteDados {

    <T> T obterDados(String json, Class<T> classe);
}
