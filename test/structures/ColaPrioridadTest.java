package structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ColaPrioridadTest {

    @Test
    void colaRecienCreadaEstaVacia() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        assertTrue(cp.isEmpty());
        assertEquals(0, cp.getSize());
    }

    @Test
    void insertAgregaElementoYActualizaSize() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        cp.insert(3, "tarea-media");
        assertFalse(cp.isEmpty());
        assertEquals(1, cp.getSize());
    }

    @Test
    void insertVariosElementosActualizaSizeCorrectamente() {
        ColaPrioridad<Integer> cp = new ColaPrioridad<>(5);
        cp.insert(1, 100);
        cp.insert(3, 300);
        cp.insert(2, 200);
        assertEquals(3, cp.getSize());
    }

    @Test
    void insertLanzaExcepcionSiColaLlena() {
        ColaPrioridad<Integer> cp = new ColaPrioridad<>(3);
        cp.insert(1, 10);
        cp.insert(2, 20);
        cp.insert(3, 30);

        try {
            cp.insert(4, 40);
            fail("Debió lanzar excepción por overflow");
        } catch (RuntimeException e) {
            assertTrue(true);
        }
    }

    @Test
    void extractMaxRetornaElDeMayorPrioridad() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        cp.insert(1, "baja");
        cp.insert(5, "critica");
        cp.insert(3, "media");

        assertEquals("critica", cp.extractMax());
        assertEquals("media",   cp.extractMax());
        assertEquals("baja",    cp.extractMax());
        assertTrue(cp.isEmpty());
    }

    // el que llegó primero no es el que sale primero si tiene menor prioridad
    @Test
    void extractMaxNoEsFIFO() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        cp.insert(2, "llega-primero");
        cp.insert(9, "llega-segundo");

        assertEquals("llega-segundo", cp.extractMax());
    }

    @Test
    void extractMaxActualizaSizeCorrectamente() {
        ColaPrioridad<Integer> cp = new ColaPrioridad<>(5);
        cp.insert(4, 400);
        cp.insert(8, 800);
        cp.insert(2, 200);

        cp.extractMax();
        assertEquals(2, cp.getSize());

        cp.extractMax();
        assertEquals(1, cp.getSize());
    }

    @Test
    void extractMaxYReinsertaMantienePrioridades() {
        ColaPrioridad<Integer> cp = new ColaPrioridad<>(5);
        cp.insert(2, 200);
        cp.insert(8, 800);
        cp.insert(4, 400);

        assertEquals(800, cp.extractMax());

        cp.insert(10, 1000);

        assertEquals(1000, cp.extractMax());
        assertEquals(400,  cp.extractMax());
        assertEquals(200,  cp.extractMax());
        assertTrue(cp.isEmpty());
    }

    @Test
    void extractMaxLanzaExcepcionSiColaVacia() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);

        try {
            cp.extractMax();
            fail("Debió lanzar excepción por underflow");
        } catch (java.util.NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    void peekRetornaElDeMayorPrioridadSinRemover() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        cp.insert(1, "baja");
        cp.insert(7, "alta");
        cp.insert(4, "media");

        assertEquals("alta", cp.peek());
        assertEquals(3, cp.getSize());
    }

    @Test
    void peekNoModificaLaCola() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        cp.insert(4, "urgente");

        assertEquals("urgente", cp.peek());
        assertEquals("urgente", cp.peek());
        assertEquals(1, cp.getSize());
    }

    @Test
    void peekLanzaExcepcionSiColaVacia() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);

        try {
            cp.peek();
            fail("Debió lanzar excepción porque la cola está vacía");
        } catch (java.util.NoSuchElementException e) {
            assertTrue(true);
        }
    }

    @Test
    void insertConPrioridadesDuplicadasPermiteAmbosElementos() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        cp.insert(3, "tarea-A");
        cp.insert(3, "tarea-B");

        assertEquals(2, cp.getSize());
    }

    // con empate en prioridad basta con que salga uno de los dos
    @Test
    void extractMaxConPrioridadesDuplicadasRetornaUno() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        cp.insert(5, "X");
        cp.insert(5, "Y");
        cp.insert(1, "Z");

        String primero = cp.extractMax();
        assertTrue(primero.equals("X") || primero.equals("Y"));
        assertEquals(2, cp.getSize());
    }

    @Test
    void toStringColaVaciaMuestraCorchetes() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        assertEquals("ColaPrioridad: []", cp.toString());
    }

    @Test
    void toStringMuestraElementosOrdenadosPorPrioridad() {
        ColaPrioridad<String> cp = new ColaPrioridad<>(5);
        cp.insert(1, "baja");
        cp.insert(3, "media");
        cp.insert(5, "alta");

        assertEquals("ColaPrioridad: [(p=1,baja), (p=3,media), (p=5,alta)]",
                     cp.toString());
    }

    @Test
    void toStringTrasExtractMaxReflejaEstadoActual() {
        ColaPrioridad<Integer> cp = new ColaPrioridad<>(5);
        cp.insert(2, 20);
        cp.insert(4, 40);
        cp.insert(6, 60);

        cp.extractMax();

        assertEquals("ColaPrioridad: [(p=2,20), (p=4,40)]", cp.toString());
    }
}