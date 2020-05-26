/*
 *@For: Universidad del Valle de Guatemala
 *@author: Javier Alejandro Cotto Argueta
 *@Carne: 19324
 *@from: https://devs4j.com/2017/11/24/implementa-un-grafo-de-ciudades-en-java/
 */

public class Edge{
	//Variables
	private int distancia;
	private Vertex destino;
	
	public Edge(Vertex destiny, int kilometers){
		distancia = kilometers;
		destino = destiny;
	}
	
	public int getDistance(){
		return distancia;
	}
	
	public Vertex getDestination(){
		return destino;
	}
	
}