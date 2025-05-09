package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Cerca di prendere un attrezzo nella stanza. Se l'attrezzo è presente nella stanza
 * lo aggiunge alla borsa del giocatore e lo rimuove dalla stanza, 
 * altrimenti stampa un messaggio di errore
 * 
 * @param nomeAttrezzo nome dell'attrezzo da prendere
 */
public class ComandoPrendi implements Comando{
	private String nomeAttrezzo;
	private IO io;
	
	public ComandoPrendi (String nomeAttrezzo, IO io) {
		this.nomeAttrezzo = nomeAttrezzo;
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		while (nomeAttrezzo == null) {
			io.mostraMessaggio("Cosa vuoi prendere? ");
			nomeAttrezzo = io.leggiRiga();
			 
		}
		
		
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzoDaPrendere = stanzaCorrente.getAttrezzo(nomeAttrezzo);
		
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
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getNome() {
		return "prendi";
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
}
