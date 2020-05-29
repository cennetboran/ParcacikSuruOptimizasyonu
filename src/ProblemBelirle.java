public class ProblemBelirle {
	public static final double KONUM_X_DUS = 1;
	public static final double KONUM_X_YUK = 4;
	public static final double KONUM_Y_DUS = -1;
	public static final double KONUM_Y_YUK = 1;
	public static final double HIZ_DUS = -1;
	public static final double HIZ_YUK = 1;
	
	public static final double TOLERANS = 1E-20; 
	public static double evaluate(Location konum) {
		double sonuc = 0;
		double x = konum.getLoc()[0]; 
		double y = konum.getLoc()[1];
		sonuc = Math.pow(2.8125 - x + x * Math.pow(y, 4), 2) + Math.pow(2.25 - x + x * Math.pow(y, 2), 2) + Math.pow(1.5 - x + x * y, 2);
		return sonuc;
	}
}
