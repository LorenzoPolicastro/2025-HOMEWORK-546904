package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.Comando;

/**
 * Cerca di posare un attrezzo nella stanza. Se l'attrezzo è presente nella Borsa
 * del giocatore, lo aggiunge alla stanza e lo rimuove dalla Borsa, 
 * altrimenti stampa un messaggio di errore
 */
public class ComandoPosa implements Comando{
	private String nomeAttrezzo;
	private IO io;
	
	public ComandoPosa (String nomeAttrezzo, IO io) {
		this.nomeAttrezzo = nomeAttrezzo;
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
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
	
	@Override
	public void setParametro(String parametro) {
		this.nomeAttrezzo = parametro;
	}
	
	@Override
	public String getNome() {
		return "posa";
	}
	
	@Override
	public String getParametro() {
		return this.nomeAttrezzo;
	}
}
