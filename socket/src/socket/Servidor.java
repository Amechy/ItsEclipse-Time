package socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {
	static final int PUERTO = 5000;
	public Servidor() {
		ServerSocket serverSocket;
		try {
			serverSocket = new ServerSocket(PUERTO);
			
			
			int nCli = 0;
			while(true) {
				System.out.println("Servidor escuchando en: " + serverSocket.getLocalSocketAddress());
				Socket skAtencion = serverSocket.accept();
				
				nCli++;
				
				//Creamos el servidor hilo
				new ServidorHilo(skAtencion,nCli).start();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Servidor();
	}

}
