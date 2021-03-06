import java.io.*; 
import java.util.*; 

interface Validacion{
	Scanner input = new Scanner(System.in);
	public static int validacionNatural() {
		int ret = 0;
		boolean err = false;
		do {
			try {
				ret = input.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Entrada no valida, intente de nuevo:");
				input.nextLine();
				err=true;
			}
			if (ret>0) {
				err=false;
			}else {
				System.out.println("Solo n�meros naturales");
				err=true;
			}
		}while(err);
		return ret;
	}
	public static int validacionCantidad() {
		int ret = 0;
		boolean err = false;
		do {
			try {
				ret = input.nextInt();
			} catch (java.util.InputMismatchException e) {
				System.out.println("Entrada no valida, intente de nuevo:");
				input.nextLine();
				err=true;
			}
			if (ret>4) {
				err=false;
			}else {
				System.out.println("Solo n�meros superiores o iguales a 5");
				err=true;
			}
		}while(err);
		return ret;
	}
}

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

	private String getPelicula() {
		return pelicula;
	}
	private void setPelicula(String pelicula) {
		this.pelicula = pelicula;
	}
	private String getGenero() {
		return genero;
	}
	private void setGenero(String genero) {
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
	public void cantidad(){
		System.out.println("Cantidad de peliculas disponibles: "+(puntero+1));
	}
	
	@Override
	public String toString() {
		return "ImplementacionPilaEstatica [peliculas=" + Arrays.toString(peliculas) + ", puntero=" + puntero + "]";
	}
	
}

class ImplementacionPilaDinamica{
	
	private Stack<Pelicula> stack=new Stack<Pelicula>();

	public ImplementacionPilaDinamica() {
	}
	public ImplementacionPilaDinamica(Stack<Pelicula> stack) {
		this.stack = stack;
	}
	
	private Stack<Pelicula> getStack() {
		return stack;
	}
	private void setStack(Stack<Pelicula> stack) {
		this.stack = stack;
	}
	
	private boolean vacia() {
		Stack<Pelicula> stack=this.getStack();
		return stack.empty();
	}
	
	public Pelicula rentar() {
		if (this.vacia()) {
			System.out.println("La pila esta vacia");
			return null;
		}else {
			Stack<Pelicula> stack = this.getStack();
			ArrayList<Pelicula> pls = new ArrayList<>(stack);
			Pelicula ret = RentaPeliculas.rentar(pls);
			pls = RentaPeliculas.desapilar(pls);
			stack.clear();
			stack.addAll(pls);
			this.setStack(stack);
			return ret;
		}	
	}
	public void regresar(Pelicula pelicula) {
		Stack<Pelicula> stack = this.getStack();
		ArrayList<Pelicula> pls = new ArrayList<>(stack);
		pls = RentaPeliculas.regresar(pls, pelicula);
		stack.clear();
		stack.addAll(pls);
		this.setStack(stack);
	}
	
	@Override
	public String toString() {
		return "ImplementacionPilaDinamica [stack=" + stack + "]";
	}
	public void cantidad(){
		System.out.println("Cantidad de peliculas disponibles: "+(this.getStack().size()));
	}
}

public class PruebaPilas {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		ImplementacionPilaEstatica ipe1 = new ImplementacionPilaEstatica(5);
		ImplementacionPilaDinamica ipd1 = new ImplementacionPilaDinamica();
		ImplementacionPilaDinamica rentadas = new ImplementacionPilaDinamica();
		Pelicula rentada = null;
		
		byte opc=0;
		boolean salir=false;
		boolean salir1=false;
		
		
		do {
			System.out.println("1)Usar pila estatica\n2)Usar pila dinamica\n3)Salir");
			opc = (byte) Validacion.validacionNatural();
			switch (opc) {
			case 1:
				do {
					salir=false;
					System.out.println("1)Cargar BD de peliculas\n2)Rentar pelicula\n3)Devolver pelicula\n4)Mostrar cantidad de peliculas disponibles para renta\n5)Salir");
					opc = (byte) Validacion.validacionNatural();
					switch (opc) {
					case 1:
						System.out.println("Cantidad(minimo 5):");
						int cnt = Validacion.validacionCantidad();
						ipe1 = new ImplementacionPilaEstatica(cnt);
						for(int i=0;i<cnt;i++) {
							System.out.println("Nombre:");
							String nombre=input.nextLine();
							System.out.println("Genero:");
							String genero=input.nextLine();
							ipe1.regresar(new Pelicula(nombre,genero));
						}
						break;
					case 2:
						rentada = ipe1.rentar();
						System.out.println(rentada);
						break;
					case 3:
						ipe1.regresar(rentada);
						break;
					case 4:
						ipe1.cantidad();
						System.out.println(ipe1);
						break;
					case 5:
						salir=true;
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
				} while (!salir);
				break;
			case 2:
				do {
					salir=false;
					System.out.println("1)Cargar BD de peliculas\n2)Rentar pelicula\n3)Devolver pelicula\n4)Mostrar cantidad de peliculas disponibles para renta\n5)Salir");
					opc = (byte) Validacion.validacionNatural();
					switch (opc) {
					case 1:
						System.out.println("Cantidad(minimo 5):");
						int cnt = Validacion.validacionCantidad();
						for(int i=0;i<cnt;i++) {
							System.out.println("Nombre:");
							String nombre=input.nextLine();
							System.out.println("Genero:");
							String genero=input.nextLine();
							ipd1.regresar(new Pelicula(nombre,genero));
						}
						break;
					case 2:
						rentada = ipd1.rentar();
						System.out.println(rentada);
						break;
					case 3:
						ipd1.regresar(rentada);
						break;
					case 4:
						ipd1.cantidad();
						System.out.println(ipd1);
						break;
					case 5:
						salir=true;
						break;
					default:
						System.out.println("Opcion no valida");
						break;
					}
				} while (!salir);
				break;
			case 3:
				salir1=true;
				break;
			default:
				System.out.println("Opcion no valida");
				break;
			}
		} while (!salir1);
		System.out.println("Fin de ejecucion");
		
	}

}
