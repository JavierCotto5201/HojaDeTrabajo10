/*
 *@For: Universidad del Valle de Guatemala
 *@author: Javier Alejandro Cotto Argueta
 *@Carne: 19324
 *@from: https://devs4j.com/2017/11/24/implementa-un-grafo-de-ciudades-en-java/
 *@from: https://blog.openalfa.com/como-leer-un-fichero-de-texto-linea-a-linea-en-java
 *@from: https://es.wikibooks.org/wiki/Programaci%C3%B3n_en_Java/Ap%C3%A9ndices/Implementaci%C3%B3n_del_Algoritmo_de_Kruskal_en_Java
 */
import java.util.*;
import java.io.*;

public class GraphBuilder{
	
	Grafo graph = new Grafo("GRAFO");
	Matriz matt = new Matriz();
	ArrayList<Vertex> list;
	ArrayList<Edge> collection;
	boolean active;
	Scanner screen = new Scanner(System.in);
	
	public void Comenzar(){
		active = true;
		this.readFile();
		list = graph.getList();
		
		while(active){
			System.out.println("\nMenu de opciones \n1. Mostrar grafo \n2. Agregar ruta \n3. Buscar ruta \n4. Salir \n");
			int input = screen.nextInt();
			
			if(input == 1){
				this.mostrarGrafo();
			}
			else if(input == 2){
				this.Informacion();
			}
			else if(input == 3){
				this.Buscar();
			}
			else if(input == 4){
				System.out.println("Gracias por usar el programa... \n");
				active = false;
			}
			else{
				System.out.println("\nPorfavor ingrese una opcion valida \n");
			}
			
			list = graph.getList(); //Se actualiza la lista de rutas
		}
	}
	
	private void readFile(){
		File file = new File("./src/guategrafo.txt");
		int lineCounter = 0;
		try{
			Scanner reader = new Scanner(file);
			
			while(reader.hasNext()){
				//Contador para saber en que linea fallo en caso de una excepcion
				lineCounter += 1;
				
				//Se lee la siguiente linea y se separa donde haya espacios
				String line = reader.nextLine();
				String[] words = line.split(" ");
				
				//Se crea el vertice con su respectiva direccion hacia otro vertice
				Vertex newOne = new Vertex(words[0]);
				Vertex destiny = new Vertex(words[1]);
				Edge newLine = new Edge(destiny, Integer.parseInt(words[2]));
				newOne.addEdge(newLine);
				
				//Se agrega el vertice a la matriz
				matt.addNode(newOne);
				matt.addNode(destiny);
				
				//Se agrega el vertice al grafo
				graph.addNode(newOne);
				
			}
		}
		catch(Exception e){
			System.out.println("\n*** Hay un error de sintaxis en el archivo en la linea " + lineCounter + " *** \n");
		}
	}
	
	private void mostrarGrafo(){
		System.out.println("\n-----------------------------------------------------------------------");
		for(int i = 0; i<list.size(); i++){
			
			collection = list.get(i).getCollection();
			
			for(int a = 0; a<collection.size(); a++){
				System.out.println("Origen: " + list.get(i).getName() + " --> Destino: " + collection.get(a).getDestination().getName() +
				" --> La distancia mas corta es: " + matt.getShortestDistance(list.get(i)) + " KM. ");
			}
		}
		System.out.println("----------------------------------------------------------------------- \n");
		
	}
	
	public void Informacion(){
		boolean done = false;
		while(!done){
			try{
				System.out.println("\nIngrese el nombre del origen");
				String answer = screen.next();
				Vertex origin = new Vertex(answer);
				screen.nextLine(); //Se limpia el buffer
				
				System.out.println("\nIngrese el nombre del destino");
				answer = screen.next();
				Vertex destiny = new Vertex(answer);
				screen.nextLine(); //Se limpia el buffer
				
				System.out.println("\nIngrese la distancia entre los dos lugares");
				int distance = screen.nextInt();
				screen.nextLine(); //Se limpia el buffer
				
				//Se crea la ruta
				Edge route = new Edge(destiny, distance);
				origin.addEdge(route);
				
				//Se agregan los vertices a la matriz
				matt.addNode(origin);
				matt.addNode(destiny);
				
				//Se agrega la ruta al grafo
				graph.addNode(origin);
				
				done = true;
			}
			catch(Exception e){
				System.out.println("Error: porfavor ingrese la informacion correctamente...");
				done = true;
			}
		}
	}
	
	public void Buscar(){
		boolean done = false;
		while(!done){
			System.out.println("\nIngrese el nombre del origen");
			String origin = screen.next();
			screen.nextLine();
			
			System.out.println("\nIngrese el nombre del destino");
			String destiny = screen.next();
			screen.nextLine();
			
			String response = "";
			
			Vertex temporal = null;
			boolean existence = false;
			
			for(int i = 0;i<list.size();i++){
				
				if(list.get(i).getName().equalsIgnoreCase(origin)){
					response += "\nEl origen \"" + origin + "\" ";
					collection = list.get(i).getCollection();
					boolean exists = false;
					existence = true;
					
					for(int a =0; a<collection.size(); a++){
						if(collection.get(a).getDestination().getName().equalsIgnoreCase(destiny)){
							response += "va hacia el destino \"" + destiny + "\" a una distancia de " + collection.get(a).getDistance() + "KM \n";
							exists = true;
						}
					}
					
					if(!exists){
						response += "no tiene un ruta existente hacia \"" + destiny + "\" \n";
					}
				}
				
				if(i == (list.size()-1) && !existence){
					response += "\nEl origen \"" + origin + "\" no existe \n";
				}
			}
			
			System.out.println(response);
			done = true;
		}
	}
}
