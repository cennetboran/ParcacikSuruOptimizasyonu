import java.util.Random;
import java.util.Vector;
public class PSOIslem implements PSOConstants {
	private Vector<Parcacik> suru = new Vector<Parcacik>();
	private double[] pBest = new double[SURU_BUYUKLUK];
	private Vector<Location> pBestKonum = new Vector<Location>();
	private double gBest;
	private Location gBestLocation;
	private double[] fitnessDegerList = new double[SURU_BUYUKLUK];
	
	Random generator = new Random();
	
	public void execute() {
		suruInit();
		fitnessListGuncelle();
		
		for(int i=0; i<SURU_BUYUKLUK; i++) {
			pBest[i] = fitnessDegerList[i];
			pBestKonum.add(suru.get(i).getKonum());
		}
		int t = 0;
		double w;
		double err = 9999;
		while(t < MAKS_ITERASYON && err > ProblemBelirle.TOLERANS) {
			
			for(int i=0; i<SURU_BUYUKLUK; i++) {
				if(fitnessDegerList[i] < pBest[i]) {
					pBest[i] = fitnessDegerList[i];
					pBestKonum.set(i, suru.get(i).getKonum());
				}
			}
				
			int bestParcacikIndex = PSOUtility.getMinKonum(fitnessDegerList);
			if(t == 0 || fitnessDegerList[bestParcacikIndex] < gBest) {
				gBest = fitnessDegerList[bestParcacikIndex];
				gBestLocation = suru.get(bestParcacikIndex).getKonum();
			}
			
			w = W_YUKSINIR - (((double) t) / MAKS_ITERASYON) * (W_YUKSINIR - W_ASSINIR);
			
			for(int i=0; i<SURU_BUYUKLUK; i++) {
				double r1 = generator.nextDouble();
				double r2 = generator.nextDouble();
				
				Parcacik p = suru.get(i);
				
				double[] newVel = new double[PROBLEM_BOYUT];
				newVel[0] = (w * p.getHiz().getPos()[0]) + 
							(r1 * C1) * (pBestKonum.get(i).getLoc()[0] - p.getKonum().getLoc()[0]) +
							(r2 * C2) * (gBestLocation.getLoc()[0] - p.getKonum().getLoc()[0]);
				newVel[1] = (w * p.getHiz().getPos()[1]) + 
							(r1 * C1) * (pBestKonum.get(i).getLoc()[1] - p.getKonum().getLoc()[1]) +
							(r2 * C2) * (gBestLocation.getLoc()[1] - p.getKonum().getLoc()[1]);
				Hiz hiz = new Hiz(newVel);
				p.setHiz(hiz);
				
				// step 4 - update location
				double[] yeniKonum = new double[PROBLEM_BOYUT];
				yeniKonum[0] = p.getKonum().getLoc()[0] + newVel[0];
				yeniKonum[1] = p.getKonum().getLoc()[1] + newVel[1];
				Location konum = new Location(yeniKonum);
				p.setKonum(konum);
			}
			
			err = ProblemBelirle.evaluate(gBestLocation) - 0; // minimizing the functions means it's getting closer to 0
			
			
			System.out.println("DÖNGÜ " + t + ": ");
			System.out.println("     Best X: " + gBestLocation.getLoc()[0]);
			System.out.println("     Best Y: " + gBestLocation.getLoc()[1]);
			System.out.println("     Değer: " + ProblemBelirle.evaluate(gBestLocation));
			
			t++;
			fitnessListGuncelle();
		}
		
		System.out.println("\nÇözüm " + (t - 1) + ". iterasyonda bulundu, çözüm sonucu:");
		System.out.println("     Best X: " + gBestLocation.getLoc()[0]);
		System.out.println("     Best Y: " + gBestLocation.getLoc()[1]);
	}
	
	public void suruInit() {
		Parcacik p;
		for(int i=0; i<SURU_BUYUKLUK; i++) {
			p = new Parcacik();
			
			// randomize location inside a space defined in Problem Set
			double[] loc = new double[PROBLEM_BOYUT];
			loc[0] = ProblemBelirle.KONUM_X_DUS + generator.nextDouble() * (ProblemBelirle.KONUM_X_YUK - ProblemBelirle.KONUM_X_DUS);
			loc[1] = ProblemBelirle.KONUM_Y_DUS + generator.nextDouble() * (ProblemBelirle.KONUM_Y_YUK - ProblemBelirle.KONUM_Y_DUS);
			Location location = new Location(loc);
			
			// randomize velocity in the range defined in Problem Set
			double[] vel = new double[PROBLEM_BOYUT];
			vel[0] = ProblemBelirle.HIZ_DUS + generator.nextDouble() * (ProblemBelirle.HIZ_YUK - ProblemBelirle.HIZ_DUS);
			vel[1] = ProblemBelirle.HIZ_DUS + generator.nextDouble() * (ProblemBelirle.HIZ_YUK - ProblemBelirle.HIZ_DUS);
			Hiz velocity = new Hiz(vel);
			
			p.setKonum(location);
			p.setHiz(velocity);
			suru.add(p);
		}
	}
	
	public void fitnessListGuncelle() {
		for(int i=0; i<SURU_BUYUKLUK; i++) {
			fitnessDegerList[i] = suru.get(i).getFitnessDeger();
		}
	}
}
