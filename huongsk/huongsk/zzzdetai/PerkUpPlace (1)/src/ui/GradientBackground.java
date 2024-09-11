package ui;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

public class GradientBackground extends JPanel{
	private Color color1;
	private Color color2;
	
	public GradientBackground(Color color1, Color color2) {
		this.color1 = color1;
		this.color2 = color2;
	}
	
	@Override
	protected void paintComponent(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		int width = getWidth();
		int height = getHeight();
		
		Color c1 = color1;
		Color c2 = color2;
		GradientPaint gp = new GradientPaint(0,0 , c1, width, 0, c2);
		g2d.setPaint(gp);
		g2d.fillRect(0, 0, width, height);
	}
	
}
