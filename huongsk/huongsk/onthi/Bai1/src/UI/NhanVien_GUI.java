package UI;

import java.awt.BorderLayout;
import java.awt.Color;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import javax.swing.JScrollPane;

import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.NhanVien_DAO;
import dao.PhongBan_DAO;
import entity.NhanVien;
import entity.PhongBan;

public class NhanVien_GUI extends JFrame implements ActionListener, MouseListener {
	
	private static final long serialVersionUID = 1L;

	private JTable tableNhanVien;
	private DefaultTableModel modelNhanVien;

	private JTextField txtMaNV;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtTuoi;
	private JTextField txtTienLuong;
	private JTextField txtTim;
	private JButton bttTim;
	private JButton bttThem;
	private JButton bttXoa;
	private JButton bttLoc;
	private JButton btnSua;
	
	private JCheckBox chkNu;
	private JComboBox<String> cboPhongBan;

	private NhanVien_DAO nv_dao;
	private PhongBan_DAO pb_dao;

	public NhanVien_GUI() {

		// khởi tạo kết nối đến CSDL
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		setTitle("^-^");
		setSize(800, 500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JLabel lblTieuDe;
		lblTieuDe = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 20));
		lblTieuDe.setForeground(Color.blue);

		Box b = Box.createVerticalBox();

		Box b11, b1, b2, b3, b4;
		JLabel lblMaNV, lblHo, lblTuoi, lblPhai, lblTienLuong, lblTim = null;
		;

		b.add(b11 = Box.createHorizontalBox());
		b11.add(lblTieuDe);
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b1.add(lblMaNV = new JLabel("Mã nhân viên:   "));
		b1.add(txtMaNV = new JTextField());

		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b2.add(lblHo = new JLabel("Họ "));
		b2.add(txtHo = new JTextField());
		b2.add(new JLabel("Tên nhân viên: "));
		b2.add(txtTen = new JTextField());

		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b3.add(lblTuoi = new JLabel("Tuổi: "));
		b3.add(txtTuoi = new JTextField());
		b3.add(lblPhai = new JLabel("Phái: "));
		b3.add(chkNu = new JCheckBox("Nữ"));

		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b4.add(lblTienLuong = new JLabel("Tiền lương: "));
		b4.add(txtTienLuong = new JTextField());
		b4.add(new JLabel("Phòng Ban: "));
		
		//Tạo và đổ dữ liệu vào comboBox
		b4.add(cboPhongBan = new JComboBox<String>());
		cboPhongBan.setEditable(true);
		pb_dao = new PhongBan_DAO();
		ArrayList<PhongBan> listPB = pb_dao.getAllPhongBan();
		for (PhongBan p : listPB) {
			cboPhongBan.addItem(p.getMaPhongBan());
		}

		lblHo.setPreferredSize(lblMaNV.getPreferredSize());
		lblPhai.setPreferredSize(lblMaNV.getPreferredSize());
		lblTienLuong.setPreferredSize(lblMaNV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaNV.getPreferredSize());
		cboPhongBan.setPreferredSize(lblMaNV.getPreferredSize());
		b.add(Box.createVerticalStrut(10));
		add(b, BorderLayout.NORTH);

		String[] colHeader = { "Mã NV", "Họ NV", "Tên NV", "Tuổi", "Phái", "Lương", "Phòng ban" };
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		add(new JScrollPane(tableNhanVien), BorderLayout.CENTER);

		// =================================
		// doc du lieu tu database SQL vao Jtable
		DocDuLieuDatabaseVaoTable();
		
		// =================================

		JPanel p = new JPanel();
		add(p, BorderLayout.SOUTH);
		JPanel pnlLeft, pnlRight;
		p.add(pnlLeft = new JPanel());
		p.add(pnlRight = new JPanel());

		pnlLeft.add(lblTim = new JLabel("Nhập mã số cần tìm: "));
		pnlLeft.add(txtTim = new JTextField(10));
		pnlLeft.add(bttTim = new JButton("Tìm"));

		pnlRight.add(bttThem = new JButton("Thêm"));
		pnlRight.add(btnSua = new JButton("Sửa"));
		pnlRight.add(bttXoa = new JButton("Xóa"));
		pnlRight.add(bttLoc = new JButton("Loc theo Ph Ban"));

