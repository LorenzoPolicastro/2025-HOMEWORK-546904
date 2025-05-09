package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

class ComandoVaiTest {
	
	private Partita partita;
	private Comando comandoVai;
	private IO io;

	@BeforeEach
	void setUp() {
		//per creare la partita ed il labirinto
		io = new IOConsole();;
		partita = new Partita(io);
	}
	
	/*Verifica che se la stanza ha direzione valida, il giocatore si sposta nella stanza corrispondente alla direzione
	 * 
	 */
	@Test
	void testComandoVaiDirezioneValida() {
		comandoVai = new ComandoVai("nord", io);
		comandoVai.esegui(partita);
		
		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}
	
	/*Verifica che se la stanza NON HA direzione valida, il giocatore NON si sposta in quella direzione e rimane nella stanza corrente
	 * 
	 */
	@Test
	void testComandoVaiDirezioneNonValida() {
		//imposto la stanza corrente du bibblioteca, la quale non ha "est" come direzione valida
		partita.setStanzaCorrente(partita.getStanzaVincente());
		
		comandoVai = new ComandoVai("nord", io);
		comandoVai.esegui(partita);
		
		//verifico che la stanza corrente non sia cambiata
		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}
}
