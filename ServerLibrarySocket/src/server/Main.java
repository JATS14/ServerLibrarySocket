package server;
import java.io.*;
 

import BaseDatos.Imagen;

public class Main {

	/*
	 * 
	 * SIRVE PARA PROBAR SOCKETS 
	 * SERVER <-> SERVER RAID 
	 * 
	 */
	public static void main(String[] args) throws IOException {
		
		RAID raid = new RAID();
		String datos = "p3~#in~800~600~255~19~18~15~17~167~53~83~94~75";
		Imagen img = new Imagen("007","perro","2014","Pedro","800-900","FOTOdePERROS",datos);
		
		//GET DATO
			System.out.println(raid.getDato(img.getId()));
		
		//SET DATO
			//raid.setDato(img.getId(), img.getDatos());;
		
	}

}
