package calculations;

public class GlobeVector {
	
	private double x;
	private double y;
	private double z;
	private String name;
	
	public GlobeVector(GlobePoint pointOne, GlobePoint pointTwo) {
		this.x = pointTwo.getX() - pointOne.getX();
		this.y = pointTwo.getY() - pointOne.getY();
		this.z = pointTwo.getZ() - pointOne.getZ();
		this.name = "v";
	}
	
	public GlobeVector(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.name = "v";
	}
	
	public void reverse() {
		x *= -1;
		y *= -1;
		z *= -1;
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
	
	public void setX(double x) {
		this.x = x;
	}
	
	public void setY(double y) {
		this.y = y;
	}
	
	public void setZ(double z) {
		this.z = z;
	}
	
	public void getName(String name) {
		this.name = name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	
	public double getMagnitude() {
		return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2) + Math.pow(z, 2));
	}
	
	@Override
	public String toString() {
		return "|" + name + "| = " + getMagnitude() + "km\n(x, y, z) = (" + x + ", " + y + ", " + z + ")\n";
	}

}
