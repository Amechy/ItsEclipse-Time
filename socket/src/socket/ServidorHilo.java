package socket;



import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ServidorHilo extends Thread{
	Socket elSocket = null;
	int id = 0;
	String mensaje = "Bienvenida a mi canal";

	public ServidorHilo(Socket skAtencion, int nCli) {
		this.elSocket = skAtencion;
		this.id = nCli;
	}
	
	@Override
	public void run() {
		BufferedOutputStream bo;
		//BufferedInputStream bufferedInputStream;
		
		BufferedReader bufferedReader;		
		PrintWriter pw = null;
		
		
		
		try {
			bo = new BufferedOutputStream(elSocket.getOutputStream());
			pw = new PrintWriter(bo,true);
			
			pw.print("id"+": "+this.mensaje);
			
			// Esperamos una respuesta de string en el cli
			bufferedReader = new BufferedReader(new InputStreamReader(elSocket.getInputStream(),"utf8"));
			
			System.out.println("Mensaje recibido desde el cliente");
			System.out.println(bufferedReader.readLine());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (pw != null) {
			pw.flush(); //Esto nunga deberia hacer falta
			pw.close();
		}
	}

}
