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
	//Inicialización de variables
	Grafo graph = new Grafo("GRAFO");
	Matriz matt = new Matriz();
	ArrayList<Vertex> list;
	ArrayList<Edge> collection;
	boolean active;
	Scanner screen = new Scanner(System.in);
	
	//Metodo para comenzar el programa
	public void Comenzar(){
		active = true;
		this.readFile();
		list = graph.getList();
		
		//En caso de que se este corriendo, muestra el menu de opciones
		while(active){
			System.out.println("\nMenu de opciones \n1. Mostrar grafo \n2. Agregar ruta \n3. Buscar ruta \n4. Salir \n");
			int op = screen.nextInt();
			
			//Dependiendo la opcion se dirige a realizar una acción
			if(op == 1){
				this.mostrarGrafo();
			}
			else if(op == 2){
				this.Informacion();
			}
			else if(op == 3){
				this.Buscar();
			}
			else if(op == 4){
				System.out.println("Vuelva Pronto\n");
				active = false;
			}
			else{
				//En caso s eingrese un valor que no este dentro de las opciones, muestra error
				System.out.println("\nEsta opción no es valida, ingrese una nueva \n");
			}
			
			list = graph.getList(); //Se actualiza la lista de rutas
		}
	}
	//Metodo para la lectura del archivo de texto
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
			//Error en caso de que el archivo de texto tenga algun error de sintaxis "CIUDAD1 CIUDAD2 DISTANCI"
			System.out.println("\n*** Hay un error de sintaxis en el archivo en la linea " + lineCounter + " *** \n");
		}
	}
	//Metodo para mostrar grafo
	private void mostrarGrafo(){
		//Encabezado
		System.out.println("\n-----------------------------------------------------------------------");
		for(int i = 0; i<list.size(); i++){
			//Agrega en la colección la lista
			collection = list.get(i).getCollection();
			//Mostrar origne, destino, distancia de cada ruta
			for(int a = 0; a<collection.size(); a++){
				//mensaje para mostrar el grafo
				System.out.println("Origen: " + list.get(i).getName() + " Destino: " + collection.get(a).getDestination().getName() +
				" --> La distancia es de: " + matt.getShortestDistance(list.get(i)) + " km ");
			}
		}
		//Cierre de encabezado
		System.out.println("----------------------------------------------------------------------- \n");
		
	}
	//Metodo para recabar añadir una ruta nueva
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
				//Error
				System.out.println("Error: Ingrese correctamente las ciudades y distancia entre ellas");
				done = true;
			}
		}
	}
	//Metodo para la busca de una ruta existente
	public void Buscar(){
		boolean done = false;
		//Se llenan los datos para encontrar la ruta
		while(!done){
			System.out.println("\nIngrese el nombre del origen");
			String origin = screen.next();
			screen.nextLine();
			
			System.out.println("\nIngrese el nombre del destino");
			String destiny = screen.next();
			screen.nextLine();
			//Variables
			String respuesta = "";
			
			Vertex temporal = null;
			boolean existence = false;
			
			for(int i = 0;i<list.size();i++){
				//Comparación del origen ingresado con el que este en el archivo de texto
				if(list.get(i).getName().equalsIgnoreCase(origin)){
					respuesta += "\nEl origen \"" + origin + "\" ";
					collection = list.get(i).getCollection();
					boolean exists = false;
					existence = true;
					
					for(int a =0; a<collection.size(); a++){
						//Comparación del destino ingresado y con el que esta en el archivo de texto
						if(collection.get(a).getDestination().getName().equalsIgnoreCase(destiny)){
							respuesta += "va hacia el destino \"" + destiny + "\" a una distancia de " + collection.get(a).getDistance() + "KM \n";
							exists = true;
						}
					}
					
					if(!exists){
						//En caso no exista el destino, muestra este mensaje
						respuesta += "no tiene un ruta existente hacia \"" + destiny + "\" \n";
					}
				}
				
				if(i == (list.size()-1) && !existence){
					//En caso no exista el origen, muestra el mensaje
					respuesta += "\nEl origen \"" + origin + "\" no existe \n";
				}
			}
			//Imprime la variable respuesta
			System.out.println(respuesta);
			done = true;
		}
	}
}
