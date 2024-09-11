package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.HoaDonDAO;
import entity.HoaDon;

public class OrderGUI extends JPanel implements MouseListener{
	public JLabel lblCreateOrder;
	private JLabel lblExportPDF;
	private JLabel lblExportExcel;
	private JPanel pnlOrderList;
	private JPanel pnlContainOrderList;
	private JTable tableOrder;
	private JScrollPane scroll;
	private OrderDetailsGUI orderDetail;
	private DefaultTableModel modelOrder;
	private HoaDonDAO hdDao;

	public OrderGUI() {
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
		hdDao = new HoaDonDAO();
		
		this.setLayout(null);
		this.setBackground(new Color(255, 255, 255));
		
		lblCreateOrder = new JLabel();
		lblCreateOrder.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCreate.png")));
		lblCreateOrder.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCreateOrder.setText("true");
		lblCreateOrder.addMouseListener(this);
		lblCreateOrder.setBounds(25, 12, 50, 50);
		this.add(lblCreateOrder);
		
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
		
		pnlOrderList = new JPanel();
		pnlOrderList.setLayout(null);
		pnlOrderList.setBackground(new Color(217, 217, 217));
		pnlOrderList.setBounds(13, 78, 1895, 1035);
		this.add(pnlOrderList);
		
		pnlContainOrderList = new JPanel();
		pnlContainOrderList.setLayout(new BorderLayout());
		pnlContainOrderList.setBackground(new Color(255, 255, 255));
		pnlContainOrderList.setBounds(12, 67, 1869, 956);
		pnlOrderList.add(pnlContainOrderList);
		
		tableOrder = new JTable();
		tableOrder.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"STT","Mã hóa đơn","Mã khách hàng","Mã nhân viên","Ngày tạo hóa đơn","Tổng tiền","Số bàn","Trạng thái"
			}
		));
//		tableOrder.setCellSelectionEnabled(false);
		tableOrder.addMouseListener(this);
		modelOrder = (DefaultTableModel)tableOrder.getModel();
		
		scroll = new JScrollPane(tableOrder);
		pnlContainOrderList.add(scroll);
		
		orderDetail = new OrderDetailsGUI();
		orderDetail.btnExit.addMouseListener(this);
		orderDetail.setVisible(false);
		
		DocDuLieuDatabaseVaoTable();
	}
	
	public void DocDuLieuDatabaseVaoTable() {
		ArrayList<HoaDon> list = hdDao.getAllTbHoaDon();
		int i=1;
		for (HoaDon hd : list) {
			modelOrder.addRow(new Object[] {i++ , hd.getMaHD(), hd.getKhachHang().getMaKH(), hd.getNhanVien().getMaNV(), hd.getNgayTaoHD(), hd.getTongHD(), hd.getBan().getMaBan(), hd.isTrangThai()});
		}
	}
	
	public void activeOrderDetailsGUI() {
		orderDetail.setVisible(true);
		int row = tableOrder.getSelectedRow();
		System.out.println(modelOrder.getValueAt(row, 1)+"");
		HoaDon hoaDon = hdDao.getOneTbHoaDon(modelOrder.getValueAt(row, 1)+"");
		if(hoaDon.getKhachHang()!=null) {
			orderDetail.txtOrderID.setText(hoaDon.getMaHD());
			orderDetail.txtCustomerID.setText(hoaDon.getKhachHang().getMaKH());
		}
		
		orderDetail.txtCustomerName.setText(hoaDon.getKhachHang().getTenKH());
		orderDetail.txtEmployeeID.setText(hoaDon.getNhanVien().getMaNV());
		orderDetail.txtEmployeeName.setText(hoaDon.getNhanVien().getTenNV());
		orderDetail.txtOrderDate.setText(hoaDon.getNgayTaoHD()+"");
		orderDetail.txtTableID.setText(hoaDon.getBan().getMaBan());
		if(hoaDon.isTrangThai()==true) {
			orderDetail.comboOrderStatus.setSelectedIndex(1);
		}else {
			orderDetail.comboOrderStatus.setSelectedIndex(0);
		}
		orderDetail.lblSumTotal.setText(hoaDon.getTongHD()+"");
		orderDetail.DocDuLieuDatabaseVaoTable(modelOrder.getValueAt(row, 1)+"");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(tableOrder)) {
			if(orderDetail.isVisible()==false) {
				activeOrderDetailsGUI();		
			}
		}else if(obj.equals(orderDetail.btnExit)) {
			orderDetail.setVisible(false);	
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
