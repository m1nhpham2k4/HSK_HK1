package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import connectDB.ConnectDB;
import dao.NhanVienDAO;
import dao.SanPhamDAO;
import entity.NhanVien;

public class LoginGUI extends JFrame implements MouseListener, FocusListener, MouseMotionListener{
	private JPanel pnlContent;
	private JPanel pnlLoginImageContainer;
	private JLabel imgLogin;
	private JPanel pnlLoginFormContainer;
	private JLabel lblWelcome;
	private JLabel lblSignInRequired;
	private JPanel pnlIconUserName;
	private JLabel iconUserName;
	private JTextField txtUserName;
	private JPanel pnlIconValidUserName;
	private JLabel iconValidUserName;
	private JPanel pnlIconPassword;
	private JLabel iconPassword;
	private JPasswordField txtPassword;
	private JPanel pnlIconPasswordVisibility;
	private JLabel iconPasswordVisibility;
	private JLabel lblForgotPassword;
	private JButton btnLogin;
	private JLabel lblMinimize;
	private JLabel lblClose;
	private JLabel lblAuthor;
	private JTextArea txtErrorMessage;
	private Point mouseDownCompCoords;
	public static NhanVien nv = null;
	private NhanVienDAO nvDao;
	
	public LoginGUI() {
		this.init();
	}

	private void init() {
		// khởi tạo kết nối đến CSDL
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nvDao = new NhanVienDAO();
		
		this.setSize(1200, 800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setUndecorated(true);
		
		ImageIcon img = new ImageIcon("img/DefaultIcon.png");
		this.setIconImage(img.getImage());
		
		pnlContent = new JPanel();
		pnlContent.setLayout(null);
		this.setContentPane(pnlContent);
		
		pnlLoginImageContainer = new JPanel();
		pnlLoginImageContainer.setLayout(null);
		pnlLoginImageContainer.setBounds(0, 0, 534, 800);
		pnlContent.add(pnlLoginImageContainer);
		
		imgLogin = new JLabel("");
		imgLogin.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/imgLogin.jpg")));
		imgLogin.setBounds(0, 0, 534, 800);
		pnlLoginImageContainer.add(imgLogin);
		
		pnlLoginFormContainer = new JPanel();
		pnlLoginFormContainer.setLayout(null);
		pnlLoginFormContainer.setBackground(new Color(255, 255, 255));
		pnlLoginFormContainer.setBounds(534, 0, 666, 800);
		pnlContent.add(pnlLoginFormContainer);
		
		lblMinimize = new JLabel();
		lblMinimize.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconMinimize.png")));
		lblMinimize.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblMinimize.addMouseListener(this);
		lblMinimize.setBounds(619, 5, 15, 15);
		pnlLoginFormContainer.add(lblMinimize);
		
		lblClose = new JLabel();
		lblClose.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconClose.png")));
		lblClose.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblClose.addMouseListener(this);
		lblClose.setBounds(644, 5, 15, 15);
		pnlLoginFormContainer.add(lblClose);
		
		lblWelcome = new JLabel("Wellcome to PerkUpPlace");
		lblWelcome.setFont(new Font("Arial", Font.BOLD, 24));
		lblWelcome.setBounds(66, 58, 307, 29);
		pnlLoginFormContainer.add(lblWelcome);
		
		lblSignInRequired = new JLabel("Please Login to your Account");
		lblSignInRequired.setFont(new Font("Arial", 0, 12));
		lblSignInRequired.setForeground(new Color(128, 128, 128));
		lblSignInRequired.setBounds(66, 98, 302, 15);
		pnlLoginFormContainer.add(lblSignInRequired);
		
		// UserName
		pnlIconUserName = new JPanel();
		pnlIconUserName.setLayout(null);
		pnlIconUserName.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		pnlIconUserName.setBackground(new Color(255, 255, 255));
		pnlIconUserName.setBounds(66, 175, 52, 52);
		pnlLoginFormContainer.add(pnlIconUserName);
		
		iconUserName = new JLabel();
		iconUserName.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconUserName.png")));
		iconUserName.setBounds(7, 7, 38, 38);
		pnlIconUserName.add(iconUserName);
		
		txtUserName = new JTextField();
		txtUserName.setFont(new Font("Arial", 0, 16));
		txtUserName.setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));
		txtUserName.setBackground(new Color(255, 255, 255));
		txtUserName.setBounds(118, 175, 424, 52);
		pnlLoginFormContainer.add(txtUserName);
		
