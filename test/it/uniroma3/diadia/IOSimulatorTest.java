package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

/**
 * I test si svolgono nel seguente modo:
 * 
 * Viene "caricata" una sequenza di comandi che dovranno essere eseguiti
 * Successivamente viene eseguito il metodo gioca() per avviare la partita
 * Tale metodo seguirà il percorso dettato dalla sequenza dei comandi che abbiamo prestabilito
 * I messaggi prodotti durante l'esecuzione verranno memorizzati attraverso il metodo mostraMessaggio()
 * 
 * Utilizzeremo altri metodi della classe IOSimulator per cercare determinati messaggi
 * prodotti o non prodotti durante l'esecuzione, in modo da verificare se il test ha
 * raggiunto l'obiettivo desiderato.
 * 
 * Per i test che non causano automaticamente la fine della partita, 
 * aggiungere alla fine dell'elenco dei comandi da far eseguire il comando "fine". 
 * Questo serve ad evitare che il test vada in errore.
 */
class IOSimulatorTest {
	
	private IOSimulator ioSimulator;
	private DiaDia diadia;
	
	@BeforeEach
	void setUp() {
		ioSimulator = new IOSimulator();
		diadia = new DiaDia(ioSimulator);
	}
	
	/**
	 * Verifica che il metodo leggiRiga() restituisca il prossimo comando 
	 * presente nella lista dei comandi
	 */
	@Test
	void testLeggiRiga() {
		ioSimulator.aggiungiComando("vai nord");
		
		assertEquals("vai nord", ioSimulator.leggiRiga());
	}
	
	/**
	 * Verifica che il metodo mostraMessaggio() memorizzi il messaggio prodotto dall'esecuzione
	 */
	@Test
	void testMostraMessaggio() {
		ioSimulator.mostraMessaggio("Bella partita");
		assertTrue(ioSimulator.cercaMessaggio("Bella partita"));
	}
	
	/**
	 * Simulazione di una partita che termina con una vittoria
	 */
	@Test
	void testPartitaVincente() {
		//carico l'elenco di comandi da eseguire nella lista del simulatore
		String[] elencoComandi = {"vai sud", "prendi lanterna", "vai nord", "vai est", "vai sud", "posa lanterna", "prendi chiave", "vai nord", "vai ovest", "posa chiave", "vai nord"};
		for(String comando : elencoComandi) {
			ioSimulator.aggiungiComando(comando);
		}
		
		//avvio la partita
		diadia.gioca();
		
		//cerco se nei messaggi memorizzati è presente il messaggio "Hai vinto!"
		assertTrue(ioSimulator.cercaMessaggio("Hai vinto!"));
	}
	
	/**
	 * Simulazione di una partita che termina con una Sconfitta
	 */
	@Test
	void testPartitaPersa() {
		//carico l'elenco di comandi inserendo 20 volte il comando "vai nord" in modo da esaurire i CFU
		String comando = "vai nord";
		for(int i = 0; i<20; i++) {
			ioSimulator.aggiungiComando(comando);
		}
		
		//avvio la partita
		diadia.gioca();
		
		assertTrue(ioSimulator.cercaMessaggio("Hai perso!"));
	}
	
	/**
	 * Simulazione del tentativo di sostarsi verso una direzione di cui la stanza attuale non dispone
	 */
	@Test
	void testDirezioneNonDisponibile() {
		ioSimulator.aggiungiComando("vai est");
		ioSimulator.aggiungiComando("vai nord");
		ioSimulator.aggiungiComando("fine");
		
		//avvio la partita
		diadia.gioca();
		
		assertTrue(ioSimulator.cercaMessaggio("Direzione inesistente"));
	}
	
	/**
	 * Simulazione dell'immissione di un comando sconosciuto
	 */
	@Test
	void testComandoSconosciuto() {
		//carico l'elenco di comandi da eseguire nella lista del simulatore
		ioSimulator.aggiungiComando("cerca");
		ioSimulator.aggiungiComando("fine");
		
		//avvio la partita
		diadia.gioca();
		
		assertTrue(ioSimulator.cercaMessaggio("Comando sconosciuto!"));
	}
	
	/**
	 * Simulazione dell'immissione di nessun comando da parte dell'utente
	 */
	@Test
	void testNessunComandoImmesso() {
		//carico l'elenco di comandi da eseguire nella lista del simulatore
		ioSimulator.aggiungiComando(""); //viene dato Invio senza scrivere il comando
		ioSimulator.aggiungiComando("fine");
		
		//avvio la partita
		diadia.gioca();
		
		assertTrue(ioSimulator.cercaMessaggio("Comando sconosciuto!"));
	}
	
	/**
	 * Simulazione del tentativo di guradare gli attrezzi presenti in una stanza Buia non illuminata
	 */
	@Test
	void testStanzaBuia() {
		//carico l'elenco di comandi da eseguire nella lista del simulatore
		ioSimulator.aggiungiComando("vai est");
		ioSimulator.aggiungiComando("vai sud"); //istruzioni per raggiungere AluaN12, che è la stanza Buia
		
		ioSimulator.aggiungiComando("guarda");
		ioSimulator.aggiungiComando("fine");
		
		//avvio la partita
		diadia.gioca();
		
		assertTrue(ioSimulator.cercaMessaggio("qui c'è buio pesto"));
	}
}
