public class Tarefa_Atividade_13{
    static class No {
        int valor;
        No proximo;
        No anterior;
        public No(int valor) {
            this.valor = valor;
            this.proximo = null;
            this.anterior = null;
        }
    }
    static class ListaDuplamenteEncadeada {
        private No cabeca;
        private No cauda;
        public ListaDuplamenteEncadeada() {
            this.cabeca = null;
            this.cauda = null;
        }
        public boolean estaVazia() {
            return this.cabeca == null;
        }
        public void inserirEmOrdem(int valor) {
            No novoNo = new No(valor);
            if (estaVazia()) {
                this.cabeca = novoNo;
                this.cauda = novoNo;
                return;
            }
            if (valor <= this.cabeca.valor) {
                novoNo.proximo = this.cabeca;
                this.cabeca.anterior = novoNo;
                this.cabeca = novoNo;
                return;
            }
            if (valor >= this.cauda.valor) {
                novoNo.anterior = this.cauda;
                this.cauda.proximo = novoNo;
                this.cauda = novoNo;
                return;
            }
            No atual = this.cabeca.proximo;
            while (atual != null) {
                if (valor <= atual.valor) {
                    No anterior = atual.anterior;
                    anterior.proximo = novoNo;
                    novoNo.anterior = anterior;
                    novoNo.proximo = atual;
                    atual.anterior = novoNo;
                    return;
                }
                atual = atual.proximo;
            }
        }
        public void imprimirAscendente() {
            if (estaVazia()) {
                System.out.println("Lista vazia.");
                return;
            }
            No atual = this.cabeca;
            StringBuilder sb = new StringBuilder();
            sb.append("Lista (Ascendente): [");
            while (atual != null) {
                sb.append(atual.valor);
                if (atual.proximo != null) {
                    sb.append(", ");
                }
                atual = atual.proximo;
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
        public void imprimirDescendente() {
            if (estaVazia()) {
                System.out.println("Lista vazia.");
                return;
            }
            No atual = this.cauda;
            StringBuilder sb = new StringBuilder();
            sb.append("Lista (Decrescente): [");
            while (atual != null) {
                sb.append(atual.valor);
                if (atual.anterior != null) {
                    sb.append(", ");
                }
                atual = atual.anterior;
            }
            sb.append("]");
            System.out.println(sb.toString());
        }
        private void removerNo(No noParaRemover) {
            if (noParaRemover == null) return;
            if (noParaRemover == this.cabeca && noParaRemover == this.cauda) {
                this.cabeca = null;
                this.cauda = null;
            }
            else if (noParaRemover == this.cabeca) {
                this.cabeca = noParaRemover.proximo;
                this.cabeca.anterior = null;
            }
            else if (noParaRemover == this.cauda) {
                this.cauda = noParaRemover.anterior;
                this.cauda.proximo = null;
            }
            else {
                noParaRemover.anterior.proximo = noParaRemover.proximo;
                noParaRemover.proximo.anterior = noParaRemover.anterior;
            }
        }
        public void removerPrimos() {
            No atual = this.cabeca;
            while (atual != null) {
                No proximoNo = atual.proximo;
                if (ehPrimo(atual.valor)) {
                    removerNo(atual);
                }
                atual = proximoNo;
            }
        }
    }
    public static int gerarAleatorio(int min, int max) {
        int range = max - min;
        return (int)(Math.random() * (range + 1)) + min;
    }
    public static boolean ehPrimo(int n) {
        if (n <= 1) {
            return false;
        }
        for (int i = 2; i * i <= n; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        int tamanhoVetor = 1000;
        int[] vetor = new int[tamanhoVetor];
        
        System.out.println("--- 1. Gerando e Imprimindo Vetor Aleatório ---");
        for (int i = 0; i < tamanhoVetor; i++) {
            vetor[i] = gerarAleatorio(-9999, 9999);
            System.out.print(vetor[i] + " ");
        }
        System.out.println("\n(Impressão do vetor de geração concluída)");
        System.out.println("\n--- 2. Inserindo na Lista Duplamente Encadeada em Ordem ---");
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        for (int i = 0; i < vetor.length; i++) {
            lista.inserirEmOrdem(vetor[i]);
        }
        System.out.println("Todos os " + tamanhoVetor + " números foram inseridos em ordem.");
        System.out.println("\n--- 3. Imprimindo a Lista Completa ---");
        lista.imprimirAscendente();
        lista.imprimirDescendente();
        System.out.println("\n--- 4. Removendo Números Primos ---");
        lista.removerPrimos();
        System.out.println("Remoção de primos concluída.");
        System.out.println("\n--- 5. Imprimindo a Lista Final (sem primos) ---");
        lista.imprimirAscendente();
    }
}