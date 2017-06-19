package it.polito.tdp.movimenti.bean;

public class Circoscrizione {
	
	private int num;

	public Circoscrizione(int num) {
		super();
		this.num = num;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	@Override
	public String toString() {
		return num+"";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + num;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Circoscrizione other = (Circoscrizione) obj;
		if (num != other.num)
			return false;
		return true;
	}
		
}
