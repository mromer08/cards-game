/**
 * @file ArbolAVL.java
 * @author 202030799 Manuel Rojas
 * @brief Clase de estructa de datos tipo ArbolAVL
 * @version 0.1
 * @date 2022-05-08
 *
 *
 */
package com.mrojas.cartas.data_structures;

import java.io.*;

import com.mrojas.cartas.models.*;

/**
 * Clase para manejar cartas en una estructura de datos AVL
 */
public class ArbolAVL {

    private Nodo raiz;

    /**
     * Método para graficar el arbol avl
     * 
     * @param path La ruta en la cual se guardará la imagen generada.
     */
    public void graficar(String path) {
        raiz.graficar(path);
    }

    /**
     * Método para obtener el recorrido inOrder del arbol
     * 
     * @return Un arreglo de cartas ordenadas inOrder
     */
    public Carta[] inOrder() {
        Cola cola = new Cola();
        return inOrder(raiz, cola).toArray();
    }

    /**
     * Método para obtener el recorrido preOrder del arbol
     * 
     * @return Un arreglo de cartas ordenadas preOrder
     */
    public Carta[] preOrder() {
        Cola cola = new Cola();
        return preOrder(raiz, cola).toArray();
    }

    /**
     * Método para obtener el recorrido postOrder del arbol
     * 
     * @return Un arreglo de cartas ordenadas postOrder
     */
    public Carta[] posOrder() {
        Cola cola = new Cola();
        return posOrder(raiz, cola).toArray();
    }

    /**
     * Método para obtener las cartas en cierto nivel del arbol comenzando desde la
     * raiz en 0
     * 
     * @param limit El nivel que se quiere obtener
     * @return El arreglo con las cartas del nivel solicitado.
     */
    public Carta[] getNivel(int limit) {
        Cola cola = new Cola();
        return getNivel(raiz, cola, 0, limit).toArray();
    }

    /**
     * Método para buscar una Carta específica dentro del árbol
     * 
     * @param dato La carta a buscar
     * @return Restorna null si no encuentra la carta, de lo contrario devuelve una
     *         Carta.
     */
    public Nodo buscar(Carta dato) {
        Nodo actual = raiz;
        while (actual != null) {
            if (actual.dato.equals(dato)) {
                break;
            }
            actual = actual.dato.getTotal() < dato.getTotal() ? actual.derecha : actual.izquierda;
        }
        return actual;
    }

    /**
     * Método para insertar una Carta en el árbol
     * 
     * @param dato La Carta a insertar
     */
    public void insertar(Carta dato) {
        raiz = insertar(raiz, dato);
    }

    /**
     * Método para eliminar una Carta del ábol
     * 
     * @param dato La Carta a eliminar
     */
    public void eliminar(Carta dato) {
        raiz = eliminar(raiz, dato);
    }

    /**
     * Método para obtener la altura del árbol
     * 
     * @return Devuelve -1 en caso de estar vacio el árbol, de lo contrario devuelve
     *         la altura del árbol
     */
    public int altura() {
        return raiz == null ? -1 : raiz.altura;
    }

    /**
     * Método para obtener la altura de un Nodo en específico
     * 
     * @param n El nodo del cual se quiere saber la altura
     * @return La altura del nodo, si el nodo es null devulve -1
     */
    public int altura(Nodo n) {
        return n == null ? -1 : n.altura;
    }

    /**
     * Método para obtener el factor de equilibrio de un nodo
     * 
     * @param n El nodo del cual se requiere saber su FE
     * @return si el nodo es null devuelve 0, de lo contrario devuelve la resta de
     *         la altura de su nodo derecho menos el izquierdo
     */
    public int getFactorEquilibrio(Nodo n) {
        return n == null ? 0 : altura(n.derecha) - altura(n.izquierda);
    }

    /**
     * Método para realizar una rotación simple a la derecha dentro del arbol
     * 
     * @param nodo El nodo desde el cual se quiere hacer la rotación
     * @return el nuevo nodo padre luego de haber hecho la rotación
     */
    private Nodo rotacionDerecha(Nodo nodo) {
        Nodo padre = nodo.izquierda;
        Nodo derechoPadre = padre.derecha;
        padre.derecha = nodo;
        nodo.izquierda = derechoPadre;
        actualizarAltura(nodo);
        actualizarAltura(padre);
        return padre;
    }

    /**
     * Método para realizar una rotación simple a la izquierda dentro del arbol
     * 
     * @param nodo El nodo desde el cual se quiere hacer la rotación
     * @return el nuevo nodo padre luego de haber hecho la rotación
     */
    private Nodo rotacionIzquierda(Nodo nodo) {
        Nodo padre = nodo.derecha;
        Nodo izquierdaPadre = padre.izquierda;
        padre.izquierda = nodo;
        nodo.derecha = izquierdaPadre;
        actualizarAltura(nodo);
        actualizarAltura(padre);
        return padre;
    }

