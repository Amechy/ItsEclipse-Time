package amechine;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class cli_ssl {
	static final String DESTINO =  "localhost";
	static final int PUERTO =  5555;
	
	private void mostrarCifrados(SSLSocket socket) {
		String[] protocolos = socket.getEnabledProtocols();
		System.out.println("Protocolos habilitados: ");
		for (int i = 0; i < protocolos.length; i++) {
			System.out.println(protocolos[i]);
		}
		
		String[] protocolosDiponibles = socket.getSupportedProtocols();
		System.out.println("Protocolos disponibles: ");
		for (int i = 0; i < protocolosDiponibles.length; i++) {
			System.out.println(protocolosDiponibles[i]);
		}
		String[] protocolosDeseados = new String[1];
		protocolosDeseados[0] = "TLSv1.2";
		socket.setEnabledProtocols(protocolosDeseados);
		
		protocolos = socket.getEnabledProtocols();
		System.out.println("Protocolos activos: ");
		for (int i = 0; i < protocolos.length; i++) {
			System.out.println(protocolos[i]);
		}
		
	}
	
	public cli_ssl(String mensaje) throws UnknownHostException, IOException{
		System.out.println("Obteniendo factoria de socket de cliente");
		SSLSocketFactory socketCliFactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
		
		System.out.println("Creando el socket del cliente.");
		SSLSocket socketCli = (SSLSocket) socketCliFactory.createSocket(DESTINO, PUERTO);
		
		mostrarCifrados(socketCli);
		
		PrintWriter pWriter = new PrintWriter(new BufferedOutputStream(socketCli.getOutputStream()),true);
		pWriter.println(mensaje);
		pWriter.flush();
		
		System.out.println("Mensaje enviado "+mensaje);
		
		//Esperamos la respuesta cifrada con hash desde el servidor y las mostramos por consola
		
		BufferedReader bReader = new BufferedReader(new InputStreamReader(socketCli.getInputStream(), "utf-8"));
		
		System.out.println("Mensaje cifrado recibido: "+bReader.readLine());
		System.out.println("Cerrando la conexión...");
		pWriter.close();
		socketCli.close();
		System.out.println("Finalizando...");
	}

	public static void main(String[] args) throws UnknownHostException, IOException {
		System.setProperty("javax.net.ssl.keyStore", "./certs/AlmacenCLI");
		System.setProperty("javax.net.ssl.keyStorePassword", "ies29700412");
		System.setProperty("javax.net.ssl.trustStore", "./certs/AlmacenCLI");
		System.setProperty("javax.net.ssl.trustStorePassword", "ies29700412");
		
		new cli_ssl("Jola mundo");

	}

}
