package it.polito.tdp.movimenti.bean;

import java.util.*;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.movimenti.dao.MovimentiDAO;

public class Model {
	
	private List<Circoscrizione> circoscrizioni;
	MovimentiDAO dao;
	private SimpleDirectedWeightedGraph<Circoscrizione,DefaultWeightedEdge> grafo;
	
	//ricorsione
	private int lunghezza;
	private int pesoMax;
	
	public Model(){
		dao=new MovimentiDAO();
	}
	
	public List<Circoscrizione> getCircoscrizioni(){
		if(circoscrizioni==null){
			circoscrizioni=dao.getCircoscrizioni();
		}
		return circoscrizioni;
	}

	public List<Stat> cercaMovimenti(Circoscrizione c) {
		List<Stat> lista=new ArrayList<Stat>();
		
		for(int i=0; i<circoscrizioni.size(); i++){
			int val=dao.getNumeroMovimenti(c,circoscrizioni.get(i));
			Stat s=new Stat(circoscrizioni.get(i), val);
			lista.add(s);
		}
		
		Collections.sort(lista);
		
		return lista;
	}
	
	public void creaGrafo(){
		
		grafo=new SimpleDirectedWeightedGraph<Circoscrizione,DefaultWeightedEdge>(DefaultWeightedEdge.class);
		
		//vertici
		Graphs.addAllVertices(grafo, circoscrizioni);
		
		//archi
		for(Circoscrizione part: grafo.vertexSet()){
			for(Circoscrizione arrivo: grafo.vertexSet()){
				if(!arrivo.equals(part)){
					int val=dao.getNumeroMovimenti(part, arrivo);
					DefaultWeightedEdge arco=grafo.addEdge(part, arrivo);
					if(arco!=null){
						grafo.setEdgeWeight(arco, val);
					}
				}
			}
		}
		//System.out.println(grafo);
	}

	public List<Circoscrizione> trovaCammino(Circoscrizione partenza, int l) {
		
		List<Circoscrizione> parziale=new ArrayList<Circoscrizione>();
		List<Circoscrizione> migliore=new ArrayList<Circoscrizione>();
		
		this.lunghezza=l;
		pesoMax=Integer.MIN_VALUE;
		parziale.add(partenza);
		
		recursive(parziale,migliore,partenza,1);
				
		return migliore;
	}

	private void recursive(List<Circoscrizione> parziale, List<Circoscrizione> migliore, Circoscrizione partenza,int step) {
		
		if(parziale.size()==lunghezza){
			int peso=0;
			for(int i=0; i<parziale.size()-1;i++){
				Circoscrizione part=parziale.get(i);
				Circoscrizione arr=parziale.get(i+1);
				DefaultWeightedEdge arco=grafo.getEdge(part, arr);
				peso+=grafo.getEdgeWeight(arco);
			}
		
			if(peso>pesoMax){
				pesoMax=peso;
				migliore.clear();
				migliore.addAll(parziale);
			}
		}		
		for(Circoscrizione c: Graphs.successorListOf(grafo, partenza)){
			if(!parziale.contains(c)){
				parziale.add(c);
				recursive(parziale,migliore,c,step+1);
				parziale.remove(c);
			}
		}
	}
}
