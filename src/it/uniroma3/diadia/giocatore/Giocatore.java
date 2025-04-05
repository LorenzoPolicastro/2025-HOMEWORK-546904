package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Giocatore: gestisce il punteggio dei CFU del giocatore e la memorizzazione
 * di oggetti di tipo Attrezzo in un oggetto di tipo Borsa che li può contenere.
 * 
 * @author Lorenzo Policastro
 * @see Borsa
 * @see Attrezzo
 * @version 1.0
 */

public class Giocatore {
	private int cfu;
    private Borsa borsa;
    private IOConsole io;
    
    public Giocatore(int cfuIniziali, int pesoMaxBorsa, IOConsole io) {
    	this.io = io;
    	this.cfu = cfuIniziali;
    	this.borsa = new Borsa(pesoMaxBorsa, io);
    }
    
    /**Restituisce i cfu correnti del giocatore
     * @return i CFU correnti del giocatore
     */
    public int getCfu() {
        return this.cfu;
    }
    
    /**
     * Imposta i CFU del giocatore
     * @param cfu nuovo valore dei CFU
     */
    public void setCfu(int cfu) {
        this.cfu = cfu;
    }
    
    /**Restituisce il riferimento all'oggetto Borsa appartenente al giocatore
     * @return la borsa del giocatore
     */
    public Borsa getBorsa() {
        return this.borsa;
    }
    
    /**
     * Aggiunge un attrezzo alla borsa del giocatore
     * @param attrezzo l'attrezzo da aggiungere
     * @return true se l'attrezzo è stato aggiunto, false altrimenti
     */
    public boolean prendiAttrezzo(Attrezzo attrezzo) {
        return this.borsa.addAttrezzo(attrezzo);
    }
    
    /**
     * Rimuove un attrezzo dalla borsa del giocatore
     * @param nomeAttrezzo il nome dell'attrezzo da rimuovere
     * @return l'attrezzo rimosso o null se non trovato
     */
    public Attrezzo posaAttrezzo(String nomeAttrezzo) {
        return this.borsa.removeAttrezzo(nomeAttrezzo);
    }
    
    /*Stampa le informazioni sullo stato del giocatore 
     * @return stringa testuale che illustra lo stato del giocatore 
     */
    public String StampaGiocatore() {
    	return "Giocatore [CFU="+cfu+" "+borsa.toString()+"]";
    }
}
