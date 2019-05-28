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
import org.insa.graph.*;
import java.util.Random;
import java.io.BufferedReader;
import java.io.FileReader;

public class Performance {
	private static String CheminFichier;
	private static String CheminExtract;
	private static File fichier;
	private static Graph graphe;
	private static int type = 0;
	private static int samples = 1000;
	

	public static void initAll(String Carte, int type) throws IOException, FileNotFoundException {
		
		String CheminCarte = "/Users/hugoarrondelle/Desktop/Maps/"+Carte+".mapgr";
		
		if(type==0) //Valeur pour fichier distance
		{
			CheminFichier = "Guadeloupe" +"_distance_" + samples + "_data.txt";
		}
		else // Valeur pour fichier temps
		{
			CheminFichier = "Guadeloupe" +"_temps_" + samples + "_data.txt";
		}
		
		fichier = new File(CheminFichier); // On cr�e le fichier avec la carte
		fichier.createNewFile();
		FileOutputStream fileoutputstream = new FileOutputStream(CheminFichier);
		BufferedWriter bufferedwriter = new BufferedWriter(new OutputStreamWriter(fileoutputstream));
		GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(CheminCarte)))); //On cr�e le GraphReader avec la carte
		graphe = reader.read();
		
		bufferedwriter.write((Carte)); 
		bufferedwriter.newLine();
		bufferedwriter.write((Integer.toString(type)));
		bufferedwriter.newLine();
		bufferedwriter.write(("Dijkstra"));
		bufferedwriter.newLine();
		bufferedwriter.write((Integer.toString(samples)));
		bufferedwriter.newLine();
		
		Random rand = new Random();
		for(int i=0;i<samples*2;i++) {
			bufferedwriter.write((rand.nextInt(graphe.getNodes().size())+" "+rand.nextInt(graphe.getNodes().size())));
			bufferedwriter.newLine();
		}
		bufferedwriter.close();
		fileoutputstream.close();
	}
	
	public static void Extract() throws IOException, FileNotFoundException 
	{
		int Ori,Dest, i = 0;
		String[] nodesString;
		
		String CheminExtract =  "/Users/hugoarrondelle/Documents/Insa/S2/Be__Graphes/Guadeloupe_distance_1000_data.txt";
		
		BufferedReader br = new BufferedReader(new FileReader(CheminExtract));
		
		String map = br.readLine();
		int a = Integer.parseInt(br.readLine());
		System.out.println(a);
		String Algo = br.readLine();
		int b = Integer.parseInt(br.readLine());
		System.out.println(b);
		
		
		
		
		File fR1 = new File("fpathR1.txt");
		fR1.createNewFile();
		FileOutputStream fosR1 = new FileOutputStream("fpathR1.txt");
		BufferedWriter bwR1 = new BufferedWriter(new OutputStreamWriter(fosR1));
		
		bwR1.write("Map : " + map);
		bwR1.newLine();
		bwR1.write(Integer.toString(a));
		bwR1.newLine();
		bwR1.write("Algorithme : " +Algo);
		bwR1.newLine();
		bwR1.write(Integer.toString(b));
		bwR1.newLine();
		
		
		while(i<samples) {
			
			nodesString = br.readLine().split(" ");
			Ori = Integer.parseInt(nodesString[0]);
			Dest = Integer.parseInt(nodesString[1]);
			
			
			bwR1.write("Origine : " + Ori);
			bwR1.write("  ----- Destination : " + Dest);
			bwR1.newLine();
			i++;
		}
		
		
		bwR1.close();
		fosR1.close();
		br.close();
	}

	 public static void main(String[] args) throws Exception {
		 initAll("guadeloupe",0);
		 Extract();
		 if(!fichier.exists()) {
			 System.out.println("ERROR");
		 }
	 }
}
