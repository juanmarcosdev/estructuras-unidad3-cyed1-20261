package ui;

import structures.Pila;
import structures.Cola;

public class Main {

    static void linea() {
        System.out.println("─".repeat(52));
    }

    static void titulo(String texto) {
        linea();
        System.out.println("  " + texto);
        linea();
    }

    static String arregloFisico(Cola c) {
        int[] arr = c.getArreglo();
        String s = "  Arreglo físico: [";
        for (int i = 0; i < arr.length; i++) {
            s += arr[i];
            if (i < arr.length - 1) s += ", ";
        }
        s += "]  head=" + c.getHead() + "  tail=" + c.getTail();
        return s;
    }

    public static void main(String[] args) {

        System.out.println("Pila LIFO");
        System.out.println();

        titulo("1. Crear pila de capacidad n = 5");
        Pila pila = new Pila(5);
        System.out.println("  new Pila(5)  →  top[S] = 0");
        System.out.println();
        System.out.println("  STACK-EMPTY(S) = " + pila.stackEmpty() + "  ✓ (recién creada)");
        System.out.println();

        titulo("2. PUSH — insertar elementos");
        int[] valoresPila = {10, 25, 7, 43, 31};
        for (int v : valoresPila) {
            pila.push(v);
            System.out.println("  PUSH(" + v + ")  →  " + pila);
        }
        System.out.println();
        System.out.println("  STACK-EMPTY(S) = " + pila.stackEmpty() + "  ✓ (pila con elementos)");
        System.out.println();

        titulo("3. Overflow — intentar PUSH en pila llena");
        System.out.println("  La pila ya tiene " + pila.getTop() + "/" + pila.getSize() + " elementos.");
        System.out.print("  PUSH(99)  →  ");
        try {
            pila.push(99);
        } catch (StackOverflowError e) {
            System.out.println("StackOverflowError capturado  ✓");
            System.out.println("  La pila NO se modificó: " + pila);
        }
        System.out.println();

        titulo("4. PEEK — consultar tope sin extraer");
        System.out.println("  Tope actual  →  PEEK() = " + pila.peek());
        System.out.println("  top[S] sigue siendo " + pila.getTop() + "  ✓ (peek no modifica)");
        System.out.println();

        titulo("5. POP — extraer elementos (orden LIFO)");
        while (!pila.stackEmpty()) {
            int extraido = pila.pop();
            System.out.println("  POP()  →  extraído = " + extraido + "   pila ahora: " + pila);
        }
        System.out.println();
        System.out.println("  STACK-EMPTY(S) = " + pila.stackEmpty() + "  ✓ (pila vaciada)");
        System.out.println();

        titulo("6. Underflow — intentar POP en pila vacía");
        System.out.print("  POP()  →  ");
        try {
            pila.pop();
        } catch (java.util.EmptyStackException e) {
            System.out.println("EmptyStackException capturado  ✓");
        }
        System.out.println();

        titulo("7. PEEK en pila vacía");
        System.out.print("  PEEK()  →  ");
        try {
            pila.peek();
        } catch (java.util.EmptyStackException e) {
            System.out.println("EmptyStackException capturado  ✓");
        }
        System.out.println();

        titulo("8. Reutilización — push después de vaciar");
        pila.push(100);
        pila.push(200);
        pila.push(300);
        System.out.println("  PUSH(100), PUSH(200), PUSH(300)");
        System.out.println("  " + pila);
        System.out.println("  PEEK() = " + pila.peek() + "  ✓");
        System.out.println();

        System.out.println(" Cola FIFO");
        System.out.println();

        titulo("9. Crear cola de capacidad n = 5");
        Cola cola = new Cola(5);
        System.out.println("  new Cola(5)  →  head=1, tail=1, size=0");
        System.out.println();
        System.out.println("  QUEUE-EMPTY(Q) = " + cola.queueEmpty() + "  ✓ (recién creada)");
        System.out.println();

        titulo("10. ENQUEUE — insertar elementos");
        int[] valoresCola = {1, 2, 3, 4, 5};
        for (int v : valoresCola) {
            cola.enqueue(v);
            System.out.println("  ENQUEUE(" + v + ")  →  " + cola);
        }
        System.out.println();

        titulo("11. Overflow — intentar ENQUEUE en cola llena");
        System.out.println("  La cola ya tiene " + cola.getSize() + "/" + cola.getSize_max() + " elementos.");
        System.out.print("  ENQUEUE(99)  →  ");
        try {
            cola.enqueue(99);
        } catch (RuntimeException e) {
            System.out.println("Overflow capturado  ✓");
            System.out.println("  La cola NO se modificó: " + cola);
        }
        System.out.println();

        titulo("12. PEEK — consultar el frente sin extraer");
        System.out.println("  Frente actual  →  PEEK() = " + cola.peek());
        System.out.println("  size sigue siendo " + cola.getSize() + "  ✓ (peek no modifica)");
        System.out.println();

        titulo("13. DEQUEUE — arreglo físico vs vista lógica");
        System.out.println("  Antes de extraer:");
        System.out.println("  Vista lógica:   " + cola);
        System.out.println(arregloFisico(cola));
        System.out.println();
        while (!cola.queueEmpty()) {
            int extraido = cola.dequeue();
            System.out.println("  DEQUEUE()  →  extraído = " + extraido);
            System.out.println("  Vista lógica:   " + cola);
            System.out.println(arregloFisico(cola));
            System.out.println("  (el " + extraido + " sigue en el arreglo pero head avanzó, ya no pertenece a la cola)");
            System.out.println();
        }
        System.out.println("  QUEUE-EMPTY(Q) = " + cola.queueEmpty() + "  ✓ (cola vaciada)");
        System.out.println();
        System.out.println("  Aunque el TAD dice que la cola está vacía,");
        System.out.println("  el arreglo físico aún conserva los valores anteriores:");
        System.out.println(arregloFisico(cola));
        System.out.println("  Esos valores son 'basura' — head y tail apuntan al mismo lugar");
        System.out.println("  y size=0, así que la cola los ignora por completo.");
        System.out.println();
        System.out.println("  Ahora se puede volver a encolar. Los nuevos valores");
        System.out.println("  irán sobreescribiendo el arreglo posición por posición:");
        System.out.println();
        int[] nuevos = {10, 20, 30, 40, 50};
        for (int v : nuevos) {
            cola.enqueue(v);
            System.out.println("  ENQUEUE(" + v + ")");
            System.out.println("  Vista lógica:   " + cola);
            System.out.println(arregloFisico(cola));
            System.out.println("  (la celda que antes tenía basura ahora tiene " + v + ")");
            System.out.println();
        }

        titulo("14. Underflow — intentar DEQUEUE en cola vacía");
        while (!cola.queueEmpty()) cola.dequeue();
        System.out.print("  DEQUEUE()  →  ");
        try {
            cola.dequeue();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("NoSuchElementException capturado  ✓");
        }
        System.out.println();

        titulo("15. PEEK en cola vacía");
        System.out.print("  PEEK()  →  ");
        try {
            cola.peek();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("NoSuchElementException capturado  ✓");
        }
        System.out.println();

        titulo("16. Comportamiento circular — físico vs lógico");
        for (int v : valoresCola) cola.enqueue(v);
        System.out.println("  Cola llena con 1 2 3 4 5:");
        System.out.println("  Vista lógica:   " + cola);
        System.out.println(arregloFisico(cola));
        System.out.println();

        int saliente = cola.dequeue();
        System.out.println("  DEQUEUE()  →  extraído = " + saliente);
        System.out.println("  Vista lógica:   " + cola);
        System.out.println(arregloFisico(cola));
        System.out.println("  (head avanzó, el " + saliente + " ya no pertenece a la cola)");
        System.out.println();

        cola.enqueue(6);
        System.out.println("  ENQUEUE(6):");
        System.out.println("  Vista lógica:   " + cola);
        System.out.println(arregloFisico(cola));
        System.out.println("  (el 6 ocupa físicamente la celda que dejó el " + saliente + ")");
        System.out.println("  → El arreglo muestra [6,2,3,4,5] pero la cola lógica es [2,3,4,5,6]");
        System.out.println();

        titulo("17. Reutilización — enqueue después de vaciar");
        while (!cola.queueEmpty()) cola.dequeue();
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        System.out.println("  ENQUEUE(10), ENQUEUE(20), ENQUEUE(30)");
        System.out.println("  " + cola);
        System.out.println("  PEEK() = " + cola.peek() + "  ✓");
        System.out.println();
    }
}