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

public class Test {
	private static String CheminFichier;
	private static String CheminExtract;
	private static File fichier;
	private static Graph graphe;
	private static int Mode = 0;
	private static int nb_Echantillions = 800;
	

	
	
	public static void Extract() throws IOException, FileNotFoundException 
	{
		String Ori,Dest;
		int i = 0;
		String[] nodesString;
		
		String CheminExtract =  "/Users/hugoarrondelle/Documents/Insa/S2/Be__Graphes/Résultat_guadeloupe.txt";
		
		BufferedReader br = new BufferedReader(new FileReader(CheminExtract));
		
		
		
		File fR1 = new File("Résultat_"+1+".txt");
		fR1.createNewFile();
		FileOutputStream fosR1 = new FileOutputStream("Résultat_"+1+".txt");
		BufferedWriter bwR1 = new BufferedWriter(new OutputStreamWriter(fosR1));
		
		
		
		while(i<nb_Echantillions) {
			
			nodesString = br.readLine().split(" ");
			Ori = nodesString[0];
			Dest = nodesString[1];
			
			
			
			

		    	bwR1.write(Ori);
		    	bwR1.newLine();
		    	

		    	
		    	
		 
		    i++;
		}
		System.out.println("Fin du test");
		
		
		bwR1.close();
		fosR1.close();
		br.close();
	}

	 public static void main(String[] args) throws Exception {
		 Extract();

			
	 }
}
