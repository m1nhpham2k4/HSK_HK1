package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.BanDAO;
import dao.CTHoaDonDAO;
import dao.CTPhieuDatDAO;
import dao.HoaDonDAO;
import dao.PhieuDatDAO;
import dao.SanPhamDAO;
import entity.Ban;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.KhachHang;
import entity.NhanVien;
import entity.SanPham;

public class SalesGUI extends JPanel implements MouseListener, KeyListener, ItemListener{
	private JPanel pnlOrder;
	private JButton btnCreateOrder;
	private JButton btnTable;
	private JButton btnTakeAway;
	private JPanel pnlProductList;
	private JPanel pnlContainProductList;
	private JLayeredPane layerPane;
	private JPanel pnlContainLayer;
	private JPanel pnlTableList;
	private JLabel lblCloseTableForm;
	private JPanel pnlContainTableList;
	private JTextField txtSearchProduct;
	private JTextField txtOrderCount;
	private JComboBox<String> comboOrderList;
	private JLabel lblChooseTable;
	private JButton btnPay;
	private JComboBox<String> comboProductType;
	private JButton btnDeleteOrder;
	private JPanel pnlContainOrderDetail;
	private JScrollPane scrollProductList;
	private JTable tableProduct;
	private JTable tableOrderDetails;
	private JScrollPane scrollOrderDetails;
	private JScrollPane scrollTableList;
	private JTable tableTable;
	private JLabel lblSearch;
	private JPanel pnlPayment;
	private JLabel lblClosePayment;
	private JLabel lblOrderID;
	private JTextField txtOrderID;
	private JLabel lblCustomerID;
	private JTextField txtCustomerID;
	private JLabel lblCustomerName;
	private JTextField txtCustomerName;
	private JLabel lblEmployeeName;
	private JTextField txtEmployeeName;
	private JLabel lblSumTotal;
	private JTextField txtSumTotal;
	private JLabel lblCustomerPay;
	private JTextField txtCustomerPay;
	private JLabel lblCustomerChange;
	private JTextField txtCustomerChange;
	private JLabel lblPayMethod;
	private JComboBox<String> comboPayMenthod;
	private JLabel lblNote;
	private JTextArea txtNote;
	private JLabel lblInvoice;
	private JRadioButton rdYes;
	private JRadioButton rdNo;
	private ButtonGroup grRDInvoice;
	private JLabel lblOrderDate;
	private JTextField txtOrderDate;
	private JLabel lblTableID;
	private JTextField txtTableID;
	private JButton btnConfirmPay;
	private JCheckBox checkBooking;
	private JPanel pnlContainBooking;
	private JLabel lblBookingDate;
	private JTextField txtBookingDate;
	private JLabel lblCustomerCount;
	private JTextField txtCustomerCount;
	private JLabel lblCustomerRequest;
	private JTextArea txtCustomerRequest;
	private JButton btnSaveRequest;
	private SanPhamDAO spDao;
	private HoaDonDAO hdDao;
	private CTHoaDonDAO cthdDao;
	private PhieuDatDAO phieuDao;
	private CTPhieuDatDAO ctpDao;
	private DefaultTableModel modelProduct;
	private DefaultTableModel modelOrderDetails;
	private BanDAO banDao;
	private DefaultTableModel modelTable;

