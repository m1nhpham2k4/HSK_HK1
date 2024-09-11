package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Panel;
import java.awt.Toolkit;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.CTHoaDonDAO;
import dao.HoaDonDAO;
import entity.CTHoaDon;
import entity.HoaDon;

public class OrderDetailsGUI extends JFrame{
	private JPanel pnlContent;
	private JPanel pnlContainInfo;
	private JPanel pnlTitle;
	private JLabel lblTitle;
	private JButton btnUpdate;
	private JButton btnSave;
	private JButton btnDelete;
	private JLabel lblOrderID;
	public JTextField txtOrderID;
	private JLabel lblCustomerID;
	public JTextField txtCustomerID;
	private JLabel lblEmployeeID;
	public JTextField txtEmployeeID;
	private JLabel lblOrderStatus;
	public JComboBox<String> comboOrderStatus;
	public JButton btnExit;
	private JPanel pnlContainOrderDetails;
	private JLabel lblOrderDate;
	public JTextField txtOrderDate;
	private JLabel lblTableID;
	public JTextField txtTableID;
	private JScrollPane scroll;
	private JTable tableOrderDetails;
	private JLabel lblTitleSumTotal;
	public JLabel lblSumTotal;
	private JLabel lblCustomerName;
	public JTextField txtCustomerName;
	private JLabel lblEmployeeName;
	public JTextField txtEmployeeName;
	private DefaultTableModel modelOrderDetails;
	private CTHoaDonDAO cthdDAO;

	public OrderDetailsGUI() {
		this.init();
	}
	
