package main.components;
import java.awt.event.*;
import org.jxmapviewer.*;
import org.jxmapviewer.viewer.*;

@SuppressWarnings("serial")
public class Map extends JXMapKit implements MouseListener {

	public Map() {
		this.setZoomButtonsVisible(true);
		// City of Toronto as Starting Location
		this.setAddressLocation(new GeoPosition(43.653225, -79.383186));	
		this.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == this) {
			this.requestFocus();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}
