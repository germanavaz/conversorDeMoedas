import java.util.Scanner;

public class Principal {

    public static void main(String[] args) {
        Scanner leitura = new Scanner(System.in);
        int escolha;

        do {
            exibirMenu();

            while (!leitura.hasNextInt()) {
                System.out.println("Digite um número válido:");
                leitura.next();
            }

            escolha = leitura.nextInt();

            if (escolha == 7) {
                System.out.println("Saindo do sistema.");
                break;
            }

            String[] moedas = obterMoedas(escolha);
            if (moedas == null) {
                System.out.println("Opção inválida.");
                continue;
            }

            System.out.print("Valor a converter: ");
            while (!leitura.hasNextDouble()) {
                System.out.println("Digite um número válido:");
                leitura.next();
            }
            double amount = leitura.nextDouble();

            double convertido = ConversorMoeda.obterCambio(moedas[0], moedas[1], amount);

            if (convertido != -1) {
                System.out.printf("Valor %.2f %s corresponde ao valor final de =>>> %.2f %s\n\n", amount, moedas[0], convertido, moedas[1]);
            } else {
                System.out.println("Erro ao obter taxa de câmbio.\n");
            }

        } while (escolha != 7);

        leitura.close();
    }

    private static void exibirMenu() {
        System.out.println("Conversor de Moedas:\n" +
                "1. Dólar (USD) -> Real (BRL)\n" +
                "2. Euro (EUR) -> Real (BRL)\n" +
                "3. Real (BRL) -> Dólar (USD)\n" +
                "4. Real (BRL) -> Euro (EUR)\n" +
                "5. Dólar (USD) -> Euro (EUR)\n" +
                "6. Euro (EUR) -> Dólar (USD)\n" +
                "7. Sair\n" +
                "Escolha uma opção: ");
    }

    private static String[] obterMoedas(int escolha) {
        return switch (escolha) {
            case 1 -> new String[]{"USD", "BRL"};
            case 2 -> new String[]{"EUR", "BRL"};
            case 3 -> new String[]{"BRL", "USD"};
            case 4 -> new String[]{"BRL", "EUR"};
            case 5 -> new String[]{"USD", "EUR"};
            case 6 -> new String[]{"EUR", "USD"};
            default -> null;
        };
    }
}
