package server;

import java.net.*;
import java.io.*;
public class RAID {
	
	private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String ip = "";
    private int port = 0;
    
    
	public String getDato(String id){
		String h = "010001011";
		try {h = getDatoRAID(id);}
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
