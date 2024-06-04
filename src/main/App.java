package main;

import calculations.Globe;
import main.components.Map;
import main.components.SearchBar;
import scraping.WebScraper;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

@SuppressWarnings("serial")
public class App extends JFrame implements ComponentListener, ActionListener {
	
	private Timer waitForSite;
	
	private SearchBar search;
	private JButton enter;
	private JButton add;
	private JButton calc;
	
	private JLabel locationOne;
	private JLabel locationTwo;
	private JPanel textHolder;
	
	private Map map;
	
	private JLayeredPane screen;
	
	private Globe earth;

	public App() {		
		search = new SearchBar(this, "Search the Map", 15, 15);
		
		enter = new JButton("Enter");
		enter.setFocusable(false);
		enter.setVisible(true);
		enter.addActionListener(this);
		
		add = new JButton("Add");
		add.setFocusable(false);
		add.setVisible(false);
		add.addActionListener(this);
		
		calc = new JButton("Calculate");
		calc.setFocusable(false);
		calc.setVisible(false);
		calc.addActionListener(this);
		
		locationOne = new JLabel();
		locationOne.setFont(new Font("Arial", Font.PLAIN, 12));
		locationOne.setVisible(false);
		
		locationTwo = new JLabel();
		locationTwo.setFont(new Font("Arial", Font.PLAIN, 12));
		locationTwo.setVisible(false);
		
		textHolder = new JPanel(new GridLayout(2, 1));
		textHolder.add(locationOne);
		textHolder.add(locationTwo);
		textHolder.setOpaque(false);
		textHolder.setBackground(Color.red);
		
		map = new Map();
		
		// DELETE AFTER DEBUG
		earth = new Globe(map.getLocations());
		
		screen = new JLayeredPane();
		screen.add(calc, JLayeredPane.PALETTE_LAYER);
		screen.add(add, JLayeredPane.PALETTE_LAYER);
		screen.add(enter, JLayeredPane.PALETTE_LAYER);
		screen.add(search, JLayeredPane.PALETTE_LAYER);
		screen.add(textHolder, JLayeredPane.MODAL_LAYER);
		screen.add(map, JLayeredPane.DEFAULT_LAYER);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setTitle("Around the World Calculator");
		this.setLayout(null);
		this.setPreferredSize(new Dimension(750, 750));
		this.setMinimumSize(new Dimension(500, 500));
		this.add(screen);
		this.pack();
		this.addComponentListener(this);
		this.setResizable(true);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	public void refresh() {
		this.repaint();
		this.revalidate();
	}
	
	public void runSearch() {
		new WebScraper(map, search.getInput());

		waitForSite = new Timer(0, this);
		waitForSite.setInitialDelay(1250);
		waitForSite.setRepeats(false);
		waitForSite.start();
		
		refresh();
	}
	
	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getSource() == this) {
			search.refreshSize();
			enter.setBounds(15 + search.getWidth(), 15, 75, search.getHeight());
			add.setBounds(15 + search.getWidth() + enter.getWidth(), 15, 75, search.getHeight());
			calc.setBounds(15 + search.getWidth() + enter.getWidth() + add.getWidth(), 15, 100, search.getHeight());
			map.setBounds(0, 0, this.getWidth(), this.getHeight());
			textHolder.setBounds(15, 15 + search.getHeight(), 30 + search.getWidth() + enter.getWidth() + add.getWidth(), 100);
			screen.setBounds(0, 0, this.getWidth(), this.getHeight());
			refresh();
		}
	}
	
	public void clearText() {
		locationOne.setText("");
		locationTwo.setText("");
		locationOne.setVisible(false);
		locationTwo.setVisible(false);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enter) {
			search.setInput();
		} else if (e.getSource() == add) {
			map.setSaved(true);
			if (!locationOne.isVisible() || locationTwo.isVisible()) {
				clearText();
				calc.setVisible(false);
				locationOne.setVisible(true);
				
				locationOne.setText(map.getPlace());
			} else if (!locationTwo.isVisible()){
				calc.setVisible(true);
				locationTwo.setVisible(true);
				
				locationTwo.setText(map.getPlace());
			}
			refresh();
		} else if (e.getSource() == waitForSite) {
			if (map.isLocationShown()) {
				add.setVisible(true);
				refresh();
			}
		} else if (e.getSource() == calc) {
			earth = new Globe(map.getLocations());
		}
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}
}
