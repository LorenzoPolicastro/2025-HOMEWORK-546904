package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.*;

/**
 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
 * e ne stampa il nome, altrimenti stampa un messaggio di errore
 */
public class ComandoVai implements Comando{
	private String direzione;
	private IO io;

	public ComandoVai(String direzione, IO io){
		this.direzione = direzione;
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
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
			int cfu = partita.getGiocatore().getCfu();
			cfu--;
			partita.getGiocatore().setCfu(cfu);
			io.mostraMessaggio("Ti trovi nella stanza "+partita.getStanzaCorrente().getNome());
			io.mostraMessaggio("CFU rimasti "+partita.getGiocatore().getCfu());
		}
	}
	
	@Override
	public void setParametro(String parametro) {
		this.direzione = parametro;
	}
	
	@Override
	public String getNome() {
		return "vai";
	}
	
	@Override
	public String getParametro() {
		return this.direzione;
	}
}
