import java.util.*;

public class JogoDescoberta {
    private String[] palavras = { "JAVA", "CODIGO", "MATRIZ", "ALGORITMO", "PROGRAMA" };
    private String palavraEscolhida;
    private String palavraEmbaralhada;
    private Scanner scanner = new Scanner(System.in);

    public void iniciar() {
        escolherPalavra();
        embaralharPalavra();
        System.out.println("Bem-vindo ao Jogo da Descoberta!");
        jogar();
    }

    private void escolherPalavra() {
        Random rand = new Random();
        palavraEscolhida = palavras[rand.nextInt(palavras.length)];
    }

    private void embaralharPalavra() {
        List<Character> letras = new ArrayList<>();
        for (char c : palavraEscolhida.toCharArray()) {
            letras.add(c);
        }
        Collections.shuffle(letras);
        StringBuilder sb = new StringBuilder();
        for (char c : letras) sb.append(c);
        palavraEmbaralhada = sb.toString();
    }

    private void jogar() {
        while (true) {
            System.out.println("\nPalavra embaralhada: " + palavraEmbaralhada);
            System.out.print("Digite sua tentativa (ou 'dica', 'desistir'): ");
            String tentativa = scanner.nextLine().toUpperCase();

            if (tentativa.equals("DICA")) {
                System.out.println("Dica: começa com '" + palavraEscolhida.charAt(0) + "' e termina com '" + palavraEscolhida.charAt(palavraEscolhida.length() - 1) + "'");
            } else if (tentativa.equals("DESISTIR")) {
                System.out.println("Você desistiu. A palavra era: " + palavraEscolhida);
                break;
            } else if (tentativa.equals(palavraEscolhida)) {
                System.out.println("Parabéns! Você acertou!");
                break;
            } else {
                System.out.println("Errado, tente novamente.");
            }
        }
    }

    public static void main(String[] args) {
        new JogoDescoberta().iniciar();
    }
}
