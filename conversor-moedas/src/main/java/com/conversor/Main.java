package com.conversor;

import com.conversor.modelo.TaxaDeCambio;
import com.conversor.servico.ConsultaApiCambio;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        ConsultaApiCambio consulta = new ConsultaApiCambio();
        
        String menu = """
        _________________________________________________
        
              BEM-VINDO(A) AO CONVERSOR DE MOEDAS!
        _________________________________________________
    
        Escolha uma opção para conversão:
        
        1. Dólar (USD) --> Real Brasileiro (BRL)
        2. Real Brasileiro (BRL) --> Dólar (USD)
        3. Dólar (USD) --> Euro (EUR)
        4. Euro (EUR) --> Dólar (USD)
        5. Euro (EUR) --> Real Brasileiro (BRL)
        6. Real Brasileiro (BRL) --> Euro (EUR)
        7. Sair
        _________________________________________________
        """;

        while (true) {
            System.out.println(menu);
            
            try {
                // 1. Lendo a Opção
                System.out.print("Digite o número das opções acima: ");
                int opcao = leitura.nextInt();

                if (opcao == 7) {
                    System.out.println("\nObrigado por usar o Conversor de Moedas!");
                    break;
                }

                if (opcao < 1 || opcao > 6) {
                    System.out.println("\n❌ Opção inválida. Por favor, escolha uma opção entre 1 e 7.");
                    continue;
                }

                // 2. Lendo o Valor
                System.out.print("Digite o valor que deseja converter: ");
                double valorParaConverter = leitura.nextDouble();
                
                // 3. Definindo as Moedas e a Moeda Base para a API
                String moedaBase = "";
                String moedaDestino = "";
                
                switch (opcao) {
                    case 1: moedaBase = "USD"; moedaDestino = "BRL"; break;
                    case 2: moedaBase = "BRL"; moedaDestino = "USD"; break;
                    case 3: moedaBase = "USD"; moedaDestino = "EUR"; break;
                    case 4: moedaBase = "EUR"; moedaDestino = "USD"; break;
                    case 5: moedaBase = "EUR"; moedaDestino = "BRL"; break;
                    case 6: moedaBase = "BRL"; moedaDestino = "EUR"; break;
                }

                // 4. Chamada da API
                System.out.printf("Buscando cotação de %s para %s...\n", moedaBase, moedaDestino);
                TaxaDeCambio taxas = consulta.buscarTaxas(moedaBase);

                // 5. Cálculo e Exibição do Resultado
                if (taxas.taxasDeConversao().containsKey(moedaDestino)) {
                    double taxaDeConversao = taxas.taxasDeConversao().get(moedaDestino);
                    double valorConvertido = valorParaConverter * taxaDeConversao;

                    System.out.println("------------------------------------------------");
                    System.out.printf("Cotação atual (1 %s): %.4f %s\n", moedaBase, taxaDeConversao, moedaDestino);
                    System.out.printf("O valor de %.2f %s equivale a %.2f %s\n", 
                                      valorParaConverter, moedaBase, valorConvertido, moedaDestino);
                    System.out.println("------------------------------------------------\n");
                } else {
                    System.out.println("\n❌ Erro: Não foi possível encontrar a taxa de conversão para a moeda escolhida.");
                }

            } catch (InputMismatchException e) {
                // Tratamento de erro para quando o usuário digita texto onde deveria ser número.
                System.out.println("\n❌ ERRO: Entrada inválida. Por favor, digite apenas números para as opções e valores.");
                leitura.next(); // Limpa o buffer do scanner
            } catch (IOException | InterruptedException e) {
                // Tratamento de erro para falhas de conexão ou chave API inválida 
                System.out.println("\n❌ ERRO NA CONEXÃO: Não foi possível obter as taxas de câmbio da API.");
                System.out.println("Verifique sua conexão e se sua API Key está correta na classe ConsultaApiCambio.");
                System.out.println("Detalhe técnico: " + e.getMessage());
            } catch (Exception e) {
                 System.out.println("\n❌ Ocorreu um erro inesperado: " + e.getMessage());
            }
        }
    }
}