package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class ForgotPasswordGUI extends JFrame implements MouseListener, MouseMotionListener{
	private JPanel pnlContent;
	private JPanel pnlForgotPasswordImageContainer;
	private JLabel imgForgotPassword;
	private JPanel pnlEmailForm;
	private JLabel lblFindAccount;
	private JLabel lblRequest;
	private JPanel pnlIconEmail;
	private JLabel iconEmail;
	private JTextField txtEmail;
	private JButton btnSend;
	private JLabel lblBack;
	private Point mouseDownCompCoords;
	private JPanel pnlIconValidEmail;
	private JLabel iconValidEmail;
	private JTextArea txtErrorMessage;

	public ForgotPasswordGUI() {
		this.init();
	}

	private void init() {
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		
		ImageIcon img = new ImageIcon("img/DefaultIcon.png");
		this.setIconImage(img.getImage());
		
		pnlContent = new JPanel();
		pnlContent.setLayout(null);
		this.setContentPane(pnlContent);
		
		pnlForgotPasswordImageContainer = new JPanel();
		pnlForgotPasswordImageContainer.setLayout(null);
		pnlForgotPasswordImageContainer.setBounds(700, 0, 500, 800);
		pnlContent.add(pnlForgotPasswordImageContainer);
		
		imgForgotPassword = new JLabel();
		imgForgotPassword.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/imgForgotPassword.png")));
		imgForgotPassword.setBounds(0, 0, 500, 800);
		pnlForgotPasswordImageContainer.add(imgForgotPassword);
		
		pnlEmailForm = new JPanel();
		pnlEmailForm.setLayout(null);
		pnlEmailForm.setBackground(new Color(38, 38, 38));
		pnlEmailForm.setBounds(0, 0, 700, 800);
		pnlContent.add(pnlEmailForm);
		
		lblFindAccount = new JLabel("Find Your Account");
		lblFindAccount.setFont(new Font("Arial", Font.BOLD, 24));
		lblFindAccount.setForeground(new Color(255, 255, 255));
		lblFindAccount.setBounds(43, 58, 307, 29);
		pnlEmailForm.add(lblFindAccount);
		
		lblRequest = new JLabel("Please enter your email address to search for your account.");
		lblRequest.setFont(new Font("Arial", 0, 12));
		lblRequest.setForeground(new Color(128, 128, 128));
		lblRequest.setBounds(43, 98, 346, 30);
		pnlEmailForm.add(lblRequest);
		
		// Email
		pnlIconEmail = new JPanel();
		pnlIconEmail.setLayout(null);
		pnlIconEmail.setBackground(new Color(255, 255, 255));
		pnlIconEmail.setBounds(43, 175, 52, 52);
		pnlEmailForm.add(pnlIconEmail);
		
		iconEmail = new JLabel();
		iconEmail.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconEmail.png")));
		iconEmail.setBounds(7, 7, 38, 38);
		pnlIconEmail.add(iconEmail);
		
		txtEmail = new JTextField();
		txtEmail.setFont(new Font("Arial", 0, 16));
		txtEmail.setBorder(null);
		txtEmail.setBackground(new Color(255, 255, 255));
		txtEmail.setBounds(95, 175, 415, 52);
		pnlEmailForm.add(txtEmail);
		
		pnlIconValidEmail = new JPanel();
		pnlIconValidEmail.setLayout(null);
		pnlIconValidEmail.setBackground(new Color(255, 255, 255));
		pnlIconValidEmail.setBounds(510, 175, 52, 52);
		pnlEmailForm.add(pnlIconValidEmail);
		
		iconValidEmail = new JLabel();
		iconValidEmail.setBounds(7, 7, 38, 38);
		pnlIconValidEmail.add(iconValidEmail);
		
		btnSend = new JButton();
		btnSend.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconArrowRight.png")));
		btnSend.setBorder(null);
		btnSend.setBackground(new Color(255, 186, 82));
		btnSend.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSend.addMouseListener(this);
		btnSend.setBounds(569, 175, 52, 52);
		pnlEmailForm.add(btnSend);
		
		txtErrorMessage = new JTextArea();
		txtErrorMessage.setFont(new Font("Arial", 0, 12));
		txtErrorMessage.setBackground(new Color(38, 38, 38));
		txtErrorMessage.setForeground(new Color(213, 63, 63));
		txtErrorMessage.setFocusable(false);
		txtErrorMessage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		txtErrorMessage.setBounds(50, 238, 559, 124);
		pnlEmailForm.add(txtErrorMessage);
		
		lblBack = new JLabel("Back");
		lblBack.setFont(new Font("Arial", 0, 14));
		lblBack.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconArrowLeft.png")));
		lblBack.setForeground(new Color(255, 255, 255));
		lblBack.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblBack.addMouseListener(this);
		lblBack.setBounds(15, 745, 76, 28);
		pnlEmailForm.add(lblBack);
		
		this.setVisible(true);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void close() {
		this.dispose();
	}
	
	private boolean validData() {
		String email = txtEmail.getText().trim();
		if(email.equals("")) {
			iconValidEmail.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
			txtErrorMessage.setText("Please enter your email address to proceed.");
			txtEmail.requestFocus();
			return false;
		}else if(!email.matches("^[a-zA-Z0-9]+@gmail.com$")) {
			iconValidEmail.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
			txtErrorMessage.setText("Please enter a valid email address in the format John1504@gmail.com.");
			txtEmail.requestFocus();
			return false;
		}
		
		iconValidEmail.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconSuccess.png")));
		txtErrorMessage.setText("");
		return true;
	}
	
	private boolean validEmail() {
		// TODO Auto-generated method stub
		return true;
	}
	
	private void findAccount() {
		if(validData()) {
			if(validEmail()) {
				// delay
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						close();
						new LoginGUI();
						timer.cancel();
					}
				};
				timer.scheduleAtFixedRate(task, 2000, 10000);
			}
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(lblBack)) {
			close();
			new LoginGUI();
		}else if(obj.equals(btnSend)) {
			findAccount();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDownCompCoords = e.getPoint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		 Point currCoords = e.getLocationOnScreen();
         this.setLocation(currCoords.x - mouseDownCompCoords.x, currCoords.y - mouseDownCompCoords.y);
         
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
