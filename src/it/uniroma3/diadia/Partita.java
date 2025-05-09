package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @see Labirinto
 * @see Giocatore
 * @version 1.2
 */

public class Partita {
	//queste due variabili appartengono solo a questa classe e non possono essere modificate
	static final private int CFU_INIZIALI = 20;
	static final private int PESO_MASSIMO_BORSA = 10;
	private IO io;
	
	private Labirinto labirinto;
	private Giocatore giocatore;
	private Stanza stanzaCorrente;
	private boolean finita;
	private int cfu;
	
	public Partita(IO io){
		this.io = io;
		this.labirinto = new Labirinto(io);
		this.giocatore = new Giocatore(CFU_INIZIALI, PESO_MASSIMO_BORSA, io);
		this.stanzaCorrente = this.labirinto.getStanzaIniziale();
		this.finita = false;
		this.cfu = CFU_INIZIALI;
	}

	public Stanza getStanzaVincente() {
		return this.labirinto.getStanzaVincente();
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	public Giocatore getGiocatore() {
        return this.giocatore;
    }
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== this.getStanzaVincente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @versione 1.1
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}

	//setCfu() e getCfu() rimossi in quanto gestiti dalla classe Giocatore
	
	/**
     * Restituisce il labirinto della partita
     * @return il labirinto
     */
    public Labirinto getLabirinto() {
        return this.labirinto;
    }
}
