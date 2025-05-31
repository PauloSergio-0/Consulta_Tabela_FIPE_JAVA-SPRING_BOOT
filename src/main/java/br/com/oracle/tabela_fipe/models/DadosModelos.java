package br.com.oracle.tabela_fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DadosModelos(
        @JsonProperty("modelos") List<Modelo> modelos
) {
}
