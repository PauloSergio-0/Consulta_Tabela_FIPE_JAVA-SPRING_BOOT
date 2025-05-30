package br.com.oracle.tabela_fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosVeiculo (
        @JsonAlias("codigo") Integer codigoVeiculo,
        @JsonAlias("nome") String nomeVeiculo
){
}
