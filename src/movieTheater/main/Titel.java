package movieTheater.main;

public class Titel {

	private int titelID;
	private String titelName;
	
	public Titel(int titelID, String titelName) {
		this.titelName = titelName;
		this.titelID = titelID;
	}
	
	public int getTitelID(){
		return titelID;
	}
	public String getTitelName(){
		return titelName;
	}
	public String toString(){
		return titelID+" "+titelName;
	}

}
