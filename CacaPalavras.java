import java.util.*;

public class CacaPalavras {
    private char[][] tabuleiro;
    private String[] palavras = { "JAVA", "CODIGO", "MATRIZ", "ALGORITMO", "PROGRAMA" };
    private String palavraEscolhida;
    private Scanner scanner = new Scanner(System.in);
    private int tamanho;

    public CacaPalavras(int tamanho) {
        this.tamanho = tamanho;
        tabuleiro = new char[tamanho][tamanho];
    }

    public void iniciar() {
        preencherTabuleiro();
        escolherEPorPalavra();
        esconderPalavra();
        jogar();
    }

    private void preencherTabuleiro() {
        Random rand = new Random();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = (char) ('A' + rand.nextInt(26));
            }
        }
    }

    private void escolherEPorPalavra() {
        Random rand = new Random();
        palavraEscolhida = palavras[rand.nextInt(palavras.length)];
    }

    private void esconderPalavra() {
        Random rand = new Random();
        boolean colocado = false;

        while (!colocado) {
            int direcao = rand.nextInt(3); // 0 horizontal, 1 vertical, 2 diagonal
            int linha = rand.nextInt(tamanho);
            int col = rand.nextInt(tamanho);

            if (direcao == 0 && col + palavraEscolhida.length() <= tamanho) {
                for (int k = 0; k < palavraEscolhida.length(); k++) {
                    tabuleiro[linha][col + k] = palavraEscolhida.charAt(k);
                }
                colocado = true;
            } else if (direcao == 1 && linha + palavraEscolhida.length() <= tamanho) {
                for (int k = 0; k < palavraEscolhida.length(); k++) {
                    tabuleiro[linha + k][col] = palavraEscolhida.charAt(k);
                }
                colocado = true;
            } else if (direcao == 2 && linha + palavraEscolhida.length() <= tamanho && col + palavraEscolhida.length() <= tamanho) {
                for (int k = 0; k < palavraEscolhida.length(); k++) {
                    tabuleiro[linha + k][col + k] = palavraEscolhida.charAt(k);
                }
                colocado = true;
            }
        }
    }

    private void mostrarTabuleiro() {
        System.out.println("\nTabuleiro:");
        for (char[] linha : tabuleiro) {
            for (char c : linha) {
                System.out.print(c + " ");
            }
            System.out.println();
        }
    }

    private void jogar() {
        System.out.println("Bem-vindo ao Caça-Palavras!");
        System.out.println("Tente descobrir a palavra escondida no tabuleiro.");

        while (true) {
            mostrarTabuleiro();
            System.out.print("\nDigite sua tentativa (ou 'dica' para ajuda, 'sair' para desistir): ");
            String chute = scanner.nextLine().toUpperCase();

            if (chute.equals("DICA")) {
                System.out.println("Dica: começa com '" + palavraEscolhida.charAt(0) +
                        "' e termina com '" + palavraEscolhida.charAt(palavraEscolhida.length() - 1) + "'");
            } else if (chute.equals("SAIR")) {
                System.out.println("Você desistiu! A palavra era: " + palavraEscolhida);
                break;
            } else if (chute.equals(palavraEscolhida)) {
                System.out.println("Parabéns! Você acertou!");
                break;
            } else {
                System.out.println("Errado, tente de novo.");
            }
        }
    }

    public static void main(String[] args) {
        CacaPalavras jogo = new CacaPalavras(10);
        jogo.iniciar();
    }
}
