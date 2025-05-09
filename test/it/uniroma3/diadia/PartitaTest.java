package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class PartitaTest {
	
	private Partita partita;
	private IO io;
	
	@BeforeEach
	void setUpPartita() {
		/* Le stanze Corrente e Vincente sono precedentemente impostate al momento della definizione
		 * della Partita tramite il metodo creaStanze
		 */
		io = new IOConsole();
		partita = new Partita(io);
	}
	
	/* Test su impostazione della stanza corrente diversa dalla stanza Vincente
	 */
	@Test
	void testSetStanzaCorrente() {
		assertFalse(partita.vinta());
	}
	
	/* Test su impostazione della stanza corrente uguale dalla stanza Vincente
	 */
	@Test
	void testSetStanzaCorrenteUgualeVincente() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		
		assertTrue(partita.vinta());
	}
	
	/* Test su impostazione della stanza corrente con valore nullo
	 */
	@Test
	void testSetStanzaCorrenteNull() {
		partita.setStanzaCorrente(null);
		assertFalse(partita.vinta());
	}
	
	/* Test su termine della partita per esaurimenti dei cfu
	 * Attenzione: questo metodo non fa più parte della classe Partita, è ora responsabilità della classe Giocatore
	 */
	/*@Test
	void testIsFinitaCfuZero() {
		partita.setCfu(0);
		assertTrue(partita.isFinita());
	}
	*/
	/* Test su termine della partita perchè vinta
	 */
	@Test
	void testIsFinitaVinta() {
		partita.setStanzaCorrente(partita.getStanzaVincente());
		
		assertTrue(partita.isFinita());
	}
	
	/* Test su termine della partita perchè terminata prematuramente
	 */
	@Test
	void testIsFinitaTerminata() {
		partita.setFinita();
		assertTrue(partita.isFinita());
	}
}
