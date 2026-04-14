package ui;

import structures.Pila;
import structures.Cola;
import structures.TablaHash;
import structures.ColaPrioridad;

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

    static String slotInfo(TablaHash t, int k, int value) throws Exception {
        boolean found = t.search(k, value);
        return "search(" + k + "," + value + ") = " + found;
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

        // ═══════════════════════════════════════════════════════════
        System.out.println();
        System.out.println(" Cola de Prioridad");
        System.out.println();

        titulo("18. Crear cola de prioridad de capacidad n = 6");
        ColaPrioridad<String> cp = new ColaPrioridad<>(6);
        System.out.println("  new ColaPrioridad<>(6)  →  size = 0");
        System.out.println();
        System.out.println("  isEmpty() = " + cp.isEmpty() + "  ✓ (recién creada)");
        System.out.println();

        titulo("19. INSERT — la clave de orden es la prioridad, no la llegada");
        System.out.println("  Insertamos en este orden:");
        System.out.println();
        cp.insert(3, "impresora");
        System.out.println("  insert(p=3, \"impresora\")  →  " + cp);
        cp.insert(7, "servidor");
        System.out.println("  insert(p=7, \"servidor\")   →  " + cp);
        cp.insert(1, "log");
        System.out.println("  insert(p=1, \"log\")         →  " + cp);
        cp.insert(5, "backup");
        System.out.println("  insert(p=5, \"backup\")     →  " + cp);
        cp.insert(9, "alarma");
        System.out.println("  insert(p=9, \"alarma\")     →  " + cp);
        System.out.println();
        System.out.println("  El arreglo interno queda ordenado ascendente por prioridad.");
        System.out.println("  El elemento de mayor prioridad siempre está en size-1.");
        System.out.println();

        titulo("20. PEEK — consultar el máximo sin extraer");
        System.out.println("  El de mayor prioridad es: \"" + cp.peek() + "\"");
        System.out.println("  size sigue siendo " + cp.getSize() + "  ✓ (peek no modifica)");
        System.out.println();

        titulo("21. EXTRACT-MAX — no es FIFO, sale el de mayor prioridad");
        System.out.println("  \"impresora\" llegó primero (p=3), pero no es la que sale primero.");
        System.out.println();
        while (!cp.isEmpty()) {
            String extraido = cp.extractMax();
            System.out.println("  EXTRACT-MAX()  →  \"" + extraido + "\"   queda: " + cp);
        }
        System.out.println();
        System.out.println("  Orden de salida: alarma(9) → servidor(7) → backup(5)");
        System.out.println("                   → impresora(3) → log(1)");
        System.out.println("  Es el orden inverso de prioridades, sin importar cuándo llegó cada uno.");
        System.out.println();

        titulo("22. INSERT actualiza el orden interno automáticamente");
        ColaPrioridad<Integer> cp2 = new ColaPrioridad<>(5);
        System.out.println("  Insertamos 5, 2, 8, 1, 6 en ese orden:");
        int[] vals = {5, 2, 8, 1, 6};
        int[] prios = {5, 2, 8, 1, 6};
        for (int i = 0; i < vals.length; i++) {
            cp2.insert(prios[i], vals[i]);
            System.out.println("  insert(p=" + prios[i] + ", " + vals[i] + ")  →  " + cp2);
        }
        System.out.println();
        System.out.println("  El arreglo se reordena en cada insert para mantener el invariante.");
        System.out.println();

        titulo("23. Prioridades duplicadas — ambos elementos coexisten");
        ColaPrioridad<String> cp3 = new ColaPrioridad<>(5);
        cp3.insert(4, "tarea-A");
        cp3.insert(4, "tarea-B");
        cp3.insert(9, "urgente");
        System.out.println("  insert(p=4,\"tarea-A\")  insert(p=4,\"tarea-B\")  insert(p=9,\"urgente\")");
        System.out.println("  " + cp3);
        System.out.println();
        System.out.println("  EXTRACT-MAX() saca \"urgente\" (p=9):");
        System.out.println("  → \"" + cp3.extractMax() + "\"   queda: " + cp3);
        System.out.println();
        System.out.println("  Ambas tareas con p=4 siguen en la cola, ninguna se pierde.");
        System.out.println();

        titulo("24. Overflow — insertar más allá de la capacidad");
        ColaPrioridad<Integer> cp4 = new ColaPrioridad<>(3);
        cp4.insert(1, 10);
        cp4.insert(2, 20);
        cp4.insert(3, 30);
        System.out.println("  Cola llena (capacidad 3): " + cp4);
        System.out.print("  insert(4, 40)  →  ");
        try {
            cp4.insert(4, 40);
        } catch (RuntimeException e) {
            System.out.println("Overflow capturado  ✓");
            System.out.println("  La cola NO se modificó: " + cp4);
        }
        System.out.println();

        titulo("25. Underflow — EXTRACT-MAX en cola vacía");
        ColaPrioridad<String> cp5 = new ColaPrioridad<>(3);
        System.out.print("  extractMax() en cola vacía  →  ");
        try {
            cp5.extractMax();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("NoSuchElementException capturado  ✓");
        }
        System.out.println();

        titulo("26. PEEK en cola vacía");
        System.out.print("  peek() en cola vacía  →  ");
        try {
            cp5.peek();
        } catch (java.util.NoSuchElementException e) {
            System.out.println("NoSuchElementException capturado  ✓");
        }
        System.out.println();

        titulo("27. Cola FIFO vs Cola de prioridad — comparación directa");
        System.out.println("  Mismos elementos, mismo orden de inserción:");
        System.out.println("  enqueue/insert: 30(p=3), 10(p=1), 20(p=2)");
        System.out.println();

        Cola colaFifo = new Cola(3);
        colaFifo.enqueue(30);
        colaFifo.enqueue(10);
        colaFifo.enqueue(20);

        ColaPrioridad<Integer> colaPrio = new ColaPrioridad<>(3);
        colaPrio.insert(3, 30);
        colaPrio.insert(1, 10);
        colaPrio.insert(2, 20);

        System.out.println("  Cola FIFO sale en orden de llegada:");
        System.out.print("  ");
        while (!colaFifo.queueEmpty()) {
            System.out.print(colaFifo.dequeue() + " ");
        }
        System.out.println("  ← llegó primero, sale primero");
        System.out.println();

        System.out.println("  Cola de prioridad sale en orden de prioridad:");
        System.out.print("  ");
        while (!colaPrio.isEmpty()) {
            System.out.print(colaPrio.extractMax() + " ");
        }
        System.out.println("  ← mayor prioridad, sale primero");
        System.out.println();

        // ═══════════════════════════════════════════════════════════
        System.out.println();
        System.out.println(" Tabla Hash — encadenamiento separado");
        System.out.println();

        titulo("28. Crear tabla de tamaño m = 7");
        TablaHash tabla = new TablaHash(7);
        System.out.println("  new TablaHash(7)  →  getSize() = " + tabla.getSize());
        System.out.println("  h(k) = k % 7  →  slots 0..6");
        System.out.println();

        titulo("29. INSERT — insertar pares (llave, valor)");
        int[][] pares = {{10,100},{17,170},{3,30},{24,240},{10,101}};
        for (int[] p : pares) {
            tabla.insert(p[0], p[1]);
            System.out.println("  insert(" + p[0] + "," + p[1] + ")  →  slot "
                    + (p[0] % 7) + "   h(" + p[0] + ")=" + p[0] + "%7=" + (p[0]%7));
        }
        System.out.println();
        System.out.println("  Nota: claves 10 y 17 comparten slot 3  (colisión)");
        System.out.println("  Nota: clave 10 tiene dos valores (100 y 101)");
        System.out.println();

        titulo("30. SEARCH — buscar valores existentes");
        int[][] busquedas = {{10,100},{17,170},{3,30},{24,240},{10,101}};
        for (int[] b : busquedas) {
            System.out.println("  " + slotInfo(tabla, b[0], b[1])
                    + "   ✓ (esperado: true)");
        }
        System.out.println();

        titulo("31. SEARCH — valores o llaves ausentes");
        int[][] ausentes = {{10,999},{5,30},{3,99}};
        for (int[] a : ausentes) {
            System.out.println("  " + slotInfo(tabla, a[0], a[1])
                    + "   ✓ (esperado: false)");
        }
        System.out.println();

        titulo("32. DELETE — eliminar un valor de slot con varios elementos");
        System.out.println("  Slot 3 antes de delete: tiene (10,100), (17,170), (10,101)");
        tabla.delete(10, 100);
        System.out.println("  delete(10,100)");
        System.out.println("  search(10,100) = " + tabla.search(10,100) + "  ✓ (eliminado)");
        System.out.println("  search(17,170) = " + tabla.search(17,170) + "  ✓ (intacto)");
        System.out.println("  search(10,101) = " + tabla.search(10,101) + "  ✓ (intacto)");
        System.out.println();

        titulo("33. DELETE — slot vacío no lanza excepción");
        System.out.print("  delete(5,50) en slot vacío  →  ");
        try {
            tabla.delete(5, 50);
            System.out.println("sin excepción  ✓");
        } catch (Exception e) {
            System.out.println("ERROR inesperado: " + e.getMessage());
        }
        System.out.println();

        titulo("34. SEARCH — slot vacío retorna false");
        System.out.println("  search(5,50) en slot sin datos = "
                + tabla.search(5,50) + "  ✓ (esperado: false)");
        System.out.println();

        titulo("35. Colisión — múltiples claves en mismo slot");
        TablaHash t2 = new TablaHash(3);
        System.out.println("  m=3 → h(k)=k%3   claves 0,3,6 → slot 0");
        t2.insert(0,10); t2.insert(3,30); t2.insert(6,60);
        System.out.println("  insert(0,10)  insert(3,30)  insert(6,60)");
        System.out.println("  search(0,10) = " + t2.search(0,10)
                + "  search(3,30) = " + t2.search(3,30)
                + "  search(6,60) = " + t2.search(6,60) + "  ✓");
        System.out.println();
    }
}