package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import entity.HoaDon;
import entity.KhachHang;

public class CustomerGUI extends JPanel implements MouseListener{
	private JLabel lblCreateCustomer;
	private JLabel lblExportPDF;
	private JLabel lblExportExcel;
	private JPanel pnlCustomerList;
	private JPanel pnlContainCustomerList;
	private JTable tableCustomer;
	private JScrollPane scroll;
	private JPanel pnlCustomerDetails;
	private JLabel lblCustomerPhone;
	private JTextField txtCustomerPhone;
	private JLabel lblCustomerName;
	private JTextField txtCustomerName;
	private JLabel lblCustomerPoint;
	private JTextField txtCustomerPoint;
	private JButton btnUpdate;
	private JButton btnSave;
	private JButton btnDelete;
	private JLabel lblCloseCustomer;
	private DefaultTableModel modelCustomer;
	private KhachHangDAO khDAO;

	public CustomerGUI() {
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
		khDAO = new KhachHangDAO();
		
		this.setLayout(null);
		this.setBackground(new Color(255, 255, 255));
		
		lblCreateCustomer = new JLabel();
		lblCreateCustomer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCreate.png")));
		lblCreateCustomer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCreateCustomer.setText("true");
		lblCreateCustomer.addMouseListener(this);
		lblCreateCustomer.setBounds(25, 12, 50, 50);
		this.add(lblCreateCustomer);
		
		lblExportPDF = new JLabel();
		lblExportPDF.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconPDF.png")));
		lblExportPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblExportPDF.setBounds(116, 12, 50, 50);
		this.add(lblExportPDF);
		
		lblExportExcel = new JLabel();
		lblExportExcel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconExcel.png")));
		lblExportExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblExportExcel.setBounds(194, 12, 50, 50);
		this.add(lblExportExcel);
		
		pnlCustomerList = new JPanel();
		pnlCustomerList.setLayout(null);
		pnlCustomerList.setBackground(new Color(217, 217, 217));
		pnlCustomerList.setBounds(13, 78, 1895, 1035);
		this.add(pnlCustomerList);
		
		pnlContainCustomerList = new JPanel();
		pnlContainCustomerList.setLayout(new BorderLayout());
		pnlContainCustomerList.setBackground(new Color(255, 255, 255));
		pnlContainCustomerList.setBounds(12, 67, 1869, 956);
		pnlCustomerList.add(pnlContainCustomerList);
		
		tableCustomer = new JTable();
		tableCustomer.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"STT","Mã khách hàng","Tên khách hàng","Điểm tích lũy"
			}
		));
//		tableCustomer.setEnabled(false);
		tableCustomer.addMouseListener(this);
		modelCustomer = (DefaultTableModel)tableCustomer.getModel();
		
		scroll = new JScrollPane(tableCustomer);
		pnlContainCustomerList.add(scroll);
		
		// Chi tiết khách hàng
		pnlCustomerDetails = new JPanel();
		pnlCustomerDetails.setLayout(null);
		pnlCustomerDetails.setBounds(1357, 67, 1869, 956);
		pnlCustomerList.add(pnlCustomerDetails);
		pnlCustomerDetails.setVisible(false);
		
		lblCloseCustomer = new JLabel();
		lblCloseCustomer.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCloseWhite.png")));
		lblCloseCustomer.setBackground(Color.black);
		lblCloseCustomer.setOpaque(true);
		lblCloseCustomer.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCloseCustomer.addMouseListener(this);
		lblCloseCustomer.setBounds(503, 10, 15, 15);
		pnlCustomerDetails.add(lblCloseCustomer);
		
		// Mã khách hàng
		lblCustomerPhone = new JLabel("Số điện thoại");
		lblCustomerPhone.setFont(new Font("Arial", 0, 14));
		lblCustomerPhone.setBounds(14, 42, 115, 30);
		pnlCustomerDetails.add(lblCustomerPhone);
		
		txtCustomerPhone = new JTextField();
		txtCustomerPhone.setBackground(new Color(255, 255, 255));
		txtCustomerPhone.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerPhone.setBounds(129, 42, 380, 30);
		pnlCustomerDetails.add(txtCustomerPhone);
		
		// Tên khách hàng
		lblCustomerName = new JLabel("Tên khách hàng");
		lblCustomerName.setFont(new Font("Arial", 0, 14));
		lblCustomerName.setBounds(14, 82, 115, 30);
		pnlCustomerDetails.add(lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setBackground(new Color(255, 255, 255));
		txtCustomerName.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerName.setBounds(129, 82, 380, 30);
		pnlCustomerDetails.add(txtCustomerName);
		
		// Điểm tích lũy
		lblCustomerPoint = new JLabel("Điểm tích lũy");
		lblCustomerPoint.setFont(new Font("Arial", 0, 14));
		lblCustomerPoint.setBounds(14, 122, 115, 30);
		pnlCustomerDetails.add(lblCustomerPoint);
		
		txtCustomerPoint = new JTextField();
		txtCustomerPoint.setBackground(new Color(255, 255, 255));
		txtCustomerPoint.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerPoint.setBounds(129, 122, 380, 30);
		pnlCustomerDetails.add(txtCustomerPoint);
		
		btnUpdate = new JButton("Chỉnh Sửa");
		btnUpdate.setBackground(new Color(255, 186, 105));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 14));
		btnUpdate.setBorder(null);
		btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnUpdate.setBounds(14, 871, 495, 30);
		pnlCustomerDetails.add(btnUpdate);
		
		btnSave = new JButton("Lưu");
		btnSave.setBackground(new Color(0, 218, 205));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Arial", Font.BOLD, 14));
		btnSave.setBorder(null);
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSave.setBounds(14, 911, 495, 30);
		pnlCustomerDetails.add(btnSave);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setBackground(new Color(255, 124, 124));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBorder(null);
		btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDelete.setBounds(14, 831, 495, 30);
		pnlCustomerDetails.add(btnDelete);
		
		DocDuLieuDatabaseVaoTable();
	}
	
	public void DocDuLieuDatabaseVaoTable() {
		ArrayList<KhachHang> list = khDAO.getAllTbKhachHang();
		int i=1;
		for (KhachHang kh : list) {
			modelCustomer.addRow(new Object[] {i++ , kh.getMaKH(), kh.getTenKH(), kh.getDiemTL()});
		}
	}
	
	public void activeCustomerDetails() {
		pnlCustomerDetails.setVisible(true);
		int row = tableCustomer.getSelectedRow();
		System.out.println(row);
		KhachHang kh = khDAO.getOneKhachHang(modelCustomer.getValueAt(row, 1)+"");
		txtCustomerPhone.setText(kh.getSoDT());
		txtCustomerName.setText(kh.getTenKH());
		txtCustomerPoint.setText(kh.getDiemTL()+"");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(lblCreateCustomer)) {
			pnlContainCustomerList.setBounds(12, 67, 1332, 956);
			pnlCustomerDetails.setVisible(true);
		}else if(obj.equals(tableCustomer)) {
			pnlContainCustomerList.setBounds(12, 67, 1332, 956);
			activeCustomerDetails();
		}else if(obj.equals(lblCloseCustomer)) {
			pnlContainCustomerList.setBounds(12, 67, 1869, 956);
			pnlCustomerDetails.setVisible(false);
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
