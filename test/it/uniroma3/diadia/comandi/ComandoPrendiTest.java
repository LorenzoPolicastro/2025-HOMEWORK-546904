package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

class ComandoPrendiTest {
	
	private Partita partita;
	private Comando comandoPrendi;
	private IO io;
	
	@BeforeEach
	void setUp() {
		//per creare la partita ed il labirinto
		io = new IOConsole();;
		partita = new Partita(io);
	}
	
	/*Verifica che se l'attrezzo è presente nella stanza, è possibile prenderlo aggiungendolo alla borsa del giocatore
	 * 
	 */
	@Test
	void testComandoPrendi() {
		//inizialmente ci troviamo in Atrio, stanza nella quale si trova l'attrezzo "osso"
		comandoPrendi = new ComandoPrendi("osso", io);
		comandoPrendi.esegui(partita);
		
		//attrezzo osso presente nella borsa del giocatore
		assertTrue(partita.getGiocatore().getBorsa().hasAttrezzo("osso"));
	}
	
	/*Verifica che se l'attrezzo NON è presente nella stanza, 
	 * 
	 */
	@Test
	void testComandoPrendiAttrezzoAssente() {
		//inizialmente ci troviamo in Atrio, stanza nella quale Non è presente l'attrezzo "lanterna"
		comandoPrendi = new ComandoPrendi("lanterna", io);
		comandoPrendi.esegui(partita);
		
		//attrezzo osso presente nella borsa del giocatore
		assertFalse(partita.getGiocatore().getBorsa().hasAttrezzo("lanterna"));
	}
}
