package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.IO;

class ComandoPosaTest {
	
	private Partita partita;
	private Comando comandoPosa;
	private IO io;
	
	@BeforeEach
	void setUp() {
		//per creare la partita ed il labirinto
		io = new IOConsole();
		partita = new Partita(io);
	}
	
	@Test
	void testComandoPosa() {
		//inserisco nella borsa l'attrezzo da posare
		Attrezzo attrezzo = new Attrezzo("torcia", 3);
		partita.getGiocatore().getBorsa().addAttrezzo(attrezzo);
		
		comandoPosa = new ComandoPosa("torcia", io);
		comandoPosa.esegui(partita);
		
		assertEquals(null, partita.getGiocatore().getBorsa().getAttrezzo("torcia"));
	}
	
	@Test
	void testComandoPosaNoAttrezzoNellaBorsa() {
		comandoPosa = new ComandoPosa("libro", io);
		comandoPosa.esegui(partita);
		
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("libro"));
	}
}
