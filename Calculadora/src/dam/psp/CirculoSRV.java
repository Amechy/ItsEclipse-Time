package dam.psp;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;


public class CirculoSRV implements ICirculo {
	double _radio;
	private final double PI = Math.PI;	
	@Override
	public void set_radio(double radio) throws RemoteException {
		this._radio = radio;

	}

	@Override
	public double area() throws RemoteException {
		// TODO Auto-generated method stub
		return PI * _radio*_radio;
	}

	@Override
	public double longitud() throws RemoteException {
		// TODO Auto-generated method stub
		return 2*PI*_radio;
	}

	public CirculoSRV(Registry registro) {
		
		System.out.println("Iniciando objeto circulo y su inscripción en el registro");
		try {
			registro.bind("Circulo", (ICirculo) UnicastRemoteObject.exportObject(this, 0));
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[] args) throws RemoteException {
		
		final int puerto = 8888;
		// Asignamos la ip a la que se tiene que conectar la máquina
		System.setProperty("java.rmi.server.hostname","192.168.4.26");
		System.setProperty("java.net,preferIPv4Stack", "true");
		
		Registry registro = LocateRegistry.createRegistry(puerto);
		new CirculoSRV(registro);
		
	}
	

}







