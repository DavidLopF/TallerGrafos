package co.edu.unbosque.controller;

import co.edu.unbosque.View.View;
import co.edu.unbosque.model.GrafoDirigido;
import co.edu.unbosque.model.GrafoNoDirigido;

public class Controller {

    private View view;
    private GrafoDirigido grafoDirigido;
    private GrafoNoDirigido grafoNoDirigido;

    public Controller() {
        view = new View();
        grafoDirigido = new GrafoDirigido();

        funcionar();
    }

    private void funcionar() {
        String opcion = view.menuPrincipal();

        switch (opcion) {
            case "1. Grafo dirigido.":
                menuDirigido();

            case "2. Grafo no dirigido":
                int vertices = view.capturarInt("Ingrese cantidad de vertices: ");
                int arcos = view.capturarInt("Ingrese cantidad de arcos");

                grafoNoDirigido = new GrafoNoDirigido(vertices, arcos);
                grafoNoDirigido.inicializarMatriz();

                menuNoDirigidos();

        }
    }

    public void menuDirigido() {
        String opcion = view.menuGrafo(grafoDirigido.mostarLista());

        switch (opcion) {
            case "1. agregar elemento":
                grafoDirigido.agregarVertice();
                menuDirigido();

            case "2. agregar arco.":
                String dato = view.capturarString("Ingrese el inicio, destino y peso separado por ( , ) : ");
                String[] arco = dato.split(",");

                if (esNumeroInt(arco[0]) && esNumeroInt(arco[1]) && esNumeroInt(arco[2])) {

                    grafoDirigido.agregarArco(Integer.parseInt(arco[0]), Integer.parseInt(arco[1]), Integer.parseInt(arco[2]));
                    menuDirigido();
                } else {
                    view.mostrarMensaje("..:: ERROR DE INGRESO ::..");
                    menuDirigido();
                }

            case "3. Eliminar elemento":
                int a = view.capturarInt("Ingrese numero de vertice a eliminar: ");
                grafoDirigido.eliminarVetice(a);
                menuDirigido();

            case "4. Eliminar arco":
                dato = view.capturarString("Ingrese el inicio, destino y peso separado por ( , ) : ");
                arco = dato.split(",");

                if (esNumeroInt(arco[0]) && esNumeroInt(arco[1]) && esNumeroInt(arco[2])) {

                    grafoDirigido.elimianrArco(Integer.parseInt(arco[0]), Integer.parseInt(arco[1]), Integer.parseInt(arco[2]));
                    menuDirigido();
                } else {
                    view.mostrarMensaje("..:: ERROR DE INGRESO ::..");
                    menuDirigido();
                }

            case "5. indicar si existe un camino entre un Nodo X y otro Nodo y":
                dato = view.capturarString("Ingrese el inicio, destino y peso separado por ( , ) : ");
                arco = dato.split(",");
                if (esNumeroInt(arco[0]) && esNumeroInt(arco[1])) {

                    view.mostrarMensaje("De el vertice " + arco[0] + " a el vertice " + arco[1] + " hay " + grafoDirigido.caminoEntreAyB(Integer.parseInt(arco[0]), Integer.parseInt(arco[1])) + " posibles caminos.");
                    menuDirigido();
                } else {
                    view.mostrarMensaje("..:: ERROR DE INGRESO ::..");
                    menuDirigido();
                }


            case "6. Ruta menos costosa entre nodo x a nodo y":
                dato = view.capturarString("Ingrese el inicio, destino y peso separado por ( , ) : ");
                arco = dato.split(",");
                if (esNumeroInt(arco[0]) && esNumeroInt(arco[1])) {
                    view.mostrarMensaje("El camino mas corto de " + arco[0] + " a " + arco[1] + " es :\n" +
                            grafoDirigido.caminoMinimo(Integer.parseInt(arco[0]), Integer.parseInt(arco[1])));
                    menuDirigido();
                } else {
                    view.mostrarMensaje("..:: ERROR DE INGRESO ::..");
                    menuDirigido();
                }

        }
    }

    public void menuNoDirigidos() {

        String opcion = view.menuGrafoNoDirigido(grafoNoDirigido.mostrarMatrizAdy() + "\n\nMatriz pesos:\n" + grafoNoDirigido.mostrarMatrizPesos());

        switch (opcion) {
            case "1. agregar arco.":
                String datos = view.capturarString("Ingrese incio, destino y peso separado de  ( ; ) : ");
                String[] enlase = datos.split(";");

                if (esNumeroInt(enlase[0]) && esNumeroInt(enlase[1]) && esNumeroInt(enlase[2])) {

                    grafoNoDirigido.ingresarArco(Integer.parseInt(enlase[0]), Integer.parseInt(enlase[1]), Integer.parseInt(enlase[2]));
                    view.mostrarMensaje("..:: ARCO INGRESADO CON EXITO ::..");
                    menuNoDirigidos();
                } else {
                    view.mostrarMensaje("..:: DATOS ERRONEOS ::..");
                    menuNoDirigidos();
                }

            case "3. Eliminar arco de vertices.":
                int a = view.capturarInt("Ingrese elemto a eliminar");
                grafoNoDirigido.eliminar(a);
                menuNoDirigidos();
        }


    }


    private boolean esNumeroInt(String mensaje) {
        try {
            Integer.parseInt(mensaje);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

}
