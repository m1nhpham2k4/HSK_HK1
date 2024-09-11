package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TitleBarComponent extends JPanel{
	private JPanel pnlTitle;
	private JLabel lblTitle;
	private JPanel pnlIconDefault;
	private JLabel iconDefault;
	private JPanel pnlControl;
	public JLabel lblMinimize;
	public JLabel lblClose;

	public TitleBarComponent(int width) {
		this.init(width);
	}

	private void init(int width) {
		this.setSize(width, 25);
		this.setLayout(new BorderLayout());
		
		// center
		pnlTitle = new JPanel();
		pnlTitle.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlTitle.setBackground(new Color(0, 0, 0));
		this.add(pnlTitle, BorderLayout.CENTER);
		
		lblTitle = new JLabel("PerkUpPlace Management");
		lblTitle.setForeground(new Color(255, 255, 255));
		pnlTitle.add(lblTitle);
		
		// left
		pnlIconDefault = new JPanel();
		pnlIconDefault.setLayout(new FlowLayout(FlowLayout.CENTER));
		pnlIconDefault.setPreferredSize(new Dimension(30, 25));
		pnlIconDefault.setBackground(new Color(0, 0, 0));
		this.add(pnlIconDefault, BorderLayout.WEST);
		
		iconDefault = new JLabel();
		iconDefault.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/DefaultIconMini.png")));
		pnlIconDefault.add(iconDefault);
		
		// right
		pnlControl = new JPanel();
		pnlControl.setLayout(null);
		pnlControl.setPreferredSize(new Dimension(60, 25));
		pnlControl.setBackground(new Color(0, 0, 0));
		this.add(pnlControl, BorderLayout.EAST);
		
		lblMinimize = new JLabel();
		lblMinimize.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconMinimizeWhite.png")));
		lblMinimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblMinimize.setBounds(13, 5, 15, 15);
		pnlControl.add(lblMinimize);
		
		lblClose = new JLabel();
		lblClose.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCloseWhite.png")));
		lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblClose.setBounds(38, 5, 15, 15);
		pnlControl.add(lblClose);
		
	}	
}