	public SalesGUI() {
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
		spDao = new SanPhamDAO();
		hdDao = new HoaDonDAO();
		cthdDao = new CTHoaDonDAO();
		phieuDao = new PhieuDatDAO();
		ctpDao = new CTPhieuDatDAO();
		banDao = new BanDAO();
		
		this.setLayout(null);
		this.setBackground(new Color(242, 242, 242));
		
		// order list
		pnlOrder = new JPanel();
		pnlOrder.setLayout(null);
		pnlOrder.setBackground(new Color(255, 255, 255));
		pnlOrder.setBounds(1154, 77, 744, 905);
		this.add(pnlOrder);
		
		btnCreateOrder = new JButton("Create New Order");
		btnCreateOrder.setBackground(new Color(0, 218, 205));
		btnCreateOrder.setForeground(new Color(255, 255, 255));
		btnCreateOrder.setFont(new Font("Arial", Font.BOLD, 14));
		btnCreateOrder.setBorder(null);
		btnCreateOrder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCreateOrder.addMouseListener(this);
		btnCreateOrder.setBounds(20, 17, 160, 50);
		pnlOrder.add(btnCreateOrder);
		
		btnTable = new JButton();
		btnTable.setBackground(new Color(194, 233, 255));
		btnTable.setBorder(null);
		btnTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnTable.addMouseListener(this);
		btnTable.setBounds(72, 802, 300, 70);
		pnlOrder.add(btnTable);
		
		btnTakeAway = new JButton();
		btnTakeAway.setSelected(true);
		btnTakeAway.setBackground(new Color(0, 192, 202));
		btnTakeAway.setBorder(null);
		btnTakeAway.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnTakeAway.addMouseListener(this);
		btnTakeAway.setBounds(372, 802, 300, 70);
		pnlOrder.add(btnTakeAway);
		
		txtOrderCount = new JTextField();
		txtOrderCount.setBackground(new Color(255, 255, 255));
		txtOrderCount.setBorder(new MatteBorder(1, 1, 1, 1, new Color(0, 0, 0)));
		txtOrderCount.setFocusable(false);
		txtOrderCount.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
		txtOrderCount.setBounds(376, 17, 50, 50);
		pnlOrder.add(txtOrderCount);
		
		comboOrderList = new JComboBox<String>();
		comboOrderList.setBackground(new Color(255, 255, 255));
		comboOrderList.setBorder(new MatteBorder(1, 0, 1, 1, new Color(0, 0, 0)));
		comboOrderList.addItemListener(this);
		comboOrderList.setBounds(426, 17, 300, 50);
		pnlOrder.add(comboOrderList);
		
		pnlContainOrderDetail = new JPanel();
		pnlContainOrderDetail.setLayout(new BorderLayout());
		pnlContainOrderDetail.setBackground(new Color(255, 255, 255));
		pnlContainOrderDetail.setBounds(20, 94, 706, 684);
		pnlOrder.add(pnlContainOrderDetail);
//		pnlContainOrderDetail.setVisible(false);
		
		tableOrderDetails = new JTable();
		tableOrderDetails.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"STT","Mã","Tên","Số lượng","Đơn giá"
			}
		));
		tableOrderDetails.addMouseListener(this);
		scrollOrderDetails = new JScrollPane(tableOrderDetails);
		pnlContainOrderDetail.add(scrollOrderDetails);
		modelOrderDetails = (DefaultTableModel)tableOrderDetails.getModel();
		
		//////////////////////////
		lblChooseTable = new JLabel("<<  Chọn Bàn");
		lblChooseTable.setForeground(new Color(0, 0, 0));
		lblChooseTable.setFont(new Font("Arial", Font.BOLD, 16));
		lblChooseTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblChooseTable.setVisible(false);
		lblChooseTable.addMouseListener(this);
		lblChooseTable.setBounds(1154, 1004, 110, 18);
		this.add(lblChooseTable);
		
		btnPay = new JButton("Thanh Toán");
		btnPay.setBackground(new Color(0, 218, 205));
		btnPay.setForeground(new Color(255, 255, 255));
		btnPay.setFont(new Font("Arial", Font.BOLD, 20));
		btnPay.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnPay.setBorder(null);
		btnPay.addMouseListener(this);
		btnPay.setBounds(1154, 1052, 618, 50);
		this.add(btnPay);
		
		btnDeleteOrder = new JButton("Xóa");
		btnDeleteOrder.setBackground(new Color(255, 124, 124));
		btnDeleteOrder.setBorder(null);
		btnDeleteOrder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDeleteOrder.addMouseListener(this);
		btnDeleteOrder.setBounds(1795, 1052, 103, 50);
		this.add(btnDeleteOrder);
		
		/////////////////////////////////////
		pnlContainLayer = new JPanel();
		pnlContainLayer.setLayout(new BorderLayout());
		pnlContainLayer.setBounds(26, 77, 1115, 1025);
		this.add(pnlContainLayer);
		
		layerPane = new JLayeredPane();
		pnlContainLayer.add(layerPane);
		
		// product list
		pnlProductList = new JPanel();
		pnlProductList.setLayout(null);
		pnlProductList.setBackground(new Color(255, 255, 255));
		pnlProductList.setBounds(0, 0, 1115, 1025);
		layerPane.add(pnlProductList);
		
		pnlContainProductList = new JPanel();
		pnlContainProductList.setLayout(new BorderLayout());
		pnlContainProductList.setBackground(new Color(214, 214, 214));
		pnlContainProductList.setBounds(18, 94, 1080, 912);
		pnlProductList.add(pnlContainProductList);
		
		comboProductType = new JComboBox<String>();
		comboProductType.setBackground(new Color(255, 255, 255));
		comboProductType.addMouseListener(this);
		comboProductType.addItemListener(this);
		comboProductType.setBounds(963, 17, 135, 30);
		pnlProductList.add(comboProductType);
		
		comboProductType.addItem("Tất cả");
		comboProductType.addItem("Thức Uống");
		comboProductType.addItem("Bánh");
		
		tableProduct = new JTable();
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"STT","Mã","Hình ảnh","Tên","Đơn giá"
			}
		) {
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;
			Class[] columnTypes = new Class[] {
					Object.class, Object.class, ImageIcon.class, Object.class, Object.class	
			};
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		tableProduct.addMouseListener(this);
		
		modelProduct = (DefaultTableModel) tableProduct.getModel();
		tableProduct.setRowHeight(50);
		
		scrollProductList = new JScrollPane(tableProduct);
		scrollProductList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnlContainProductList.add(scrollProductList);
		
		txtSearchProduct = new JTextField();
		txtSearchProduct.setBorder(new MatteBorder(1, 0, 1, 1, new Color(0, 0, 0)));
		txtSearchProduct.addKeyListener(this);
		txtSearchProduct.setBounds(48, 17, 759, 30);
		pnlProductList.add(txtSearchProduct);
		
		lblSearch = new JLabel();
		lblSearch.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconSearch.png")));
		lblSearch.setBorder(new MatteBorder(1, 1, 1, 0, new Color(0, 0, 0)));
		lblSearch.setBounds(18, 17, 30, 30);
		pnlProductList.add(lblSearch);
		
		// table list
		pnlTableList = new JPanel();
		pnlTableList.setLayout(null);
		pnlTableList.setBackground(new Color(255, 255, 255));
		pnlTableList.setBounds(0, 0, 1115, 1025);
		
		pnlContainTableList = new JPanel();
		pnlContainTableList = new JPanel(new BorderLayout());
		pnlContainTableList.setBackground(new Color(214, 214, 214));
		pnlContainTableList.setBounds(18, 94, 1080, 912);
		pnlTableList.add(pnlContainTableList);
		
		lblCloseTableForm = new JLabel("hello");
		lblCloseTableForm.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCloseWhite.png")));
		lblCloseTableForm.setBackground(Color.black);
		lblCloseTableForm.setOpaque(true);
		lblCloseTableForm.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCloseTableForm.addMouseListener(this);
		lblCloseTableForm.setBounds(1084, 24, 15, 15);
		pnlTableList.add(lblCloseTableForm);
		
		tableTable = new JTable();
		tableTable.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"STT","Số Bàn","Số lượng ghế","Trạng thái"
			}
		));
		tableTable.addMouseListener(this);
		modelTable = (DefaultTableModel) tableTable.getModel();
		
		scrollTableList = new JScrollPane(tableTable);
		scrollTableList.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		pnlContainTableList.add(scrollTableList);
		
		// nút đặt trước
		checkBooking = new JCheckBox("Đặt trước");
		checkBooking.setBackground(new Color(255, 255, 255));
		checkBooking.setFont(new Font("Arial", 0, 14));
		checkBooking.addMouseListener(this);
		checkBooking.setBounds(962, 64, 136, 30);
		pnlTableList.add(checkBooking);	
		
		// Booking
		pnlContainBooking = new JPanel();
		pnlContainBooking.setLayout(null);
		pnlContainBooking.setBounds(18, 596, 1080, 412);
		pnlTableList.add(pnlContainBooking);
		pnlContainBooking.setVisible(false);
		
		// ngày đặt
		lblBookingDate = new JLabel("Ngày đặt");
		lblBookingDate.setFont(new Font("Arial", 0, 14));
		lblBookingDate.setBounds(8, 11, 118, 30);
		pnlContainBooking.add(lblBookingDate);
		
		txtBookingDate = new JTextField();
		txtBookingDate.setBackground(new Color(255, 255, 255));
		txtBookingDate.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtBookingDate.setBounds(108, 11, 400, 30);
		pnlContainBooking.add(txtBookingDate);
		
		// Số lượng chỗ
		lblCustomerCount = new JLabel("Số lượng");
		lblCustomerCount.setFont(new Font("Arial", 0, 14));
		lblCustomerCount.setBounds(8, 51, 118, 30);
		pnlContainBooking.add(lblCustomerCount);
		
		txtCustomerCount = new JTextField();
		txtCustomerCount.setBackground(new Color(255, 255, 255));
		txtCustomerCount.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerCount.setBounds(108, 51, 400, 30);
		pnlContainBooking.add(txtCustomerCount);
		
		// yêu cầu
		lblCustomerRequest = new JLabel("Yêu cầu");
		lblCustomerRequest.setFont(new Font("Arial", 0, 14));
		lblCustomerRequest.setBounds(8, 96, 118, 30);
		pnlContainBooking.add(lblCustomerRequest);
		
		txtCustomerRequest = new JTextArea();
		txtCustomerRequest.setBackground(new Color(255, 255, 255));
		txtCustomerRequest.setBorder(new MatteBorder(1, 1, 1, 1, new Color(184, 184, 184)));
		txtCustomerRequest.setBounds(108, 96, 400, 90);
		pnlContainBooking.add(txtCustomerRequest);
		
		btnSaveRequest = new JButton("Lưu");
		btnSaveRequest.addMouseListener(this);
		btnSaveRequest.setBounds(8, 374, 100, 30);
		pnlContainBooking.add(btnSaveRequest);
		
		// Payment
		pnlPayment = new JPanel();
		pnlPayment.setLayout(null);
		pnlPayment.setBackground(new Color(255, 255, 255));
		pnlPayment.setBounds(0, 0, 1115, 1025);
		
		lblClosePayment= new JLabel();
		lblClosePayment.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCloseWhite.png")));
		lblClosePayment.setBackground(Color.black);
		lblClosePayment.setOpaque(true);
		lblClosePayment.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblClosePayment.addMouseListener(this);
		lblClosePayment.setBounds(1084, 24, 15, 15);
		pnlPayment.add(lblClosePayment);
		
		// Mã hóa đơn
		lblOrderID = new JLabel("Mã hóa đơn");
		lblOrderID.setFont(new Font("Arial", 0, 14));
		lblOrderID.setBounds(15, 27, 100, 30);
		pnlPayment.add(lblOrderID);
		
		txtOrderID = new JTextField();
		txtOrderID.setBackground(new Color(255, 255, 255));
		txtOrderID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtOrderID.setBounds(115, 27, 400, 30);
		pnlPayment.add(txtOrderID);
		
		// Ngày tạo
		lblOrderDate = new JLabel("Ngày tạo");
		lblOrderDate.setFont(new Font("Arial", 0, 14));
		lblOrderDate.setBounds(15, 67, 100, 30);
		pnlPayment.add(lblOrderDate);
		
		txtOrderDate = new JTextField();
		txtOrderDate.setBackground(new Color(255, 255, 255));
		txtOrderDate.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtOrderDate.setBounds(115, 67, 400, 30);
		pnlPayment.add(txtOrderDate);
		
		// Tên nhân viên
		lblEmployeeName = new JLabel("Tên nhân viên");
		lblEmployeeName.setFont(new Font("Arial", 0, 14));
		lblEmployeeName.setBounds(15, 107, 118, 30);
		pnlPayment.add(lblEmployeeName);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setBackground(new Color(255, 255, 255));
		txtEmployeeName.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtEmployeeName.setBounds(115, 107, 400, 30);
		pnlPayment.add(txtEmployeeName);
		
		// Tổng hóa đơn
		lblSumTotal = new JLabel("Tổng hóa đơn");
		lblSumTotal.setFont(new Font("Arial", 0, 14));
		lblSumTotal.setBounds(15, 147, 118, 30);
		pnlPayment.add(lblSumTotal);
		
		txtSumTotal = new JTextField();
		txtSumTotal.setBackground(new Color(255, 255, 255));
		txtSumTotal.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtSumTotal.setBounds(115, 147, 400, 30);
		pnlPayment.add(txtSumTotal);
		
		// Tiền khách trả
		lblCustomerPay = new JLabel("Tiền khách trả");
		lblCustomerPay.setFont(new Font("Arial", 0, 14));
		lblCustomerPay.setBounds(15, 187, 118, 30);
		pnlPayment.add(lblCustomerPay);
		
		txtCustomerPay = new JTextField();
		txtCustomerPay.setBackground(new Color(255, 255, 255));
		txtCustomerPay.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerPay.addKeyListener(this);
		txtCustomerPay.setBounds(115, 187, 400, 30);
		pnlPayment.add(txtCustomerPay);
		
		// Tiền trả khách
		lblCustomerChange = new JLabel("Tiền trả khách");
		lblCustomerChange.setFont(new Font("Arial", 0, 14));
		lblCustomerChange.setBounds(15, 227, 118, 30);
		pnlPayment.add(lblCustomerChange);
		
		txtCustomerChange = new JTextField();
		txtCustomerChange.setBackground(new Color(255, 255, 255));
		txtCustomerChange.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerChange.setBounds(115, 227, 400, 30);
		pnlPayment.add(txtCustomerChange);
		
		lblTableID = new JLabel("Số bàn");
		lblTableID.setFont(new Font("Arial", 0, 14));
		lblTableID.setBounds(15, 267, 118, 30);
		pnlPayment.add(lblTableID);
		
		txtTableID = new JTextField();
		txtTableID.setBackground(new Color(255, 255, 255));
		txtTableID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtTableID.setBounds(115, 267, 400, 30);
		pnlPayment.add(txtTableID);
		
		// Phương thức thanh toán
		lblPayMethod = new JLabel("Phương thức thanh toán");
		lblPayMethod.setFont(new Font("Arial", 0, 14));
		lblPayMethod.setBounds(15, 307, 165, 30);
		pnlPayment.add(lblPayMethod);
		
		comboPayMenthod = new JComboBox<String>();
		comboPayMenthod.setBackground(new Color(255, 255, 255));
		comboPayMenthod.addItemListener(this);
		comboPayMenthod.setBounds(180, 307, 335, 30);
		pnlPayment.add(comboPayMenthod);
		
		comboPayMenthod.addItem("Tiền mặt");
		comboPayMenthod.addItem("Chuyển khoản");
		
		// Mã khách hàng
		lblCustomerID = new JLabel("Số điện thoại");
		lblCustomerID.setFont(new Font("Arial", 0, 14));
		lblCustomerID.setBounds(543, 27, 106, 30);
		pnlPayment.add(lblCustomerID);
		
		txtCustomerID = new JTextField();
		txtCustomerID.setBackground(new Color(255, 255, 255));
		txtCustomerID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerID.setBounds(654, 27, 400, 30);
		pnlPayment.add(txtCustomerID);
		
		// Tên khách hàng
		lblCustomerName = new JLabel("Tên khách hàng");
		lblCustomerName.setFont(new Font("Arial", 0, 14));
		lblCustomerName.setBounds(543, 67, 106, 30);
		pnlPayment.add(lblCustomerName);
		
		txtCustomerName = new JTextField();
		txtCustomerName.setBackground(new Color(255, 255, 255));
		txtCustomerName.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtCustomerName.setBounds(654, 67, 400, 30);
		pnlPayment.add(txtCustomerName);
		
		// Ghi chú
		lblNote = new JLabel("Ghi chú");
		lblNote.setFont(new Font("Arial", 0, 14));
		lblNote.setBounds(543, 117, 106, 30);
		pnlPayment.add(lblNote);
		
		txtNote = new JTextArea();
		txtNote.setBackground(new Color(255, 255, 255));
		txtNote.setBorder(new MatteBorder(1, 1, 1, 1, new Color(184, 184, 184)));
		txtNote.setBounds(654, 127, 400, 90);
		pnlPayment.add(txtNote);
		
		// Xuất hóa đơn
		lblInvoice = new JLabel("Xuất hóa đơn");
		lblInvoice.setFont(new Font("Arial", 0, 14));
		lblInvoice.setBounds(543, 227, 106, 30);
		pnlPayment.add(lblInvoice);
		
		rdYes = new JRadioButton("Có");
		rdYes.setBackground(new Color(255, 255, 255));
		rdYes.setSelected(true);
		rdYes.setBounds(654, 227, 108, 30);
		pnlPayment.add(rdYes);
		
		rdNo = new JRadioButton("Không");
		rdNo.setBackground(new Color(255, 255, 255));
		rdNo.setBounds(762, 227, 108, 30);
		pnlPayment.add(rdNo);
		
		grRDInvoice = new ButtonGroup();
		grRDInvoice.add(rdYes);
		grRDInvoice.add(rdNo);
		
		// nút xác nhận thanh toán
		btnConfirmPay = new JButton("Xác nhận thanh toán");
		btnConfirmPay.addMouseListener(this);
		btnConfirmPay.setBounds(15, 975, 300, 30);
		pnlPayment.add(btnConfirmPay);
		
		ArrayList<SanPham> list = spDao.getAllTbSanPham();
		DocDuLieuDatabaseVaoTableProduct(list);
		getOrderNonPayment();
		DocDuLieuDatabaseVaoTableTable();
	}
	
	public void DocDuLieuDatabaseVaoTableTable() {
		ArrayList<Ban> list = banDao.getAllTbBan();
		String trangThai="";
		int i=1;
		for (Ban b : list) {
			if(b.getTrangThai()==1) {
				trangThai = "Đang trống";
			}else if(b.getTrangThai()==2) {
				trangThai = "Đang sử dụng";
			}else {
				trangThai = "Đã đặt trước";
			}
			modelTable.addRow(new Object[] {i++ , b.getMaBan(), b.getSoGhe(), trangThai});
		}
	}
	
	
	public void DocDuLieuDatabaseVaoTableProduct(ArrayList<SanPham> list) {
		int n = tableProduct.getRowCount();
		for(int i=0; i<n; i++) {
			modelProduct.removeRow(0);
		}
		int i=1;
		for (SanPham sp : list) {
			modelProduct.addRow(new Object[] {i++ , sp.getMaSP(), ResizeImg(sp.getHinhAnh()),sp.getTenSP(),  sp.getDonGia()});
		}
	}
	
	public ImageIcon ResizeImg(String imgPath) {
		ImageIcon myImg = new ImageIcon(imgPath);
		Image img = myImg.getImage();
		Image newImg = img.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
	
	public void changeForm(JPanel form) {
		layerPane.removeAll();
		layerPane.add(form);
		layerPane.repaint();
		layerPane.revalidate();
	}
	
	public void changeOption() {
		if(btnTable.isSelected()==true) {
			btnTable.setBackground(new Color(0, 192, 202));
			btnTakeAway.setBackground(new Color(194, 233, 255));	
		}else {
			btnTakeAway.setBackground(new Color(0, 192, 202));
			btnTable.setBackground(new Color(194, 233, 255));
		}
	}
	
	public String createOrderID(int n) {
		LocalDate date = LocalDate.now();
		String yyyy = date.getYear()+"";
		String mm = "";
		if(date.getMonthValue()<10) {
			mm = "0"+date.getMonthValue();
		}else {
			mm = ""+date.getMonthValue();
		}
		String dd = date.getDayOfMonth()+"";
		if(n<10) {
			return yyyy+mm+dd+"-0000" + n;
		}else if(n<100) {
			return yyyy+mm+dd+"-000"  + n;
		}else if(n<1000) {
			return  yyyy+mm+dd+"-00"  + n;
		}else if(n<10000) {
			return yyyy+mm+dd+ "-0"  + n;
		}else {
			return yyyy+mm+dd+ "-"  + n;
		}
	}
	
	private void newOrderID() {
		int n = hdDao.getSLHoaDon();
		n++;
		String maHD = createOrderID(n);
		HoaDon hoaDon = new HoaDon(maHD);
		hdDao.createNewHoaDon(hoaDon);
		comboOrderList.addItem(maHD);
		
		txtOrderCount.setText(comboOrderList.getItemCount()+"");
		comboOrderList.setSelectedIndex(comboOrderList.getItemCount()-1);
	}
	
	public void getOrderNonPayment() {
		ArrayList<HoaDon> list= hdDao.getHoaDonChuaThanhToan();
		if(list!=null) {
			for(HoaDon hd: list) {
				comboOrderList.addItem(hd.getMaHD());
			}
			String maHD = comboOrderList.getItemAt(list.size()-1);
			ArrayList<CTHoaDon> listCT = cthdDao.getCTHDFromHD(maHD);
			
		}
		txtOrderCount.setText(comboOrderList.getItemCount()+"");
	}
	
	public void DocDuLieuDatabaseVaoTableCTHD(ArrayList<CTHoaDon> listCT) {
		int i=1;
		for (CTHoaDon ct : listCT) {
			modelOrderDetails.addRow(new Object[] {i++ , ct.getSanPham().getMaSP(), ct.getSanPham().getTenSP(), ct.getSoLuong(), ct.getDonGia()});
		}
	}

	private void deleteOrder() {
		if(comboOrderList.getItemCount()==0) {
			comboOrderList.addItem("");
			comboOrderList.removeItemAt(0);
		}else
		comboOrderList.removeItem(comboOrderList.getSelectedItem());
		txtOrderCount.setText(comboOrderList.getItemCount()+"");
		System.out.println(comboOrderList.getItemCount());
	}
	
	public void addProductToOrder() {
		int row = tableProduct.getSelectedRow();
		String maSP = modelProduct.getValueAt(row, 1)+"";
		
		for(int i=0; i<tableOrderDetails.getRowCount(); i++) {
			if(maSP.equals(modelOrderDetails.getValueAt(i, 1))) {
				String soLuong = modelOrderDetails.getValueAt(i, 3)+"";
				
				if(soLuong.equals("")) {
					modelOrderDetails.setValueAt(1, i, 3);
					return;
				}else {
					int sl = Integer.parseInt(soLuong);
					modelOrderDetails.setValueAt(++sl, i, 3);
					double donGia = Double.parseDouble(modelOrderDetails.getValueAt(i, 4)+"");
					donGia*=sl;
					modelOrderDetails.setValueAt(donGia, i, 4);
					return;
				}
			}
		}
		
		SanPham sp = spDao.getOneSanPham(maSP);
		modelOrderDetails.addRow(new Object[] {
				tableOrderDetails.getRowCount()+1,
				sp.getMaSP(),
				sp.getTenSP(),
				1,
				sp.getDonGia()
		});
		
		String maHD = comboOrderList.getItemAt(comboOrderList.getSelectedIndex());
		HoaDon hd = new HoaDon(maHD);
		cthdDao.addCTHoaDon(new CTHoaDon(sp, hd, 1, sp.getDonGia()));
	}
	
	public boolean validData() {
		if(txtTableID.getText().equals("") && btnTakeAway.isSelected()==false) {
			JOptionPane.showMessageDialog(this, "Chưa chọn bàn");
			return false;
		}
		changeCustomer();
		if(!txtCustomerID.getText().equals("")) {
			if(!txtCustomerID.getText().matches("^0\\d{9,10}$")) {
				JOptionPane.showMessageDialog(this, "Số điện thoại không đúng");
				return false;
			}
		}
			
		
		return true;
	}
	
	private void pay() {
		if(validData()) {
			String maHD = comboOrderList.getItemAt(comboOrderList.getSelectedIndex());
			NhanVien nv = new NhanVien(LoginGUI.nv.getMaNV());
			KhachHang kh = new KhachHang();
			if(!txtCustomerID.getText().trim().equals("")) {
				kh.setMaKH(txtCustomerID.getText());				
			}
			LocalDate ngayTao = LocalDate.now();
			Ban ban = new Ban();
			ban.setMaBan(txtTableID.getText());
			
			double tongHD = cthdDao.tongHoaDon(maHD);
			hdDao.updateHoaDon(new HoaDon(maHD, nv, kh, ngayTao, true, ban, tongHD));
		}
		
	}

	
	public void activePayForm() {
		String maHD = comboOrderList.getItemAt(comboOrderList.getSelectedIndex());
		txtOrderID.setText(maHD);
		LocalDate ngayTao = LocalDate.now();
		txtOrderDate.setText(ngayTao+"");
		txtEmployeeName.setText(LoginGUI.nv.getTenNV());
		txtSumTotal.setText(cthdDao.tongHoaDon(maHD)+"");
		if(btnTakeAway.isSelected()) {
			txtTableID.setText("");
		}else {
			int row = tableTable.getSelectedRow();
			if(row<0) {
				JOptionPane.showMessageDialog(this, "Chưa chọn bàn");
			}else {
				String maBan = modelTable.getValueAt(row, 1)+"";
				txtTableID.setText(maBan);
			}
		}
	}
	
	public void changeCustomer() {
		String cp = txtCustomerPay.getText().trim();
		if(cp.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(this, "Không để trống!");
			return;
		}else if(!cp.matches("[0-9.]+")) {
			JOptionPane.showMessageDialog(this, "Phải nhập số!");
			return;
		}
		double cUP = Double.parseDouble(cp);
		double sumTotal = Double.parseDouble(txtSumTotal.getText());
		if(cUP<sumTotal) {
			JOptionPane.showMessageDialog(this, "Khách trả không đủ!");
			return;
		}
		txtCustomerChange.setText((cUP-sumTotal)+"");
	}
	
	public void findProduct() {
		// khởi tạo kết nối đến CSDL
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<SanPham> list = null;
		String ten = txtSearchProduct.getText();
		String loai = comboProductType.getItemAt(comboProductType.getSelectedIndex());
		if(comboProductType.getSelectedIndex()==0) {
			if(ten.equals("")) {
				list = spDao.getAllTbSanPham();
			}else {
//				list = spDao.findSPByName(ten);
			}
		}else {
			if(ten.equals("")) {
				list = spDao.findSPByType(loai);
			}else {
				list = spDao.findSP(ten, loai);
			}
		}
		
		DocDuLieuDatabaseVaoTableProduct(list);
	}
	
	public void autoChangeCustomer() {
		if(comboPayMenthod.getSelectedIndex()==1) {
			String cUP = txtSumTotal.getText();
			txtCustomerPay.setText(cUP);
			txtCustomerChange.setText("0");			
		}else {
			txtCustomerPay.setText("");
			txtCustomerChange.setText("");
		}
	}
	
	public void changeOrder() {
		int n = tableOrderDetails.getRowCount();
		for(int i=0; i<n; i++) {
			modelOrderDetails.removeRow(0);
		}
		int i = comboOrderList.getSelectedIndex();
		String maHD = comboOrderList.getItemAt(i);
		ArrayList<CTHoaDon> listCT = cthdDao.getCTHDFromHD(maHD);
		DocDuLieuDatabaseVaoTableCTHD(listCT);
	}
	

	
	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btnTable)) {
			btnTable.setSelected(true);
			btnTakeAway.setSelected(false);
			lblChooseTable.setVisible(true);
			changeOption();
		}else if(obj.equals(btnTakeAway)) {
			btnTakeAway.setSelected(true);
			btnTable.setSelected(false);
			lblChooseTable.setVisible(false);
			changeOption();
			changeForm(pnlProductList);
		}else if(obj.equals(lblCloseTableForm)) {
			changeForm(pnlProductList);
			// Chọn bàn
		}else if(obj.equals(lblChooseTable)) {
			changeForm(pnlTableList);
		}else if(obj.equals(btnCreateOrder)) {
			// Tạo hóa đơn mới
			pnlContainOrderDetail.setVisible(true);
			newOrderID();
		}else if(obj.equals(btnPay)) {
			// Chọn thanh toán
			changeForm(pnlPayment);
			activePayForm();
		}else if(obj.equals(lblClosePayment)) {
			// Đóng thanh toán
			changeForm(pnlProductList);
		}else if(obj.equals(checkBooking)) {
			// Đặt chỗ
			if(checkBooking.isSelected()) {
				pnlContainTableList.setBounds(18, 94, 1080, 482);
				pnlContainBooking.setVisible(true);
			}else {
				pnlContainTableList.setBounds(18, 94, 1080, 912);
				pnlContainBooking.setVisible(false);
			}
		}else if(obj.equals(btnSaveRequest)) {
			// Lưu thông tin đặt chỗ
			pnlContainTableList.setBounds(18, 94, 1080, 912);
			pnlContainBooking.setVisible(false);
		}else if(obj.equals(btnConfirmPay)) {
			// Xác nhận thanh toán
//			changeForm(pnlProductList);
			pay();
		}else if(obj.equals(comboProductType)) {
			// Tìm theo loại
		}else if(obj.equals(btnDeleteOrder)) {
			deleteOrder();
		}else if(obj.equals(tableProduct)) {
			addProductToOrder();
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

	@Override
	public void keyTyped(KeyEvent e) {
		Object key = e.getKeyChar();
		Object obj = e.getSource();
		if(key.equals('\n') && obj.equals(txtSearchProduct)) {
			findProduct();
		}else if(key.equals('\n') && obj.equals(txtCustomerPay)) {
			changeCustomer();
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(comboOrderList)) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				changeOrder();				
			}
		}else if(obj.equals(comboPayMenthod)) {
			if(e.getStateChange()==ItemEvent.SELECTED) {
				autoChangeCustomer();				
			}
		}
	}
}
