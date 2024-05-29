package main.components;

import main.App;

import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

@SuppressWarnings("serial")
public class SearchBar extends JTextField implements FocusListener, KeyListener {

	private App frame;
	
	private String placeholder;
	private String input;
	
	private double width;
	private double height;
	private final int MINWIDTH = 230;
	private final int MINHEIGHT = 50;
	
	public SearchBar(App frame, String placeholder, int x, int y) {
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
	
	public void setInput() {
		input = this.getText().strip();
		this.setText(placeholder);
		frame.requestFocus();
		frame.runSearch();
	}
	
	public void refreshSize() {
		width = ((double) frame.getWidth()) / 3;
		height = ((double) frame.getHeight()) / 12.5;
		
		if (height < MINHEIGHT) {
			height = MINHEIGHT;
		}
		
		if (width < MINWIDTH) {
			width = MINWIDTH;
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
				setInput();
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {}
	@Override
	public void keyPressed(KeyEvent e) {}
}
