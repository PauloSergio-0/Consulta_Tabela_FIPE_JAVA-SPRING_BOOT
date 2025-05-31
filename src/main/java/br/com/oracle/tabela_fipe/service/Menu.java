package br.com.oracle.tabela_fipe.service;

import br.com.oracle.tabela_fipe.models.*;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Menu {
    private static final Scanner scanner = new Scanner(System.in).useLocale(Locale.US);
    private static final String url = "https://parallelum.com.br/fipe/api/v1/";
    private static final ConsumoApi consumoApi = new ConsumoApi();
    private static final ConverteDados conversor = new ConverteDados();

    public static void menu() throws IOException, InterruptedException {

        //tipo de veiculo (carros, motos, caminhões)
        String tipoVeiculo = tiposVeiculosApi();
        var json = consumoApi.obterDados(url+tipoVeiculo);
        List<DadosVeiculo> veiculos = conversor.obterLista(json, DadosVeiculo.class);
        listarVeiculos(veiculos);

        // Seleciona Marcas dos veiculo e mostra modelos
        String marcaVeiculo = selecionarMarca(veiculos);
        json = consumoApi.obterDados(url+tipoVeiculo+marcaVeiculo);
        DadosModelos dadosModelos = conversor.obterDados(json, DadosModelos.class);
        listarModelos(dadosModelos.modelos());


        //seleciona um modelo
        String modeloVeiculo = selecionarModelo(dadosModelos.modelos());
        json = consumoApi.obterDados(url+tipoVeiculo+marcaVeiculo+modeloVeiculo);
        List<DadosVeiculo> dadosModelo = conversor.obterLista(json, DadosVeiculo.class);
        listarVeiculos(dadosModelo);

        //Veiculo Selecionado
        json = consumoApi.obterDados(url+tipoVeiculo+marcaVeiculo+modeloVeiculo+"/"+dadosModelo.getFirst().codigoVeiculo());

        Veiculo veiculo= conversor.obterDados(json, Veiculo.class);
        VeiculoDesc veiculoDesc =new VeiculoDesc(veiculo);
        System.out.println(veiculoDesc);


    }


    private static String tiposVeiculosApi(){
        while (true){
            System.out.println("""
                    Opções de veiculo:
                    
                    1. Carros
                    2. Motos
                    3. Caminhões
                    
                    Escolha uma opção:
                    """);

            int opcao = scanner.nextInt();
            scanner.nextLine();

            if (opcao == 1) {
                return "carros/marcas";

            } else if (opcao == 2) {
                return "motos/marcas";

            } else if (opcao == 3) {
                return "caminhao/marcas";

            } else {
                System.out.println("Opção não encontrada, Tente Novamente!!!!");
            }
        }

    }


    private static void listarVeiculos(List<DadosVeiculo> listaVeiculos){
        listaVeiculos.stream()
                //.sorted(Comparator.comparing(DadosVeiculo::codigoVeiculo))
                .sorted(Comparator.comparing(v -> Integer.parseInt(v.codigoVeiculo())))
                .forEach(v -> System.out.println(
                        "Cod: " + v.codigoVeiculo()
                        + "\nNome: " + v.nomeVeiculo()
                +"\n"));
    }

    private static void listarModelos(List<Modelo> listaVeiculos){
        listaVeiculos.stream()
                //.sorted(Comparator.comparing(DadosVeiculo::codigoVeiculo))
                .sorted(Comparator.comparing(Modelo::nomeVeiculo))
                .forEach(v -> System.out.println(
                        "Cod: " + v.codigoVeiculo()
                        + "\nNome: " + v.nomeVeiculo()
                +"\n"));
    }


    private static String selecionarMarca(List<DadosVeiculo> listaVeiculos) {

        while (true){
            System.out.println("selecione uma marca pelo nome: ");
            String marcaSelecionada = scanner.nextLine();

            for (DadosVeiculo marcas : listaVeiculos) {

                if (marcaSelecionada.equalsIgnoreCase(marcas.nomeVeiculo()) ) {
                    return "/"+marcas.codigoVeiculo()+"/modelos";
                }
            }

            System.out.println("Marca não encontrada!! Tente Novamente!!!");
        }
    }

    private static String selecionarModelo(List<Modelo> listaModelos){

        System.out.println("Escolha um modelo: ");
        String modelo = scanner.nextLine();
        List<Modelo> modelosFiltrados = listaModelos.stream()
                .sorted(Comparator.comparing(Modelo::codigoVeiculo))
                .filter(m -> m.nomeVeiculo().toLowerCase().contains(modelo.toLowerCase()))
                .toList();

        if (modelosFiltrados.isEmpty()) {
            System.out.println("Modelo Não encontrado! Tente Novamente");
            return selecionarModelo(listaModelos);
        }

        System.out.println("Veiculos Filtrados");

        modelosFiltrados.forEach(mf -> System.out.println(
                "cod: " + mf.codigoVeiculo()
                +"\nnome: " + mf.nomeVeiculo()
        ));

        while (true){
            System.out.println("Escolha o modelo pelo Código: ");
            int codModelo = scanner.nextInt();
            scanner.nextLine();

            Modelo veiculoSelecionado = modelosFiltrados.stream()
                    .filter(mf -> mf.codigoVeiculo() == codModelo)
                    .findFirst()
                    .orElse(null);

            if (veiculoSelecionado != null) {
                return "/"+veiculoSelecionado.codigoVeiculo()+"/anos";
            } else {
                System.out.println("Codigo não encontrado!!! Tente Novamente!!");
            }
        }
    }


}