		pnlIconValidUserName = new JPanel();
		pnlIconValidUserName.setLayout(null);
		pnlIconValidUserName.setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
		pnlIconValidUserName.setBackground(new Color(255, 255, 255));
		pnlIconValidUserName.setBounds(542, 175, 52, 52);
		pnlLoginFormContainer.add(pnlIconValidUserName);
		
		iconValidUserName = new JLabel();
		iconValidUserName.setBounds(7, 7, 38, 38);
		pnlIconValidUserName.add(iconValidUserName);
		
		// Password
		pnlIconPassword = new JPanel();
		pnlIconPassword.setLayout(null);
		pnlIconPassword.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		pnlIconPassword.setBackground(new Color(255, 255, 255));
		pnlIconPassword.setBounds(66, 265, 52, 52);
		pnlLoginFormContainer.add(pnlIconPassword);
		
		iconPassword = new JLabel();
		iconPassword.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconPassword.png")));
		iconPassword.setBounds(7, 7, 38, 38);
		pnlIconPassword.add(iconPassword);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Arial", 0, 16));
		txtPassword.setBorder(new MatteBorder(1, 0, 1, 0, Color.BLACK));
		txtPassword.setBackground(new Color(255, 255, 255));
		txtPassword.addFocusListener(this);
		txtPassword.setBounds(118, 265, 424, 52);
		pnlLoginFormContainer.add(txtPassword);
		
		pnlIconPasswordVisibility = new JPanel();
		pnlIconPasswordVisibility.setLayout(null);
		pnlIconPasswordVisibility.setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
		pnlIconPasswordVisibility.setBackground(new Color(255, 255, 255));
		pnlIconPasswordVisibility.setBounds(542, 265, 52, 52);
		pnlLoginFormContainer.add(pnlIconPasswordVisibility);
		
		iconPasswordVisibility = new JLabel();
//		iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconShowPassword.png")));
		iconPasswordVisibility.setCursor(new Cursor(Cursor.HAND_CURSOR));
		iconPasswordVisibility.setName("Hide");
		iconPasswordVisibility.addMouseListener(this);
		iconPasswordVisibility.setBounds(7, 7, 38, 38);
		pnlIconPasswordVisibility.add(iconPasswordVisibility);
		
		lblForgotPassword = new JLabel("Forgot Password?");
		lblForgotPassword.setFont(new Font("Arial", 0, 12));
		lblForgotPassword.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblForgotPassword.addMouseListener(this);
		lblForgotPassword.setBounds(492, 323, 154, 15);
		pnlLoginFormContainer.add(lblForgotPassword);
		
		txtErrorMessage = new JTextArea();
		txtErrorMessage.setFont(new Font("Arial", 0, 12));
		txtErrorMessage.setForeground(new Color(213, 63, 63));
		txtErrorMessage.setFocusable(false);
		txtErrorMessage.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		txtErrorMessage.setBounds(73, 348, 511, 15);
		pnlLoginFormContainer.add(txtErrorMessage);
		
		btnLogin = new JButton("LOGIN");
		btnLogin.setFont(new Font("Arial", Font.BOLD, 24));
		btnLogin.setForeground(new Color(255, 255, 255));
		btnLogin.setBackground(new Color(0, 0, 0));
		btnLogin.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnLogin.addMouseListener(this);
		btnLogin.setBounds(66, 373, 528, 64);
		pnlLoginFormContainer.add(btnLogin);
		
		lblAuthor = new JLabel("Copyright © 2024 PerkUpPlace.");
		lblAuthor.setFont(new Font("Arial", 0, 12));
		lblAuthor.setForeground(new Color(128, 128, 128));
		lblAuthor.setBounds(240, 767, 185, 24);
		pnlLoginFormContainer.add(lblAuthor);
		
