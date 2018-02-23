package mechine;

public class Principal {


	public static void main(String[] args) {
		Globos globos = new Globos();
		
		HinchaGlobos HG1 = new HinchaGlobos(globos, "HG1");
		HinchaGlobos HG2 = new HinchaGlobos(globos, "HG2");
		HinchaGlobos HG3 = new HinchaGlobos(globos, "HG3");
		HinchaGlobos HG4 = new HinchaGlobos(globos, "HG4");
		HinchaGlobos HG5 = new HinchaGlobos(globos, "HG5");
		
		PinchaGlobos PG1 = new PinchaGlobos(globos, "PG1");
		PinchaGlobos PG2 = new PinchaGlobos(globos, "PG2");
		PinchaGlobos PG3 = new PinchaGlobos(globos, "PG3");
		PinchaGlobos PG4 = new PinchaGlobos(globos, "PG4");
		PinchaGlobos PG5 = new PinchaGlobos(globos, "PG5");
		
		HG1.start();
		HG2.start();
		HG3.start();
		HG4.start();
		HG5.start();
		
		PG1.start();
		PG2.start();
		PG3.start();
		PG4.start();
		PG5.start();
		
		try {
			HG1.join();
			HG2.join();
			HG3.join();
			HG4.join();
			HG5.join();
			
			
			PG1.join();
			PG2.join();
			PG3.join();
			PG4.join();
			PG5.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Finaliza hilo principal");
		
		
		
		
		
	}

}
