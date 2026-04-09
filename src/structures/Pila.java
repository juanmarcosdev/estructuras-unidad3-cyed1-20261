package structures;

public class Pila {

    private int[] arreglo;
    private int top;
    private int size;

    public Pila(int n) {
        this.size    = n;
        this.arreglo = new int[n];
        this.top     = 0;
    }

    public boolean stackEmpty() {
        return this.top == 0;
    }

    public void push(int x) {
        if (this.top == this.size) {
            throw new StackOverflowError();
        }
        this.top++;
        this.arreglo[this.top - 1] = x;
    }

    public int pop() {
        if (this.top == 0) {
            throw new java.util.EmptyStackException();
        }
        this.top--;
        return this.arreglo[this.top];
    }

    public int peek() {
        if (this.top == 0) {
            throw new java.util.EmptyStackException();
        }
        return this.arreglo[this.top - 1];
    }

    public int[] getArreglo() { return arreglo; }
    public int   getTop()     { return top; }
    public int   getSize()    { return size; }

    public void setArreglo(int[] arreglo) {
        this.arreglo = arreglo;
        this.size    = arreglo.length;
        if (this.top > this.size) {
            this.top = this.size;
        }
    }

    public void setTop(int top) {
        this.top = top;
    }

    public void setSize(int size) {
        this.size = size;
    }

    @Override
    public String toString() {
        if (stackEmpty()) return "Pila: []";
        String resultado = "Pila (tope a la derecha): [";
        for (int i = 0; i < this.top; i++) {
            resultado += this.arreglo[i];
            if (i < this.top - 1) resultado += ", ";
        }
        resultado += "]";
        return resultado;
    }
}