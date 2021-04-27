package co.edu.unbosque.model;

public class GrafoNoDirigido {

    int[][] matrizAdy;
    int[][] pesos;
    int vertices;
    int arcos;

    public GrafoNoDirigido(int vertices, int arcos) {
        this.vertices = vertices;
        this.arcos = arcos;
        matrizAdy = new int[vertices][vertices];
        pesos = new int[vertices][vertices];
    }

    public void inicializarMatriz() {
        for (int i = 0; i < vertices; i++) {
            for (int j = 0; j < vertices; j++) {
                matrizAdy[i][j] = 0;
                pesos[i][j] = 1<< 30;
            }
        }
    }

    public void ingresarArco(int inicio, int destino, int peso) {
        matrizAdy[inicio][destino] = 1;
        matrizAdy[destino][inicio] = 1;
        pesos[inicio][destino] = peso;
        pesos[destino][inicio] = peso;
    }

    public String mostrarMatrizAdy() {
        String a = "";
        for (int i = 0; i < vertices; i++) {
            a += i + "  -  ";
            for (int j = 0; j < vertices; j++) {
                a += matrizAdy[i][j] + "  ";
            }
            a += "\n";
        }

        return a;
    }

    public String mostrarMatrizPesos() {
        String a = "";
        for (int i = 0; i < vertices; i++) {
            a += i + "  -  ";
            for (int j = 0; j < vertices; j++) {
                a += pesos[i][j] + "  ";
            }
            a += "\n";
        }

        return a;
    }

    public void eliminar(int a) {
        for (int i = 0; i < vertices; i++) {
            if (matrizAdy[i][a] == 1) {

                matrizAdy[i][a] = 0;
                matrizAdy[a][i] = 0;
                pesos[i][a] = 0;
                pesos[a][i] = 0;

            }
        }
    }

}