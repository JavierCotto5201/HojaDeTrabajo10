/*
 *@For: Universidad del Valle de Guatemala
 *@author: Javier Alejandro Cotto Argueta
 *@Carne: 19324
 *@from: https://devs4j.com/2017/11/24/implementa-un-grafo-de-ciudades-en-java/
 */

import java.util.ArrayList;

public class Vertex{
	
	String name;
	ArrayList<Edge> edges = new ArrayList<Edge>();
	boolean hasEdges = false;
	
	//Constructor
	public Vertex(String id){
		name = id;
	}
	
	//Añade una arista al vértice
	public void addEdge(Edge line){
		edges.add(line);
	}
	
	//Regresa la arista dirigida al vértice especificado
	public Edge getEdge(String destiny){
		Edge result = null;
		for(int i = 0; i<edges.size(); i++){
			if(edges.get(i).getDestination().getName().equalsIgnoreCase(destiny)){
				result = edges.get(i);
			}
		}
		return result;
	}
	
	public boolean deleteEdge(String destiny){
		boolean success = false;
		for(int i = 0; i<edges.size(); i++){
			if(edges.get(i).getDestination().getName().equals(destiny)){
				edges.remove(i);
				success = true;
			}
		}
		return success;
	}
	
	public boolean hasEdges(){
		if(edges.size() > 0){
			hasEdges = true;
		}
		else if(edges.size() == 0){
			hasEdges = false;
		}
		return hasEdges;
	}
	
	public String getName(){
		return name;
	}
	
	public ArrayList<Edge> getCollection(){
		return edges;
	}
	
}
