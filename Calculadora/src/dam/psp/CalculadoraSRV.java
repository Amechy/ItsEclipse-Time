package dam.psp;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraSRV implements ICalculadora {
	public CalculadoraSRV(Registry registro) {
		System.out.println("Iniciando objeto calculadora y su inscripción en el registro");
		try {
			registro.bind("Calculadora", (ICalculadora) UnicastRemoteObject.exportObject(this, 0));
		} catch (RemoteException | AlreadyBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public float suma(float a, float b) throws RemoteException {
		// TODO Auto-generated method stub
		return a+b;
	}

	@Override
	public float resta(float a, float b) throws RemoteException {
		// TODO Auto-generated method stub
		return a-b;
	}

	@Override
	public float producto(float a, float b) throws RemoteException {
		// TODO Auto-generated method stub
		return a*b;
	}

	@Override
	public float division(float a, float b) throws RemoteException {
		// TODO Auto-generated method stub
		if (b==0) {
			throw new RemoteException("División por cero, atontao.");
		}else {
			return a/b;
		}
		
	}
	public static void main(String[] args) throws RemoteException {
		final int puerto = 8889;
		// Asignamos la ip a la que se tiene que conectar la máquina
		System.setProperty("java.rmi.server.hostname","192.168.4.26");
		System.setProperty("java.net,preferIPv4Stack", "true");
		
		Registry registro = LocateRegistry.createRegistry(puerto);
		new CalculadoraSRV(registro);
	}

}
