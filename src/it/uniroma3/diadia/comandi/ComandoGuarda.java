package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.Comando;

/*Restituisce una descrizione delle direzioni che è possibile prendere da questa stanza e gli 
 * eventuali oggetti che contiene
 */
public class ComandoGuarda implements Comando{
	private IO io;
	
	public ComandoGuarda(IO io){
		this.io = io;
	}
	
	@Override
	public void esegui(Partita partita) {
		io.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());

	}
	
	@Override
	public void setParametro(String parametro) {
		//vuoto perche' non vuole parametri
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return " ";
	}
}