    /**
     * Método para actualizar la altura de cierto nodo, sumandole 1 al máximo entre
     * la altura de su hijo izquierdo y derecho
     * 
     * @param n El nodo a actualizar la altura
     */
    private void actualizarAltura(Nodo n) {
        n.altura = 1 + Math.max(altura(n.izquierda), altura(n.derecha));
    }

    /**
     * Método recursivo para rebalancear el árbol avl luego de alguna eliminación o
     * inserción
     * 
     * @param nodo El nodo desde el cual se comenzará a realizar el rebalanceo
     * @return El nodo luego de haber realizado rotaciones de ser necesarias
     */
    private Nodo rebalancear(Nodo nodo) {
        actualizarAltura(nodo);
        int fe = getFactorEquilibrio(nodo);
        if (fe > 1) {
            if (altura(nodo.derecha.derecha) > altura(nodo.derecha.izquierda)) {
                nodo = rotacionIzquierda(nodo);
            } else {
                nodo.derecha = rotacionDerecha(nodo.derecha);
                nodo = rotacionIzquierda(nodo);
            }
        } else if (fe < -1) {
            if (altura(nodo.izquierda.izquierda) > altura(nodo.izquierda.derecha)) {
                nodo = rotacionDerecha(nodo);
            } else {
                nodo.izquierda = rotacionIzquierda(nodo.izquierda);
                nodo = rotacionDerecha(nodo);
            }
        }
        return nodo;
    }

    /**
     * Método recursivo para realizar una inserción en el arbol
     * 
     * @param nodo El nodo desde el cual se realizará la inserción
     * @param dato La carta que se va a insertar
     * @return El nodo donde se insertó luego de aplicarle un rebalanceo
     */
    private Nodo insertar(Nodo nodo, Carta dato) {
        if (nodo == null) {
            return new Nodo(dato);
        } else if (nodo.dato.getTotal() > dato.getTotal()) {
            nodo.izquierda = insertar(nodo.izquierda, dato);
        } else {
            nodo.derecha = insertar(nodo.derecha, dato);
        }
        return rebalancear(nodo);
    }

    /**
     * Método recursivo para eliminar un nodo del arbol
     * 
     * @param nodo El nodo desde el cual se comenzará a eliminar
     * @param dato La Carta que se quiere eliminar
     * @return El nodo final luego de haber completado la eliminación, puede ser
     *         null y dependiendo de eso se le aplica rebalanceo
     */
    private Nodo eliminar(Nodo nodo, Carta dato) {
        if (nodo == null) {
            return nodo;
        } else if (nodo.dato.getTotal() > dato.getTotal()) {
            nodo.izquierda = eliminar(nodo.izquierda, dato);
        } else if (nodo.dato.getTotal() < dato.getTotal()) {
            nodo.derecha = eliminar(nodo.derecha, dato);
        } else {
            if (nodo.izquierda == null || nodo.derecha == null) {
                nodo = nodo.izquierda == null ? nodo.derecha : nodo.izquierda;
            }
        }
        if (nodo != null) {
            nodo = rebalancear(nodo);
        }
        return nodo;
    }

    /**
     * Método recursivo para obtener el recorrido inOrder del arbol
     * 
     * @param nodo El nodo desde el cual se comenzará a hacer el recorrido
     * @param cola La cola de cartas acumuladas antes de hacer el recorrido
     * @return La cola de cartas acumuladas luego de hacer el recorrido
     */
    private Cola inOrder(Nodo nodo, Cola cola) {
        if (nodo.izquierda != null) {
            inOrder(nodo.izquierda, cola);
        }
        cola.insertar(nodo.dato);
        if (nodo.derecha != null) {
            inOrder(nodo.derecha, cola);
        }
        return cola;
    }

    /**
     * Método recursivo para obtener el recorrido preOrder del arbol
     * 
     * @param nodo El nodo desde el cual se comenzará a hacer el recorrido
     * @param cola La cola de cartas acumuladas antes de hacer el recorrido
     * @return La cola de cartas acumuladas luego de hacer el recorrido
     */
    private Cola preOrder(Nodo nodo, Cola cola) {
        cola.insertar(nodo.dato);
        if (nodo.izquierda != null) {
            preOrder(nodo.izquierda, cola);
        }
        if (nodo.derecha != null) {
            preOrder(nodo.derecha, cola);
        }
        return cola;
    }

    /**
     * Método recursivo para obtener el recorrido postOrder del arbol
     * 
     * @param nodo El nodo desde el cual se comenzará a hacer el recorrido
     * @param cola La cola de cartas acumuladas antes de hacer el recorrido
     * @return La cola de cartas acumuladas luego de hacer el recorrido
     */
    private Cola posOrder(Nodo nodo, Cola cola) {
        if (nodo.izquierda != null) {
            posOrder(nodo.izquierda, cola);
        }
        if (nodo.derecha != null) {
            posOrder(nodo.derecha, cola);
        }
        cola.insertar(nodo.dato);
        return cola;
    }

