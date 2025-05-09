package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Comando;

public class ComandoNonValido implements Comando{
private IO io;
	
	public ComandoNonValido(IO io){
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio("Comando sconosciuto!");

	}
	
	@Override
	public void setParametro(String parametro) {
		//vuoto perche' non vuole parametri
	}
	
	@Override
	public String getNome() {
		return "comandoNonValido";
	}
	
	@Override
	public String getParametro() {
		return " ";
	}
}
