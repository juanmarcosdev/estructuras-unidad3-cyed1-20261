package structures;

public class Cola {

    private int head;
    private int tail;
    private int size_max;
    private int size;
    private int[] arreglo;

    public Cola(int n) {
        this.head = 1;
        this.tail = 1;
        this.size_max = n;
        this.size = 0;
        this.arreglo = new int[n];
    }

    public boolean queueEmpty() {
        return this.size == 0;
    }

    public void enqueue(int x) {
        if (this.size == this.size_max) {
            throw new RuntimeException("Overflow: la cola está llena.");
        }

        this.arreglo[this.tail - 1] = x;
        this.tail = (this.tail == this.size_max) ? 1 : this.tail + 1;
        this.size++;
    }

    public int dequeue() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("Underflow: la cola está vacía.");
        }

        int valor = this.arreglo[this.head - 1];
        this.head = (this.head == this.size_max) ? 1 : this.head + 1;
        this.size--;
        return valor;
    }

    public int peek() {
        if (this.size == 0) {
            throw new java.util.NoSuchElementException("La cola está vacía.");
        }
        return this.arreglo[this.head - 1];
    }

    public int getHead() {
        return head;
    }

    public int getTail() {
        return tail;
    }

    public int getSize_max() {
        return size_max;
    }

    public int getSize() {
        return size;
    }

    public int[] getArreglo() {
        return arreglo;
    }

    public void setHead(int head) {
        this.head = head;
    }

    public void setTail(int tail) {
        this.tail = tail;
    }

    public void setSize_max(int size_max) {
        this.size_max = size_max;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setArreglo(int[] arreglo) {
        this.arreglo = arreglo;
    }

    @Override
    public String toString() {
        if (queueEmpty()) return "Cola: []";

        /*Recorremos la cola en orden lógico (frente → final),
         no en orden físico del arreglo.
         Como head puede estar en cualquier posición del arreglo
        (por el comportamiento circular), usamos aritmética modular
        para calcular el índice real de cada elemento:

           índice físico = (head - 1 + i) % size_max

         Donde i va de 0 (frente) hasta size-1 (último elemento).
         El "-1" convierte head de base 1 a base 0 (índice de arreglo).
         El "% size_max" hace que al llegar al final del arreglo,
        la siguiente posición sea la 0 (comportamiento circular).

        Ejemplo con size_max=5, head=4, size=3:
          i=0 → (4-1+0) % 5 = 3  → arreglo[3]  ← frente
          i=1 → (4-1+1) % 5 = 4  → arreglo[4]
           i=2 → (4-1+2) % 5 = 0  → arreglo[0]  ← da la vuelta
         */

        String resultado = "Cola: [";
        for (int i = 0; i < this.size; i++) {
            int indice = (this.head - 1 + i) % this.size_max;
            resultado += this.arreglo[indice];
            if (i < this.size - 1) {
                resultado += ", ";
            }
        }
        resultado += "]";
        return resultado;
    }
}