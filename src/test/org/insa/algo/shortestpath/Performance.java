package org.insa.algo.shortestpath;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.File;
import java.io.FileOutputStream;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.*;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;

public class Performance {
	private static String CheminFichier;
	private static String CheminExtract;
	private static File fichier;
	private static Graph graphe;
	private static int Mode = 0;
	private static int nb_Echantillions = 1000;
	

	public static void initAll(String Carte, int Mode) throws IOException, FileNotFoundException {
		
		String CheminCarte = "/Users/hugoarrondelle/Desktop/Maps/"+Carte+".mapgr";
		
		if(Mode==0) //Valeur pour fichier distance
		{
			CheminFichier = Carte +"_" + nb_Echantillions + "_data.txt";
		}
		else // Valeur pour fichier temps
		{
			CheminFichier = Carte +"_" + nb_Echantillions + "_data.txt";
		}
		
		fichier = new File(CheminFichier); // On cr�e le fichier avec la carte
		fichier.createNewFile();
		FileOutputStream fileoutputstream = new FileOutputStream(CheminFichier);
		BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(fileoutputstream));
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(CheminCarte)))); //On cr�e le GraphReader avec la carte
		graphe = reader.read();
		
		bufferedwriter.write((Carte)); 
		bufferedwriter.newLine();
		bufferedwriter.write((Integer.toString(Mode)));
		bufferedwriter.newLine();
		bufferedwriter.write(("Dijkstra"));
		bufferedwriter.newLine();
		bufferedwriter.write((Integer.toString(nb_Echantillions)));
		bufferedwriter.newLine();
		
		Random rand = new Random();
		for(int i=0;i<nb_Echantillions*2;i++) {
			bufferedwriter.write((rand.nextInt(graphe.getNodes().size())+" "+rand.nextInt(graphe.getNodes().size())));
			bufferedwriter.newLine();
		}
		bufferedwriter.close();
		fileoutputstream.close();
	}
	
	public static void Extract(String args) throws IOException, FileNotFoundException 
	{
		int Ori,Dest, i = 0;
		String[] nodesString;
		
		String CheminExtract =  "/Users/hugoarrondelle/Documents/Insa/S2/Be__Graphes/Guadeloupe_"+nb_Echantillions+"_data.txt";
		
		BufferedReader br = new BufferedReader(new FileReader(CheminExtract));
		
		String map = br.readLine();
		int a = Integer.parseInt(br.readLine());
		String Algo = br.readLine();
		int b = Integer.parseInt(br.readLine());
		
		
		
		
		File fR1 = new File("Résultat_"+map+".txt");
		fR1.createNewFile();
		FileOutputStream fosR1 = new FileOutputStream("Résultat_"+map+".txt");
		BufferedWriter bwR1 = new BufferedWriter(new OutputStreamWriter(fosR1));
		
		bwR1.write("Map : " + map);
		bwR1.newLine();
		bwR1.write("Mode : " +Integer.toString(a));
		bwR1.newLine();
		bwR1.write("Algorithme : " +Algo);
		bwR1.newLine();
		bwR1.write("Nombre d'échantillons : " + Integer.toString(b));
		bwR1.newLine();
		String mapGuadeloupe = "/Users/hugoarrondelle/Desktop/Maps/guadeloupe.mapgr";
		GraphReader Guadeloupe = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapGuadeloupe))));
		Graph graph = Guadeloupe.read();
		System.out.println("Début du test");
		
		while(i<nb_Echantillions) {
			
			nodesString = br.readLine().split(" ");
			Ori = Integer.parseInt(nodesString[0]);
			Dest = Integer.parseInt(nodesString[1]);
			
			
			
			
			
			
			ShortestPathData data = new ShortestPathData(graph, graph.getNodes().get(Ori), graph.getNodes().get(Dest),ArcInspectorFactory.getAllFilters().get(0) );
			
			ShortestPathAlgorithm Choix_Algo;
			
			switch(args) {
			case "B" :
				Choix_Algo = new BellmanFordAlgorithm(data);
				break;
			case "D" :
				Choix_Algo = new DijkstraAlgorithm(data);
				break;
			case "A" :
				Choix_Algo = new AStarAlgorithm(data);
				break;
			default : 
				System.out.println("Erreur algo pas reconnu: use is A of A* B for BellmanFord or D for Djikstra");
				throw new IOException();
			}
			long startTime = System.currentTimeMillis();
		    ShortestPathSolution Solution = Choix_Algo.doRun();
		    long endTime = System.currentTimeMillis();
		    long temps_exe = endTime - startTime;
		    
		    
		    bwR1.write("********************************************************");
			bwR1.newLine();
		    bwR1.write("Origine : " + Ori + "  ----- Destination : " + Dest);
			bwR1.newLine();
		    
		    if(Solution.isFeasible())
		    {
		    	bwR1.write("Distance : " + Solution.getPath().getLength() + " Temps d'exécution : " + temps_exe + " ms");
		    	bwR1.newLine();
		    	bwR1.newLine();
		    	
		    	
		    }
		    else
		    {
		    	bwR1.write("Infaisable");
		    	bwR1.newLine();
		    }
		    i++;
		}
		System.out.println("Fin du test");
		
		
		bwR1.close();
		fosR1.close();
		br.close();
	}

	 public static void main(String[] args) throws Exception {
		 initAll("guadeloupe",0);
		 Extract("D");
		 if(!fichier.exists()) {
			 System.out.println("ERROR");
		 }
	 }
}
