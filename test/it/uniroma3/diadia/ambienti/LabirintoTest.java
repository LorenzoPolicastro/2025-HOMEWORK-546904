package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.*; 

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

class LabirintoTest {
	
	private Labirinto labirinto;
	private IO io;
	
	@BeforeEach
    void setUp() {
		io = new IOConsole();
        labirinto = new Labirinto(io);
    }
	
	/*Test che verifica se la stanza Iniziale sia "Atrio", come definito dal metodo
	 * 
	 */
	@Test
    void testGetStanzaIniziale() {
        assertEquals("Atrio", labirinto.getStanzaIniziale().getNome());
    }
	
	/*Test che verifica se la stanza Vincente sia "Biblioteca", come definito dal metodo
	 * 
	 */
	@Test
	void testGetStanzaVincente() {
		assertEquals("Biblioteca", labirinto.getStanzaVincente().getNome());
	}
	
	/*Test che verifica se la stanza Vincente sia collegata alla stanza Iniziale
	 * 
	 */
	@Test
	void testCollegamentoInizialeVincente() {
		Stanza atrio = labirinto.getStanzaIniziale();
	    Stanza biblioteca = labirinto.getStanzaVincente();
		assertEquals(biblioteca, atrio.getStanzaAdiacente("nord"));
	}
	
	/*Test che verifica se la stanza Iniziale sia collegata alla stanza Vincente
	 * 
	 */
	@Test
	void testCollegamentoVincenteIniziale() {
		Stanza atrio = labirinto.getStanzaIniziale();
	    Stanza biblioteca = labirinto.getStanzaVincente();
		assertEquals(atrio, biblioteca.getStanzaAdiacente("sud"));	
	}
	
	/*Test che verifica se in una specifica stanza sia presente un attrezzo
	 * 
	 */
	@Test
	void testAttrezzoNellaStanza() {
		Stanza aulaN10 = labirinto.getStanzaIniziale().getStanzaAdiacente("sud");
        
        assertTrue(aulaN10.hasAttrezzo("lanterna"));
	}
	
	/*Test che verifica se in una specifica stanza non sia presente uno specifico attrezzo
	 * 
	 */
	@Test
	void testAttrezzoAssenteNellaStanza() {
		Stanza aulaN10 = labirinto.getStanzaIniziale().getStanzaAdiacente("sud");
        
        assertFalse(aulaN10.hasAttrezzo("osso"));
	}
	
}
