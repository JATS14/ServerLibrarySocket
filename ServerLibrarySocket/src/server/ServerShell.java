package server;

import java.io.IOException;
import java.util.Scanner;
import BaseDatos.Imagen;

public class ServerShell{

	public static Scanner scan = new Scanner(System.in);
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
	
	public static void run() throws IOException {
		try {
		System.out.println("---------------BD Shell------------");
		String str = scan.nextLine();
		String[] comand = str.split(" ");
		if (comand[0].equals("SELECT")) {
				String findTodo = scan.nextLine();
				String[] donde = findTodo.split(" ");
				String where = scan.nextLine();
				String[] condi = where.split(" ");
				System.out.println("Buscando en Colecion: " + donde[1] + ", Condicion: " + condi[1]);
				if(comand[1].equals("*")){
					if (condi[1].equals("*")) {
						verTodo();
						System.out.println(todo);
					}else {
						obtenerImagen(condi[1]);
						System.out.println(imagen);
					}	
				}if(comand[1].equals("NOMBRE")){
						obtenerImagen(condi[1]);
						Imagen img = Libreria.obtenerLibreria().deserializarImagen(imagen);
						System.out.println(img.getNombre());
				}if(comand[1].equals("DESCRIPCION")){
						obtenerImagen(condi[1]);
						Imagen img = Libreria.obtenerLibreria().deserializarImagen(imagen);
						System.out.println(img.getDescripcion());
				}if(comand[1].equals("AUTOR")){
					obtenerImagen(condi[1]);
					Imagen img = Libreria.obtenerLibreria().deserializarImagen(imagen);
					System.out.println(img.getAutor());
				}
		}if (str.equals("INSERT INTO")) {
			System.out.println("INSERT");
			
		}if (comand[0].equals("DELETE")) {
			System.out.println("DELETE");
			
		}if (comand[0].equals("UPDATE")) {
			System.out.println("UPDATE");
			
		}if (comand[0].equals("CLOSE")) {
			System.out.println("CLOSE");
		
		}
		}catch(IOException e) {
			
		}
		run();
	}

	public static void main(String[] args) throws IOException {
		run();
	}
}
