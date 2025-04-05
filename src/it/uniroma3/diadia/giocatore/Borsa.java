package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.*;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private IOConsole io;
	
	public Borsa(int pesoMax, IOConsole io) {
		this.io = io;
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[10]; // speriamo bastino...
		this.numeroAttrezzi = 0;
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		if (this.numeroAttrezzi==10)
			return false;
		this.attrezzi[this.numeroAttrezzi] = attrezzo;
		this.numeroAttrezzi++;
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.numeroAttrezzi; i++)
			if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
				a = attrezzi[i];

	return a;
	}
	
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.numeroAttrezzi; i++)
		peso += this.attrezzi[i].getPeso();

		return peso;
	}
	
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		boolean trovato = false;
		//cerco l'attrezzo che ha nome uguale a quello passato a parametro
		for(int i=0; i<this.numeroAttrezzi && trovato==false; i++) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				
				//salvo l'attrezzo cercato
				a = this.attrezzi[i];
				trovato = true;
				
				//riordino la collezione degli attrezzi, spostando indietro gli attrezzi dopo l'attrozzo da rimuovere
				for(int j=i; j<this.numeroAttrezzi; j++) {
					this.attrezzi[j] = this.attrezzi[j+1];
				}
				this.attrezzi[this.numeroAttrezzi - 1] = null;
				this.numeroAttrezzi--;
			}
		}
		return a;
	}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
			for (int i= 0; i<this.numeroAttrezzi; i++)
				s.append(attrezzi[i].toString()+" ");
		}else
			s.append("Borsa vuota");
		
		return s.toString();
	}
}
