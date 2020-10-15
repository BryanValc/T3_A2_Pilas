import java.util.ArrayList;
import java.util.Stack;

interface RentaPeliculas{
	
	public static ArrayList<Pelicula> crear(){
		ArrayList<Pelicula> pila = new ArrayList<Pelicula>();
		return pila;
	}
	public static ArrayList<Pelicula> apilar(ArrayList<Pelicula> pila, Pelicula pelicula){
		pila.add(pelicula);
		return pila;
	}
	public static ArrayList<Pelicula> desapilar(ArrayList<Pelicula> pila){
		pila.remove(pila.size()-1);
		return pila;
	}
	public static Pelicula cima(ArrayList<Pelicula> pila){
		return pila.get(pila.size()-1);
	}
	public static boolean vacia(ArrayList<Pelicula> pila) {
		return pila.isEmpty();
	}
	
	
}

class Pelicula{
	private String pelicula;
	private String genero;
	
	public Pelicula(String pelicula, String genero) {
		this.pelicula = pelicula;
		this.genero = genero;
	}

	public String getPelicula() {
		return pelicula;
	}
	public void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}

	@Override
	public String toString() {
		return "Pelicula [pelicula=" + pelicula + ", genero=" + genero + "]";
	}

}

public class PruebaPilas {

	public static void main(String[] args) {
		
		
	}

}
