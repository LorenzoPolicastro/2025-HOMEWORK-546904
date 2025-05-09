package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Comando;


/*Restituisce i CFU rimanenti del giocatore e gli oggetti contenuti nella sua borsa
 * 
 */
public class ComandoInventario implements Comando{
	private IO io;
	
	public ComandoInventario (IO io) {
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getGiocatore().StampaGiocatore());
	}
	
	@Override
	public void setParametro(String parametro) {
		//vuoto perche' non vuole parametri
	}
	
	@Override
	public String getNome() {
		return "inventario";
	}
	
	@Override
	public String getParametro() {
		return " ";
	}
}
