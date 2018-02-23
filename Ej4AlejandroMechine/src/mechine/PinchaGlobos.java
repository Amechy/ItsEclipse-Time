package mechine;

import java.util.Random;


public class PinchaGlobos extends Thread {
	private static final int MINIMO_MILISEGUNDOS = 1000;
	private static final int MAXIMO_MILISEGUNDOS = 10000;
	Globos globos;
	String nPG;
	Random rnd;
	public PinchaGlobos(Globos globos,String nPG) {
		this.globos = globos;
		this.nPG = nPG;	
		rnd = new Random();
	}
	@Override
	public void run() {
		
		for (int i = 0; i < globos.globos.length; i++) {			
			globos.PincharGlobo(i, nPG);			
			try {
				Thread.sleep(rnd.nextInt(MAXIMO_MILISEGUNDOS)+MINIMO_MILISEGUNDOS);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("Termina "+nPG);
	}
}
