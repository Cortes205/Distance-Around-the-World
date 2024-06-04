package calculations;

public class GlobeVector {
	
	private double x;
	private double y;
	private double z;
	
	public GlobeVector(GlobePoint pointOne, GlobePoint pointTwo) {
		this.x = pointTwo.getX() - pointOne.getX();
		this.y = pointTwo.getY() - pointOne.getY();
		this.z = pointTwo.getZ() - pointOne.getZ();
	}
	
	public GlobeVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public double getZ() {
		return z;
	}
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}
	
	public void printDetails() {
		System.out.println(getMagnitude() + ": (" + x + ", " + y + ", " + z + ")");
	}

}
