package br.com.oracle.tabela_fipe.service;

import br.com.oracle.tabela_fipe.models.DadosVeiculo;

import java.io.IOException;
import java.util.Locale;
import java.util.Scanner;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private static final String url = "https://parallelum.com.br/fipe/api/v1/";
    private static final ConsumoApi consumoApi = new ConsumoApi();
    private static final ConverteDados conversor = new ConverteDados();

    public static void menu() throws IOException, InterruptedException {
        System.out.println("Qual o tipo de Veiculo");
        String tipoVeiculo = scanner.nextLine();

        var json = consumoApi.obterDados(url+tipoVeiculo+"/marcas");
        DadosVeiculo dadosVeiculo = conversor.obterDados(json, DadosVeiculo.class);

        System.out.println(dadosVeiculo);
    }

}
