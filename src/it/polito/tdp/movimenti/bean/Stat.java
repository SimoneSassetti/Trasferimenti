package it.polito.tdp.movimenti.bean;

public class Stat implements Comparable<Stat>{
	
	private Circoscrizione c;
	private int numMovimenti;
	public Stat(Circoscrizione c, int numMovimenti) {
		super();
		this.c = c;
		this.numMovimenti = numMovimenti;
	}
	public Circoscrizione getC() {
		return c;
	}
	public void setC(Circoscrizione c) {
		this.c = c;
	}
	public int getNumMovimenti() {
		return numMovimenti;
	}
	public void setNumMovimenti(int numMovimenti) {
		this.numMovimenti = numMovimenti;
	}
	@Override
	public int compareTo(Stat s) {
		return -(this.numMovimenti-s.numMovimenti);
	}
}
