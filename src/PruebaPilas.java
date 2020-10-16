import java.util.ArrayList;
import java.util.Arrays;
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
		if (RentaPeliculas.vacia(pila)) {
			System.out.println("La pila esta vacia");
			return null;
		}else {
			pila.remove(pila.size()-1);
			return pila;
		}
	}
	public static Pelicula cima(ArrayList<Pelicula> pila){
		if (RentaPeliculas.vacia(pila)) {
			System.out.println("La pila esta vacia");
			return null;
		}else {
			return pila.get(pila.size()-1);
		}
	}
	public static boolean vacia(ArrayList<Pelicula> pila) {
		return pila.isEmpty();
	}
	public static Pelicula rentar(ArrayList<Pelicula> pila){
		System.out.println("Rentando pelicula ...");
		Pelicula ret =  RentaPeliculas.cima(pila);
		System.out.println(ret==null ? "No se pudo rentar la pelicula":"pelicula rentada exitosamente");
		return ret;
	}
	public static ArrayList<Pelicula> regresar(ArrayList<Pelicula> pila,Pelicula pelicula){
		System.out.println("Regresando pelicula ...");
		int iSize=pila.size();
		RentaPeliculas.apilar(pila,pelicula);
		int fSize=pila.size();
		System.out.println((iSize+1)==fSize ? "Pelicula regresada correctamente":"No se pudo regresar la pelicula");
		return pila;
	}
}

class Pelicula{
	private String pelicula;
	private String genero;
	
	public Pelicula() {
	}
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

class ImplementacionPilaEstatica{
	private Pelicula peliculas[];
	private int puntero;
	
	public ImplementacionPilaEstatica() {
	}
	public ImplementacionPilaEstatica(int longitud) {
		this.peliculas = new Pelicula[longitud];
		puntero=-1;
	}

	private Pelicula[] getPeliculas() {
		return peliculas;
	}
	private void setPeliculas(Pelicula[] peliculas) {
		this.peliculas = peliculas;
	}
	private int getPuntero() {
		return puntero;
	}
	private void setPuntero(int puntero) {
		this.puntero = puntero;
	}
	
	private boolean llena() {
		Pelicula pls[]=this.getPeliculas();
		return this.getPuntero()==(pls.length-1);
	}
	private boolean vacia() {
		return this.getPuntero()==(-1);
	}
	public Pelicula rentar() {
		if (this.vacia()) {
			System.out.println("La pila esta vacia");
			return null;
		}else{
			Pelicula peliculas[]=this.getPeliculas();
			Pelicula peliculastmp[]=new Pelicula[this.getPuntero()+1];
			for (int i = 0; i < peliculastmp.length; i++) {
				peliculastmp[i]=peliculas[i];
			}
			ArrayList<Pelicula> pls = new ArrayList<>(Arrays.asList(peliculastmp));
			Pelicula ret = RentaPeliculas.rentar(pls);
			pls = RentaPeliculas.desapilar(pls);
			Pelicula[] peliculasret= pls.toArray(new Pelicula[this.getPeliculas().length]);
			this.setPeliculas(peliculasret);
			this.setPuntero(this.getPuntero()-1);
			return ret;
		}
		
	}
	public void regresar(Pelicula pelicula) {
		if (this.llena()) {
			System.out.println("La pila estatica esta llena");
		}else {
			Pelicula peliculas[]=this.getPeliculas();
			Pelicula peliculastmp[]=new Pelicula[this.getPuntero()+1];
			for (int i = 0; i < peliculastmp.length; i++) {
				peliculastmp[i]=peliculas[i];
			}
			ArrayList<Pelicula> pls = new ArrayList<>(Arrays.asList(peliculastmp));
			pls = RentaPeliculas.regresar(pls, pelicula);
			Pelicula[] peliculasret= pls.toArray(new Pelicula[this.getPeliculas().length]);
			this.setPeliculas(peliculasret);
			this.setPuntero(this.getPuntero()+1);
		}
	}
	
	@Override
	public String toString() {
		return "ImplementacionPilaEstatica [peliculas=" + Arrays.toString(peliculas) + ", puntero=" + puntero + "]";
	}
	
}


public class PruebaPilas {

	public static void main(String[] args) {
		
		Pelicula p1 = new Pelicula("Endgame","Accion");
		Pelicula p2 = new Pelicula("Interstellar","Ciencia ficcion");
		ImplementacionPilaEstatica ipe1 = new ImplementacionPilaEstatica(10);
		ipe1.regresar(p1);
		ipe1.regresar(p2);
		/*ipe1.regresar(p1);
		ipe1.regresar(p2);
		ipe1.regresar(p1);
		ipe1.regresar(p2);
		ipe1.regresar(p1);
		ipe1.regresar(p2);
		ipe1.regresar(p1);
		ipe1.regresar(p2);
		ipe1.regresar(p1);
		ipe1.regresar(p2);
		ipe1.rentar();
		ipe1.rentar();
		ipe1.rentar();*/
		//Pelicula p2 = ipe1.rentar();
		System.out.println(ipe1);
		System.out.println(ipe1.rentar());
		
		System.out.println(ipe1);
		
	}

}
