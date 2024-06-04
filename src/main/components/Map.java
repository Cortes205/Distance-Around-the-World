package main.components;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.painter.CompoundPainter;
import org.jxmapviewer.painter.Painter;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.Waypoint;
import org.jxmapviewer.viewer.WaypointPainter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.DefaultWaypoint;
import org.jxmapviewer.JXMapKit;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.GeoPosition;

@SuppressWarnings("serial")
public class Map extends JXMapKit {
	
	private TileFactoryInfo info;
	private DefaultTileFactory tileFactory;
	
	private LinkedList<GeoPosition> locations;
	private LinkedList<String> titles;
	
	private boolean savedLastLocations;
	private boolean locationShown;

	public Map() {		
		this.setZoomButtonsVisible(true);
		this.setMiniMapVisible(false);
		// City of Toronto as Starting Location
		this.setAddressLocation(new GeoPosition(43.653225, -79.383186));
		
		info = new OSMTileFactoryInfo();
		info.setDefaultZoomLevel(10);
        tileFactory = new DefaultTileFactory(info);
        this.getMainMap().setTileFactory(tileFactory);
		
		savedLastLocations = false;
		locationShown = false;
		locations = new LinkedList<GeoPosition>();
		titles = new LinkedList<String>();
		
		// DELETE AFTER DEBUG
		locations.add(new GeoPosition(43.653225, -79.383186));
		locations.add(new GeoPosition(40.741895, -73.989308));
	}
	
	public void setWaypoint(double lat, double lon, String title) {
		// Fix locations bug: deleted by excessive searching (without hitting add or calculate)
		if (!savedLastLocations && !locations.isEmpty()) {
			locations.remove(locations.getLast());
			titles.remove(titles.getLast());
		} else if (locations.size() == 2) {
			locations.removeAll(locations);
			titles.removeAll(titles);
		}
		
		locations.add(new GeoPosition(lat, lon));
		titles.add(title);
		
		paintWaypoints(false);
		locationShown = true;
		
		this.setAddressLocation(locations.getLast());
		this.setZoom(5);
				
		savedLastLocations = false;
	}
	
	public void paintWaypoints(boolean paintAll) {
		Set<Waypoint> waypoints = new HashSet<Waypoint>();
		if (paintAll) {
			for (int i = 0; i < locations.size(); i++) {
				waypoints.add(new DefaultWaypoint(locations.get(i)));
			}
		} else {
			waypoints.add(new DefaultWaypoint(locations.getLast()));
		}

		WaypointPainter<Waypoint> waypointPainter = new WaypointPainter<Waypoint>();
		waypointPainter.setWaypoints(waypoints);

		List<Painter<JXMapViewer>> painters = new ArrayList<Painter<JXMapViewer>>();
		painters.add(waypointPainter);

	    CompoundPainter<JXMapViewer> painter = new CompoundPainter<JXMapViewer>(painters);
	    this.getMainMap().setOverlayPainter(painter);
	}
	
	public void setSaved(boolean saved) {
		this.savedLastLocations = saved;
	}
	
	public String getPlace() {
		return titles.getLast();
	}
	
	public LinkedList<GeoPosition> getLocations() {
		return locations;
	}
	
	public boolean isLocationShown() {
		return locationShown;
	}
}
