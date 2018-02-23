package mechine;

public class Globos {
	private static final int MAXIMO_GLOBOS_HINCHANDOSE = 3;
	private static final int VOLUMEN_MAXIMO_GLOBO = 5;
	private static final int VOLUMEN_INICIAL_GLOBO = -1;

	private int globosHinchandoseActual;
	int[] globos;

	public Globos() {

		globos = new int[10];
		globosHinchandoseActual = 0;

		// Introducimos los globos:
		for (int i = 0; i < globos.length; i++) {
			globos[i] = VOLUMEN_INICIAL_GLOBO;

		}
	}

	public synchronized void HincharGlobo(int nGlobo, String nHG) {
		while (globosHinchandoseActual >= 3) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		globosHinchandoseActual++;

		// Los globos que todavían no se han entregado tienen un valor de -1
		if (globos[nGlobo] == -1) {
			// Cuando se entregan pasan a tener valor de 1
			globos[nGlobo] = 1;
			System.out.println("Globo " + nGlobo + " Entregado a " + nHG + ".");
		}
		
		
		// si un globo tiene menos volumen que 5 y mayot volumen que 0 (estallado), se
		// puede hinchar
		if (globos[nGlobo] < 5 && globos[nGlobo] > 0) {
			globos[nGlobo]++;

			System.out.println("Globo " + nGlobo + " volumen " + globos[nGlobo] + ".");
		} else if (globos[nGlobo] == 5) {
			globos[nGlobo] = 0;
			System.out.println("Globo " + nGlobo + " estalla.");
		}
		globosHinchandoseActual--;
		notifyAll();
	}

	public synchronized void PincharGlobo(int nGlobo, String nPH) {
		while (globos[nGlobo] <= 0) {
			try {
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		globos[nGlobo] = 0;
		System.out.println("El globo " + nGlobo + " ha sido pinchado por " + nPH + ".");

		notifyAll();

	}
}
