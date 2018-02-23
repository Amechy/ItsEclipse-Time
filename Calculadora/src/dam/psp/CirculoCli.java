package dam.psp;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class CirculoCli {

	public static String servidor = "192.168.3.57";
	public static int puerto = 8888;
	public static int puerto1 = 8889;
	
	public static void main(String[] args) throws RemoteException {
		int a = 20;
		int b = 10;
		ICirculo circulo = null;
		ICalculadora calculadora = null;
		
		System.out.println("Localizando en la red el objeto remoto...");
		Registry registro =  LocateRegistry.getRegistry(servidor,puerto);
		Registry registro2 =  LocateRegistry.getRegistry(servidor,puerto1);
		
		System.out.println("Obteniendo el falso objeto <stub> del servidor remoto.");
		try {
			
			circulo = (ICirculo)registro.lookup("Circulo");
			if (circulo!=null) {
				circulo.set_radio(20);
				System.out.println("Longitud de la circunferencia: "+ circulo.longitud());
				System.out.println("Área de la circulo: "+ circulo.area());
			}
			System.out.println("\n\n");
			
			
			calculadora = (ICalculadora)registro2.lookup("Calculadora");
			if (calculadora!=null) {
				
				System.out.println("Suma de "+a+"+"+b+": "+ calculadora.suma(a,b));
				System.out.println("Resta de "+a+"-"+b+": "+ calculadora.resta(a,b));
				System.out.println("Multiplicación de "+a+"*"+b+": "+ calculadora.producto(a,b));
				System.out.println("División de "+a+"/"+b+": "+ calculadora.division(a, b));
				
			}
			
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