		this.setVisible(true);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}
	
	private void visibilityPassword() {
		if(txtPassword.getName()=="focus") {
			if(iconPasswordVisibility.getName().equals("Hide")) {
			iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconHidePassword.png")));
			txtPassword.setEchoChar((char) 0);
			iconPasswordVisibility.setName("Show");
		}else {
			iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconShowPassword.png")));
			txtPassword.setEchoChar('\u2022');
			iconPasswordVisibility.setName("Hide");
		}
		}
		
	}
	
	private void hoverBtnLogin(Color color) {
		btnLogin.setBackground(color);
	}
	
	private void close() {
		this.dispose();
	}
	
	private boolean validData() {
		btnLogin.setBounds(66, 373, 528, 64);
		txtErrorMessage.setBounds(73, 348, 511, 15);
		String username = txtUserName.getText().trim();
		String password = new String(txtPassword.getPassword());
		if(username.equals("") || password.equals("")) {
			if(username.equals("") && password.equals("")) {
				iconValidUserName.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
				iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
				iconPassword.setName("Error");
				txtErrorMessage.setText("Please enter your username and password.");
				txtUserName.requestFocus();
				txtPassword.setName("unfocus");
				iconPasswordVisibility.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
				return false;
			}
			if(username.equals("")) {
				System.out.println("username blank");
				iconValidUserName.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
				txtErrorMessage.setText("Please enter your username.");
				txtUserName.requestFocus();
			}
			if(password.equals("")) {
				System.out.println("password blank");
				iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
				iconPassword.setName("Error");
				txtErrorMessage.setText("Please enter your password.");
				txtPassword.setName("unfocus");
				iconPasswordVisibility.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			}
			return false;
		}else if(!password.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d).{8,}$")) {
			System.out.println("password is not matches");
			iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
			iconPassword.setName("Error");
			txtErrorMessage.setText("Invalid password format. Password must be at least 8 characters long and contain at least\none uppercase letter, one lowercase letter, and one digit.");
			txtErrorMessage.setBounds(73, 348, 511, 32);
			btnLogin.setBounds(66, 380, 528, 64);
			txtPassword.setName("unfocus");
			iconPasswordVisibility.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			return false;
		}
		
		iconValidUserName.setIcon(null);
		iconPasswordVisibility.setIcon(null);
		txtErrorMessage.setText("");
		txtErrorMessage.setBounds(73, 348, 511, 15);
		btnLogin.setBounds(66, 373, 528, 64);
		return true;
	}
	
	private boolean validAccount() throws SQLException {
		// check username and password
		nv = nvDao.checkAccount(txtUserName.getText(), txtPassword.getText());
		if(nv==null) {
			iconValidUserName.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
			iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
			txtErrorMessage.setText("Không tìm thấy tài khoản");
			return false;
		}
		iconValidUserName.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconSuccess.png")));
		iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconSuccess.png")));
		return true;
	}
	
	private void accessAccount() throws SQLException {
		if(validData()) {
			if(validAccount()) {
				// delay
				Timer timer = new Timer();
				TimerTask task = new TimerTask() {
					@Override
					public void run() {
						close();
						new ManagementGUI();
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
		
		if(obj.equals(lblClose)) {
			System.exit(0);
		}else if(obj.equals(lblMinimize)) {
			this.setState(JFrame.ICONIFIED);
		}else if(obj.equals(iconPasswordVisibility)) {
			visibilityPassword();
		}else if(obj.equals(btnLogin)) {
			try {
				accessAccount();
			} catch (SQLException ex) {
				throw new RuntimeException(ex);
			}
		}else if(obj.equals(lblForgotPassword)) {
			new ForgotPasswordGUI();
			close();
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseDownCompCoords = e.getPoint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btnLogin)) {
			hoverBtnLogin(new Color(64, 64, 64));
		}
	}

	@Override
	public void mouseExited(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btnLogin)) {
			hoverBtnLogin(new Color(0, 0, 0));
		}
	}

	@Override
	public void focusGained(FocusEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(txtPassword)) {
			iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconShowPassword.png")));
			txtPassword.setName("focus");
			iconPasswordVisibility.setCursor(new Cursor(Cursor.HAND_CURSOR));
		}
	}

	@Override
	public void focusLost(FocusEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(txtPassword) && iconPassword.getName()=="Error") {
			iconPasswordVisibility.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconFailure.png")));
			txtPassword.setName("unfocus");
			iconPasswordVisibility.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		}else if(obj.equals(txtPassword) && iconPassword.getName()!="!Error") {
			iconPasswordVisibility.setIcon(null);
		}
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
