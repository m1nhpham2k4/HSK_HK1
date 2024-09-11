package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.CauThu_DAO;
import dao.ViTriThiDau_DAO;
import entity.CauThu;
import entity.ViTriThiDau;

public class CauThu_GUI extends JFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = -1554680235689968471L;

	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnXoa;
	private JButton btnKetThuc;

	private DefaultTableModel dataModel;
	private JTable table;

	private JScrollPane scroll;

	private JComboBox<String> cboViTriThiDau;
	private JTextField txtMaCauThu;
	private JTextField txtTenCauThu;
	private JTextField txtTuoi;

	private JButton btnLoc;

	private ViTriThiDau_DAO viTri_dao;
	private CauThu_DAO cauThu_dao;

	public CauThu_GUI() {
		try {
			ConnectDB.getInstance().connect();
			System.out.println("Connected!!");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		cauThu_dao = new CauThu_DAO();
		viTri_dao = new ViTriThiDau_DAO();

		setTitle("Bài thi cuối kỳ - TL HSK Java - HK2 (2020-2021)");
		setSize(800, 500);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		Box b, b1, b2, b3, b4, b5, b6, b7, b8;
		add(b = Box.createVerticalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b1 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b2 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b3 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b4 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b5 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b8 = Box.createHorizontalBox());

		b.add(Box.createVerticalStrut(10));
		b.add(b6 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b7 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		b.add(b8 = Box.createHorizontalBox());
		b.add(Box.createVerticalStrut(10));
		JLabel lblTieuDe, lblMaVDV, lblTenVDV, lblViTri, lblTuoi;
		b1.add(lblTieuDe = new JLabel("-THÔNG TIN CẦU THỦ- ", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Times New Roman", Font.BOLD, 24));
		lblTieuDe.setForeground(Color.BLUE);

		b2.add(lblMaVDV = new JLabel("  Mã số cầu thủ:  ", JLabel.RIGHT));
		b2.add(txtMaCauThu = new JTextField());
		b2.add(Box.createHorizontalStrut(50));
		b3.add(lblTenVDV = new JLabel("Tên cầu thủ:  ", JLabel.RIGHT));
		b3.add(txtTenCauThu = new JTextField());
		b3.add(Box.createHorizontalStrut(50));
		b4.add(lblViTri = new JLabel("Vị trí thi đấu:  ", JLabel.RIGHT));
		b4.add(cboViTriThiDau = new JComboBox<String>());
		b4.add(Box.createHorizontalStrut(300));

		b5.add(lblTuoi = new JLabel("Tuổi:  ", JLabel.RIGHT));
		b5.add(txtTuoi = new JTextField());
		b5.add(Box.createHorizontalStrut(50));

		DefaultComboBoxModel<String> dataModelLop = new DefaultComboBoxModel<String>();
		cboViTriThiDau.setModel(dataModelLop);

		lblViTri.setPreferredSize(lblMaVDV.getPreferredSize());
		lblTenVDV.setPreferredSize(lblMaVDV.getPreferredSize());
		lblTuoi.setPreferredSize(lblMaVDV.getPreferredSize());

		b6.add(Box.createHorizontalStrut(40));
		b6.add(btnThem = new JButton("Thêm Mới "));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btnLuu = new JButton("Lưu "));
		b6.add(Box.createHorizontalStrut(10));
		b6.add(btnXoa = new JButton("Xóa"));
		b6.add(Box.createHorizontalStrut(50));
		b6.add(btnKetThuc = new JButton("Kết Thúc"));

		String[] tieuDe = { "Mã Số", "Tên cầu thủ", "Tuổi", "Vị trí thi đấu" };
		b7.add(scroll = new JScrollPane(table = new JTable(dataModel = new DefaultTableModel(tieuDe, 0))));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách cầu thủ:"));

		JLabel lblName;
		b8.add(lblName = new JLabel("Họ tên sv: ................massv:.............."));
		lblName.setFont(new Font("Times", Font.ITALIC, 12));
		lblName.setForeground(Color.RED);
		b8.add(Box.createHorizontalStrut(50));
		b8.add(btnLoc = new JButton("   Lọc danh sách cầu thủ theo vị trí thi đấu "));
		btnLoc.setFont(new Font("Times New Roman", Font.ITALIC, 14));
		btnLoc.setForeground(Color.BLUE);
		table.addMouseListener(this);
		btnKetThuc.addActionListener(this);
		btnLoc.addActionListener(this);
		btnLuu.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);

		ArrayList<ViTriThiDau> dsViTri = viTri_dao.getAllViTri();
		for (ViTriThiDau viTri : dsViTri) {
			cboViTriThiDau.addItem(viTri.getMaViTri());
		}

		// Add action listener to combo box for dynamic filtering
		cboViTriThiDau.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (btnLoc.getText().equals("Hủy")) {
					filterData();
				}
			}
		});

		docTatCaDuLieuTuSQL();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if (o.equals(btnThem)) {
			xoaTrang();
		}

		if(o.equals(btnLuu)) {
			if(isValidData())
				xoaTrang();
		}
		
		if (o.equals(btnXoa)) {
			int row = table.getSelectedRow();
			if (row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xóa");
			} else {
				if (JOptionPane.showConfirmDialog(this, "Bạn muốn xóa dòng này?", "Thông báo",
						JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
					dataModel.removeRow(row);
					cauThu_dao.xoaCauThu(txtMaCauThu.getText());
					xoaTrang();
				}
			}
		}

		if (o.equals(btnKetThuc)) {
			if (JOptionPane.showConfirmDialog(this, "Bạn muốn thoát chương trình?", "Thông báo",
					JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
				System.exit(0);
			}
		}

		if (o.equals(btnLoc)) {
			if (btnLoc.getText().equals("Hủy")) {
				dataModel.setRowCount(0);
				btnLoc.setText("Lọc danh sách cầu thủ theo vị trí thi đấu");
				cboViTriThiDau.setEnabled(true);
				docTatCaDuLieuTuSQL();
			} else {
				btnLoc.setText("Hủy");
				filterData();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = table.getSelectedRow();
		docDuLieuDuocChon(row);
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
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	private void docTatCaDuLieuTuSQL() {
		List<CauThu> dsCauThu = cauThu_dao.getTatCaCauThu();
		for (CauThu cauThu : dsCauThu) {
			dataModel.addRow(new Object[] { cauThu.getMaCauThu(), cauThu.getTenCauThu(), cauThu.getTuoi(),
					cauThu.getViTri().getMaViTri() });
		}
	}

	private void xoaTrang() {
		txtMaCauThu.setText("");
		txtTenCauThu.setText("");
		txtTuoi.setText("");
		cboViTriThiDau.setSelectedIndex(0);
		txtMaCauThu.requestFocus();
		table.clearSelection();
	}

	private void docDuLieuDuocChon(int row) {
		txtMaCauThu.setText(dataModel.getValueAt(row, 0).toString());
		txtTenCauThu.setText(dataModel.getValueAt(row, 1).toString());
		txtTuoi.setText(dataModel.getValueAt(row, 2).toString());
		cboViTriThiDau.setSelectedItem(dataModel.getValueAt(row, 3).toString());
	}

	private boolean isValidData() {

		String maCauThu = txtMaCauThu.getText();
		String tenCauThu = txtTenCauThu.getText();
		String tuoiCauThu = txtTuoi.getText();
		int tuoi = 0;
		
		if(maCauThu.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập mã cầu thủ");
			txtMaCauThu.requestFocus();
			return false;
		} else if(!(maCauThu.matches("^VDV[\\d]{2}$"))) {
			JOptionPane.showMessageDialog(this, "3 ký tự đầu là “VDV”, tiếp 2 ký số tùy ý (ví dụ: VDV02)");
			txtMaCauThu.requestFocus();
			return false;
		}
		if(tenCauThu.trim().equals("")) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tên cầu thủ");
			txtTenCauThu.requestFocus();
			return false;
		} else if(!(tenCauThu.matches("^([A-Z][a-z]*\\s)*[A-Z][a-z]*$"))) {
			JOptionPane.showMessageDialog(this, "Viết hoa chữ cái đầu");
			txtTenCauThu.requestFocus();
			return false;
		}
		if(tuoiCauThu.length()==0) {
			JOptionPane.showMessageDialog(this, "Vui lòng nhập tuổi cầu thủ");
			txtTuoi.requestFocus();
			return false;
		} else {
			try {
				tuoi = Integer.parseInt(tuoiCauThu);
				if(tuoi < 18 || tuoi > 23) {
					JOptionPane.showMessageDialog(this, "Tuổi cầu thủ phải từ 18 đến 23");
					txtTuoi.requestFocus();
					return false;
				}
			} catch (Exception e) {
				JOptionPane.showMessageDialog(this, "Tuổi phải nhập số");
				txtTuoi.requestFocus();
				return false;
			}
		}
		
		
		CauThu cauThu = new CauThu(maCauThu, tenCauThu, tuoi, new ViTriThiDau(cboViTriThiDau.getSelectedItem().toString()));
		if(cauThu_dao.maCauThuExists(maCauThu)) {
			JOptionPane.showMessageDialog(this, "Trùng mã cầu thủ");
			txtMaCauThu.requestFocus();
			return false;
		} 
		cauThu_dao.themCauThu(cauThu);
		dataModel.addRow(new Object[] {cauThu.getMaCauThu(), cauThu.getTenCauThu(), cauThu.getTuoi(),
				cauThu.getViTri().getMaViTri() });
		return true;
	}

	private void filterData() {
		dataModel.setRowCount(0);
		ArrayList<CauThu> dsCauThu = cauThu_dao.dsCauThuTheoViTri(cboViTriThiDau.getSelectedItem().toString());
		for (CauThu cauThu : dsCauThu) {
			dataModel.addRow(new Object[] { cauThu.getMaCauThu(), cauThu.getTenCauThu(), cauThu.getTuoi(),
					cauThu.getViTri().getMaViTri() });
		}
	}
}
