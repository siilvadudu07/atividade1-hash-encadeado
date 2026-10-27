import java.util.Random;

public class TabelaHash {

    // Estrutura do nó da lista encadeada
    static class No {
        int valor;
        No proximo;

        No(int valor) {
            this.valor = valor;
            this.proximo = null;
        }
    }

    public static void main(String[] args) {
        No[] tabela = new No[100];
        Random random = new Random(1090);
        int[] ultimosTres = new int[3];

        // Inserção de 1000 elementos
        for (int i = 0; i < 1000; i++) {
            int valor = random.nextInt(100001);
            int indice = valor % 100;
            No novoNo = new No(valor);

            // Tratamento de colisão por encadeamento
            if (tabela[indice] == null) {
                tabela[indice] = novoNo;
            } else {
                No atual = tabela[indice];
                while (atual.proximo != null) {
                    atual = atual.proximo;
                }
                atual.proximo = novoNo;
            }

            // Guarda os três últimos valores inseridos
            ultimosTres[0] = ultimosTres[1];
            ultimosTres[1] = ultimosTres[2];
            ultimosTres[2] = valor;
        }

        int maiorListaIndice = -1;
        int maiorTamanho = 0;
        int qtdListasGeradas = 0;
        int posicoesVazias = 0;

        // Coleta as estatísticas da tabela
        for (int i = 0; i < 100; i++) {
            int tamanhoAtual = 0;
            No atual = tabela[i];

            while (atual != null) {
                tamanhoAtual++;
                atual = atual.proximo;
            }

            if (tamanhoAtual == 0) {
                posicoesVazias++;
            } else if (tamanhoAtual > 1) {
                qtdListasGeradas++;
            }

            if (tamanhoAtual > maiorTamanho) {
                maiorTamanho = tamanhoAtual;
                maiorListaIndice = i;
            }
        }

        // Impressão dos resultados
        System.out.println("===== RESULTADOS =====");
        System.out.println("Posicoes vazias: " + posicoesVazias);
        System.out.println("Listas encadeadas geradas (colisoes): " + qtdListasGeradas);

        System.out.println("\nTres ultimos valores inseridos:");
        System.out.println("1: " + ultimosTres[0] + " | 2: " + ultimosTres[1] + " | 3: " + ultimosTres[2]);

        System.out.println("\nMAIOR LISTA ENCADEADA (Indice " + maiorListaIndice + " com " + maiorTamanho + " elementos):");
        No atualMaior = tabela[maiorListaIndice];
        while (atualMaior != null) {
            System.out.print(atualMaior.valor + " -> ");
            atualMaior = atualMaior.proximo;
        }
        System.out.println("null");
    }
}