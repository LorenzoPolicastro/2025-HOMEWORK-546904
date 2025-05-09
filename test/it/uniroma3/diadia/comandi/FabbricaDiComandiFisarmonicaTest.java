package it.uniroma3.diadia.comandi;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.FabbricaDiComandi;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandi factory;
	private Comando comandoDaEseguire;
	private IO io;
	
	@BeforeEach
	void setUp() {
		io = new IOConsole();
		factory = new FabbricaDiComandiFisarmonica(io);
	}
	
	/*Verifica che il comando "vai" venga riconosciuto
	 * 
	 */
	@Test
	void testRiconoscimentoComandoVai() {
		comandoDaEseguire = factory.costruisciComando("vai");
		
		assertEquals("vai", comandoDaEseguire.getNome());
	}
	
	/*Verifica che il comando "vai" ed il parametro "nord" vengano riconosciuti
	 * 
	 */
	@Test
	void testRiconoscimentoComandoVai_Parametro() {
		comandoDaEseguire = factory.costruisciComando("vai nord");
		
		assertEquals("nord", comandoDaEseguire.getParametro());
	}
	
	/*Verifica che il comando "aiuto" venga riconosciuto
	 * 
	 */
	@Test
	void testRiconoscimentoComandoAiuto() {
		comandoDaEseguire = factory.costruisciComando("aiuto");
		
		assertEquals("aiuto", comandoDaEseguire.getNome());
	}
	
	/*Verifica che il comando "fine" venga riconosciuto
	 * 
	 */
	@Test
	void testRiconoscimentoComandoFine() {
		comandoDaEseguire = factory.costruisciComando("fine");
		
		assertEquals("fine", comandoDaEseguire.getNome());
	}
	
	/*Verifica che il comando "guarda" venga riconosciuto
	 * 
	 */
	@Test
	void testRiconoscimentoComandoGuarda() {
		comandoDaEseguire = factory.costruisciComando("guarda");
		
		assertEquals("guarda", comandoDaEseguire.getNome());
	}
	
	/*Verifica che il comando "inventario" venga riconosciuto
	 * 
	 */
	@Test
	void testRiconoscimentoComandoInventario() {
		comandoDaEseguire = factory.costruisciComando("inventario");
		
		assertEquals("inventario", comandoDaEseguire.getNome());
	}
	
	/*Verifica venga richiamata la classe ComandoNonValido se inserito un comando
	 * non compreso tra quelli validi definiti fino ad ora
	 */
	@Test
	void testRiconoscimentoComandoNonValido() {
		comandoDaEseguire = factory.costruisciComando("cerca");
		
		assertEquals("comandoNonValido", comandoDaEseguire.getNome());
	}
	
	/*Verifica venga richiamata la classe ComandoNonValido se inserito un comando
	 * non compreso tra quelli validi definiti fino ad ora
	 */
	@Test
	void testRiconoscimentoComandoNonValido_inputNull() {
		comandoDaEseguire = factory.costruisciComando("");
		
		assertEquals("comandoNonValido", comandoDaEseguire.getNome());
	}
	
	/*Verifica che il comando "prendi" venga riconosciuto
	 * 
	 */
	@Test
	void testRiconoscimentoComandoPrendi() {
		comandoDaEseguire = factory.costruisciComando("prendi");
		
		assertEquals("prendi", comandoDaEseguire.getNome());
	}
	
	/*Verifica che il comando "vai" ed il parametro "nord" vengano riconosciuti
	 * 
	 */
	@Test
	void testRiconoscimentoComandoPrendi_Parametro() {
		comandoDaEseguire = factory.costruisciComando("prendi forbici");
		
		assertEquals("forbici", comandoDaEseguire.getParametro());
	}
	
	/*Verifica che il comando "prendi" venga riconosciuto
	 * 
	 */
	@Test
	void testRiconoscimentoComandoPosa() {
		comandoDaEseguire = factory.costruisciComando("posa");
		
		assertEquals("posa", comandoDaEseguire.getNome());
	}
	
	/*Verifica che il comando "vai" ed il parametro "nord" vengano riconosciuti
	 * 
	 */
	@Test
	void testRiconoscimentoComandoPosa_Parametro() {
		comandoDaEseguire = factory.costruisciComando("posa forbici");
		
		assertEquals("forbici", comandoDaEseguire.getParametro());
	}
}
