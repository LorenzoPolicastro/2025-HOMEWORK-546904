package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {
	
	private Stanza stanza1;
	
	@BeforeEach
	void setUpStanza(){
		stanza1 = new Stanza("Ingresso");
		
	}

	/* Test su impostazione di una stanza adiacente ad una stanza che 
	 * inizialmente non ha stanze adiacenti
	 */
	@Test
	void testImpostaStanzaAdiacente() {
		Stanza stanza2 = new Stanza("Soggiorno");
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		
		assertSame(stanza2, stanza1.getStanzaAdiacente("nord"));
	}
	
	/* Test su impostazione di un'ulteriore stanza adiacente ad una stanza che 
	 * inizialmente ha una stanza adiacente esistente
	 */
	@Test
	void testImpostaStanzaAdiacenteAggiunta() {

		//stanza adiacente precedentemente impostata
		Stanza stanza2 = new Stanza("Soggiorno");
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		
		//impostazione nuova stanza adiacente
		Stanza stanza3 = new Stanza("Bagno");
		stanza1.impostaStanzaAdiacente("ovest", stanza3); 
		
		assertSame(stanza3, stanza1.getStanzaAdiacente("ovest"));
	}
	
	/* Test su sostituzione di una stanza adiacente esistente
	 */
	@Test
	void testImpostaStanzaAdiacenteSostituita() {

		//stanze adiacenti precedentemente impostate
		Stanza stanza2 = new Stanza("Soggiorno");
		stanza1.impostaStanzaAdiacente("nord", stanza2);
		
		//sostituisce stanza adiacente già esistente con una nuova stanza adiacente
		Stanza stanza3 = new Stanza("Bagno");
		stanza1.impostaStanzaAdiacente("nord", stanza3); 
		
		assertSame(stanza3, stanza1.getStanzaAdiacente("nord"));
	}
	
	/* Test su recupero di un attrezzo in una stanza
	 */
	@Test
	void testGetAttrezzo() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		assertSame(attrezzo1,stanza1.getAttrezzo("Torcia"));
	}
	
	/* Test su esistenza di un attrezzo non presente in una stanza
	 */
	@Test
	void testGetAttrezzoAssente() {
			
		assertSame(null,stanza1.getAttrezzo("Torcia"));
	}
	
	/* Test su recupero di un attrezzo in una stanza
	 */
	@Test
	void testGetAttrezzoVersione2() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		assertSame(attrezzo1,stanza1.getAttrezzoVersione2("Torcia"));
	}
	
	/* Test su esistenza di un attrezzo non presente in una stanza
	 */
	@Test
	void testGetAttrezzoAssenteVersione2() {
			
		assertSame(null,stanza1.getAttrezzoVersione2("Torcia"));
	}
	
	/* Test su esistenza di un attrezzo in una stanza
	 */
	@Test
	void testHasAttrezzo() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		
		stanza1.addAttrezzo(attrezzo1);
		
		assertTrue(stanza1.hasAttrezzo("Torcia"));
	}
	
	/* Test su esistenza di un attrezzo in una stanza che contiene più attrezzi
	 */
	@Test
	void testHasAttrezzoPiuAttrezzi() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		Attrezzo attrezzo2 = new Attrezzo("Batteria", 1);
		stanza1.addAttrezzo(attrezzo2);
		
		assertTrue(stanza1.hasAttrezzo("Batteria"));
	}
	
	/* Test su assenza di un attrezzo in una stanza
	 */
	@Test
	void testHasAttrezzoAssente() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		assertFalse(stanza1.hasAttrezzo("Batteria"));
	}
	
	/* Test su assenza di un attrezzo in una stanza con più attrezzi
	 */
	@Test
	void testHasAttrezzoAssentePiuAttrezzi() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		Attrezzo attrezzo2 = new Attrezzo("Batteria", 1);
		stanza1.addAttrezzo(attrezzo2);
		
		assertFalse(stanza1.hasAttrezzo("Telecamera"));
	}
	
	/* Test su instanza del metodo con parametro nullo
	 */
	@Test
	void testHasAttrezzoNullo() {
		assertTrue(stanza1.addAttrezzo(null));
		
	}
	
	/* Test su esistenza di un attrezzo in una stanza richiamando hasAttrezzoVersione2
	 */
	@Test
	void testHasAttrezzoVersione2() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		
		stanza1.addAttrezzo(attrezzo1);
		
		assertTrue(stanza1.hasAttrezzoVersione2("Torcia"));
	}
	
	/* Test su esistenza di un attrezzo in una stanza che contiene più attrezzi richiamando hasAttrezzoVersione2
	 */
	@Test
	void testHasAttrezzoVersione2PiuAttrezzi() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		Attrezzo attrezzo2 = new Attrezzo("Batteria", 1);
		stanza1.addAttrezzo(attrezzo2);
		
		assertTrue(stanza1.hasAttrezzoVersione2("Batteria"));
	}
	
	/* Test su assenza di un attrezzo in una stanza richiamando hasAttrezzoVersione2
	 */
	@Test
	void testHasAttrezzoVersione2Assente() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		assertFalse(stanza1.hasAttrezzoVersione2("Batteria"));
	}
	
	/* Test su assenza di un attrezzo in una stanza con più attrezzi richiamando hasAttrezzoVersione2
	 */
	@Test
	void testHasAttrezzoVersione2AssentePiuAttrezzi() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		Attrezzo attrezzo2 = new Attrezzo("Batteria", 1);
		stanza1.addAttrezzo(attrezzo2);
		
		assertFalse(stanza1.hasAttrezzoVersione2("Telecamera"));
	}
	
	/*
	 * Test rimozione di un attrezzo dalla stanza
	 * @Test
	void testRemoveAttrezzo() {
		Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
		stanza1.addAttrezzo(attrezzo1);
		
		stanza1.removeAttrezzo(attrezzo1);
		
		assertFalse(stanza1.hasAttrezzoVersione2("Torcia"));
	}
	
	/* Test rimozione di un attrezzo non presente nella stanza
			@Test
			void testRemoveAttrezzoAssente() {
				Attrezzo attrezzo1 = new Attrezzo("Torcia", 2);
				
				stanza1.removeAttrezzo(attrezzo1);
				
				assertFalse(stanza1.hasAttrezzoVersione2("Torcia"));
			}

	 */
	
}
