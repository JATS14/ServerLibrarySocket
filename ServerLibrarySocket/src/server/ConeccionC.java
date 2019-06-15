package server;

import BaseDatos.Imagen;
import java.io.*;
import java.net.*;

//Clase que controla la comunicacion con el servidor
//en java y en cliente en C++, por medio de sockets
public class ConeccionC implements Runnable{
	
	
	private static String todo = "";
	private static String imagen = "";

	public ConeccionC() {
		Thread hilo = new Thread(this);
		hilo.start();
	}
	
	public static void main(String[] args) throws IOException{
		new ConeccionC();
	}
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
	@Override
	public void run() {
		int port = 6611;
		try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Servidor esta escuchando en puerto: " + port);
            while(true){
                try{
                    System.out.println("Esperando una coneccion");
                    Socket socket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream())); 
                    System.out.println("------Se ha conectado un Cliente-----");
                    String inputLine; 
                    inputLine = in.readLine();
                    boolean confirmacion = false;
                    System.out.println("Cliente dice: " + inputLine);
                    //bUSCAR TODO
                    if (inputLine.startsWith("1") && confirmacion == false) {
                        System.out.println("Cliente Hace comando: " + "1");
                        OutputStream output = socket.getOutputStream();
                        PrintWriter wr = new PrintWriter(output, true);
                        verTodo();
                    	if(todo != null) {
                    		System.out.println("Cliente Pide todos los datos de la BD");
                    		System.out.println("Se envia al Cliente: " + todo);
                    		wr.println(todo);
                    	}
                    	confirmacion = true;
                    wr.close();
                    }//bUSCAR POR ID
                    if (inputLine.startsWith("2")) {
                        System.out.println("Cliente Hace comando: 2... con texto: " + inputLine );
                        OutputStream output = socket.getOutputStream();
                        PrintWriter wr = new PrintWriter(output, true);
                        String id = inputLine.substring(1);
                        obtenerImagen(id);
                		System.out.println("Buscar por id" + id);
                    	System.out.println("Se envia al Cliente: " + imagen);
                    	wr.println(imagen);
                    	wr.close();
                    }//INSERTAR
                    if (inputLine.startsWith("3")) {
                        System.out.println("Cliente Hace comando: 3");
                        OutputStream output = socket.getOutputStream();
                        PrintWriter wr = new PrintWriter(output, true);
                        String insertar = inputLine.substring(1);
                        guardarImagen(Libreria.obtenerLibreria().deserializarImagen(insertar));
                		System.out.println("Cliente inserar imagen: " + insertar);
                    	wr.println("Se agrego correctamente");
                    	wr.close();
                    }//BORRAR
                    if (inputLine.startsWith("4")) {
                        System.out.println("Cliente Hace comando: 4");
                        OutputStream output = socket.getOutputStream();
                        PrintWriter wr = new PrintWriter(output, true);
                        String borrar = inputLine.substring(1);
                        borrarImagen(borrar);
                		System.out.println("Cliente borro imagen: " + borrar);
                    	wr.println("Se Borro correctamente");
                    	wr.close();
                    }//MODIFICAR
                    if (inputLine.startsWith("5")) {
                        System.out.println("Cliente Hace comando: 5");
                        OutputStream output = socket.getOutputStream();
                        PrintWriter wr = new PrintWriter(output, true);
                        String modificar = inputLine.substring(1);
                        String div1 = modificar.substring(0, 2);
                        String imag = inputLine.substring(3);
                        renovarImagen(div1,Libreria.obtenerLibreria().deserializarImagen(imag));
                		System.out.println("Cliente Actualizo imagen con id: " + modificar + "por: " + div1);
                    	wr.println("Se actualizo correctamente");
                    	wr.close();
                    }
                    System.out.println("----Fin de la coneccion----");
                    cerrarBD();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
      
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
}

}
	
