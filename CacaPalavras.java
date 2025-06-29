import java.util.*;

public class CacaPalavras {
    private char[][] tabuleiro;
    private String[] palavras = { "JAVA", "CODIGO", "MATRIZ", "ALGORITMO", "PROGRAMA" };
    private String palavraSecreta;
    private Scanner scanner = new Scanner(System.in);
    private int tamanho;

    public CacaPalavras(int tamanho) {
        this.tamanho = tamanho;
        tabuleiro = new char[tamanho][tamanho];
    }

    public void iniciar() {
        preencherComLetrasAleatorias();
        escolherPalavraSecreta();
        colocarPalavraNoTabuleiro();
        jogar();
    }

    private void preencherComLetrasAleatorias() {
        Random rand = new Random();
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                tabuleiro[i][j] = (char) ('A' + rand.nextInt(26));
            }
        }
    }

    private void escolherPalavraSecreta() {
        Random rand = new Random();
        palavraSecreta = palavras[rand.nextInt(palavras.length)];
    }

    private void colocarPalavraNoTabuleiro() {
        Random rand = new Random();
        boolean colocada = false;

        while (!colocada) {
            int direcao = rand.nextInt(3);
            int linha = rand.nextInt(tamanho);
            int coluna = rand.nextInt(tamanho);

            if (direcao == 0 && coluna + palavraSecreta.length() <= tamanho) {
                for (int k = 0; k < palavraSecreta.length(); k++) {
                    tabuleiro[linha][coluna + k] = palavraSecreta.charAt(k);
                }
                colocada = true;
            } else if (direcao == 1 && linha + palavraSecreta.length() <= tamanho) {
                for (int k = 0; k < palavraSecreta.length(); k++) {
                    tabuleiro[linha + k][coluna] = palavraSecreta.charAt(k);
                }
                colocada = true;
            } else if (direcao == 2 && linha + palavraSecreta.length() <= tamanho
                    && coluna + palavraSecreta.length() <= tamanho) {
                for (int k = 0; k < palavraSecreta.length(); k++) {
                    tabuleiro[linha + k][coluna + k] = palavraSecreta.charAt(k);
                }
                colocada = true;
            }
        }
    }

    private void mostrarTabuleiro() {
        System.out.println("Tabuleiro:");
        for (int i = 0; i < tamanho; i++) {
            for (int j = 0; j < tamanho; j++) {
                System.out.print(tabuleiro[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void jogar() {
        System.out.println("Bem-vindo ao Caça-Palavras!");
        System.out.println("Tente adivinhar a palavra escondida.");

        while (true) {
            mostrarTabuleiro();
            System.out.print("Sua tentativa (ou 'dica' para ajuda, 'sair' para desistir): ");
            String chute = scanner.nextLine().toUpperCase();

            if (chute.equals("DICA")) {
                System.out.println("Dica: começa com '" + palavraSecreta.charAt(0) + "' e termina com '"
                        + palavraSecreta.charAt(palavraSecreta.length() - 1) + "'");
            } else if (chute.equals("SAIR")) {
                System.out.println("Você desistiu! A palavra era: " + palavraSecreta);
                break;
            } else if (chute.equals(palavraSecreta)) {
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
