package main;
import main.components.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class App extends JFrame implements ComponentListener, ActionListener {
	
	private SearchBar search;
	private JButton enter;
	
	private Map map;
	
	private JLayeredPane screen;

	public App() {
		
		search = new SearchBar(this, "Search the Map", 15, 15);
		search.refreshSize();		
		
		enter = new JButton();
		enter.setText("Enter");
		enter.setFocusable(false);
		enter.setVisible(true);
		enter.addActionListener(this);
		
		map = new Map();
		
		screen = new JLayeredPane();
		screen.add(enter, JLayeredPane.PALETTE_LAYER);
		screen.add(search, JLayeredPane.PALETTE_LAYER);
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
	
	@Override
	public void componentResized(ComponentEvent e) {
		if (e.getSource() == this) {
			search.refreshSize();
			enter.setBounds(15 + search.getWidth(), 15, 75, search.getHeight());
			map.setBounds(0, 0, this.getWidth(), this.getHeight());
			screen.setBounds(0, 0, this.getWidth(), this.getHeight());
			this.revalidate();
			this.repaint();
		}
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == enter) {
			search.setInput();
		}
		
	}
	
	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

	@Override
	public void componentHidden(ComponentEvent e) {}
}
