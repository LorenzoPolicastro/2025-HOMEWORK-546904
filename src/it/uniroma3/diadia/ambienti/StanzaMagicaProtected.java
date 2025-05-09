package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected{
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
	
	private int sogliaMagica;
	private int contatoreAttrezziPosati;
	
	public StanzaMagicaProtected (String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	public StanzaMagicaProtected (String nome, int soglia) {
		/*della classe estesa deve
		 *poter delegare l'inizializzazione delle variabili di istanza
		 *della classe base ad un costruttore della stessa
		 *chiamando dal corpo del
		 *costruttore della classe estesa il costruttore della classe base
		 *mediante la parola chiave super()
	     */
		super(nome); //viene impostata la stringa "nome" della classe Stanza, con il valore del parametro "nome"
		this.sogliaMagica = soglia;
	}
	
	public Attrezzo modificaAttrezzo (Attrezzo attrezzo) {
		StringBuilder nomeInvertito = new StringBuilder(attrezzo.getNome());
		int pesoX2 = attrezzo.getPeso() * 2;
		
		nomeInvertito = nomeInvertito.reverse(); //Riscrive la stringa invertendo la posizione dei caratteri
		attrezzo = new Attrezzo(nomeInvertito.toString(), pesoX2); //creo un nuovo oggetto Attrezzo con il nome invertito ed il peso raddoppiato presi dall'attrezzo originale
		
		return attrezzo;
	}
	
	/*
	 * Sovrascrive il metodo addAttrezzo della classe padre Stanza, aggiungendo l'attrezzo alla stanza magica. 
	 * Se è stata superata la soglia di attrezzi posati nella stanza, 
	 * viene aggiunto l'attrezzo attuale con nuome invertito e peso raddoppiato
	 * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
	 */
	@Override
	public boolean addAttrezzo (Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		
		if (this.contatoreAttrezziPosati > this.sogliaMagica) {
			attrezzo = this.modificaAttrezzo(attrezzo);
		}
		
		if (this.numeroAttrezzi < this.attrezzi.length) {
        	this.attrezzi[numeroAttrezzi] = attrezzo;
        	this.numeroAttrezzi++;
        	return true;
        }
		
		return false;
	}
}
