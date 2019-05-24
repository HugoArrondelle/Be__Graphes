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

public class AStar_Test 
{

}