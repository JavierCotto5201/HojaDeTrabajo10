/*
 *@For: Universidad del Valle de Guatemala
 *@author: Javier Alejandro Cotto Argueta
 *@Carne: 19324
 *@from: https://devs4j.com/2017/11/24/implementa-un-grafo-de-ciudades-en-java/
 */

import java.util.ArrayList;

public class Grafo{
	
	String name;
	ArrayList<Vertex> nodo = new ArrayList<Vertex>();
	//Constructor
	public Grafo(String nombre) {
		name=nombre;
	}
	//Añadir nodo
	public void addNode(Vertex point){
		nodo.add(point);
	}
	//Obtener nodo 
	public Vertex getNode(String point){
		Vertex result = null;
		for(int i = 0; i<nodo.size(); i++){
			if(point.equalsIgnoreCase(nodo.get(i).getName())){
				result = nodo.get(i);
			}
		}
		return result;
	}
	//Elimianr nodo
	public boolean deleteNode(String point){
		boolean completed = false;
		for(int i = 0; i<nodo.size(); i++){
			if(point.equalsIgnoreCase(nodo.get(i).getName())){
				nodo.remove(i);
				completed = true;
			}
		}
		return completed;
	}
	//Setters y Getters
	public String getName(){
		return name;
	}
	
	public ArrayList<Vertex> getList(){
		return nodo;
	}
	
}
