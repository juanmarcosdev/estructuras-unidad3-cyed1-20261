package structures;

public class TablaHash {

    private ListaEnlazadaGenerica<Integer>[] tabla;
    private int size;

    public int getSize() { return size; }
    public void setSize(int size) { this.size = size; }

    @SuppressWarnings("unchecked")
    public TablaHash(int n) throws Exception {
        this.tabla = new ListaEnlazadaGenerica[n];
        this.size  = n;
    }

    public boolean search(int k, int value) throws Exception {
        int pos = hashFunction(k);
        ListaEnlazadaGenerica<Integer> lista = tabla[pos];

        if (lista != null) {
            for (int i = 0; i < lista.size(); i++) {
                if (lista.obtener(i) == value) return true;
            }
        }
        return false;
    }

    public void insert(int k, int value) throws Exception {
        int pos = hashFunction(k);

        if (tabla[pos] == null) {
            tabla[pos] = new ListaEnlazadaGenerica<>();
        }
        tabla[pos].agregar(value);
    }

    public void delete(int k, int v) throws Exception {
        int pos = hashFunction(k);
        ListaEnlazadaGenerica<Integer> lista = tabla[pos];

        if (lista == null || lista.estaVacia()) return;


        for (int i = 0; i < lista.size(); i++) {
            if (lista.obtener(i) == v) {
                lista.eliminar(i);
                return;
            }
        }
    }

    private int hashFunction(int k) throws Exception {
        return k % this.size;
    }
}