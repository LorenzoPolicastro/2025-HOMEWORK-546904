package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version 1.1
 * Sono stati aggiunti i comandi "prendi" e posa"
 * 
 * @version 1.2
 * E' stato aggiunto il comando "inventario"
 * 
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa", "inventario", "esplora"};

	private Partita partita;
	private IOConsole io;

	public DiaDia(IOConsole io) {
		this.partita = new Partita(io);
		this.io = io;
	}

	public void gioca() {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);	
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));

	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai")) {
			this.vai(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("aiuto")) {
			this.aiuto();
		}
		else if (comandoDaEseguire.getNome().equals("prendi")) {
			this.prendi(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("posa")) {
			this.posa(comandoDaEseguire.getParametro());
		}
		else if (comandoDaEseguire.getNome().equals("inventario")) {
			io.mostraMessaggio(this.inventario());
		}
		else if (comandoDaEseguire.getNome().equals("esplora")) {
			io.mostraMessaggio(this.esplora());
		}
		else {
			io.mostraMessaggio("Comando sconosciuto!");
		}
		
		if (this.partita.getGiocatore().getCfu() == 0) { 
			io.mostraMessaggio("Hai perso!");
			return true;
		}
		
		if (this.partita.vinta()) { 
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		io.mostraMessaggio("Elenco comandi: ");
		
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			io.mostraMessaggio("Dove vuoi andare ?");
			direzione = io.leggiRiga();
			
		}while (direzione == null);
		
		
		Stanza prossimaStanza = null;
		prossimaStanza = partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		
		if (prossimaStanza == null) {
			io.mostraMessaggio("Direzione inesistente");
		}else {
			partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			cfu--;
			partita.getGiocatore().setCfu(cfu);
			io.mostraMessaggio("Ti trovi nella stanza "+partita.getStanzaCorrente().getNome());
			io.mostraMessaggio("CFU rimasti "+partita.getGiocatore().getCfu());
		}
	}
	
	/**
	 * Cerca di prendere un attrezzo nella stanza. Se l'attrezzo è presente nella stanza
	 * lo aggiunge alla borsa del giocatore e lo rimuove dalla stanza, 
	 * altrimenti stampa un messaggio di errore
	 * 
	 * @param nomeAttrezzo nome dell'attrezzo da prendere
	 */
	
	private void prendi(String nomeAttrezzo) {
		while (nomeAttrezzo == null) {
			io.mostraMessaggio("Cosa vuoi prendere? ");
			nomeAttrezzo = io.leggiRiga();
			 
		}
		
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzoVersione2(nomeAttrezzo);
		
		if(attrezzoDaPrendere == null) {
			io.mostraMessaggio("Attrezzo non presente nella stanza.");

		}else {
			if(partita.getGiocatore().prendiAttrezzo(attrezzoDaPrendere)) {
				stanzaCorrente.removeAttrezzo(attrezzoDaPrendere);
				io.mostraMessaggio("Hai preso " + nomeAttrezzo);
			}else {
				io.mostraMessaggio("Borsa piena o limite di peso raggiunto.");
			}
		}
			
	}
	/**
	 * Cerca di posare un attrezzo nella stanza. Se l'attrezzo è presente nella Borsa
	 * del giocatore, lo aggiunge alla stanza e lo rimuove dalla Borsa, 
	 * altrimenti stampa un messaggio di errore
	 */
	private void posa(String nomeAttrezzo) {
		while (nomeAttrezzo == null){
			io.mostraMessaggio("Cosa vuoi posare? ");
			nomeAttrezzo = io.leggiRiga();
			
		}
			
		if(partita.getGiocatore().getBorsa().hasAttrezzo(nomeAttrezzo)) {
			Attrezzo attrezzoDaPosare = partita.getGiocatore().posaAttrezzo(nomeAttrezzo);
			if (partita.getStanzaCorrente().addAttrezzo(attrezzoDaPosare)) {
				io.mostraMessaggio("Hai posato l'attrezzo "+nomeAttrezzo);
			}else {
				io.mostraMessaggio("Stanza piena di attrezzi. Impossibile posare l'attrezzo.");
			}
			
		}else {
			io.mostraMessaggio("L'attrezzo cercato non è nella borsa.");
		}
		
	}
	
	/*Restituisce i CFU rimanenti del giocatore e gli oggetti contenuti nella sua borsa
	 * @return Stringa testuale che descrive lo stato del giocatore
	 */
	private String inventario() {
		return partita.getGiocatore().StampaGiocatore();
	}
	
	/*Restituisce una descrizione delle direzioni che è possibile prendere da questa stanza e gli 
	 * eventuali oggetti che contiene
	 * @return Stringa testuale che descrive la stanza
	 */
	private String esplora() {
		return partita.getStanzaCorrente().getDescrizione();
	}
	
	/**
	 * Comando "Fine".
	 */
	private void fine() {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}

	public static void main(String[] argc) {
		IOConsole io = new IOConsole();
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
		
	}
}