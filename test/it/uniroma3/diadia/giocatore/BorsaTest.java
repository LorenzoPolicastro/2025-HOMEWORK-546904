package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class BorsaTest {
	
	private Borsa borsa;
	private Attrezzo lanterna;
    private Attrezzo osso;
	
	@BeforeEach
    void setUp() {
        this.borsa = new Borsa(); // Capacità 10kg di default
        this.lanterna = new Attrezzo("lanterna", 3);
        this.osso = new Attrezzo("osso", 1);
    }
	
	/*Test su aggiunta di un attrezzo alla borsa inizialmente vuota del giocatore
	 * 
	 */
	@Test
    void testAddAttrezzo() {
        assertTrue(borsa.addAttrezzo(lanterna));
    }
	
	/*Test su aggiunta di un attrezzo eccessivamente pesante 
	 * alla borsa inizialmente vuota del giocatore
	 */
	@Test
	void testAddAttrezzoPesoEccessivo() {
        Attrezzo incudine = new Attrezzo("incudine", 20);
        assertFalse(borsa.addAttrezzo(incudine));
    }
	
	
	/*Test restituzione di un attrezzo presente nella borsa
	 */
	@Test
    void testGetAttrezzo() {
        borsa.addAttrezzo(lanterna);
        assertEquals(lanterna, borsa.getAttrezzo("lanterna"));
    }
	
	/*Test restituzione di un attrezzo presente nella borsa che contiene più attrezzi
	 */
	@Test
    void testGetAttrezzoPiuAttrezzi() {
        borsa.addAttrezzo(lanterna);
        borsa.addAttrezzo(osso);
        assertEquals(lanterna, borsa.getAttrezzo("lanterna"));
    }
	
	/*Test restituzione di un attrezzo assente nella borsa
	 */
	@Test
    void testGetAttrezzoAssente() {
        borsa.addAttrezzo(lanterna);
        assertEquals(null, borsa.getAttrezzo("osso"));
    }
	
	/*Test presenza di un attrezzo specifico nella borsa 
	 * 
	 */
	@Test
    void testHasAttrezzo() {
        borsa.addAttrezzo(lanterna);
        assertTrue(borsa.hasAttrezzo("lanterna"));
    }
	
	/*Test presenza di un attrezzo specifico nella borsa che contiene più attrezzi
	 * 
	 */
	@Test
    void testHasAttrezzoPiuAttrezzi() {
        borsa.addAttrezzo(lanterna);
        borsa.addAttrezzo(osso);
        assertTrue(borsa.hasAttrezzo("lanterna"));
	}
	
	/*Test assenza di un attrezzo specifico nella borsa 
	 * 
	 */
	@Test
    void testHasAttrezzoAssente() {
        borsa.addAttrezzo(lanterna);
        assertFalse(borsa.hasAttrezzo("osso"));
	}
	
	/*Test su rimozione di un attrezzo dalla borsa 
	 */
	@Test
	void testRemoveAttrezzo() {
		borsa.addAttrezzo(lanterna);
		borsa.removeAttrezzo("lanterna");
		assertFalse(borsa.hasAttrezzo("lanterna"));
    }
	
	/*Test su rimozione di un attrezzo dalla borsa che contiene più attrezzi
	 */
	@Test
	void testRemoveAttrezzoPiuAttrezzi() {
		borsa.addAttrezzo(lanterna);
		borsa.addAttrezzo(osso);
		borsa.removeAttrezzo("lanterna");
		assertFalse(borsa.hasAttrezzo("lanterna"));
    }
	
	
	/*Test su metodo getPeso() che restituisce il peso totale degli attrezzi contenuti nella borsa
	 * 
	 */
	@Test
    void testGetPeso() {
        borsa.addAttrezzo(lanterna);
        borsa.addAttrezzo(osso);
        assertEquals(4, borsa.getPeso());
    }
	
	/*Test che verifica che la borsa sia vuota
	 * 
	 */
	@Test
    void testIsEmpty() {
        assertTrue(borsa.isEmpty());
    }
	
	/*Test che verifica che la borsa non sia vuota
	 * 
	 */
	@Test
    void testIsNotEmpty() {
		borsa.addAttrezzo(lanterna);
        assertFalse(borsa.isEmpty());
    }
}
