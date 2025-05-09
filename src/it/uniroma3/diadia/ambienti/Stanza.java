package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
	
    private Attrezzo[] attrezzi;
    private int numeroAttrezzi;
    
    private Stanza[] stanzeAdiacenti;
    private int numeroStanzeAdiacenti;
    
	private String[] direzioni;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.numeroStanzeAdiacenti = 0;
        this.numeroAttrezzi = 0;
        this.direzioni = new String[NUMERO_MASSIMO_DIREZIONI];
        this.stanzeAdiacenti = new Stanza[NUMERO_MASSIMO_DIREZIONI];
        this.attrezzi = new Attrezzo[NUMERO_MASSIMO_ATTREZZI];
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false;
    	for(int i=0; i<this.direzioni.length; i++)
        	if (direzione.equals(this.direzioni[i])) {
        		this.stanzeAdiacenti[i] = stanza;
        		aggiornato = true;
        	}
    	if (!aggiornato)
    		if (this.numeroStanzeAdiacenti < NUMERO_MASSIMO_DIREZIONI) {
    			this.direzioni[numeroStanzeAdiacenti] = direzione;
    			this.stanzeAdiacenti[numeroStanzeAdiacenti] = stanza;
    		    this.numeroStanzeAdiacenti++;
    		}
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
        Stanza stanza = null;
		for(int i=0; i<this.numeroStanzeAdiacenti; i++)
        	if (this.direzioni[i].equals(direzione))
        		stanza = this.stanzeAdiacenti[i];
        return stanza;
	}

    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Attrezzo[] getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.numeroAttrezzi < NUMERO_MASSIMO_ATTREZZI) {
        	this.attrezzi[numeroAttrezzi] = attrezzo;
        	this.numeroAttrezzi++;
        	return true;
        }
        else {
        	return false;
        }
    }

    /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* Il metodo originale "toString" genera un errore nel caso in cui un elemento delle collezioni direzioni o attrezzi della stanza 
	* siano impostato con valore null,
	* ovvero, non sia stato inserito il numero massimo di oggetti Attrezzo.
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	
    	String[] direzioneN = this.getDirezioni();
    	for (int i=0; i<numeroStanzeAdiacenti; i++) {
    		
    		if (direzioneN[i]!=null)
    			risultato.append(" " + direzioneN[i]);
    	}
    		
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (int i=0; i<numeroAttrezzi; i++) {
    		if(this.attrezzi[i] != null) {
    			risultato.append(attrezzi[i].toString()+" ");
    		}
    	}
    	return risultato.toString();
    }

	/**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* Questo metodo è una variante dell'originale metodo hasAttrezzo, che tuttavia ammette che la stanza che lo richiama abbia una collezione
	* di attrezzi non completamente piena di oggetti Attrezzo. 
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {

		for (int i = 0; i < this.numeroAttrezzi; i++) {
			Attrezzo attrezzo = this.attrezzi[i];
			if(attrezzo != null && nomeAttrezzo.equals(attrezzo.getNome())) {
				return true;
			}
		}
		return false;
	}
	
	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	 /*public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				attrezzoCercato = attrezzo;
		}
		return attrezzoCercato;	
	}
	*/
	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * Il metodo originale "hasAttrezzo" genera un errore nel caso in cui un elemento della collezione attrezzi della stanza sia impostato con valore null,
	 * ovvero, non sia stato inserito il numero massimo di oggetti Attrezzo.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
	    if (nomeAttrezzo == null) {
	    	return null;  // Gestione input nullo
	    }
	    
	    for (int i = 0; i < this.numeroAttrezzi; i++) {  // Uso numeroAttrezzi invece dell'array.length
	        Attrezzo attrezzo = this.attrezzi[i];
	        if (attrezzo != null && nomeAttrezzo.equals(attrezzo.getNome())) {
	            return attrezzo;  // Restituisco immediatamente l'attrezzo appena lo trovo
	        }
	    }
	    return null;
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		String nomeAttrezzo = attrezzo.getNome();
		boolean rimosso = false;
		
		for (int i=0; i<this.numeroAttrezzi; i++) {
			if (attrezzi[i].getNome().equals(nomeAttrezzo)) {
				rimosso = true;
				
				for(int j=i; j<numeroAttrezzi; j++) {
					this.attrezzi[j] = this.attrezzi[j+1];
				}
				this.numeroAttrezzi--;
			}
		}
		return rimosso;
	}


	public String[] getDirezioni() {
		String[] direzioni = new String[this.numeroStanzeAdiacenti];
	    for(int i=0; i<this.numeroStanzeAdiacenti; i++)
	    	direzioni[i] = this.direzioni[i];
	    return direzioni;
    }

}