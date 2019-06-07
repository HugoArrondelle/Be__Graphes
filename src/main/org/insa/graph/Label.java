package org.insa.graph;


import org.insa.graph.Node;

public class Label implements Comparable<Label> {
	protected double cout; // valeur courante du plus court chemin depuis l'origine vers le sommet.
	private boolean marque; // booléen, vrai lorsque le coût min de ce sommet est définitivement connu par l'algorithme.
	private Arc pere; // correspond au sommet précédent sur le chemin correspondant au plus court chemin courant
	private int sommetcourant; // sommet associé à ce label (sommet ou numéro de sommet).
	private Node noeud;
	
	
	public Label(int pCourant , double cost, Arc pFather)
	{
		this.sommetcourant = pCourant;
		this.marque = false;
		this.cout = cost;
		this.pere = pFather; 
	}
	
	public Label(int pCourant , double cost)
	{
		this.sommetcourant = pCourant;
		this.marque = false;
		this.cout = cost;
		this.pere = null; 
	}

	public Label(Node noeud){
		this.noeud = noeud;
		this.marque = false;
		this.cout = Float.POSITIVE_INFINITY;
		this.pere = null; 
	}

	public double getCout() 
	{
		return this.cout;
	}
	public int getSommetCourant() 
	{
		return this.sommetcourant;
	}
	public boolean getMarque() 
	{
		return this.marque;
	}
	
	public Arc getPere() 
	{
		return this.pere;
	}


	public void setFather(Arc pFather) 
	{
		this.pere = pFather;
		
	}

	public void setMarque(boolean pMarque) 
	{
		this.marque = pMarque;
	}

	public void setCost(double newdistance) 
	{
		this.cout = newdistance;
		
	}
	
	public double getTotalCost() 
	{
		return this.cout;
	}
	
	
			
	//public boolean equals (Label other) 
	//{
	//	return this.sommetcourant.equals(other.getSommetCourant());
	//}
	//@Override
	//public int compareTo(Label other) {
	//	return Double.compare(this.cout,  other.getCout());
	//}
	public int compareTo(Label autre) {
		int resultat;
		if (this.getTotalCost() < autre.getTotalCost()) {
			resultat = -1;
		}
		else if (this.getTotalCost() == autre.getTotalCost()) {
			resultat = 0;
		}
		else {
			resultat = 1;
		}
		return resultat;
	}


}