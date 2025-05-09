package it.uniroma3.diadia;

import java.util.*;
/**
 * Classe IOSimulator - implementa l'interfaccia IO per simulare l'input/output
 * durante i test automatici. Memorizza i messaggi mostrati e permette di
 * iniettare righe di comando da "leggere".
 * 
 * @author Lorenzo Policastro
 * @version 1.0
 */
public class IOSimulator implements IO{
	private List<String> messaggiProdotti;
	private List<String> comandiDaLeggere;
	private int indiceComando;
	
	public IOSimulator() {
		this.messaggiProdotti = new ArrayList<String>();
		this.comandiDaLeggere = new ArrayList<String>();
		this.indiceComando = 0;
	}
	
	/**
	 * Simula la scrittura di un messaggio in output
	 * @param messaggio il messaggio da scrivere in output
	 */
	@Override
	public void mostraMessaggio(String messaggio) {
		this.messaggiProdotti.add(messaggio);
	}
	
	/**
	 * Simula l'immissione di un comando in input dall'utente
	 * @return comando il prossimo comando da immettere in input al programma,
	 * 			se non rimangono comandi di immettere ritorna null
	 */
	@Override
	public String leggiRiga() {
		if(indiceComando < comandiDaLeggere.size()) {
			 return comandiDaLeggere.get(indiceComando++);
		}
		
		return null;
	}
	
	/**
	 * Permette di aggiungere un altro comando alla lista di comandi che dovranno 
	 * essere eseguiti durante i test
	 * @param comandoDaAggiungere il comando da aggiungere alla lista dei comandi
	 * 
	 */
	public void aggiungiComando(String comandoDaAggiungere) {
		this.comandiDaLeggere.add(comandoDaAggiungere);
	}
	
	/**
     * Verifica se un determinato messaggio è presente nella lista dei messaggi.
     * @param messaggio il messaggio da cercare
     * @return true se il messaggio è stato prodotto, false altrimenti
     */
	public boolean cercaMessaggio(String messaggio) {
		return this.messaggiProdotti.contains(messaggio);
	}
}
