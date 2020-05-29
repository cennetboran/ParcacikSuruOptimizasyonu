public class PSOUtility {
	public static int getMinKonum(double[] list) {
		int pos = 0;
		double minDeger = list[0];
		for(int i=0; i<list.length; i++) {
			if(list[i] < minDeger) {
				pos = i;
				minDeger = list[i];
			}
		}
		return pos;
	}
}
