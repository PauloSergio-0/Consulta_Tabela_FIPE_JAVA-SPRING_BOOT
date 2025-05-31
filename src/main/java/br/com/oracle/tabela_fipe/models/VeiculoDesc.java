package br.com.oracle.tabela_fipe.models;

import com.fasterxml.jackson.annotation.JsonAlias;

public class VeiculoDesc {
    private String marca;
    private String modelo;
    private String valor;
    private Integer AnoLancamento;
    private String conbustivel;
    private String codFipe;
    private String mesReferencia;
    private String siglaConbustivel;

    public VeiculoDesc(Veiculo veiculo) {
        this.marca = veiculo.marca();
        this.modelo = veiculo.modelo();
        this.valor = veiculo.valor();
        this.AnoLancamento = veiculo.AnoLancamento();
        this.conbustivel = veiculo.conbustivel();
        this.codFipe = veiculo.codFipe();
        this.mesReferencia = veiculo.mesReferencia();
        this.siglaConbustivel = veiculo.siglaConbustivel();
    }

    @Override
    public String toString() {
        return "VeiculoDesc{" +
                "marca: " + marca + '\n' +
                " modelo :" + modelo + '\n' +
                " valor: " + valor + '\n' +
                " AnoLancamento= " + AnoLancamento +
                " conbustivel: " + conbustivel + '\n' +
                " codFipe: " + codFipe + '\n' +
                " mesReferencia: " + mesReferencia + '\n' +
                " siglaConbustivel: " + siglaConbustivel;
    }
}
