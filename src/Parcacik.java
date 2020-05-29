public class Parcacik {
	private double fitnessDegeri;
	private Hiz hiz;
	private Location konum;
	
	public Parcacik() {
		super();
	}

	public Parcacik(double fitnessDegeri, Hiz hiz, Location konum) {
		super();
		this.fitnessDegeri = fitnessDegeri;
		this.hiz = hiz;
		this.konum = konum;
	}

	public Hiz getHiz() {
		return hiz;
	}

	public void setHiz(Hiz velocity) {
		this.hiz = velocity;
	}

	public Location getKonum() {
		return konum;
	}

	public void setKonum(Location location) {
		this.konum = location;
	}

	public double getFitnessDeger() {
		fitnessDegeri = ProblemBelirle.evaluate(konum);
		return fitnessDegeri;
	}
}
