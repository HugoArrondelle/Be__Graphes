package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;

import org.insa.algo.AbstractSolution.Status;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Label;
import org.insa.graph.Node;
import org.insa.graph.Path;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) 
    {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() 
    {
    	
        ShortestPathData data = getInputData(); 	// recupere les datas
        ShortestPathSolution solution = null; 		// initialise la solution à null
        Graph graph = data.getGraph(); 				// recupere les données de la carte
        int nbNodes = graph.size();  				// On récupere le nombre de noeuds dans le graphe
        
        
        
        BinaryHeap<Label> tas = new BinaryHeap<Label>() ; // creation du tas ( BinaryHeap )
        
        Arc[] predecessorArcs = new Arc[nbNodes];   // Initialisation d'un tableau des arcs prédecesseurs 
        
        
        Node origin_node = data.getOrigin(); //On récupere le noeud d'origine
        
        if (data.getOrigin() == null || data.getDestination()== null) 
        {
        	 solution = new ShortestPathSolution(data, Status.UNKNOWN); // Création de la solution finale
        }
        else
        {
        
        
        Label[] label_tab = new Label[nbNodes]; // On crée un tableau de label pour stocker les labels
        label_tab[origin_node.getId()]=new Label(origin_node.getId(),0); //On insere le label de l'origine dans le tableau
        
        for(int i=0;i<nbNodes;i++) // On rempli le tableau de label sauf l'origine d'un cout infini 
        {
        	if(i != origin_node.getId()) 
        	{
        		label_tab[i]=new Label(i,Float.POSITIVE_INFINITY);
        	}
        }
        
        tas.insert(label_tab[origin_node.getId()]); // On insere l'origine dans le tas ( Binary Heap ) 
       
        //notifyOriginProcessed(data.getOrigin()); //
        
        Label current_label; //Création d'un label courant
        
        while(!tas.isEmpty()) //Tant que le tas n'est pas vide
        { 
        	     	
        	
        	current_label = tas.deleteMin(); // On supprime le plus petit élément du tas
        	
        	//tas.print();
        	//System.out.println("On enlève : " + current_label.toString());
        	
        	
        	label_tab[current_label.getSommetCourant()].setMarque(true); // On marque le sommet courant comme vu
        	
        	if (current_label.getPere() != null) // On vérifie si le label courant a un père (arc)
        	{
        		predecessorArcs[current_label.getPere().getDestination().getId()] = current_label.getPere(); // On ajoute l'arc précedent à la solution
        	}
        	double current_length = current_label.getCout(); // On regarde le cout de l'arc
        	current_label.setMarque(true); 
        	
        	for(Arc arc: graph.getNodes().get(current_label.getSommetCourant()).getSuccessors()) // Pour tous les arcs successeurs du noeud courant
        	{ 
                if (!data.isAllowed(arc)) // On regarde si la route est autorisée
                {
                    continue;
                }
        		Node successor = arc.getDestination(); // On recupere le noeud successeur 
        		
        		if (!label_tab[successor.getId()].getMarque()) // Si le successeur n'est pas marqué
        		{ 
        			double olddistance = label_tab[successor.getId()].getCout(); // On recupere le cout du successeur
        			double newdistance; 
        			if (!Double.isInfinite(current_length)) // Si le cout n'est pas infinie
        			{
        				newdistance = current_length+data.getCost(arc) ; // On ajoute le cout de l'arc actuel 
        			} 
        			else 
        			{
        				newdistance = data.getCost(arc) ; // On initialise le cout 
        			}
        			if (Double.isInfinite(olddistance) && Double.isFinite(newdistance)) 
        			{
        				notifyNodeReached(arc.getDestination());
        			}
        			if(Double.compare(newdistance,olddistance) < 0) 		// Si newdistance < oldistance 
        			{ 
        				if (Double.isFinite(olddistance)) 					// Si l'ancienne distance est finie
        				{
        					tas.remove(label_tab[successor.getId()]); 		// On supprime le label du successeur
        				}
        				label_tab[successor.getId()].setCost(newdistance); 	// On insere le nouveau cout dans le tableau de label 
        				tas.insert(label_tab[successor.getId()]);  			// On insere le tableau de label dans le tas
        				label_tab[successor.getId()].setFather(arc); 		// On insere le pere dans le tableau de label
        			}
        		}
        	}
        	
        	if (current_label.getSommetCourant() == data.getDestination().getId()) 
        	{
        		break;
        	}

        }

        if (predecessorArcs[data.getDestination().getId()] == null) //Si la destination n'a pas de predecesseur, la solution n'est pas faisable
        {
            solution = new ShortestPathSolution(data, Status.INFEASIBLE); 
        }
        else 
        {

            // notifyDestinationReached(data.getDestination()); //La solution a été trouvée

            ArrayList<Arc> arcs = new ArrayList<>(); // On crée une ArrayList d'arc
            Arc arc = predecessorArcs[data.getDestination().getId()]; // On recupere l'arc predecesseur 
            while (arc != null) // Tant qu'il existe un arc predecesseur
            {
                arcs.add(arc); // On ajoute l'arc à la liste
                arc = predecessorArcs[arc.getOrigin().getId()]; // On va à l'arc précedent
            }

            Collections.reverse(arcs); // On inverse le chemin

            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(graph, arcs)); // Création de la solution finale
        }	
        
        }
        
        return solution;
    }
    /* Crée et retourne le Label correspondant au Node */
	protected Label newLabel(Node node, ShortestPathData data) {
		return new Label(node);
	}

}