		bttTim.addActionListener(this);
		bttThem.addActionListener(this);
		bttXoa.addActionListener(this);
		bttLoc.addActionListener(this);
		btnSua.addActionListener(this);
		tableNhanVien.addMouseListener(this);
	}

	private void DocDuLieuDatabaseVaoTable() {
		nv_dao = new NhanVien_DAO();
		List<NhanVien> dsnv = nv_dao.getAllNV();
		for (NhanVien nv : dsnv) {
			modelNhanVien.addRow(new Object[]{nv.getMaNV(),nv.getHoNV(),nv.getTenNV(),nv.getTuoi(),nv.isPhai()?"Nữ":"Nam",nv.getLuong(),nv.getPhong().getMaPhongBan()});
		}
	}

	public static void main(String[] args) {
		new NhanVien_GUI().setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(bttThem)) {
			them();
			xoaTrang();

		}
		if (o.equals(bttXoa)) {
			int r = tableNhanVien.getSelectedRow();
			if (r == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn hàng để xóa.");
				return;
			}
			String ma= modelNhanVien.getValueAt(r,0).toString();
			modelNhanVien.removeRow(r); // xóa trong table model
			if(nv_dao.delete(ma)){
				//xóa trong database
				JOptionPane.showMessageDialog(this,"Xóa thành công");
			}
			xoaTrang();

		}
		if (o.equals(btnSua)) {
			sua();
			xoaTrang();
		}
		if (o.equals(bttTim)) {
			try {
				tim();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this,"Lỗi tìm kiếm");
			}
		}
		if (o.equals(bttLoc)) {
			try {
				loc();
			} catch (SQLException ex) {
				JOptionPane.showMessageDialog(this,"Lỗi tìm kiếm");
			}
		}

	}

	private void sua() {
		String ma = txtMaNV.getText();
		String ho = txtHo.getText();
		String ten = txtTen.getText();
		Boolean phai = chkNu.isSelected();
		String phongban = cboPhongBan.getSelectedItem().toString();
		try{
			int tuoi = Integer.parseInt(txtTuoi.getText());
			float luong = Float.parseFloat(txtTienLuong.getText());
			NhanVien nv = new NhanVien(ma,ho,ten,tuoi,phai,new PhongBan(phongban),luong);
			if(nv_dao.update(nv)){
				modelNhanVien.setRowCount(0);
				DocDuLieuDatabaseVaoTable();
			}

		}catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this,"Tuổi và lương phải là số");
		}
	}

	private void loc() throws SQLException {
		String maPhong = cboPhongBan.getSelectedItem().toString();
		List<NhanVien> dsnv = nv_dao.getNVIdforPhongBan(maPhong);
		modelNhanVien.setRowCount(0);
		for (var nv : dsnv){
			modelNhanVien.addRow(new Object[]{nv.getMaNV(),nv.getHoNV(),nv.getTenNV(),nv.getTuoi(),nv.isPhai()?"Nữ":"Nam",nv.getLuong(),nv.getPhong().getMaPhongBan()});
		}
	}

	private void tim() throws SQLException {
		String ma = txtTim.getText();
		if(ma.isEmpty()) {
			modelNhanVien.setRowCount(0);
			DocDuLieuDatabaseVaoTable();
			JOptionPane.showMessageDialog(this,"Ô tìm mã không được rỗng");
			return;
		}
		List<NhanVien> dsnv = nv_dao.getNVId(ma);
		if(dsnv !=null ){
			modelNhanVien.setRowCount(0);
			for (var nv : dsnv){
				modelNhanVien.addRow(new Object[]{nv.getMaNV(),nv.getHoNV(),nv.getTenNV(),nv.getTuoi(),nv.isPhai()?"Nữ":"Nam",nv.getLuong(),nv.getPhong().getMaPhongBan()});
			}
		}else {
			JOptionPane.showMessageDialog(this,"Không tìm thấy");
		}
	}

	private void xoaTrang() {
		txtHo.setText("");
		txtMaNV.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		txtTienLuong.setText("");
		txtMaNV.requestFocus();
	}

	private boolean them() {
		String ma = txtMaNV.getText();
		String ho = txtHo.getText();
		String ten = txtTen.getText();

		Boolean phai = chkNu.isSelected();

		String phongban = cboPhongBan.getSelectedItem().toString();
		try{

			int tuoi = Integer.parseInt(txtTuoi.getText());
			float luong = Float.parseFloat(txtTienLuong.getText());
			if(txtMaNV.getText().isEmpty()||ho.isEmpty() ||txtTuoi.getText().isEmpty()|| ten.isEmpty() || txtTienLuong.getText().isEmpty()){
				JOptionPane.showMessageDialog(this,"Không được để trống trường nào");
				return false;
			}

			if(!ma.matches("^NV\\d{2}$")) {
				JOptionPane.showMessageDialog(this,"Bạn phải đúng định dạng bắt đầu là NV kết thúc là 2 số");
				return false;
			}

			if(!ho.matches("^[A-Z]\\D*$")){
				JOptionPane.showMessageDialog(this,"Bắt đầu là chữ cái in hoa và chỉ có 1 từ");
				return false;
			}

			NhanVien nv = new NhanVien(ma,ho,ten,tuoi,phai,new PhongBan(phongban),luong);
			if(nv_dao.create(nv)){
				modelNhanVien.addRow(new Object[]{nv.getMaNV(),nv.getHoNV(),nv.getTenNV(),nv.getTuoi(),nv.isPhai()?"Nữ":"Nam",nv.getLuong(),nv.getPhong().getMaPhongBan()});
			}else  {
				JOptionPane.showMessageDialog(this,"Trùng mã");
				return false;
			}
		}catch (NumberFormatException e){
			JOptionPane.showMessageDialog(this,"tuổi,lương phải là số");
		} catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return true;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableNhanVien.getSelectedRow();
		txtMaNV.setText(modelNhanVien.getValueAt(row, 0).toString());
		txtHo.setText(modelNhanVien.getValueAt(row, 1).toString());
		txtTen.setText(modelNhanVien.getValueAt(row, 2).toString());
		txtTuoi.setText(modelNhanVien.getValueAt(row, 3).toString());
		chkNu.setSelected(modelNhanVien.getValueAt(row, 4).toString() == "Nữ" ? true : false);
		txtTienLuong.setText(modelNhanVien.getValueAt(row, 5).toString());
		cboPhongBan.setSelectedItem(modelNhanVien.getValueAt(row, 6).toString());
		txtMaNV.setEditable(false);
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
