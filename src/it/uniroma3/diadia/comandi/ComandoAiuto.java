package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Comando;

/**
 * Stampa informazioni di aiuto.
 */
public class ComandoAiuto implements Comando{
	private IO io;
	private String[] elencoComandi;
	
	public ComandoAiuto(String[] elencoComandi, IO io){
		this.elencoComandi = elencoComandi;
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Elenco comandi: ");
		
		for(int i=0; i< elencoComandi.length; i++) 
			io.mostraMessaggio(elencoComandi[i]+" ");
	}
	
	@Override
	public void setParametro(String parametro) {
		//vuoto perche' non vuole parametri
	}
	
	@Override
	public String getNome() {
		return "aiuto";
	}
	
	@Override
	public String getParametro() {
		return "";
	}
}
