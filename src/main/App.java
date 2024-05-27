package main;
import main.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import org.jxmapviewer.*;
import org.jxmapviewer.viewer.*;


@SuppressWarnings("serial")
public class App extends JFrame implements ComponentListener {
	
	private SearchBar search;
	private JButton enter;
	
	private JXMapKit map;

	public App() {
		
		search = new SearchBar(this, "Search the Map", 15, 15);
		search.refreshSize();		
		
		enter = new JButton();
		enter.setText("Enter");
		enter.setBounds(15 + search.getWidth(), 15, 50, search.getHeight());
		enter.setFocusable(false);
		enter.setVisible(true);
		
		map = new JXMapKit();
		map.setBounds(0, 0, 750, 750);
		map.setZoomButtonsVisible(true);
		// City of Toronto as Starting Location
		map.setAddressLocation(new GeoPosition(43.653225, -79.383186));	
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Around the World Calculator");
		this.setLayout(null);
		this.setPreferredSize(new Dimension(750, 750));
		this.setMinimumSize(new Dimension(500, 500));
		this.add(enter);
		this.add(search);
		this.add(map);
		this.pack();
		this.addComponentListener(this);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getSource() == this) {
			search.refreshSize();
			map.setSize(new Dimension(this.getWidth(), this.getHeight()));
			enter.setBounds(15 + search.getWidth(), 15, 75, search.getHeight());
			this.revalidate();
			this.repaint();
		}
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}
}
