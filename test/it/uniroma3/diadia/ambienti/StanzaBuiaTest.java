package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import it.uniroma3.diadia.attrezzi.Attrezzo;



class StanzaBuiaTest {
	
	private Stanza stanzaBuia;
	private Attrezzo lanterna;
	
	/*Per questa classe, i test sono tutti "monolocali" in quanto non ho bisogno
	 * di istanziare altre stanze per verificare il comportamento di una stanza buia.
	 */
	@BeforeEach
	void setUp() {
		lanterna = new Attrezzo("lanterna", 2);
		stanzaBuia = new StanzaBuia("Stanza Oscura","lanterna");
	}
	
	/*Verifica che se è presente l'attrezzo illuminante nella stanza buia,
	 * il metodo getDescrizione() restituisce una descrizione diversa da "qui c'è buio pesto"
	 */
	@Test
	void testStanzaBuia_Illuminata() {
		stanzaBuia.addAttrezzo(lanterna);
		assertFalse(stanzaBuia.getDescrizione().equals("qui c'è buio pesto"));
	}
	
	/*Verifica che se l'attrezzo illuminante non si torva nella stanza buia,
	 * il metodo getDescrizione() restituisce la frase "qui c'è buio pesto"
	 */
	@Test
	void testStanzaBuia_NonIlluminata() {
		assertEquals("qui c'è buio pesto", stanzaBuia.getDescrizione());
	}

}