	public void init() {
		// khởi tạo kết nối đến CSDL
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cthdDAO = new CTHoaDonDAO();
		
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
		
		lblTitle = new JLabel("Thông Tin Chi Tiết Hóa Đơn");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 20));
		lblTitle.setHorizontalAlignment((int) CENTER_ALIGNMENT);
		pnlTitle.add(lblTitle);
		
		// content		
		btnUpdate = new JButton("Chỉnh Sửa");
		btnUpdate.setBackground(new Color(255, 186, 105));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 14));
		btnUpdate.setBorder(null);
		btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnUpdate.setBounds(28, 798, 200, 30);
		pnlContainInfo.add(btnUpdate);
		
		btnSave = new JButton("Lưu");
		btnSave.setBackground(new Color(0, 218, 205));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Arial", Font.BOLD, 14));
		btnSave.setBorder(null);
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSave.setBounds(242, 798, 200, 30);
		pnlContainInfo.add(btnSave);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setBackground(new Color(255, 124, 124));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBorder(null);
		btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDelete.setBounds(456, 798, 200, 30);
		pnlContainInfo.add(btnDelete);
		
		// Mã sản phẩm
		lblOrderID = new JLabel("Mã Hóa Đơn");
		lblOrderID.setFont(new Font("Arial", 0, 14));
		lblOrderID.setBounds(28, 56, 100, 30);
		pnlContainInfo.add(lblOrderID);
		
		txtOrderID = new JTextField();
		txtOrderID.setBackground(new Color(255, 255, 255));
		txtOrderID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtOrderID.setBounds(205, 56, 510, 30);
		pnlContainInfo.add(txtOrderID);
		
		// Mã khách hàng
		lblCustomerID = new JLabel("Mã khách hàng");
		lblCustomerID.setFont(new Font("Arial", 0, 14));
		lblCustomerID.setBounds(28, 94, 100, 30);
		pnlContainInfo.add(lblCustomerID);
		
		txtCustomerID = new JTextField();
		txtCustomerID.setBackground(new Color(255, 255, 255));
		txtCustomerID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerID.setBounds(205, 94, 510, 30);
		pnlContainInfo.add(txtCustomerID);
		
		// Tên khách hàng
		lblCustomerName = new JLabel("Tên khách hàng");
		lblCustomerName.setFont(new Font("Arial", 0, 14));
		lblCustomerName.setBounds(736, 94, 118, 30);
		pnlContainInfo.add(lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setBackground(new Color(255, 255, 255));
		txtCustomerName.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerName.setBounds(913, 94, 510, 30);
		pnlContainInfo.add(txtCustomerName);
		
		// Mã nhân viên
		lblEmployeeID = new JLabel("Mã nhân viên");
		lblEmployeeID.setFont(new Font("Arial", 0, 14));
		lblEmployeeID.setBounds(28, 132, 100, 30);
		pnlContainInfo.add(lblEmployeeID);
		
		txtEmployeeID = new JTextField();
		txtEmployeeID.setBackground(new Color(255, 255, 255));
		txtEmployeeID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtEmployeeID.setBounds(205, 132, 510, 30);
		pnlContainInfo.add(txtEmployeeID);
		
		// Tên nhân viên
		lblEmployeeName = new JLabel("Tên nhân viên");
		lblEmployeeName.setFont(new Font("Arial", 0, 14));
		lblEmployeeName.setBounds(736, 132, 118, 30);
		pnlContainInfo.add(lblEmployeeName);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setBackground(new Color(255, 255, 255));
		txtEmployeeName.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtEmployeeName.setBounds(913, 132, 510, 30);
		pnlContainInfo.add(txtEmployeeName);
		
		// Ngày tạo
		lblOrderDate = new JLabel("Ngày tạo");
		lblOrderDate.setFont(new Font("Arial", 0, 14));
		lblOrderDate.setBounds(28, 170, 100, 30);
		pnlContainInfo.add(lblOrderDate);
		
		txtOrderDate = new JTextField();
		txtOrderDate.setBackground(new Color(255, 255, 255));
		txtOrderDate.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtOrderDate.setBounds(205, 170, 510, 30);
		pnlContainInfo.add(txtOrderDate);
		
		// Số bàn
		lblTableID = new JLabel("Số bàn");
		lblTableID.setFont(new Font("Arial", 0, 14));
		lblTableID.setBounds(28, 208, 100, 30);
		pnlContainInfo.add(lblTableID);
		
		txtTableID = new JTextField();
		txtTableID.setBackground(new Color(255, 255, 255));
		txtTableID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtTableID.setBounds(205, 208, 510, 30);
		pnlContainInfo.add(txtTableID);
		
		// Trạng thái
		lblOrderStatus = new JLabel("Trạng thái");
		lblOrderStatus.setFont(new Font("Arial", 0, 14));
		lblOrderStatus.setBounds(28, 246, 100, 30);
		pnlContainInfo.add(lblOrderStatus);
		
		comboOrderStatus = new JComboBox<String>();
		comboOrderStatus.setBackground(new Color(255, 255, 255));
		comboOrderStatus.setBounds(205, 246, 510, 30);
		pnlContainInfo.add(comboOrderStatus);
		
		comboOrderStatus.addItem("Chưa thanh toán");
		comboOrderStatus.addItem("Đã thanh toán");
		
		btnExit = new JButton("Thoát");
		btnExit.setBackground(new Color(217, 217, 217));
		btnExit.setBorder(null);
		btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnExit.setBounds(1323, 798, 100, 30);
		pnlContainInfo.add(btnExit);
		
		pnlContainOrderDetails = new JPanel();
		pnlContainOrderDetails.setLayout(new BorderLayout());
		pnlContainOrderDetails.setBounds(28, 312, 1395, 342);
		pnlContainInfo.add(pnlContainOrderDetails);
		
		tableOrderDetails = new JTable();
		tableOrderDetails.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"STT","Tên","Số lượng","Đơn giá"
			}
		));
		modelOrderDetails = (DefaultTableModel)tableOrderDetails.getModel();
		
		scroll = new JScrollPane(tableOrderDetails);
		pnlContainOrderDetails.add(scroll);
		
		lblTitleSumTotal = new JLabel("Tổng hóa đơn");
		lblTitleSumTotal.setFont(new Font("Arial", Font.BOLD, 14));	
		lblTitleSumTotal.setBounds(28, 654, 100, 30);
		pnlContainInfo.add(lblTitleSumTotal);
		
		lblSumTotal = new JLabel("1.000.000 đ", JLabel.RIGHT);
		lblSumTotal.setFont(new Font("Arial", 0, 14));	
		lblSumTotal.setBounds(1323, 654, 100, 30);
		pnlContainInfo.add(lblSumTotal);
	}
	
	public void DocDuLieuDatabaseVaoTable(String maHD) {
		ArrayList<CTHoaDon> list = cthdDAO.getAllTbCTHoaDon(maHD);
		int row = tableOrderDetails.getRowCount();
		for(int j=0; j<row; j++) {
			modelOrderDetails.removeRow(0);			
		}
		int i=1;
		for (CTHoaDon cthd : list) {
			modelOrderDetails.addRow(new Object[] {i++, cthd.getSanPham().getTenSP(), cthd.getSoLuong(), cthd.getDonGia()});
		}
	}
}
