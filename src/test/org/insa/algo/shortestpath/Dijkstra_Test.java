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
     public void Carre_Valid() throws Exception
	 {
        ShortestPathData Carre_Valid = new ShortestPathData(graph_Carre, graph_Carre.get(23), graph_Carre.get(16),ArcInspectorFactory.getAllFilters().get(0) );
        DijkstraAlgorithm DA = new DijkstraAlgorithm(Carre_Valid);
        ShortestPathSolution SPS = DA.doRun();
        assertEquals(SPS.getStatus(),Status.OPTIMAL);
        assertTrue(SPS.getPath().isValid());
	 }
	 @Test
     public void Empty_Insa() throws Exception
	 {
		 
		 ShortestPathData EmptyPath = new ShortestPathData(graph_Insa, null, null,ArcInspectorFactory.getAllFilters().get(0) );
	     DijkstraAlgorithm DA = new DijkstraAlgorithm(EmptyPath);
	     ShortestPathSolution SPS = DA.doRun();
	     assertEquals(SPS.getStatus(),Status.UNKNOWN);
	 }
	 @Test
     public void Valid_Insa() throws Exception
	 {
		 ShortestPathData Valid = new ShortestPathData(graph_Insa, graph_Insa.get(255), graph_Insa.get(525),ArcInspectorFactory.getAllFilters().get(0) );
		 DijkstraAlgorithm DA = new DijkstraAlgorithm(Valid);
		 ShortestPathSolution SPS = DA.doRun();
		 assertEquals(SPS.getStatus(),Status.OPTIMAL);
		 assertTrue(SPS.getPath().isValid());
	 }
	 @Test
     public void Invalid_Guadeloupe() throws Exception
	 {
        ShortestPathData Invalid = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(4113), graph_Guadeloupe.get(7864),ArcInspectorFactory.getAllFilters().get(0) );
        DijkstraAlgorithm DA = new DijkstraAlgorithm(Invalid);
        ShortestPathSolution SPS = DA.doRun();
        assertEquals(SPS.getStatus(),Status.INFEASIBLE);
	 }
	 @Test
     public void Valid_Distance_Guadeloupe() throws Exception
	 {
        ShortestPathData valid_Distance = new ShortestPathData(graph_Guadeloupe, graph_Guadeloupe.get(33022), graph_Guadeloupe.get(15053),ArcInspectorFactory.getAllFilters().get(0) );
        DijkstraAlgorithm DA = new DijkstraAlgorithm(valid_Distance);
        ShortestPathSolution SPS = DA.doRun();
        assertEquals(SPS.getStatus(),Status.OPTIMAL);
        assertTrue(SPS.getPath().isValid());
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