/*
 *@For: Universidad del Valle de Guatemala
 *@author: Javier Alejandro Cotto Argueta
 *@Carne: 19324
 *@from: https://devs4j.com/2017/11/24/implementa-un-grafo-de-ciudades-en-java/
 */

import java.util.ArrayList;

public class Matriz{
	
	ArrayList<Vertex> rows = new ArrayList<Vertex>();
	ArrayList<Vertex> columns = new ArrayList<Vertex>();
	ArrayList<Integer> excentric = new ArrayList<Integer>();
	Vertex center;
	int min;
	
	public void addNode(Vertex node){
		rows.add(node);
		columns.add(node);
	}
	
	//Implementacion del algoritmo de floyd para calcular la distancia mas corta
	public int getShortestDistance(Vertex node){
		int distance = 999999999;
		int i = rows.indexOf(node);
		for(int j = 0; j<columns.size(); j++){
			String identifier = rows.get(j).getName();
			if(columns.get(i).getEdge(identifier) != null && j > 0){
				if(columns.get(i).getEdge(identifier).getDistance() < distance){
					distance = columns.get(i).getEdge(identifier).getDistance();
				}
			}
			else if(columns.get(i).getEdge(identifier) != null && j == 0){
				distance = columns.get(i).getEdge(identifier).getDistance();// falta comparar las distancias
			}
		}
		return distance;
	}
	
	public void collectExcentrity(){
		//Crea la lista con las excentricidades
		for(int i = 0; i<rows.size(); i++){
			excentric.add(this.getShortestDistance(rows.get(i)));
		}
		
		//Busca la excentricidad menor
		if(excentric.size()>0){
			min = excentric.get(0);
		}
		
		for(int j = 0; j<excentric.size(); j++){
			if(j < (excentric.size()-1)){
				if(excentric.get(j+1) < min){
					min = excentric.get(j+1);
				}
			}
		}
		
		//Busca el vertice al que pertenece la excentricidad hallada
		for(int i = 0; i<rows.size(); i++){
			for(int j = 0; j<columns.size(); j++){
				if(rows.get(i).getEdge(columns.get(j).getName()).getDistance() == min){
					center = rows.get(i);
				}
			}
		}
	}
	
	public Vertex getCenter(){
		this.collectExcentrity();
		return center;
	}
}