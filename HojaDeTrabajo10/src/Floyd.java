import java.io.IOException;
/**
 * 
 * @author Daniela Batz 19214
 *
 */
public class Floyd {
	RFile a = new RFile();
	IGrafo D;
	int [][] P;
	int [] max;
	int centro;
	int minimo = 10000;
	
	public Floyd(){
		
		try {
			a.ReadFile("guategrafo.txt");
			a.arreglosN(); //Nombre de los nodos
			D = a.CostoM(); // Peso de los arcos entre los nodos
			P = new int [25][25]; //matriz de nodos intermedios
			max = new int[25];
		}catch (IOException ex) {
			ex.printStackTrace();
		}
		for (int n=0;n<a.graf.size();n++){
			for (int m=0;m<a.graf.size();m++){
				P[n][m]=10000;
			}
		}
		
	}
	/**
	 * Metodo para encontar el camino corto en el grafo
	 */
	public void CamCorto(){
		for (int k=0; k<D.size();k++) {
			for (int i=0;i<D.size();i++){
				for (int j=0;j<D.size();j++) {
					if(D.getEdge(D.get(i), D.get(j))>(D.getEdge(D.get(i), D.get(k))+D.getEdge(D.get(k),D.get(j)))) {
						D.addEdge(D.get(i), D.get(j), (D.getEdge(D.get(i), D.get(k))+D.getEdge(D.get(k), D.get(j))));
                        P[i][j]=k;
					}
				}
			}
		}
	}
	
	/**
	 * Este metodo sirve para encontrar el centro de los grafos
	 */
	public void CentGrafo() {
		CamCorto();
		int n=0;
		//Maximos de cada columna de la matriz 
		for (int i=0;i<D.size();i++) {
			for (int j=0;j<D.size()-1;j++) {
				int num1=D.getEdge(D.get(j), D.get(i));
				n=j;
				n++;
				int num2=D.getEdge(D.get(n), D.get(i));
				if(num1>num2) {
					max[i]=num1;
				}
				else {
					max[i]=num2;
				}
			}
			n++;
		}
		//Minimo de los maximos de cada una de las columnas
		for (int i=0;i<D.size();i++) {
			int num1=max[i];
			if (num1<minimo) {
				centro=i;
				minimo=num1;
			}
		}
		System.out.println("El centro del grafo es: \n"+ D.get(centro)+"\n");
	}
	/**
	 * Metodo para mostrar las intermedias
	 * @param num1
	 * @param num2
	 */
	public void Intermedios (int num1, int num2) {
		if (P[num1][num2]!=10000) {
			Intermedios(num1, P[num1][num2]);
			System.out.println(" , "+D.get(P[num1][num2]));
			Intermedios(P[num1][num2],num2);
			return;
		}
		else return;
	}
}