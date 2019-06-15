package server;

import java.util.ArrayList;

import BaseDatos.Imagen;

public class Libreria {
	
	private static Libreria INSTANCE = new Libreria();
	private static BDDirector director = new BDDirector();
	private static RAID raid = new RAID();
	
	
	public static Libreria obtenerLibreria() {
		return INSTANCE;
	}
	
	public String verTodo(){
		ArrayList<Imagen> list = director.verTodo();
		for (int i = 0; i < list.size();i++) {
			list.get(i).setDatos(raid.getDato(list.get(i).getId()));;	
		}
		return director.serializador(list);
	}
	public String obtenerImagen(String id) {
		Imagen img = director.obtenerImagen(id);
		img.setDatos(raid.getDato(id));
		return serializarImagen(img);
	}
	public void guardarImagen(Imagen img) {
		raid.setDato(img.getId(), img.getDatos());
		director.guardarImagen(img);
	}
	public void renovarImagen(String id, Imagen img) {
		director.renovarImagen(id, img);
	}
	public void borrarImagen(String id) {
		director.borrarImagen(id);
	}
	public String serializarImagen(Imagen img) {
		return director.serializarImagen(img);
	}
	public ArrayList<Imagen> deserializador(String str) {
		return director.deserializador(str);
	}
	public Imagen deserializarImagen(String str) {
		return director.deserializarImagen(str);
	}
	public void cerrarBD() {
		director.cerrarBD();
	}
}
