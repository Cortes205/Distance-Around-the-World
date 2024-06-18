package calculations;

import org.jxmapviewer.viewer.GeoPosition;

public class GlobePoint extends GlobeVector {
	
	private final static double RADIUS = 6371;

	/* Convert Latitude and Longitude to (x, y, z) coordinates on a relation
	 * that imitates the Earth (if it was a perfect sphere)
	 * x^2 + y^2 + z^2 = 6371^2 */
	public GlobePoint(GeoPosition point) {		
		super(
				RADIUS * Math.cos(convertAngle(point.getLatitude())) * Math.cos(convertAngle(point.getLongitude())),
				RADIUS * Math.cos(convertAngle(point.getLatitude())) * Math.sin(convertAngle(point.getLongitude())),
				RADIUS * Math.sin(convertAngle(point.getLatitude()))
			);
		super.setName("P");
	}
	
	public GlobePoint(double x, double y, double z) {
		super(x, y, z);
		super.setName("P");
	}
	
	public static double convertAngle(double angle) {
		return (Math.PI / 180) * angle;
	}
	
}
