package stt20_LeVuThanhDuong_22643441;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class App_NhanVien extends JFrame implements ActionListener, MouseListener{

	private static final String FILENAME = "src/stt20_LeVuThanhDuong_22643441/NhanVien.txt";
	private JLabel lblTitle;
	private JLabel lblHo;
	private JLabel lblTen;
	private JLabel lblTuoi;
	private JLabel lblMa;
	private JLabel lblPhai;
	private JLabel lblLuong;
	private JTextField txtMa;
	private JTextField txtTen;
	private JTextField txtHo;
	private JTextField txtTuoi;
	private JTextField txtLuong;
	private JRadioButton radNam;
	private JRadioButton radNu;
	private ButtonGroup groupPhai;
	private DefaultTableModel modelNhanVien;
	private JTable tableNhanVien;
	private JLabel lblTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btnLuu;
	private JTextField txtTim;
	private NhanVien_Collection dsNV;

	public App_NhanVien() {
		setTitle("Quản lý nhân viên");
		setSize(800,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		lblTitle = new JLabel("THÔNG TIN NHÂN VIÊN");
		lblTitle.setFont(new Font("Arial", Font.BOLD, 24));
		lblTitle.setForeground(Color.blue);
		lblHo = new JLabel("Họ: ");
		lblTen = new JLabel("Tên nhân viên: ");
		lblTuoi = new JLabel("Tuổi: ");
		lblMa = new JLabel("Mã nhân viên: ");
		lblPhai = new JLabel("Phái: ");
		lblLuong = new JLabel("Tiền lương: ");
		txtMa = new JTextField();
		txtTen = new JTextField();
		txtHo = new JTextField();
		txtTuoi = new JTextField();
		txtLuong = new JTextField();
		radNam = new JRadioButton("Nam");
		radNu = new JRadioButton("Nữ");
		groupPhai = new ButtonGroup();
		groupPhai.add(radNam);
		groupPhai.add(radNu);
		String[] colHeader = {"Mã nhân viên", "Họ", "Tên", "Tuổi", "Phái", "Tiền lương"};
		modelNhanVien = new DefaultTableModel(colHeader, 0);
		tableNhanVien = new JTable(modelNhanVien);
		lblMa.setPreferredSize(new Dimension(90, 15));
		lblHo.setPreferredSize(new Dimension(90, 15));
		lblTen.setPreferredSize(new Dimension(90, 15));
		lblTuoi.setPreferredSize(new Dimension(90, 15));
		lblPhai.setPreferredSize(new Dimension(40, 15));
		lblLuong.setPreferredSize(new Dimension(90, 15));
		lblTim = new JLabel("Nhập mã số cần tìm: ");
		btnTim = new JButton("Tìm");
		btnThem = new JButton("Thêm");
		btnXoaTrang = new JButton("Xóa trắng");
		btnXoa = new JButton("Xóa");
		btnLuu = new JButton("Lưu");
		txtTim = new JTextField(15);
	
		JPanel p = new JPanel(new BorderLayout());
		JPanel p1 = new JPanel();
		Box b, b1, b2, b3, b4, b5;
		b  = Box.createVerticalBox();
		b1 = Box.createHorizontalBox();
		b2 = Box.createHorizontalBox();
		b3 = Box.createHorizontalBox();
		b4 = Box.createHorizontalBox();
		b5 = Box.createHorizontalBox();
		b1.add(lblTitle);
		b2.add(lblMa);
		b2.add(txtMa);
		b3.add(lblHo);
		b3.add(txtHo);
		b3.add(lblTen);
		b3.add(txtTen);
		b4.add(lblTuoi);
		b4.add(txtTuoi);
		b4.add(lblPhai);
		b4.add(radNam);
		b4.add(radNu);
		b5.add(lblLuong);
		b5.add(txtLuong);
		b.add(Box.createRigidArea(new Dimension(0,5)));
		b.add(b1);
		b.add(Box.createRigidArea(new Dimension(0,10)));
		b.add(b2);
		b.add(Box.createRigidArea(new Dimension(0,10)));
		b.add(b3);
		b.add(Box.createRigidArea(new Dimension(0,10)));
		b.add(b4);
		b.add(Box.createRigidArea(new Dimension(0,10)));
		b.add(b5);
		b.add(Box.createRigidArea(new Dimension(0,10)));
		p.add(b, BorderLayout.NORTH);
		p.add(new JScrollPane(tableNhanVien), BorderLayout.CENTER);
		p1.add(lblTim);
		p1.add(txtTim);
		p1.add(btnTim);
		p1.add(btnThem);
		p1.add(btnXoa);
		p1.add(btnXoaTrang);
		p1.add(btnLuu);
		p.add(p1, BorderLayout.SOUTH);
		add(p);
		
		dsNV = new NhanVien_Collection();
		LuuTru luu = new LuuTru();
		try {
			dsNV = (NhanVien_Collection) luu.DocFile(FILENAME);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy file!");
		}
		
		themNVTuArrayVaoModel();
		
		btnTim.addActionListener(this);
		btnThem.addActionListener(this);
		btnXoa.addActionListener(this);
		btnXoaTrang.addActionListener(this);
		btnLuu.addActionListener(this);
		tableNhanVien.addMouseListener(this);
		

	}
	
	public static void main(String[] args) {
		new App_NhanVien().setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		int row = tableNhanVien.getSelectedRow();
		docDuLieuDuocChon(row);
		
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
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		
		if(o.equals(btnThem)) {
			String ma = txtMa.getText();
			String ho = txtHo.getText();
			String ten = txtTen.getText();
			int tuoi = Integer.parseInt(txtTuoi.getText());
			Boolean phai = radNam.isSelected();
			double luong = Double.parseDouble(txtLuong.getText());
			NhanVien nv = new NhanVien(ma, ho, ten, tuoi, phai, luong);
			if(dsNV.themNV(nv)) {
				modelNhanVien.addRow(new Object[] {nv.getMaNV(), nv.getHo(), nv.getTen(), 
						nv.getTuoi(), nv.isPhai()? "Nam":"Nữ", nv.getLuong()});
				xoaTrang();
			}
			else
				JOptionPane.showMessageDialog(this, "Trùng mã nhân viên!!");
			
		}
		if(o.equals(btnXoa)) {
			int row = tableNhanVien.getSelectedRow();
			if(row == -1) {
				JOptionPane.showMessageDialog(this, "Vui lòng chọn dòng muốn xóa!");
			}else {
				if(JOptionPane.showConfirmDialog(this, "Bạn muốn xóa dòng này?","Cảnh báo!!"
						,JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION) {
					String maNV = tableNhanVien.getValueAt(row, 0).toString(); 
		            modelNhanVien.removeRow(row);
		            dsNV.xoaNV(maNV);
		            xoaTrang();
				}
			}
		}
		
		if(o.equals(btnXoaTrang)) {
			xoaTrang();
		}
		
		if(o.equals(btnTim)) {
			if(txtTim.getText().trim().equals(""))
				JOptionPane.showMessageDialog(this, "Vui lòng nhập dữ liệu!!");
			else {
				NhanVien nv1 = dsNV.timNV(txtTim.getText());
				if(nv1 != null) {
					int index = dsNV.viTriNV(nv1);
					tableNhanVien.setRowSelectionInterval(index, index );
					docDuLieuDuocChon(index);
				}
				else
					JOptionPane.showMessageDialog(this, "Không tìm thấy!!");
			}	
		}	
		
		if(o.equals(btnLuu)) {
			LuuTru luu = new LuuTru();
			try {
				if(luu.LuuFile(dsNV, FILENAME))
					JOptionPane.showMessageDialog(null, "Lưu thành công!");
			} catch (Exception e1) {
				e1.printStackTrace();
			}
			
		}
	}
	
	private void docDuLieuDuocChon(int index) {
		txtMa.setText(tableNhanVien.getValueAt(index,0).toString());
		txtHo.setText(tableNhanVien.getValueAt(index,1).toString());
		txtTen.setText(tableNhanVien.getValueAt(index,2).toString());
		txtTuoi.setText(tableNhanVien.getValueAt(index,3).toString());
		txtLuong.setText(tableNhanVien.getValueAt(index,5).toString());
		if(tableNhanVien.getValueAt(index, 4).toString().equals("Nam"))
			radNam.setSelected(true);
		else
			radNu.setSelected(true);
	}
	
	private void xoaTrang() {
		txtMa.setText("");
		txtHo.setText("");
		txtTen.setText("");
		txtTuoi.setText("");
		groupPhai.clearSelection();
		txtLuong.setText("");
		txtMa.requestFocus();
		tableNhanVien.clearSelection();
	}
	
	public void themNVTuArrayVaoModel() {
		for (int i = 0; i < dsNV.getSize(); i++) {
			NhanVien nv = dsNV.getElement(i);
			modelNhanVien.addRow(new Object[] {nv.getMaNV(), nv.getHo(), nv.getTen(), 
						nv.getTuoi(), nv.isPhai()? "Nam":"Nữ", nv.getLuong()});
		}
	}
}


