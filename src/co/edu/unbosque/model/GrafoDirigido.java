package co.edu.unbosque.model;

import java.util.*;

public class GrafoDirigido {

    private List<List<Nodo>> listaADY;


    public GrafoDirigido() {
        listaADY = new ArrayList<>();

    }


    public void agregarVertice() {
        listaADY.add(new ArrayList<>());
    }

    public void agregarArco(int inicio, int destino, int peso) {
        listaADY.get(inicio).add(new Nodo(destino, peso));
    }

    public void eliminarVetice(int a) {
        listaADY.remove(a);
        for (int i = 0; i < listaADY.size(); i++) {
            for (int j = 0; j < listaADY.get(i).size(); j++) {
                if (listaADY.get(i).get(j).getVertice() == a) {
                    listaADY.get(i).remove(j);
                }
            }
        }
    }

    public void elimianrArco(int inicio, int destino, int peso) {
        for (int j = 0; j < listaADY.get(inicio).size(); j++) {
            if (listaADY.get(inicio).get(j).getVertice() == destino && listaADY.get(inicio).get(j).getPeso() == peso) {
                listaADY.get(inicio).remove(j);
            }
        }
    }

    public int caminoEntreAyB(int inicio, int desitno) {
        int[] visitado = new int[obtenerVertices()];
        Queue<Integer> cola = new LinkedList<Integer>();
        int cont = 0;
        int actual = 0;
        int next = 0;
        cola.add(inicio);  // se inserta el nodo con el que se comienza
        visitado[inicio] = 0; // se marca como visitado para no pasar por el


        while (!cola.isEmpty()) {
            actual = cola.poll(); // se toma el elemento que sigue en la cola.
            System.out.println(actual);
            for (int i = 0; i < listaADY.get(actual).size(); i++) { // se recorre lista de adyasencia
                next = listaADY.get(actual).get(i).getVertice();
                if (next == desitno) { // se evalua la condicion
                    cont++;
                }

                if (visitado[next] <= 0) {  // se evalua si el siguiente nodo ya esta visitdado
                    visitado[next] = visitado[actual] + 1; // si no esta visitado se suma uno para indicar que ya fue visitado una vez
                    cola.add(next); // se agrega a la cola
                }
            }

        }
        return cont;
    }


    public int obtenerVertices() {
        return listaADY.size();
    }

    public String caminoMinimo(int inicial, int destino) {
        String a = "";
        boolean[] visitados = new boolean[obtenerVertices()];
        int[] distancia = new int[obtenerVertices()];
        int[] previo = new int[obtenerVertices()];

        for (int i = 0; i < obtenerVertices(); ++i) {
            distancia[i] = 1 << 30;  //inicializamos todas las distancias con valor infinito
            visitados[i] = false; //inicializamos todos los vértices como no visitados
            previo[i] = -1;      //inicializamos el previo del vertice i con -1
        }

        PriorityQueue<Nodo> colarPrioridad = new PriorityQueue<Nodo>();
        colarPrioridad.add(new Nodo(inicial, 0));

        distancia[inicial] = 0;
        int actual, adyacente, peso;


        while (!colarPrioridad.isEmpty()) {
            actual = colarPrioridad.element().getVertice();
            colarPrioridad.remove();
            if (!visitados[actual]) {
                visitados[actual] = true;

                for (int i = 0; i < listaADY.get(actual).size(); i++) {

                    adyacente = listaADY.get(actual).get(i).getVertice();
                    peso = listaADY.get(actual).get(i).getPeso();

                    if (!visitados[adyacente]) {
                        if (distancia[actual] + peso < distancia[adyacente]) {
                            distancia[adyacente] = distancia[actual] + peso;
                            previo[adyacente] = actual;
                            colarPrioridad.add(new Nodo(adyacente, distancia[adyacente]));

                        }
                    }

                }
            }

        }
        ArrayList camino = new ArrayList();
        camino.add(inicial);

        for (int i = 0; i < distancia.length; i++) {
            a += "Vetice " + i + ",distancia mas corta  a " + inicial + ": " + distancia[i] + "\n";
        }
        return print(destino, previo, camino).toString() + "\n" + a;

    }

    public ArrayList print(int destino, int[] previo, ArrayList a) {
        if (previo[destino] != -1) {//si aun poseo un vertice previo
            a.add(destino);
            print(previo[destino], previo, a); //recursivamente sigo explorando
        }
        return a;
    }


    public String mostarLista() {
        String a = "";
        for (int i = 0; i < listaADY.size(); i++) {
            a += i + " - " + listaADY.get(i).toString() + "\n";
        }
        return a;
    }


}