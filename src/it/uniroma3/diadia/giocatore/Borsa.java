package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.attrezzi.Attrezzo;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Classe Borsa: definisce le proprietà di un oggetto Borsa che permette di memorizzare
 * oggetti di tipo Attrezzo. Viene definita la sua capacità e peso massimo trasportabile 
 * 
 * @author Lorenzo Policastro
 * @see Attrezzo
 * @version 1.0
 */

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	/* il numero degli attrezzi nella borsa non è più necessario, in quanto serviva
	 * loro ad evitare che si aggiungesse un oggetto oltre la lunghezza dell'array.
	 */
	//private int numeroAttrezzi;
	private int pesoMax;
	private IO io;
	
	public Borsa(int pesoMax, IO io) {
		this.io = io;
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}
	
	/*Aggiunge un attrezzo alla borsa
	 * @param attrezzo attrezzo da aggiungere alla borsa
	 * @return true se l'attrezzo viene aggiunto alla borsa, altrimenti flase
	 */
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		
		this.attrezzi.add(attrezzo);
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	/*Restituisce un attrezzo se presente nella borsa
	 * Questo metodo usa un iteratore esplicitandone l'implementazione
	 * 
	 * @param nomeAttrezzo nome del attrezzo cercato nella borsa
	 * @return attrezzo se è presente nella borsa, altrimenti null
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		if(nomeAttrezzo == null) {
			return null;
		}
		
		Attrezzo a = null;
		boolean trovato = false;
		
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		
		while(it.hasNext() && !trovato) {
			Attrezzo b = it.next();
			if (nomeAttrezzo.equals(b.getNome())){
				a = b;
				trovato = true;
			}
		}

		return a;
	}
	
	/*Restituisce la somma del peso di tutti gli attrezzi nella borsa
	 * Questo metodo utilizza un iteratore tramite il ciclo for-each
	 * 
	 * @return peso il peso totale degli attrezzi
	 */
	public int getPeso() {
		int peso = 0;
		for (Attrezzo attrezzo : this.attrezzi) {
			peso += attrezzo.getPeso();
		}

		return peso;
	}
	
	/*Verifica se la borsa è vuota
	 * @return true se la borsa è vuota, false altrimenti
	 */
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	
	/*Verifica se un attrezzo è presente nella borsa
	 * @return true se viene trovato l'attrezzo, false altrimenti
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	/*Rimuove un attrezzo dalla borsa e lo restituisce
	 * @nomeAttrezzo nome dell'attrezzo usato per ricercare l'attrezzo.
	 * @return attrezzo l'attrezzo cercato nella borsa
	 */
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		boolean trovato = false;
		
		Iterator<Attrezzo> it = this.attrezzi.iterator();
		//cerco l'attrezzo che ha nome uguale a quello passato a parametro
		while(it.hasNext() && !trovato) {
			
			a = it.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				
				it.remove();
				trovato = true;
				
				//riordino la collezione degli attrezzi, spostando indietro gli attrezzi dopo l'attrozzo da rimuovere
				/*OPERAZIONE NON NECESSARIA: L'iteratore scansiona sempre ogni oggetto
				                             nell'arraylist, non c'è rischio che ci sia un Null
					for(int j=i; j<this.numeroAttrezzi; j++) {
						this.attrezzi[j] = this.attrezzi[j+1];
					}
					  this.attrezzi[this.numeroAttrezzi - 1] = null;
					this.numeroAttrezzi--;
			    */
			}
		}
		return a;
	}
	
	/*Stampa a video il contenuto della Borsa, il peso trasportato ed il peso di ogni attrezzo
	 * @return Stringa testuale che descrive il contenuto della borsa
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			
			Iterator<Attrezzo> it = this.attrezzi.iterator();
			Attrezzo a;
			while(it.hasNext()) {
				a = it.next();
				s.append(a.toString()+" ");
			}
		
		}else
			s.append("Borsa vuota");
		
		return s.toString();
	}
}
