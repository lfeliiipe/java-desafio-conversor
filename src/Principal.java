import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Principal {
    public static void main(String[] args) {

        System.out.println("Olá este é o conversor de moedas");
        Scanner leitor = new Scanner(System.in);

        List<Moeda> moedas = new ArrayList<>();
        moedas.add(new Moeda("BRL", "Real Brasileiro"));
        moedas.add(new Moeda("USD", "Dólar Americano"));
        moedas.add(new Moeda("JPY", "Yene Japonês"));

        List<Cotacao> cotacoes = new ArrayList<>();
        for (int i = 0; i < moedas.size(); i++) {
            for (int j = 0; j < moedas.size(); j++){
                if (i != j) {
                    cotacoes.add(new Cotacao(moedas.get(i), moedas.get(j)));
                }
            }
        }

        int escolha = 0;
        while (escolha != 7) {
            System.out.println("=================================");
            System.out.println("\nEscolha uma opção de conversão:");
            for (int i = 0; i < cotacoes.size(); i++) {
                System.out.println((i + 1) + ". " + cotacoes.get(i));
            }
            System.out.println((cotacoes.size() + 1) + ". Encerrar programa");
            System.out.println("\n=================================");

            try {
                escolha = leitor.nextInt();
                if (escolha == 7) {
                    System.out.println("Programa encerrado");
                    break;
                }

                Cotacao cotacaoEscolhida = cotacoes.get(escolha - 1);
                System.out.println(cotacaoEscolhida);
                System.out.println("Digite o valor que deseja converter:");

                double valor = leitor.nextDouble();
                if (valor < 0) {
                    throw new RuntimeException("Valor não permitido");
                }

                MoedaConvertida convertida = cotacaoEscolhida.converter(valor);
                System.out.println("O valor " + valor + "[" + cotacaoEscolhida.getMoedaOrigem().getSigla() +
                        "] equivale a " + convertida.conversion_result() + "[" +
                        cotacaoEscolhida.getMoedaDestino().getSigla() + "] com taxa de convers" +
                        "ão de " + convertida.conversion_rate());

            } catch (InputMismatchException | IndexOutOfBoundsException e) {
                System.out.println("Erro: Opção não disponível");
                break;
            } catch (RuntimeException e) {
                System.out.println("Erro: " + e.getMessage());
                break;
            }
        }
    }
}