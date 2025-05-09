package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Comando;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando{
	private IO io;
	
	public ComandoFine (IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
		partita.setFinita();
	}
	
	@Override
	public void setParametro(String parametro) {
		//vuoto perche' non vuole parametri
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	@Override
	public String getParametro() {
		return " ";
	}
}
