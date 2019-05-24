package org.insa.graph;


import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.graph.Node;
import org.insa.graph.Point;
import org.insa.algo.AbstractInputData;

public class LabelStar extends Label implements Comparable<Label>{
	private float inf;

	public LabelStar(Node noeud, ShortestPathData data) {
		super(noeud);

		if (data.getMode() == AbstractInputData.Mode.LENGTH) {
			this.inf = (float)Point.distance(noeud.getPoint(),data.getDestination().getPoint());
		}
		else {
			int vitesse = Math.max(data.getMaximumSpeed(), data.getGraph().getGraphInformation().getMaximumSpeed());
			this.inf = (float)Point.distance(noeud.getPoint(),data.getDestination().getPoint())/(vitesse*1000.0f/3600.0f);
		}
	}

	@Override
	/* Renvoie le coût de l'origine jusqu'au noeud + coût à vol d'oiseau du noeud jusqu'à la destination */
	public double getTotalCost() {
		return this.inf+this.cout;
	}

}