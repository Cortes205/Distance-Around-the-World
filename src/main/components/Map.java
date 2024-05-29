package main.components;

import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.viewer.GeoPosition;

@SuppressWarnings("serial")
public class Map extends JXMapKit {

	public Map() {
		this.setZoomButtonsVisible(true);
		this.setMiniMapVisible(false);
		// City of Toronto as Starting Location
		this.setAddressLocation(new GeoPosition(43.653225, -79.383186));
	}
	
	public void setWaypoint(double lat, double lon) {
		this.setAddressLocation(new GeoPosition(lat, lon));
		this.setZoom(2);
	}
}
