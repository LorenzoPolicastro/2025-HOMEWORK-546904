package it.uniroma3.diadia.giocatore;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	
	private Giocatore giocatore;
	
	@BeforeEach
    void setUp() {
        this.giocatore = new Giocatore(20, 10); // 20 CFU, borsa da 10kg
    }
	
	/*Test sul valore corrente dei CFU del giocatore
	 * 
	 */
	@Test
    void testGetCfu() {
        assertEquals(20, giocatore.getCfu());
    }
	
	/*Test sul valore corrente dei CFU del giocatore dopo averlo impostato
	 * 
	 */
	@Test
	void testSetCfu() {
        giocatore.setCfu(15);
        assertEquals(15, giocatore.getCfu());
    }
	
	/*Test sulla memorizzazione di un attrezzo nella borsa del giocatore
	 * 
	 */
	@Test
    void testPrendiAttrezzo() {
		Attrezzo lanterna = new Attrezzo("lanterna",3);
        assertTrue(giocatore.prendiAttrezzo(lanterna));
	}
	
	/*Test sulla memorizzazione di un attrezzo nella borsa del giocatore, con la stanza che contiene più attrezzi
	 * 
	 */
	@Test
    void testPrendiAttrezzoPiuAttrezzi() {
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
        assertTrue(giocatore.prendiAttrezzo(osso));
	}
	
	
	/*Test sul rilascio di un attrezzo dalla borsa del giocatore
	 * 
	 */
	@Test
    void testPosaAttrezzo() {
		Attrezzo lanterna = new Attrezzo("lanterna",3);
        giocatore.prendiAttrezzo(lanterna);
        Attrezzo rilasciato = giocatore.posaAttrezzo("lanterna");
        assertEquals(lanterna, rilasciato);
	}
	
	/*Test sul rilascio di un attrezzo dalla borsa del giocatore
	 * 
	 */
	@Test
    void testPosaAttrezzoPiuAttrezzi() {
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
        giocatore.prendiAttrezzo(lanterna);
        giocatore.prendiAttrezzo(osso);
        Attrezzo rilasciato = giocatore.posaAttrezzo("lanterna");
        assertEquals(lanterna, rilasciato);
	}
	
}
