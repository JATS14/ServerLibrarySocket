package server;

import java.net.*;
import java.io.*;
public class RAID {
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String ip = "192.168.0.9";
    private int port = 8085;
    
    
	public String getDato(String id){
		String h = "010001011";
		//id = "\""+id+"\"";
		try {h = getDatoRAID(id);
		if(h == null) {h="Me Cago en Blen :)";}}
		catch(IOException e) {}
		return h;
	}
	
	public void setDato(String id,String dato){
		String enviar = id + "-"+ dato;
		try {setDatoRAID(enviar);}
		catch(IOException  e){}
		return;
	}
	private String getDatoRAID(String id) throws IOException {
		startConnection(ip,port);
		String data = sendMessage(id);
		stopConnection();
		return data;	
	}
	private void setDatoRAID(String enviar) throws IOException {
		startConnection(ip,port);
		sendMessage(enviar);
		stopConnection();	
	}
	
    private void startConnection(String ip1, int port1) throws IOException {
        clientSocket = new Socket(ip1, port1);
        out = new PrintWriter(clientSocket.getOutputStream(), true);
        System.out.println("se conecto");
        in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }
 
    private String sendMessage(String msg) throws IOException {
        out.println(msg);
        String resp = in.readLine();
        return resp;
    }
 
    private void stopConnection() throws IOException {
        in.close();
        out.close();
        clientSocket.close();
    }
    
	
	
}
