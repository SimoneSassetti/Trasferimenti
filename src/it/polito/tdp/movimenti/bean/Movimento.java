package it.polito.tdp.movimenti.bean;

import java.util.Date;

public class Movimento {
	
	private Date registrazione ;
	private int circoscrizioneProvenienza ;
	private int circoscrizioneDestinazione ;
	private int numeroEventi ;
	
	public Movimento(Date registrazione, int circoscrizioneProvenienza,
			int circoscrizioneDestinazione, int numeroEventi) {
		super();
		this.registrazione = registrazione;
		this.circoscrizioneProvenienza = circoscrizioneProvenienza;
		this.circoscrizioneDestinazione = circoscrizioneDestinazione;
		this.numeroEventi = numeroEventi;
	}
	public Date getRegistrazione() {
		return registrazione;
	}
	public void setRegistrazione(Date registrazione) {
		this.registrazione = registrazione;
	}
	public int getCircoscrizioneProvenienza() {
		return circoscrizioneProvenienza;
	}
	public void setCircoscrizioneProvenienza(int circoscrizioneProvenienza) {
		this.circoscrizioneProvenienza = circoscrizioneProvenienza;
	}
	public int getCircoscrizioneDestinazione() {
		return circoscrizioneDestinazione;
	}
	public void setCircoscrizioneDestinazione(int circoscrizioneDestinazione) {
		this.circoscrizioneDestinazione = circoscrizioneDestinazione;
	}
	public int getNumeroEventi() {
		return numeroEventi;
	}
	public void setNumeroEventi(int numeroEventi) {
		this.numeroEventi = numeroEventi;
	}

	
}
