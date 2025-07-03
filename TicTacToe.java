import java.util.Scanner;

public class TicTacToe {

    // Função para imprimir o tabuleiro
    public static void imprimirTabuleiro(char[][] tabuleiro) {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 2; coluna++) {
                System.out.print("\t" + tabuleiro[linha][coluna]);
                if (coluna < 2) {
                    System.out.print("\t|");
                }
            }
            System.out.println();
            if (linha < 2) {
                System.out.println("-------------------------------------------------------");
            }
            System.out.println();
        }
    }

    // Função para verificar se um jogador venceu
    public static boolean verificarVitoria(char[][] tabuleiro, char jogador) {
        // Verificar linhas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[i][0] == jogador && tabuleiro[i][1] == jogador && tabuleiro[i][2] == jogador) {
                return true;
            }
        }

        // Verificar colunas
        for (int i = 0; i < 3; i++) {
            if (tabuleiro[0][i] == jogador && tabuleiro[1][i] == jogador && tabuleiro[2][i] == jogador) {
                return true;
            }
        }

        // Verificar diagonal principal
        if (tabuleiro[0][0] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][2] == jogador) {
            return true;
        }

        // Verificar diagonal secundária
        if (tabuleiro[0][2] == jogador && tabuleiro[1][1] == jogador && tabuleiro[2][0] == jogador) {
            return true;
        }

        return false;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] matriz = {
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' },
            { ' ', ' ', ' ' }
        };

        char jogador1 = 'x';
        char jogador2 = 'o';
        char jogadorAtual = jogador1;
        int escolhaLinha, escolhaColuna;
        int jogadas = 0;
        boolean venceu = false;

        System.out.println("### JOGO DA VELHA ###");

        while (jogadas < 9) {
            // Mostrar jogador atual
            if (jogadorAtual == jogador1) {
                System.out.println("\nJogador 1 (x)");
            } else {
                System.out.println("\nJogador 2 (o)");
            }

            // Escolher posição
            System.out.print("Escolha a linha (0-2): ");
            escolhaLinha = scanner.nextInt();

            System.out.print("Escolha a coluna (0-2): ");
            escolhaColuna = scanner.nextInt();

            // Verificar se posição é válida
            if (escolhaLinha < 0 || escolhaLinha > 2 || escolhaColuna < 0 || escolhaColuna > 2) {
                System.out.println("Posição inválida. Escolha entre 0 e 2.");
                continue;
            }

            if (matriz[escolhaLinha][escolhaColuna] == ' ') {
                matriz[escolhaLinha][escolhaColuna] = jogadorAtual;
                jogadas++;

                imprimirTabuleiro(matriz); // mostrar tabuleiro atualizado

                // Verificar se o jogador venceu
                if (verificarVitoria(matriz, jogadorAtual)) {
                    System.out.println("Parabéns! Jogador " + (jogadorAtual == jogador1 ? "1 (x)" : "2 (o)") + " venceu!");
                    venceu = true;
                    break;
                }

                // Trocar jogador
                jogadorAtual = (jogadorAtual == jogador1) ? jogador2 : jogador1;
            } else {
                System.out.println("Posição já ocupada! Tente novamente.");
            }
        }

        if (!venceu) {
            System.out.println("Empate! Ninguém venceu.");
        }

        scanner.close();
    }
}
