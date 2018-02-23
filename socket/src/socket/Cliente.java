package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import javax.xml.stream.util.EventReaderDelegate;


public class Cliente {

	static final String HOST = "192.168.3.57";
	static final int PUERTO = 5000;
	Scanner entrada;
	
	
	public Cliente() {
		try {
			Socket sckClient = new Socket(HOST, PUERTO);				
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(sckClient.getInputStream(),"utf-8"));
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in,"utf-8"));
			PrintWriter pWriter = new PrintWriter(new OutputStreamWriter(sckClient.getOutputStream(),"utf-8"),true);
			
			
			System.out.println(bufferedReader.readLine());
			
			//Enviamos un mensaje pedido por consola del servidor
			System.out.println("Mensaje para enviar?");
			String mensaje = teclado.readLine();
			System.out.println("Enviando al servidor el mensaje: "+mensaje);
			pWriter.println(mensaje);
			
			
			sckClient.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) {				
			new Cliente();		
	}
	
	

}
