package structures;

public class ColaPrioridad {

    private int[] valores;
    private int[] prioridades;
    private int size;
    private int size_max;

    public ColaPrioridad(int n) {
        this.size_max    = n;
        this.size        = 0;
        this.valores     = new int[n];
        this.prioridades = new int[n];
    }

    public boolean isEmpty()    { return size == 0; }
    public int getSize()        { return size; }
    public int getSizeMax()     { return size_max; }
    public int[] getValores()   { return valores; }
    public int[] getPrioridades() { return prioridades; }

    /**
     * Inserta x con prioridad p.
     * El arreglo se mantiene ordenado de forma ASCENDENTE por prioridad:
     * el elemento de MAYOR prioridad queda siempre en el indice size-1
     * T(n) = O(n) , complejidad lineal
     */
    public void insert(int x, int p) {
        if (size == size_max)
            throw new RuntimeException("Overflow: cola de prioridad llena.");

        int i = size - 1;
        while (i >= 0 && prioridades[i] > p) {
            valores[i + 1]     = valores[i];
            prioridades[i + 1] = prioridades[i];
            i--;
        }
        valores[i + 1]     = x;
        prioridades[i + 1] = p;
        size++;
    }

    /**
     * Consulta (sin extraer) el elemento de mayor prioridad,
     * Costo constante O(1)
     */
    public int maximum() {
        if (size == 0)
            throw new java.util.NoSuchElementException("Cola de prioridad vacía.");
        return valores[size - 1];
    }

    /**
     * Extrae y retorna el elemento de mayor prioridad.
     * Costo constante O(1)
     */
    public int extractMax() {
        if (size == 0)
            throw new java.util.NoSuchElementException("Underflow: cola de prioridad vacía.");
        int val = valores[size - 1];
        size--;
        return val;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "ColaPrioridad: []";
        String s = "ColaPrioridad: [";
        for (int i = size - 1; i >= 0; i--) {
            s += "(" + valores[i] + ", p=" + prioridades[i] + ")";
            if (i > 0) s += ", ";
        }
        return s + "]";
    }
}