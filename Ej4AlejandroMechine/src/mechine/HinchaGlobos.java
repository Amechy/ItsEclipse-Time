package mechine;

public class HinchaGlobos extends Thread {
	Globos globos;
	String nHG;
	private static final int MILISEGUNDOS_ENTRE_BOCANADA = 1000;

	public HinchaGlobos(Globos globos, String nHG) {
		this.globos = globos;
		this.nHG = nHG;
	}

	@Override
	public void run() {
		for (int i = 0; i < globos.globos.length; i++) {
			while (globos.globos[i] != 0) {
				globos.HincharGlobo(i, nHG);
				try {
					Thread.sleep(MILISEGUNDOS_ENTRE_BOCANADA);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}		
		}
		System.out.println("Termina "+nHG);
	}
}
