package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaBloccataTest {
	
	private Stanza stanzaBloccata;
	private Stanza stanzaAdiacente;
	private Stanza stanzaAccedibile;
	private Attrezzo chiave;
	
	/*Per questa classe, i test sono tutti "bilocali" in quanto ho bisognio di una
	 * seconda stanza per verificare l'effettivo spostamento, avvenuto o meno,
	 * in base alla presenta dell'attrezzo sbloccante nella stanza bloccata.
	 */
	@BeforeEach
	void setUp() {
		chiave = new Attrezzo("chiave", 1);
		stanzaBloccata = new StanzaBloccata("Stanza Chiusa", "nord", "chiave");
		stanzaAdiacente = new Stanza("Stanza Adiacente");
		stanzaAccedibile = new Stanza("Stanza Accedibile");
		stanzaBloccata.impostaStanzaAdiacente("nord", stanzaAdiacente);
	}
	
	/*Verifica che, in presenza dell'attrezzo sbloccante nella stanza bloccata,
	 * il metodo getStanzaAdiacente() permetta di muoversi nella stanza adiacente
	 * alla direzione bloccata
	 */
	@Test
	void testStanzaBloccata_direzioneBloccataAccessibile() {
		stanzaBloccata.addAttrezzo(chiave); //aggiungo l'attrezzo sbloccante
		assertSame(stanzaAdiacente, stanzaBloccata.getStanzaAdiacente("nord"));
		
	}
	
	/*Verifica che, in assenza dell'attrezzo sbloccante nella stanza bloccata,
	 * il metodo getStanzaAdiacente() restituisca il riferimento alla stanza
	 * attuale, impedendo cosi lo spostamente nella stanza adiacente alla 
	 * direzione bloccata
	 */
	@Test
	void testStanzaBloccata_direzioneBloccataInaccessibile() {
		assertSame(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
	}

	/*Verifica che il metodo getStanzaAdiacente() mantenga il l'usuale comportamento
	 * per una direzione non bloccata della stanza bloccata.
	 */
	@Test
	void testStanzaBloccata_direzioneNonBloccata() {
		//imposto una stanza nella direzione non bloccata "sud" della stanza Bloccata
		stanzaBloccata.impostaStanzaAdiacente("sud", stanzaAccedibile);
		assertSame(stanzaAccedibile, stanzaBloccata.getStanzaAdiacente("sud"));
	}
}