    /**
     * Método recursivo para obtener las Cartas de un nivel específico
     * 
     * @param nodo   el nodo desde el cual se comenzará a recorrer el arbol
     * @param cola   La cola de cartas acumuladas antes de hacer el recorrido
     * @param altura La altura que se lleva recorrida del arbol
     * @param limit  La altura limite en la cual se ecuentran las cartas de interés
     * @return La cola de cartas acumuladas luego de hacer el recorrido y comprobado
     *         que se encuentran en el nivel solicitado
     */
    private Cola getNivel(Nodo nodo, Cola cola, int altura, int limit) {
        if (nodo.izquierda != null) {
            getNivel(nodo.izquierda, cola, altura + 1, limit);
        }

        if (altura == limit) {
            cola.insertar(nodo.dato);
        }

        if (nodo.derecha != null) {
            getNivel(nodo.derecha, cola, altura + 1, limit);
        }
        return cola;
    }

    /**
     * Clase Nodo para el ArbolAVL
     */
    class Nodo {
        Carta dato;
        int altura;
        Nodo izquierda;
        Nodo derecha;
        
        /**
         * Variable privada con la que lleva el control de un correlativo que se le
         * asignará a cada nodo que es creado, este será único para cada nodo y
         * servirá para hacer la gráfica del árbol con graphviz.
         */
        private static int correlativo = 1;
        /**
         * Constante privada que posee cada nodo y es única, funciona como
         * identificador y será útil para hacer la gráfica del árbol con graphviz.
         */
        private final int id;

        /**
         * Constructor de la clase Nodo.
         * 
         * @param valor Valor específico que el nodo almacenará.
         */

        Nodo(Carta dato) {
            this.dato = dato;
            id = correlativo++;
        }

        /**
         * Método que genera el gráfico del árbol avl con graphviz,
         * considerando como la raíz de dicho árbol el actual Nodo.
         * 
         * @param path Ruta de la imagen que se generará.
         */
        public void graficar(String path) {
            FileWriter fichero = null;
            PrintWriter escritor;
            try {
                fichero = new FileWriter("aux_grafico.dot");
                escritor = new PrintWriter(fichero);
                escritor.print(getCodigoGraphviz());
            } catch (Exception e) {
                System.err.println("Error al escribir el archivo aux_grafico.dot");
            } finally {
                try {
                    if (null != fichero)
                        fichero.close();
                } catch (Exception e2) {
                    System.err.println("Error al cerrar el archivo aux_grafico.dot");
                }
            }
            try {
                Runtime rt = Runtime.getRuntime();
                rt.exec("dot -Tjpg -o " + path + " aux_grafico.dot");
                // Esperamos medio segundo para dar tiempo a que la imagen se genere.
                // Para que no sucedan errores en caso de que se decidan graficar varios
                // árboles sucesivamente.
                Thread.sleep(500);
            } catch (Exception ex) {
                System.err.println("Error al generar la imagen para el archivo aux_grafico.dot");
            }
        }

        /**
         * Método que retorna el código que grapviz usará para generar la imagen
         * del árbol binario de búsqueda.
         * 
         * @return Código que grapviz usará para generar la imagen
         *         del árbol binario de búsqueda.
         */
        private String getCodigoGraphviz() {
            return "digraph grafica{\n" +
                    "rankdir=TB;\n" +
                    "node [shape = record, style=filled, fillcolor=\"#8b7bc3\"];\n" +
                    getCodigoInterno() +
                    "}\n";
        }

        /**
         * Genera el código interior de graphviz, este método tiene la particularidad
         * de ser recursivo, esto porque recorrer un árbol de forma recursiva es
         * bastante
         * sencillo y reduce el código considerablemente.
         * 
         * @return el código interno de graphviz del nodo actual
         */
        private String getCodigoInterno() {
            String etiqueta;
            if (izquierda == null && derecha == null) {
                etiqueta = "nodo" + id + " [ label =\"" + dato + "\"];\n";
            } else {
                etiqueta = "nodo" + id + " [ label =\"<C0>|" + dato + "|<C1>\"];\n";
            }
            if (izquierda != null) {
                etiqueta = etiqueta + izquierda.getCodigoInterno() +
                        "nodo" + id + ":C0->nodo" + izquierda.id + "\n";
            }
            if (derecha != null) {
                etiqueta = etiqueta + derecha.getCodigoInterno() +
                        "nodo" + id + ":C1->nodo" + derecha.id + "\n";
            }
            return etiqueta;
        }
    }
}