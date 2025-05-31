package br.com.oracle.tabela_fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record Veiculo(
        @JsonAlias("Marca") String marca,
        @JsonAlias("Modelo") String modelo,
        @JsonAlias("Valor") String valor,
        @JsonAlias("AnoModelo") Integer AnoLancamento,
        @JsonAlias("Combustivel") String conbustivel,
        @JsonAlias("CodigoFipe") String codFipe,
        @JsonAlias("MesReferencia") String mesReferencia,
        @JsonAlias("SiglaCombustivel") String siglaConbustivel
) {
}
