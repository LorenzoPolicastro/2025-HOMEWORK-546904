package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

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

public class StanzaBuia extends Stanza{
	
	private String attrezzoIlluminante;
    
    /**
     * Crea una stanza buia. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     * @param attrezzoIlluminante attrezzo che permette di stampare la descrizione della stanza
     */
    public StanzaBuia(String nome, String attrezzoIlluminante) {
    	super(nome);
    	this.attrezzoIlluminante = attrezzoIlluminante;
    }
    
    /**
     * Restituisce la descrizione della stanza se l'attrezzo illuminante è presente in essa.
     * @return la descrizione della stanza
     */
    @Override
    public String getDescrizione() {
    	if(this.hasAttrezzo(attrezzoIlluminante)) {
    		return super.toString();
    	}else {
    		return "qui c'è buio pesto";
    	}
    }
}
