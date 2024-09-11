import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;



public class ThongTinLopHoc extends JFrame implements ActionListener, MouseListener {

	private JButton jButton_them;
	private JButton jButton_luu;
	private JButton jButton_sua;
	private JButton jButton_xoa;
	private JTextField jTextField_malop;
	private JTextField jTextField_tenlop;
	private JTextField jTextField_giaovien;
	private DanhSachLopHoc dSachLopHoc = new DanhSachLopHoc();
	private DefaultTableModel defaultTableModel;
	private JTable jTable;

	public ThongTinLopHoc() {
		setTitle("Thong tin lop hoc");
		setSize(850, 600);
		setLocationRelativeTo(null);

		JPanel jPanel_north = new JPanel();
		JLabel jLabel_north = new JLabel("THÔNG TIN LỚP HỌC");
		Font font = new Font("Arial", Font.BOLD, 25);
		jLabel_north.setFont(font);
		jPanel_north.add(jLabel_north);

		Box box = Box.createVerticalBox();
		JLabel jLabel_maLop = new JLabel("                         Mã lớp: ");
		JLabel jLabel_tenLop = new JLabel("                        Tên lớp: ");
		JLabel jLabel_giaovien = new JLabel("Giáo viên chủ nhiệm: ");
		box.add(box.createVerticalStrut(7));
		box.add(jLabel_maLop);
		box.add(box.createVerticalStrut(7));
		box.add(jLabel_tenLop);
		box.add(box.createVerticalStrut(7));
		box.add(jLabel_giaovien);
		box.add(box.createVerticalStrut(7));

		JPanel jPanel_phai = new JPanel(new FlowLayout());
		jTextField_malop = new JTextField(64);
		jTextField_tenlop = new JTextField(64);
		jTextField_giaovien = new JTextField(64);
		jPanel_phai.add(jTextField_malop);
		jPanel_phai.add(jTextField_tenlop);
		jPanel_phai.add(jTextField_giaovien);

		JPanel jPanel_button = new JPanel();
		;
		jButton_them = new JButton("Thêm");
		jButton_luu = new JButton("Lưu");
		jButton_sua = new JButton("Sửa");
		jButton_xoa = new JButton("Xóa");
		jButton_them.addActionListener(this);
		jButton_luu.addActionListener(this);
		jButton_sua.addActionListener(this);
		jButton_xoa.addActionListener(this);

		Box jPanel_button1 = Box.createHorizontalBox();
		jPanel_button1.add(jButton_them);
		jPanel_button1.add(jButton_luu);
		jPanel_button1.add(jButton_sua);
		jPanel_button1.add(jButton_xoa);
		jPanel_button.add(jPanel_button1);

		JPanel jPanel_center1 = new JPanel(new BorderLayout());
		jPanel_center1.add(box, BorderLayout.WEST);
		jPanel_center1.add(jPanel_phai, BorderLayout.CENTER);
		jPanel_center1.add(jPanel_button, BorderLayout.SOUTH);
		jPanel_center1.add(jPanel_north, BorderLayout.NORTH);

		TitledBorder titledBorder = BorderFactory.createTitledBorder("Danh sách lớp học");
		Object[] thongTin = { "Mã số lớp", "Tên lớp", "Giáo viên chủ nhiệm" };
		defaultTableModel = new DefaultTableModel(thongTin, 0);
		jTable = new JTable(defaultTableModel);
		jTable.setRowHeight(25);
		jTable.addMouseListener(this);
		Database.getInstance().connect();
		updateTableData();
		JScrollPane jScrollPane = new JScrollPane(jTable);
		jScrollPane.setBorder(titledBorder);

		JPanel jPanel_south = new JPanel(new FlowLayout(FlowLayout.RIGHT, 24, 5));
		JButton jButton_xemDanhSach = new JButton("Xem danh sách sinh viên của lớp hiện tại");
		jButton_xemDanhSach.setForeground(Color.RED);
		jPanel_south.add(jButton_xemDanhSach);

		add(jPanel_center1, BorderLayout.NORTH);
		add(jScrollPane, BorderLayout.CENTER);
		add(jPanel_south, BorderLayout.SOUTH);

		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public static void main(String[] args) {
		new ThongTinLopHoc();
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row=jTable.getSelectedRow();
		jTextField_malop.setText(jTable.getValueAt(row, 0).toString()); 
		jTextField_tenlop.setText(jTable.getValueAt(row, 1).toString()); 
		jTextField_giaovien.setText(jTable.getValueAt(row, 2).toString());
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if (o.equals(jButton_them)) {
			LopHoc lh = new LopHoc(jTextField_malop.getText(), jTextField_tenlop.getText(),
	                jTextField_giaovien.getText());
	        try {
	            themMoiHoaDon(lh, Database.getInstance().getConnection());
	            Object[] rowData = { lh.getMaLop(), lh.getTenLop(), lh.getGiaoVienCN() };
	            defaultTableModel.addRow(rowData);
	        } catch (SQLException e1) {
	            e1.printStackTrace();
	        }
	        xoaRongTextfields();
	        jTextField_malop.setEditable(true);

		} else if (o.equals(jButton_luu)) {
			LopHoc lh = new LopHoc(jTextField_malop.getText(), jTextField_tenlop.getText(),
					jTextField_giaovien.getText());
			if (dSachLopHoc.create(lh)) {
				Object[] rowData = { jTextField_malop.getText(), jTextField_tenlop.getText(),
						jTextField_giaovien.getText() };
				defaultTableModel.addRow(rowData);
			}
		} else if (o.equals(jButton_sua)) {
			int row = jTable.getSelectedRow();
			if (row >= 0) {
				LopHoc lh = new LopHoc(jTextField_malop.getText(), jTextField_tenlop.getText(),
						jTextField_giaovien.getText());
				if (dSachLopHoc.update(lh)) {
					jTable.setValueAt(jTextField_tenlop.getText(), row, 1);
					jTable.setValueAt(jTextField_giaovien.getText(), row, 2);
				}
			}
		} else if (o.equals(jButton_xoa)) {
			int row = jTable.getSelectedRow();
			if (row >= 0) {
				String maLop = (String) jTable.getValueAt(row, 0);
				if (dSachLopHoc.delete(maLop)) {
					defaultTableModel.removeRow(row);
					xoaRongTextfields();
				}
			}
		}
	}

	private void xoaRongTextfields() {
		jTextField_malop.setText("");
		jTextField_tenlop.setText("");
		jTextField_giaovien.setText("");
		jTextField_malop.requestFocus();
	}

	private static void themMoiHoaDon(LopHoc lopHoc, Connection connection) throws SQLException {
	    String sql = "INSERT INTO Lop (MaLop, TenLop, GiaoVienChuNhiem) VALUES (?, ?, ?)";
	    try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
	        preparedStatement.setString(1, lopHoc.getMaLop());
	        preparedStatement.setString(2, lopHoc.getTenLop());
	        preparedStatement.setString(3, lopHoc.getGiaoVienCN());
	        preparedStatement.executeUpdate();
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	}

	private void updateTableData() {
	    
	    defaultTableModel.setRowCount(0);
	    
	    DanhSachLopHoc ds = new DanhSachLopHoc();
	    List<LopHoc> list = ds.docTuBang();
	    for (LopHoc s : list) {
	        String[] rowData = { s.getMaLop(), s.getTenLop(), s.getGiaoVienCN() + "" };
	        defaultTableModel.addRow(rowData);
	    }
	}

	public static void main(String[] args) {
		new ThongTinLopHoc();
	}
}
