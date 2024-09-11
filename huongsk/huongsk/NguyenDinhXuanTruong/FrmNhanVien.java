package NguyenBaSon_22668921;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class FrmNhanVien extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtDiaChi;
	private JTextField txtTuoi;
	private JTextField txtWeb;
	
	private JButton btnThem;
	private JButton btnXoa;
	private JButton btnSua;
	private JButton btnLuu;
	
	private JTable table;
	private JTextField txtMess;
	private JButton btnXoaRong;

	private QuanLy thuvien;
	private DefaultTableModel tableModel;

	private JButton btnLoc;

	public FrmNhanVien() {
		setTitle("Quản lý sách");
		setSize(900, 600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		buildUI();
	}

	private void buildUI() {

		// Phần North
		JPanel pnlNorth;
		add(pnlNorth = new JPanel(), BorderLayout.NORTH);
		pnlNorth.setPreferredSize(new Dimension(0, 180));
		pnlNorth.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		pnlNorth.setLayout(null); // Absolute layout

		JLabel lblMaSach, lblTuaSach, lblTacGia, lblNamXB, lblNhaXB, lblSoTrang, lblDonGia, lblISBN;
		pnlNorth.add(lblMaSach = new JLabel("Mã Nhân Viên: "));
		pnlNorth.add(lblTuaSach = new JLabel("Họ Tên: "));
		pnlNorth.add(lblTacGia = new JLabel("địa chỉ: "));
		pnlNorth.add(lblNamXB = new JLabel("Tuổi: "));
		pnlNorth.add(lblNhaXB = new JLabel("Phòng: "));
		
		pnlNorth.add(txtMa = new JTextField());
		pnlNorth.add(txtTen = new JTextField());
		pnlNorth.add(txtDiaChi = new JTextField());
		pnlNorth.add(txtTuoi = new JTextField());
		pnlNorth.add(txtWeb = new JTextField());
		
		pnlNorth.add(txtMess = new JTextField());
		txtMess.setEditable(false);
		txtMess.setBorder(null);
		txtMess.setForeground(Color.red);
		txtMess.setFont(new Font("Arial", Font.ITALIC, 12));

		int w1 = 100, w2 = 300, h = 20;
		lblMaSach.setBounds(20, 20, w1, h);
		txtMa.setBounds(120, 20, 200, h);

		lblTuaSach.setBounds(20, 45, w1, h);
		txtTen.setBounds(120, 45, w2, h);
		lblTacGia.setBounds(450, 45, w1, h);
		txtDiaChi.setBounds(570, 45, w2, h);

		lblNamXB.setBounds(20, 70, w1, h);
		txtTuoi.setBounds(120, 70, w2, h);
		lblNhaXB.setBounds(450, 70, w1, h);
		txtWeb.setBounds(570, 70, w2, h);

	
		txtMess.setBounds(20, 145, 550, 20);

		// Phần Center
		JPanel pnlCenter;
		add(pnlCenter = new JPanel(), BorderLayout.CENTER);
		pnlCenter.add(btnThem = new JButton("Thêm - kiểm tra dữ liệu ..."));
		pnlCenter.add(btnXoaRong = new JButton("Xóa rỗng"));

		// Phần South
		JScrollPane scroll;
		String[] headers = "MaNhanVien;HoTenNV;Tuoi;DiaChi; Website".split(";");

		tableModel = new DefaultTableModel(headers, 0);
		add(scroll = new JScrollPane(table = new JTable(tableModel), JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), BorderLayout.SOUTH);
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách"));
		table.setRowHeight(20);
		scroll.setPreferredSize(new Dimension(0, 350));

		// Xử lý

		thuvien = new QuanLy();
	
		updateTableData(); // Cập nhật dữ liệu cho table
		
		btnThem.addActionListener(this);		
		btnXoaRong.addActionListener(this);
		
	}

	private void fillForm(int row) {
		if (row != -1) {
			String ma = (String) table.getValueAt(row, 0);
			NhanVien s = new NhanVien(ma);
			ArrayList<NhanVien> dsSach = thuvien.getDs();
			if (thuvien.getDs().contains(s)) {
				s = dsSach.get(dsSach.indexOf(s));
				txtMa.setText(s.getMaNV());
				txtTen.setText(s.getHoTen());
				txtDiaChi.setText(s.getDiaChi());
				txtWeb.setText(s.getEmail());
				txtTuoi.setText(s.getTuoi() + "");				
				txtMa.setEditable(false);
			}
		}
	}

	private void updateTableData() {
		XoaHetDuLieuTrenTableModel();
		for (NhanVien s : thuvien.getDs()) {
			tableModel.addRow(new Object[] { s.getMaNV(), s.getHoTen(),
					 s.getTuoi(),s.getDiaChi(), s.getEmail(),
					});
		}

		table.setModel(tableModel);

	}

	private void XoaHetDuLieuTrenTableModel() {
		DefaultTableModel dm = (DefaultTableModel) table.getModel();
		dm.getDataVector().removeAllElements();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			if (validData()) {
				NhanVien s = revertSachFromTextfields();
				if (thuvien.them(s)) {
					txtMess.setText("Thêm thành công..........");
					updateTableData(); // Cập nhật dữ liệu xuống bảng
				} else {
					showMessage("Error: Trùng mã số", txtMa);
				}
			}
		} else if (o.equals(btnXoaRong)) {
			clearTextfields();
		}

	}

	private void showMessage(String message, JTextField txt) {
		txt.requestFocus();
		txtMess.setText(message);
	}

	private boolean validData() {
		
		if(!txtMa.getText().trim().matches("^\\d{3}NV$"))
		{
			showMessage("ma phai nhap theo kieu (^\\d{3}NV$)", txtMa);
			return false;
		}
		
		if(!txtTen.getText().trim().matches("[\\w]+")) {
			showMessage("ten phai nhap theo kieu (\"[\\w]+\") ", txtTen);
			return false;
		}
		if(!txtDiaChi.getText().trim().matches("[\\w]+")) {
			showMessage("dia chi phai nhap theo kieu (\"[\\w]+\") ", txtDiaChi);
			return false;
		}
		
		try {
			int tuoi =Integer.parseInt(txtTuoi.getText().trim());  
			
			if(tuoi<18||tuoi>60) {
				showMessage("Tuổi Nhân Viên từ 18-60 tuổi", txtTuoi);
				return false;
			}
		} catch (Exception e) {
			showMessage("Tuổi Nhân Viên khong duoc rong", txtTuoi);
			return false;
		}
		
		
		
		if(!txtWeb.getText().trim().matches("^[TP][0-9]{2}CV$")) {
			showMessage("ma phong phai theo kieu (\"^TP[\\\\d]{2}[CV||TN]$\")", txtWeb);
			return false;
		}
		return true;
	}

	private NhanVien revertSachFromTextfields() {
		String maSach = txtMa.getText().trim();
		String tuaSach = txtTen.getText().trim();
		String tacGia = txtDiaChi.getText().trim();

		String nam = txtTuoi.getText().trim();
		int namXB = nam.length() == 0 ? 0 : Integer.parseInt(nam);
		// Để trống thì coi như là 0

		String nhaXB = txtWeb.getText().trim();
		
		return new NhanVien(maSach, tuaSach, tacGia, namXB, nhaXB);
	}

	private void clearTextfields() {
		txtMa.setText("");
		txtTen.setText("");
		txtDiaChi.setText("");
		txtTuoi.setText("");
		txtWeb.setText("");
	
		txtMa.setEditable(true);
		txtMa.requestFocus();
	}
}
