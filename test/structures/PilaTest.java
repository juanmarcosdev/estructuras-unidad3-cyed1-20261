package structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class PilaTest {

    @Test
    void pilaRecienCreadaEstaVacia() {
        Pila pila = new Pila(5);
        assertTrue(pila.stackEmpty());
    }

    @Test
    void pushAgregaElementoYActualizaTop() {
        Pila pila = new Pila(5);
        pila.push(10);
        assertFalse(pila.stackEmpty());
        assertEquals(1, pila.getTop());
        assertEquals(10, pila.peek());
    }

    @Test
    void popRetornaElUltimoInsertadoLIFO() {
        Pila pila = new Pila(5);
        pila.push(10);
        pila.push(20);
        pila.push(30);
        assertEquals(30, pila.pop());
        assertEquals(20, pila.pop());
        assertEquals(10, pila.pop());
        assertTrue(pila.stackEmpty());
    }

    @Test
    void peekNoModificaLaPila() {
        Pila pila = new Pila(5);
        pila.push(42);
        assertEquals(42, pila.peek());
        assertEquals(1, pila.getTop());
    }

    @Test
    void pushLanzaOverflowSiPilaLlena() {
        Pila pila = new Pila(3);
        pila.push(1);
        pila.push(2);
        pila.push(3);
        try {
            pila.push(4);
            fail("Debió lanzar StackOverflowError");
        } catch (StackOverflowError e) {
            assertTrue(true);
        }
    }

    @Test
    void popLanzaExcepcionSiPilaVacia() {
        Pila pila = new Pila(5);
        try {
            pila.pop();
            fail("Debió lanzar EmptyStackException");
        } catch (java.util.EmptyStackException e) {
            assertTrue(true);
        }
    }

    @Test
    void peekLanzaExcepcionSiPilaVacia() {
        Pila pila = new Pila(5);
        try {
            pila.peek();
            fail("Debió lanzar EmptyStackException");
        } catch (java.util.EmptyStackException e) {
            assertTrue(true);
        }
    }

    @Test
    void toStringMuestraElementosCorrectamente() {
        Pila pila = new Pila(5);
        assertEquals("Pila: []", pila.toString());
        pila.push(1);
        pila.push(2);
        assertTrue(pila.toString().contains("1") && pila.toString().contains("2"));
    }
}