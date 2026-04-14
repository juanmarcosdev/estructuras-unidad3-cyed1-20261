package structures;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TablaHashTest {

    @Test
    void tablaNuevaTieneElTamanioCorrecto() throws Exception {
        TablaHash tabla = new TablaHash(7);
        assertEquals(7, tabla.getSize());
    }

    @Test
    void tablaVaciaNOEncuentraNingunValor() throws Exception {
        TablaHash tabla = new TablaHash(10);
        assertFalse(tabla.search(3, 30));
    }


    //insert y search

    @Test
    void searchEncuentraValorRecienInsertado() throws Exception {
        TablaHash tabla = new TablaHash(10);
        tabla.insert(5, 50);
        assertTrue(tabla.search(5, 50));
    }

    @Test
    void searchNoEncuentraValorDistintoEnMismaLlave() throws Exception {
        TablaHash tabla = new TablaHash(10);
        tabla.insert(5, 50);
        assertFalse(tabla.search(5, 99));
    }

    @Test
    void searchNoEncuentraLlaveDistintaConMismoValor() throws Exception {
        TablaHash tabla = new TablaHash(10);
        tabla.insert(5, 50);
        assertFalse(tabla.search(9, 50));
    }

    @Test
    void insertVariosValoresEnDiferentesSlots() throws Exception {
        TablaHash tabla = new TablaHash(10);
        tabla.insert(1, 10);
        tabla.insert(2, 20);
        tabla.insert(3, 30);
        assertTrue(tabla.search(1, 10));
        assertTrue(tabla.search(2, 20));
        assertTrue(tabla.search(3, 30));
    }

    //Colisiones 

    @Test
    void dosLlavesColisionanEnMismoSlot() throws Exception {
        // m=5 → h(2)=2, h(7)=2 → COLISIÓn!!
        TablaHash tabla = new TablaHash(5);
        tabla.insert(2, 100);
        tabla.insert(7, 200);
        assertTrue(tabla.search(2, 100));
        assertTrue(tabla.search(7, 200));
    }

    @Test
    void multiplesColisionesEnUnSlot() throws Exception {
        // m=3 = claves 0,3,6,9 = todas al MISMO SLOT: 0
        TablaHash tabla = new TablaHash(3);
        tabla.insert(0, 10);
        tabla.insert(3, 30);
        tabla.insert(6, 60);
        tabla.insert(9, 90);
        assertTrue(tabla.search(0, 10));
        assertTrue(tabla.search(3, 30));
        assertTrue(tabla.search(6, 60));
        assertTrue(tabla.search(9, 90));
    }

    @Test
    void colisionNoContaminaValoresDeOtraLlave() throws Exception {
        TablaHash tabla = new TablaHash(5);
        tabla.insert(2, 100);
        tabla.insert(7, 200);
        assertFalse(tabla.search(2, 200));
        assertFalse(tabla.search(7, 100));
    }

    // insert — duplicados

    @Test
    void insertDuplicadoPermiteRepetidosEnSlot() throws Exception {
        TablaHash tabla = new TablaHash(10);
        tabla.insert(4, 40);
        tabla.insert(4, 40);   //mismo par dos veces
        assertTrue(tabla.search(4, 40));
        //elk segundo debe seguir
        tabla.delete(4, 40);
        assertTrue(tabla.search(4, 40));
    }


    @Test
    void deleteEliminaElValorIndicado() throws Exception {
        TablaHash tabla = new TablaHash(10);
        tabla.insert(6, 60);
        tabla.delete(6, 60);
        assertFalse(tabla.search(6, 60));
    }

    @Test
    void deleteDejaIntactosOtrosValoresDelMismoSlot() throws Exception {
        // m=5 → h(1)=1, h(6)=1,  Colisión!!
        TablaHash tabla = new TablaHash(5);
        tabla.insert(1, 10);
        tabla.insert(6, 60);
        tabla.delete(1, 10);
        assertFalse(tabla.search(1, 10));
        assertTrue(tabla.search(6, 60));
    }

    @Test
    void deleteEnSlotVacioNoLanzaExcepcion() throws Exception {
        TablaHash tabla = new TablaHash(10);
        //slot 5 NO usado
        assertDoesNotThrow(() -> tabla.delete(5, 50));
    }

    @Test
    void deleteDeValorAusenteNoLanzaExcepcion() throws Exception {
        TablaHash tabla = new TablaHash(10);
        tabla.insert(3, 30);
        //99 no existe en ese slot
        assertDoesNotThrow(() -> tabla.delete(3, 99));
        // el valor original sigue intacto
        assertTrue(tabla.search(3, 30));
    }

    // hashFunction h(k) = k % m
    @Test
    void hashFunctionDistribuyeCorrectamente() throws Exception {
        // m=7: h(7)=0, h(8)=1, h(13)=6
        TablaHash tabla = new TablaHash(7);
        tabla.insert(7,  70);
        tabla.insert(8,  80);
        tabla.insert(13, 130);
        assertTrue(tabla.search(7,  70));
        assertTrue(tabla.search(8,  80));
        assertTrue(tabla.search(13, 130));
    }

    @Test
    void llaveMultiploDeMNoColisionaConOtrasLlaves() throws Exception {
        // m=10: h(10)=0, h(20)=0, h(1)=1
        TablaHash tabla = new TablaHash(10);
        tabla.insert(10, 100);
        tabla.insert(20, 200);
        tabla.insert(1,  10);
        assertTrue(tabla.search(10, 100));
        assertTrue(tabla.search(20, 200));
        assertTrue(tabla.search(1,  10));
        assertFalse(tabla.search(10, 200));
        assertFalse(tabla.search(20, 100));
    }
}