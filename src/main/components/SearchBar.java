package main.components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

@SuppressWarnings("serial")
public class SearchBar extends JTextField implements FocusListener, KeyListener {

	private JFrame frame;
	
	private String placeholder;
	private String input;
	
	private double width;
	private double height;
	private final int minWidth = 230;
	private final int minHeight = 50;
	
	public SearchBar(JFrame frame, String placeholder, int x, int y) {
		this.frame = frame;
		this.placeholder = placeholder;
		
		this.setLocation(x,  y);
		this.addKeyListener(this);
		this.addFocusListener(this);
		this.setForeground(Color.GRAY);
		this.setText(placeholder);
	}
	
	public String getInput() {
		return input;
	}
	
	public void refreshSize() {
		width = ((double) frame.getWidth()) / 3;
		height = ((double) frame.getHeight()) / 12.5;
		
		if (height < minHeight) {
			height = minHeight;
		}
		
		if (width < minWidth) {
			width = minWidth;
		}
		
		this.setFont(new Font("Arial", Font.PLAIN, (int) height / 2));
		this.setSize(new Dimension((int) width, (int) height));
	}

	@Override
	public void focusGained(FocusEvent e) {
		if (e.getSource() == this) {
			this.setForeground(Color.BLACK);
			this.setText("");
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		if (e.getSource() == this) {
			this.setForeground(Color.GRAY);
			this.setText(placeholder);
		}
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		if (e.getSource() == this) {
			if (e.getKeyChar() == KeyEvent.VK_ENTER) {
				input = this.getText().strip();
				this.setText("");
				frame.requestFocus();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
}
