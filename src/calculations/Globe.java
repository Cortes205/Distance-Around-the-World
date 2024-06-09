package calculations;

import java.util.LinkedList;

import org.jxmapviewer.viewer.GeoPosition;

public class Globe {
	
	private double realDistance;
	// "atw" = "around the world"
	private double atwDistance;
		
	private GlobePoint locationOne;
	private GlobePoint locationTwo;

	public Globe(LinkedList<GeoPosition> locations) {
		locationOne = new GlobePoint(locations.get(0));
		locationTwo = new GlobePoint(locations.get(1));
		
		locationOne.setName("u");
		locationTwo.setName("v");
		
		GlobeVector uv = new GlobeVector(locationOne, locationTwo);
		uv.setName("uv");
		
		System.out.println(locationOne);
		System.out.println(locationTwo);
		System.out.println(uv);
	}
	
	public double getAngle(GlobeVector u, GlobeVector v) {
		return Math.acos(
					((u.getX() * v.getX()) + (u.getY() * v.getY()) + (u.getZ() * v.getZ()))
					/ (u.getMagnitude() * v.getMagnitude())
				);
	}
	
	public double getRealDistance() {
		return realDistance;
	}
	
	public double getAtwDistance() {
		return atwDistance;
	}

}
