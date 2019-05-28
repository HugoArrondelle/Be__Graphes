package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JFrame;

import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.AbstractSolution.Status;
import org.insa.graph.Graph;
import org.insa.graph.Path;
import org.insa.graph.RoadInformation.RoadType;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.BinaryPathReader;
import org.insa.graph.io.GraphReader;
import org.insa.graph.io.PathReader;
import org.insa.graphics.drawing.Drawing;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.Color;


public class Dijkstra_Test 
{
	 public static Graph graph_Carre;
	 public static Graph graph_Insa;
	 public static Graph graph_Guadeloupe;
	 

	 
	 @BeforeClass
	 public static void initAll() throws IOException 
	 {
		// Visit these directory to see the list of available files on Commetud.
	        String mapCarre = "/Users/hugoarrondelle/Desktop/Maps/carre.mapgr";
	 
	        // Create a graph reader.
	        GraphReader Carre = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapCarre))));

	        // Read the graph.
	        graph_Carre = Carre.read();
	       
	        // Visit these directory to see the list of available files on Commetud.
	        String mapInsa = "/Users/hugoarrondelle/Desktop/Maps/insa.mapgr";
	 
	        // Create a graph reader.
	        GraphReader Insa = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));

	        // Read the graph.
	        graph_Insa = Insa.read();
	        
	        
	        // Visit these directory to see the list of available files on Commetud.
	        String mapGuadeloupe = "/Users/hugoarrondelle/Desktop/Maps/guadeloupe.mapgr";
	 
	        // Create a graph reader.
	        GraphReader Guadeloupe = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapGuadeloupe))));

	        // Read the graph.
	        graph_Guadeloupe = Guadeloupe.read();
		 
	 }	 
	
	 @Test
     public void Empty_Insa() throws Exception
	 {
		 System.out.println("\n/****************************************/\n");
		 System.out.println("Test map Insa et chemin vide \n");
		 ShortestPathData EmptyPath = new ShortestPathData(graph_Insa, null, null,ArcInspectorFactory.getAllFilters().get(0) );
	     DijkstraAlgorithm DA = new DijkstraAlgorithm(EmptyPath);
	     long startTime_Dijkstra = System.currentTimeMillis();
	     ShortestPathSolution SPS_D = DA.doRun();
	     long endTime_Dijkstra = System.currentTimeMillis();
	     System.out.println("	Dijkstra      : " + (endTime_Dijkstra - startTime_Dijkstra) + " millisecondes");
	     assertEquals(SPS_D.getStatus(),Status.UNKNOWN);
	     //System.out.println("	Dijkstra      : " + SPS_D.getPath().getLength() + " m");
	     
	     
	     BellmanFordAlgorithm BE = new BellmanFordAlgorithm(EmptyPath);
	     long startTime_Bellman = System.currentTimeMillis();
	     ShortestPathSolution SPS_B = DA.doRun();
	     long endTime_Bellman = System.currentTimeMillis();
	     System.out.println("	Bellman-Ford  : " + (endTime_Bellman - startTime_Bellman) + " millisecondes");
	     assertEquals(SPS_B.getStatus(),Status.UNKNOWN);
	     //System.out.println("	Dijkstra      : " + SPS_B.getPath().getLength() + " m");
	     System.out.println("\n/****************************************/\n");
	 }

	 @Test
     public void Valid_Insa() throws Exception
	 {
		 System.out.println("Test map Insa et chemin valide en longueur \n");
		 ShortestPathData Valid = new ShortestPathData(graph_Insa, graph_Insa.get(255), graph_Insa.get(525),ArcInspectorFactory.getAllFilters().get(0) );
		 DijkstraAlgorithm DA = new DijkstraAlgorithm(Valid);
		 long startTime_Dijkstra = System.currentTimeMillis();
	     ShortestPathSolution SPS_D = DA.doRun();
	     long endTime_Dijkstra = System.currentTimeMillis();
	     System.out.println("	Dijkstra      : " + (endTime_Dijkstra - startTime_Dijkstra) + " millisecondes");
		 assertEquals(SPS_D.getStatus(),Status.OPTIMAL);
		 assertTrue(SPS_D.getPath().isValid());
		 System.out.println("	Dijkstra      : " + SPS_D.getPath().getLength() + " m");
		 
		 
		 
		 BellmanFordAlgorithm BE = new BellmanFordAlgorithm(Valid);
	     long startTime_Bellman = System.currentTimeMillis();
	     ShortestPathSolution SPS_B = DA.doRun();
	     long endTime_Bellman = System.currentTimeMillis();
	     System.out.println("	Bellman-Ford  : " + (endTime_Bellman - startTime_Bellman) + " millisecondes");
	     assertEquals(SPS_B.getStatus(),Status.OPTIMAL);
	     assertTrue(SPS_D.getPath().isValid());
	     System.out.println("	Bellman-Ford  : " + SPS_B.getPath().getLength() + " m");
	     System.out.println("\n/****************************************/\n");
		 
	    
	 }
	 
	 
	 
	 @Test
     public void Carre_Valid() throws Exception
	 {
		 System.out.println("Test map Carre et chemin valide en longueur \n");
		 ShortestPathData Carre_Valid = new ShortestPathData(graph_Carre, graph_Carre.get(23), graph_Carre.get(16),ArcInspectorFactory.getAllFilters().get(0) );
	     DijkstraAlgorithm DA = new DijkstraAlgorithm(Carre_Valid);
		 long startTime_Dijkstra = System.currentTimeMillis();
	     ShortestPathSolution SPS_D = DA.doRun();
	     long endTime_Dijkstra = System.currentTimeMillis();
	     System.out.println("	Dijkstra      : " + (endTime_Dijkstra - startTime_Dijkstra) + " millisecondes");
		 assertEquals(SPS_D.getStatus(),Status.OPTIMAL);
		 assertTrue(SPS_D.getPath().isValid());
		 System.out.println("	Dijkstra      : " + SPS_D.getPath().getLength() + " m");
		 
		 BellmanFordAlgorithm BE = new BellmanFordAlgorithm(Carre_Valid);
	     long startTime_Bellman = System.currentTimeMillis();
	     ShortestPathSolution SPS_B = DA.doRun();
	     long endTime_Bellman = System.currentTimeMillis();
	     System.out.println("	Bellman-Ford  : " + (endTime_Bellman - startTime_Bellman) + " millisecondes");
	     assertEquals(SPS_B.getStatus(),Status.OPTIMAL);
	     assertTrue(SPS_D.getPath().isValid());
	     System.out.println("	Bellman-Ford  : " + SPS_B.getPath().getLength() + " m");
	     System.out.println("\n/****************************************/\n");
		
      
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 @Test
     public void Invalid_Guadeloupe() throws Exception
	 {     
        
        System.out.println("Test map Guadeloupe et chemin invalide \n");
        ShortestPathData Invalid = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(4113), graph_Guadeloupe.get(7864),ArcInspectorFactory.getAllFilters().get(0) );
    	DijkstraAlgorithm DA = new DijkstraAlgorithm(Invalid);
		long startTime_Dijkstra = System.currentTimeMillis();
		ShortestPathSolution SPS_D = DA.doRun();
		long endTime_Dijkstra = System.currentTimeMillis();
		System.out.println("	Dijkstra      : " + (endTime_Dijkstra - startTime_Dijkstra) + " millisecondes");
		assertEquals(SPS_D.getStatus(),Status.INFEASIBLE);
		 
		BellmanFordAlgorithm BE = new BellmanFordAlgorithm(Invalid);
		long startTime_Bellman = System.currentTimeMillis();
		ShortestPathSolution SPS_B = DA.doRun();
		long endTime_Bellman = System.currentTimeMillis();
		System.out.println("	Bellman-Ford  : " + (endTime_Bellman - startTime_Bellman) + " millisecondes");
		assertEquals(SPS_B.getStatus(),Status.INFEASIBLE);
		System.out.println("\n/****************************************/\n");
	 }
	 
	 
	 @Test
     public void Valid_Distance_Guadeloupe() throws Exception
	 {
        
        
        
        System.out.println("Test map Guadeloupe et chemin invalide \n");
        ShortestPathData valid_Distance = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(33022), graph_Guadeloupe.get(15053),ArcInspectorFactory.getAllFilters().get(0) );
        DijkstraAlgorithm DA = new DijkstraAlgorithm(valid_Distance);
		long startTime_Dijkstra = System.currentTimeMillis();
		ShortestPathSolution SPS_D = DA.doRun();
		long endTime_Dijkstra = System.currentTimeMillis();
		System.out.println("	Dijkstra      : " + (endTime_Dijkstra - startTime_Dijkstra) + " millisecondes");
		assertEquals(SPS_D.getStatus(),Status.OPTIMAL);
        assertTrue(SPS_D.getPath().isValid());
		 
		BellmanFordAlgorithm BE = new BellmanFordAlgorithm(valid_Distance);
		long startTime_Bellman = System.currentTimeMillis();
		ShortestPathSolution SPS_B = DA.doRun();
		long endTime_Bellman = System.currentTimeMillis();
		System.out.println("	Bellman-Ford  : " + (endTime_Bellman - startTime_Bellman) + " millisecondes");
		assertEquals(SPS_B.getStatus(),Status.OPTIMAL);
        assertTrue(SPS_B.getPath().isValid());
		System.out.println("\n/****************************************/\n");
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 @Test
     public void Valid_Temps_Guadeloupe() throws Exception
	 {
        ShortestPathData valid_Temps = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(33022), graph_Guadeloupe.get(15053),ArcInspectorFactory.getAllFilters().get(2) );
        DijkstraAlgorithm DA = new DijkstraAlgorithm(valid_Temps);
        ShortestPathSolution SPS = DA.doRun();
        assertEquals(SPS.getStatus(),Status.OPTIMAL);
        assertTrue(SPS.getPath().isValid());
	 }
	 @Test
     public void Valid_bicycle_Guadeloupe() throws Exception
	 {
        ShortestPathData valid_bicycle = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(33022), graph_Guadeloupe.get(15053),ArcInspectorFactory.getAllFilters().get(3) );
        DijkstraAlgorithm DA = new DijkstraAlgorithm(valid_bicycle);
        ShortestPathSolution SPS = DA.doRun();
        assertEquals(SPS.getStatus(),Status.OPTIMAL);
        assertTrue(SPS.getPath().isValid());
	 }
	
} 