package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
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

public class AStar_Test 
{
	 public static Graph graph_Carre;
	 public static Graph graph_Insa;
	 public static Graph graph_Guadeloupe;
	 
	 @BeforeClass
	 public static void initAll() throws IOException 
	 {
		// Visit these directory to see the list of available files on Commetud.
	        String mapCarre = "C:\\Users\\marco\\Downloads\\carre.mapgr";
	 
	        // Create a graph reader.
	        GraphReader Carre = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapCarre))));

	        // Read the graph.
	        graph_Carre = Carre.read();
	       
	        // Visit these directory to see the list of available files on Commetud.
	        String mapInsa = "C:\\Users\\marco\\Downloads\\insa.mapgr";
	 
	        // Create a graph reader.
	        GraphReader Insa = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapInsa))));

	        // Read the graph.
	        graph_Insa = Insa.read();
	        
	        
	        // Visit these directory to see the list of available files on Commetud.
	        String mapGuadeloupe = "C:\\Users\\marco\\Downloads\\guadeloupe.mapgr";
	 
	        // Create a graph reader.
	        GraphReader Guadeloupe = new BinaryGraphReader(
	                new DataInputStream(new BufferedInputStream(new FileInputStream(mapGuadeloupe))));

	        // Read the graph.
	        graph_Guadeloupe = Guadeloupe.read();
		 
	 }	 
	 
	 @Test
     public void Carre_Valid() throws Exception
	 {
		ShortestPathData Carre_Valid = new ShortestPathData(graph_Carre, graph_Carre.get(23), graph_Carre.get(16),ArcInspectorFactory.getAllFilters().get(0) );
		AStarAlgorithm DAa = new AStarAlgorithm(Carre_Valid);
        ShortestPathSolution SPSa = DAa.doRun();
        assertEquals(SPSa.getStatus(),Status.OPTIMAL);
        assertTrue(SPSa.getPath().isValid());
	 }

	 @Test
     public void Empty_Insa() throws Exception
	 {
		 System.out.println("\n/****************************************/\n");
		 System.out.println("Test map Insa et chemin vide \n");
		 ShortestPathData EmptyPath = new ShortestPathData(graph_Insa, null, null,ArcInspectorFactory.getAllFilters().get(0) );
		 AStarAlgorithm DAa = new AStarAlgorithm(EmptyPath);
	     long startTime_AStar = System.currentTimeMillis();
	     ShortestPathSolution SPS_D = DAa.doRun();
	     long endTime_AStar = System.currentTimeMillis();
	     System.out.println("	AStar      : " + (endTime_AStar - startTime_AStar) + " millisecondes");
	     assertEquals(SPS_D.getStatus(),Status.UNKNOWN);
	     //System.out.println("	AStar      : " + SPS_D.getPath().getLength() + " m");
	 }

	 @Test
     public void Valid_Insa() throws Exception
	 {
		 System.out.println("Test map Insa et chemin valide en longueur \n");
		 ShortestPathData Valid = new ShortestPathData(graph_Insa, graph_Insa.get(255), graph_Insa.get(525),ArcInspectorFactory.getAllFilters().get(0) );
		 AStarAlgorithm DAa = new AStarAlgorithm(Valid);
		 long startTime_AStar = System.currentTimeMillis();
	     ShortestPathSolution SPS_D = DAa.doRun();
	     long endTime_AStar = System.currentTimeMillis();
	     System.out.println("	AStar      : " + (endTime_AStar - startTime_AStar) + " millisecondes");
		 assertEquals(SPS_D.getStatus(),Status.OPTIMAL);
		 assertTrue(SPS_D.getPath().isValid());
		 System.out.println("	AStar      : " + SPS_D.getPath().getLength() + " m");
	 }
	 
	 @Test
     public void Invalid_Guadeloupe() throws Exception
	 {
        ShortestPathData Invalid = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(4113), graph_Guadeloupe.get(7864),ArcInspectorFactory.getAllFilters().get(0) );
        AStarAlgorithm DAa = new AStarAlgorithm(Invalid);
        ShortestPathSolution SPS = DAa.doRun();
        assertEquals(SPS.getStatus(),Status.INFEASIBLE);
	 }
	 @Test
     public void Valid_Distance_Guadeloupe() throws Exception
	 {
        ShortestPathData valid_Distance = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(33022), graph_Guadeloupe.get(15053),ArcInspectorFactory.getAllFilters().get(0) );
        AStarAlgorithm DAa = new AStarAlgorithm(valid_Distance);
        ShortestPathSolution SPS = DAa.doRun();
        assertEquals(SPS.getStatus(),Status.OPTIMAL);
        assertTrue(SPS.getPath().isValid());
	 }
	 @Test
     public void Valid_Temps_Guadeloupe() throws Exception
	 {
        ShortestPathData valid_Temps = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(33022), graph_Guadeloupe.get(15053),ArcInspectorFactory.getAllFilters().get(2) );
        AStarAlgorithm DAa = new AStarAlgorithm(valid_Temps);
        ShortestPathSolution SPS = DAa.doRun();
        assertEquals(SPS.getStatus(),Status.OPTIMAL);
        assertTrue(SPS.getPath().isValid());
	 }
	 @Test
     public void Valid_bicycle_Guadeloupe() throws Exception
	 {
        ShortestPathData valid_bicycle = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(33022), graph_Guadeloupe.get(15053),ArcInspectorFactory.getAllFilters().get(3) );
        AStarAlgorithm DAa = new AStarAlgorithm(valid_bicycle);
        ShortestPathSolution SPS = DAa.doRun();
        assertEquals(SPS.getStatus(),Status.OPTIMAL);
        assertTrue(SPS.getPath().isValid());
	 }
}