package structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColaTest {

    @Test
    void colaRecienCreadaEstaVacia() {
        Cola cola = new Cola(5);
        assertTrue(cola.queueEmpty());
    }

    @Test
    void enqueueAgregaElementoYActualizaSize() {
        Cola cola = new Cola(5);
        cola.enqueue(10);
        assertFalse(cola.queueEmpty());
        assertEquals(1, cola.getSize());
        assertEquals(10, cola.peek());
    }

    @Test
    void dequeueRetornaElPrimeroInsertadoFIFO() {
        Cola cola = new Cola(5);
        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        assertEquals(10, cola.dequeue());
        assertEquals(20, cola.dequeue());
        assertEquals(30, cola.dequeue());
        assertTrue(cola.queueEmpty());
    }

    @Test
    void peekNoModificaLaCola() {
        Cola cola = new Cola(5);
        cola.enqueue(42);
        assertEquals(42, cola.peek());
        assertEquals(1, cola.getSize());
    }

    @Test
    void enqueueDequeueCircularFuncionaBien() {
        Cola cola = new Cola(5);
        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);
        cola.enqueue(4);
        cola.enqueue(5);
        cola.dequeue();
        cola.enqueue(6);
        assertEquals(2, cola.peek());
        assertEquals(5, cola.getSize());
        assertEquals("Cola: [2, 3, 4, 5, 6]", cola.toString());
    }

    @Test
    void enqueueLanzaOverflowSiColaLlena() {
        Cola cola = new Cola(3);
        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);

        try {
            cola.enqueue(4);
            fail("Debió lanzar excepción por overflow");
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    void dequeueLanzaExcepcionSiColaVacia() {
        Cola cola = new Cola(5);

        try {
            cola.dequeue();
            fail("Debió lanzar excepción por underflow");
        } catch (java.util.NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    void peekLanzaExcepcionSiColaVacia() {
        Cola cola = new Cola(5);

        try {
            cola.peek();
            fail("Debió lanzar excepción porque la cola está vacía");
        } catch (java.util.NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    void toStringMuestraElementosEnOrdenFIFO() {
        Cola cola = new Cola(5);
        assertEquals("Cola: []", cola.toString());

        cola.enqueue(1);
        cola.enqueue(2);
        cola.enqueue(3);

        assertEquals("Cola: [1, 2, 3]", cola.toString());
    }
}