package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.MatteBorder;

public class ManagementGUI extends JFrame implements MouseListener{
	private JPanel pnlContent;
	private TitleBarComponent pnlTitleBar;
	private JMenu menuOrders;
	private JMenu menuProducts;
	private JMenu menuCustomers;
	private JMenu menuAccountManagement;
	private JMenuBar menuBar;
	private JMenuItem menuItemAddOrder;
	private DataSummaryGUI pnlDataSummary;
	private JMenuItem menuItemAddProduct;
	private JPanel pnlURL;
	private JLabel lblURL;
	private JLayeredPane layerPane;
	private SalesGUI pnlSalesGUI;
	private ProductGUI pnlProductGUI;
	private ProductDetailGUI pnlProduct;
	private OrderGUI pnlOrderGUI;
	private CustomerGUI pnlCustomerGUI;
	private JMenu menuTable;
	private TableGUI pnlTableGUI;
	private AccountGUI pnlAccountGUI;
	private JMenu menuHome;

	public ManagementGUI() {
		this.init();
	}

	private void init() {
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(screen.width, screen.height);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setUndecorated(true);
		
		ImageIcon img = new ImageIcon("img/DefaultIcon.png");
		this.setIconImage(img.getImage());
		
		pnlContent = new JPanel();
		pnlContent.setLayout(null);
		this.setContentPane(pnlContent);
		
		// title bar
		pnlTitleBar = new TitleBarComponent(this.getWidth());
		pnlTitleBar.lblMinimize.addMouseListener(this);
		pnlTitleBar.lblClose.addMouseListener(this);
		pnlTitleBar.setBounds(0, 0, 1920, 25);
		pnlContent.add(pnlTitleBar);
		
		// menu
		menuBar = new JMenuBar();
		menuBar.setBorder(null);
		menuBar.setBackground(new Color(217, 217, 217));
		menuBar.setBounds(0, 25, 1920, 30);
		pnlContent.add(menuBar);
		
		// Home
		menuHome = new JMenu("Trang chính");
		menuHome.addMouseListener(this);
		menuHome.addMouseListener(this);
		
		// Orders
		menuOrders = new JMenu("Hóa đơn");
		menuOrders.addMouseListener(this);
		
		
		menuItemAddOrder = new JMenuItem("Tạo hóa đơn mới");
		menuItemAddOrder.addMouseListener(this);
		menuOrders.add(menuItemAddOrder);
		
		
		// Products
		menuProducts = new JMenu("Sản phẩm");
		menuProducts.addMouseListener(this);
		
		menuItemAddProduct = new JMenuItem("Add a new Product");
		menuProducts.add(menuItemAddProduct);
		
		menuCustomers = new JMenu("Khách Hàng");
		menuCustomers.addMouseListener(this);
		
		menuTable = new JMenu("Bàn");
		menuTable.addMouseListener(this);

		
		menuAccountManagement = new JMenu("Tài khoản");
		menuAccountManagement.addMouseListener(this);
		

		menuBar.add(menuHome);
		menuBar.add(menuOrders);
		menuBar.add(menuProducts);
		menuBar.add(menuCustomers);
		menuBar.add(menuTable);
		menuBar.add(menuAccountManagement);
		
		pnlURL = new JPanel();
		pnlURL.setLayout(new FlowLayout(FlowLayout.LEFT, 26, 5));
		pnlURL.setPreferredSize(new Dimension(1920, 20));
		pnlURL.setBackground(new Color(255, 255, 255));
		pnlURL.setBorder(new MatteBorder(0, 0, 1, 0, new Color(238, 238, 238)));
		pnlURL.setBounds(0, 55, 1920, 20);
		pnlContent.add(pnlURL);
		
		lblURL = new JLabel("Home");
		lblURL.setFont(new Font("Arial", 0, 12));
		lblURL.setForeground(new Color(130, 130, 130));
		pnlURL.add(lblURL);
		
		// other panel by layer
		layerPane = new JLayeredPane();
		layerPane.setBounds(0, 75, 1920, 1125);
		pnlContent.add(layerPane);
		
		// Data Summary
		pnlDataSummary = new DataSummaryGUI();
		pnlDataSummary.setBounds(0, 0, 1920, 1200);
//		layerPane.add(pnlDataSummary);
		
		pnlDataSummary.btnSales.addMouseListener(this);
		
		// Sales
		pnlSalesGUI = new SalesGUI();
		pnlSalesGUI.setBounds(0, 0, 1920, 1200);
		layerPane.add(pnlSalesGUI);
		
		// Product
		pnlProductGUI = new ProductGUI();
		pnlProductGUI.setBounds(0, 0, 1920, 1200);
//		layerPane.add(pnlProductGUI);
		
		// Orders
		pnlOrderGUI = new OrderGUI();
		pnlOrderGUI.setBounds(0, 0, 1920, 1200);
//		layerPane.add(pnlOrderGUI);
		
		pnlOrderGUI.lblCreateOrder.addMouseListener(this);
		
		// Orders
		pnlCustomerGUI = new CustomerGUI();
		pnlCustomerGUI.setBounds(0, 0, 1920, 1200);
		
		
		// Table
		pnlTableGUI = new TableGUI();
		pnlTableGUI.setBounds(0, 0, 1920, 1200);
		
		// Account
		pnlAccountGUI = new AccountGUI();
		pnlAccountGUI.setBounds(0, 0, 1920, 1200);
		
		this.setVisible(true);
	}
	
	public void changeForm(JPanel form) {
		layerPane.removeAll();
		layerPane.add(form);
		layerPane.repaint();
		layerPane.revalidate();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(pnlTitleBar.lblMinimize)) {
			this.setState(JFrame.ICONIFIED);
		}else if(obj.equals(pnlTitleBar.lblClose)) {
			System.exit(0);
		}else if(obj.equals(pnlDataSummary.btnSales) 
				|| obj.equals(pnlOrderGUI.lblCreateOrder)
				|| obj.equals(menuItemAddOrder)) {
			lblURL.setText("Bán Hàng");
			this.changeForm(pnlSalesGUI);
		}else if(obj.equals(menuOrders)) {
			lblURL.setText("Quản lý hóa đơn");
			this.changeForm(pnlOrderGUI);
		}else if(obj.equals(menuProducts)) {
			lblURL.setText("Quản lý sản phẩm");
			this.changeForm(pnlProductGUI);
		}else if(obj.equals(menuCustomers)) {
			lblURL.setText("Quản lý khách hàng");
			this.changeForm(pnlCustomerGUI);
		}else if(obj.equals(menuTable)) {
			lblURL.setText("Quản lý bàn");
			this.changeForm(pnlTableGUI);
		}else if(obj.equals(menuAccountManagement)) {
			lblURL.setText("Quản lý tài khoản");
			this.changeForm(pnlAccountGUI);
		}else if(obj.equals(menuHome)) {
			lblURL.setText("Trang chính");
			this.changeForm(pnlDataSummary);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	
	
}
