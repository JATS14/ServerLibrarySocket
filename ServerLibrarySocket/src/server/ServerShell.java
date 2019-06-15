package server;

import java.io.IOException;
import java.util.Scanner;
import BaseDatos.Imagen;

public class ServerShell{

	private static String todo = "";
	private static String imagen = "";
	
	static private void verTodo() throws IOException {
		todo = Libreria.obtenerLibreria().verTodo();
		return;
	}
	static private void obtenerImagen(String id) throws IOException{
		imagen = Libreria.obtenerLibreria().obtenerImagen(id);
	}
	static private void guardarImagen(Imagen img) throws IOException{
		Libreria.obtenerLibreria().guardarImagen(img);
	}
	static private void renovarImagen(String id, Imagen n) {
		Libreria.obtenerLibreria().renovarImagen(id, n);	
	}
	static private void borrarImagen(String n) {
		Libreria.obtenerLibreria().borrarImagen(n);
	}
	static private void cerrarBD() {
		Libreria.obtenerLibreria().cerrarBD();
	}
	
	public static void run() {
		Scanner scan = new Scanner(System.in);
		System.out.println("---------------BD Shell------------");
		String str = scan.nextLine();
		if (str.equals("FIND")) {
			System.out.println("FIND");
			
		}if (str.equals("INSERT INTO")) {
			System.out.println("INSERT");
			
		}if (str.equals("DELETE")) {
			System.out.println("DELETE");
			
		}if (str.equals("UPDATE")) {
			System.out.println("UPDATE");
			
		}if (str.equals("CLOSE")) {
			System.out.println("CLOSE");
		
		}
		scan.close();
		run();
	}

	public static void main(String[] args) {
		run();
	}
}
