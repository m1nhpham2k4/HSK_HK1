package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

public class ProductDetailGUI extends JFrame{
	private JPanel pnlContent;
	private JPanel pnlTitle;
	private JPanel pnlContainInfo;
	private JLabel lblTitle;
	private JPanel pnlProductImg;
	public JButton btnUpdate;
	public JButton btnSave;
	public JButton btnDelete;
	private JLabel lblProductID;
	public JTextField txtProductID;
	private JLabel lblProductName;
	public JTextField txtProductName;
	private JLabel lblProductUnitPrice;
	public JTextField txtProductUnitPrice;
	private JLabel lblProductType;
	public JComboBox<String> comboProductType;
	public JButton btnExit;
	private JLabel lblChangeImg;
	public JButton btnChangeImg;
	public JLabel lblImg;
	private JLabel lblProductStatus;
	public JComboBox<String> comboProductStatus;
	public String imgPath = "";

	public ProductDetailGUI() {
		this.init();
	}
	
	public void init() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screen.width, screen.height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		this.setBackground(new Color(0, 0, 0, 20));
		
		ImageIcon img = new ImageIcon("img/DefaultIcon.png");
		this.setIconImage(img.getImage());
		
		pnlContent = new JPanel();
		pnlContent.setLayout(null);
		pnlContent.setBackground(new Color(0, 0, 0, 80));
		this.setContentPane(pnlContent);
		
		pnlContainInfo = new JPanel();
		pnlContainInfo.setLayout(null);
		pnlContainInfo.setBackground(new Color(255, 255, 255));
		pnlContainInfo.setBounds(233, 222, 1453, 848);
		pnlContent.add(pnlContainInfo);
		
		// title
		pnlTitle = new JPanel();
		pnlTitle.setLayout(new BorderLayout());
		pnlTitle.setBounds(0, 0, 1453, 48);
		pnlContainInfo.add(pnlTitle);
		
		lblTitle = new JLabel("Thông Tin Sản Phẩm");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		pnlTitle.add(lblTitle);
		
		// content
		pnlProductImg = new JPanel();
		pnlProductImg.setLayout(null);
		pnlProductImg.setBackground(new Color(217, 217, 217));
		pnlProductImg.setBounds(46, 56, 179, 228);
		pnlContainInfo.add(pnlProductImg);
		
		lblImg = new JLabel();
		lblImg.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().createImage("img/DefaultProductImg.jpg")));
		lblImg.setBounds(0, 0, 179, 228);
		pnlProductImg.add(lblImg);
		
		lblChangeImg = new JLabel("Đổi ảnh:");
		lblChangeImg.setBounds(46, 301, 89, 15);
		pnlContainInfo.add(lblChangeImg);
		
		btnChangeImg = new JButton("Chọn file");
		btnChangeImg.setBackground(new Color(217, 217, 217));
		btnChangeImg.setBorder(null);
		btnChangeImg.setBounds(141, 301, 84, 20);
		pnlContainInfo.add(btnChangeImg);
		
		btnUpdate = new JButton("Chỉnh Sửa");
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(new Color(255, 186, 105));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 14));
		btnUpdate.setBorder(null);
		btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnUpdate.setBounds(297, 94, 200, 30);
		pnlContainInfo.add(btnUpdate);
		
		btnSave = new JButton("Lưu");
		btnSave.setEnabled(false);
		btnSave.setBackground(new Color(0, 218, 205));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Arial", Font.BOLD, 14));
		btnSave.setBorder(null);
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSave.setBounds(297, 56, 200, 30);
		pnlContainInfo.add(btnSave);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setEnabled(false);
		btnDelete.setBackground(new Color(255, 124, 124));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBorder(null);
		btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDelete.setBounds(297, 132, 200, 30);
		pnlContainInfo.add(btnDelete);
		
		// Mã sản phẩm
		lblProductID = new JLabel("Mã Sản Phẩm");
		lblProductID.setFont(new Font("Arial", 0, 14));
		lblProductID.setBounds(551, 56, 100, 30);
		pnlContainInfo.add(lblProductID);
		
		txtProductID = new JTextField();
		txtProductID.setFocusable(false);
		txtProductID.setBackground(new Color(255, 255, 255));
		txtProductID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtProductID.setBounds(728, 56, 695, 30);
		pnlContainInfo.add(txtProductID);
		
		// Tên sản phẩm
		lblProductName = new JLabel("Tên Sản Phẩm");
		lblProductName.setFont(new Font("Arial", 0, 14));
		lblProductName.setBounds(551, 94, 100, 30);
		pnlContainInfo.add(lblProductName);
		
		txtProductName = new JTextField();
		txtProductName.setFocusable(false);
		txtProductName.setBackground(new Color(255, 255, 255));
		txtProductName.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtProductName.setBounds(728, 94, 695, 30);
		pnlContainInfo.add(txtProductName);
		
		// Đơn giá
		lblProductUnitPrice = new JLabel("Đơn Giá");
		lblProductUnitPrice.setFont(new Font("Arial", 0, 14));
		lblProductUnitPrice.setBounds(551, 132, 100, 30);
		pnlContainInfo.add(lblProductUnitPrice);
		
		txtProductUnitPrice = new JTextField();
		txtProductUnitPrice.setFocusable(false);
		txtProductUnitPrice.setBackground(new Color(255, 255, 255));
		txtProductUnitPrice.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtProductUnitPrice.setBounds(728, 132, 695, 30);
		pnlContainInfo.add(txtProductUnitPrice);
		
		// Loại sản phẩm
		lblProductType = new JLabel("Loại Sản Phẩm");
		lblProductType.setFont(new Font("Arial", 0, 14));
		lblProductType.setBounds(551, 170, 100, 30);
		pnlContainInfo.add(lblProductType);
		
		comboProductType = new JComboBox<String>();
		comboProductType.setEnabled(false);
		comboProductType.setBackground(new Color(255, 255, 255));
		comboProductType.setBounds(728, 170, 695, 30);
		pnlContainInfo.add(comboProductType);
		
		comboProductType.addItem("Thức uống");
		comboProductType.addItem("Bánh");
		
		// Trạng thái
		lblProductStatus = new JLabel("Trạng thái");
		lblProductStatus.setFont(new Font("Arial", 0, 14));
		lblProductStatus.setBounds(551, 208, 100, 30);
		pnlContainInfo.add(lblProductStatus);
				
		comboProductStatus = new JComboBox<String>();
		comboProductStatus.setEnabled(false);
		comboProductStatus.setBackground(new Color(255, 255, 255));
		comboProductStatus.setBounds(728, 208, 695, 30);
		pnlContainInfo.add(comboProductStatus);
		
		comboProductStatus.addItem("Đang bán");
		comboProductStatus.addItem("Ngừng bán");
		
		btnExit = new JButton("Thoát");
		btnExit.setBackground(new Color(217, 217, 217));
		btnExit.setBorder(null);
		btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnExit.setBounds(1323, 798, 100, 30);
		pnlContainInfo.add(btnExit);
	}
}
