package calculations;

import org.jxmapviewer.viewer.GeoPosition;

public class GlobePoint extends GlobeVector {
	
	private final static double RADIUS = 6371;

	/* Convert Latitude and Longitude to (x, y, z) coordinate on a function
	 * that imitates the Earth (if it was a perfect sphere)
	 * x^2 + y^2 + z^2 = 6371^2 */
	public GlobePoint(GeoPosition point) {
		super(
				RADIUS * Math.cos(point.getLatitude()) * Math.cos(point.getLongitude()),
				RADIUS * Math.cos(point.getLatitude()) * Math.sin(point.getLongitude()),
				RADIUS * Math.sin(point.getLatitude())
			);
	}
	
	public GlobePoint(double x, double y, double z) {
		super(x, y, z);
	}
	
}
