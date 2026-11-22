package cine.circular;

public class ListaCircularCine<T extends Proyeccion> {

    private static class Nodo<E> {
        E dato;
        Nodo<E> siguiente;
        Nodo<E> anterior;

        Nodo(E dato) {
            this.dato = dato;
        }
    }

    private Nodo<T> cabeza;
    private Nodo<T> actual;
    private int tamanio;

    public ListaCircularCine() {
        this.cabeza = null;
        this.actual = null;
        this.tamanio = 0;
    }

    public boolean isEmpty() {
        return cabeza == null;
    }

    public int getTamanio() {
        return tamanio;
    }

    public void insertarFinal(T proyeccion) {
        if (proyeccion == null) {
            throw new IllegalArgumentException("La proyección no puede ser nula.");
        }
        Nodo<T> nuevo = new Nodo<>(proyeccion);
        if (isEmpty()) {
            nuevo.siguiente = nuevo;
            nuevo.anterior = nuevo;
            cabeza = nuevo;
            actual = nuevo;
        } else {
            Nodo<T> ultimo = cabeza.anterior;
            nuevo.siguiente = cabeza;
            nuevo.anterior = ultimo;
            ultimo.siguiente = nuevo;
            cabeza.anterior = nuevo;
        }
        tamanio++;
    }

    public void insertarInicio(T proyeccion) {
        insertarFinal(proyeccion);
        if (tamanio > 1) {
            cabeza = cabeza.anterior;
        }
    }

    public void insertarPosicion(T proyeccion, int posicion) {
        if (posicion < 0 || posicion > tamanio) {
            throw new IndexOutOfBoundsException("Posición inválida. Rango permitido: 0 a " + tamanio);
        }
        if (posicion == 0) {
            insertarInicio(proyeccion);
            return;
        }
        if (posicion == tamanio) {
            insertarFinal(proyeccion);
            return;
        }

        Nodo<T> nuevo = new Nodo<>(proyeccion);
        Nodo<T> temp = cabeza;
        for (int i = 0; i < posicion; i++) {
            temp = temp.siguiente;
        }

        Nodo<T> previo = temp.anterior;
        nuevo.siguiente = temp;
        nuevo.anterior = previo;
        previo.siguiente = nuevo;
        temp.anterior = nuevo;
        tamanio++;
    }

    public T eliminar(int posicion) {
        if (isEmpty()) {
            throw new IllegalStateException("La lista circular está vacía.");
        }
        if (posicion < 0 || posicion >= tamanio) {
            throw new IndexOutOfBoundsException("Posición inválida. Rango permitido: 0 a " + (tamanio - 1));
        }

        Nodo<T> objetivo = cabeza;
        for (int i = 0; i < posicion; i++) {
            objetivo = objetivo.siguiente;
        }

        T eliminado = objetivo.dato;
        eliminarNodo(objetivo);
        return eliminado;
    }

    public T eliminarPorTitulo(String titulo) {
        if (isEmpty()) {
            throw new IllegalStateException("La lista circular está vacía.");
        }
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("Ingresa un título válido para eliminar.");
        }

        Nodo<T> temp = cabeza;
        for (int i = 0; i < tamanio; i++) {
            if (temp.dato.getTitulo().equalsIgnoreCase(titulo.trim())) {
                T eliminado = temp.dato;
                eliminarNodo(temp);
                return eliminado;
            }
            temp = temp.siguiente;
        }
        return null;
    }

    private void eliminarNodo(Nodo<T> objetivo) {
        if (tamanio == 1) {
            cabeza = null;
            actual = null;
            tamanio = 0;
            return;
        }

        Nodo<T> previo = objetivo.anterior;
        Nodo<T> siguiente = objetivo.siguiente;
        previo.siguiente = siguiente;
        siguiente.anterior = previo;

        if (objetivo == cabeza) {
            cabeza = siguiente;
        }
        if (objetivo == actual) {
            actual = siguiente;
        }
        tamanio--;
    }

    public T siguiente() {
        if (isEmpty()) {
            return null;
        }
        actual = actual.siguiente;
        return actual.dato;
    }

    public T anterior() {
        if (isEmpty()) {
            return null;
        }
        actual = actual.anterior;
        return actual.dato;
    }

    public T consultarActual() {
        if (isEmpty()) {
            return null;
        }
        return actual.dato;
    }

    public void vaciarLista() {
        cabeza = null;
        actual = null;
        tamanio = 0;
    }

    public String mostrarTodos() {
        if (isEmpty()) {
            return " [ LISTA CIRCULAR DE CINE VACÍA - No hay funciones registradas ]";
        }

        StringBuilder sb = new StringBuilder();
        sb.append("Funciones en Lista Circular (Total: ").append(tamanio).append(")\n");
        sb.append("====================================================================================\n");

        Nodo<T> temp = cabeza;
        for (int i = 0; i < tamanio; i++) {
            String marcaCabeza = (temp == cabeza) ? "[CABEZA] " : "         ";
            String marcaActual = (temp == actual) ? "  ◄ [ACTUAL SELECCIONADO]" : "";
            sb.append("Pos ").append(i).append(" | ")
              .append(marcaCabeza)
              .append(temp.dato.toString())
              .append(marcaActual)
              .append("\n");
            if (i < tamanio - 1) {
                sb.append("         ↕\n");
            }
            temp = temp.siguiente;
        }

        sb.append("         ↻ (Enlace circular: el último nodo apunta de regreso a CABEZA: \"")
          .append(cabeza.dato.getTitulo()).append("\")\n");
        sb.append("====================================================================================");
        return sb.toString();
    }
}