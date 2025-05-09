package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccata extends Stanza{
	/**
	 * Classe Stanza buia - una stanza in un gioco di ruolo che necessita di uno specifico
	 * oggetto per poter mostrare la sua descrizione
	 * Una stanza e' un luogo fisico nel gioco.
	 * E' collegata ad altre stanze attraverso delle uscite.
	 * Ogni uscita e' associata ad una direzione.
	 * 
	 * @author Lorenzo Policastro
	 * @see Attrezzo
	 * @extends Stanza
	 * @version base
	*/
		
	private String direzioneBloccata;
	private String attrezzoSbloccante;
	    
	/**
	 * Crea una stanza bloccata. Non ci sono stanze adiacenti, non ci sono attrezzi.
	 * @param nome il nome della stanza
	 * @param direzioneBloccata la direzione che necessita della presenza di uno specifico
	 *        attrezzo per muoversi verso di essa
	 * @param attrezzoSbloccante attrezzo che sblocca la direzione bloccata
	 */
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
	  	super(nome);
	  	this.direzioneBloccata = direzioneBloccata;
	   	this.attrezzoSbloccante = attrezzoSbloccante;
	}
	
	/**
     * Restituisce la stanza adiacente nella direzione specificata.
     * Se la direzione specificata è quella bloccata, restituisce la stanza adiacente a tale direzione solo se nella stanza
     * è presente l'attrezzo bloccante, altrimenti restituisce la stanza attuale.
     * @param direzione
     */
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if(direzione.equals(direzioneBloccata)) {
			if(this.hasAttrezzo(attrezzoSbloccante)) {
				return super.getStanzaAdiacente(direzione);
			}else {
				return this;
			}
		}else {
			return super.getStanzaAdiacente(direzione);
		}
		
		
	}
	
	/*Permette di impostare la direzione bloccata della stanza
	 * @param direzioneDaBloccare la direzione della stanza che vogliamo sia bloccata
	 */
	public void setDirezioneBloccata(String direzioneDaBloccare) {
		this.direzioneBloccata = direzioneDaBloccare;
	}
	
	/*Restituisce la direzione bloccata della stanza
	 * @return direzioneBloccata la direzione bloccata della stanza
	 */
	public String getDirezioneBloccata() {
		return this.direzioneBloccata;
	}
}
