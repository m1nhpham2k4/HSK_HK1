package GUI;

import java.awt.Color;
import java.awt.Font;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.naming.spi.DirStateFactory.Result;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

import DAO.ListGiaoVien;
import DAO.ListLopHoc;
import Database.ConnectionDB;



public class FrmLopHoc extends JFrame  implements ActionListener{
	 
	private JTextField txtMaLop ;
	private JTextField txtTenLop;
	private JTextField txtSiSo;
	private JComboBox cboGVCN;
	private JButton btnThem;
	private JButton btnLuu;
	private JButton btnSua;
	private JButton btnXoa;
	private JButton btnTimGV,btnTimLop;
	private static DefaultTableModel dataModel = new DefaultTableModel();
	private JTable table;
	private ListGiaoVien listGiaoVien = new ListGiaoVien();
	private ListLopHoc listLopHoc = new ListLopHoc();
	private static ConnectionDB connect = new ConnectionDB();
	public FrmLopHoc() throws SQLException {
		

		Box b, b1, b2, b3, b4, b44, b5, b6, b7;
		//Dùng Box layout
		add(b = Box.createVerticalBox()); //Box theo chi�?u d�?c
		b.add(Box.createVerticalStrut(10)); //Tạo khoảng cách theo chi�?u d�?c
		b.add(b1 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); //b1 -> b7: Box theo chi�?u ngang
		b.add(b2 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b3 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b4 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b44 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b5 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b6 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 
		b.add(b7 = Box.createHorizontalBox()); b.add(Box.createVerticalStrut(10)); 

		JLabel lblTieuDe, lblMaLop, lblTenLop, lblGVCN, lblSiSo;
		b1.add(lblTieuDe = new JLabel("THÔNG TIN LỚP HỌC", JLabel.CENTER));
		lblTieuDe.setFont(new Font("Arial", Font.BOLD, 26));

		b2.add(lblMaLop = new JLabel("Mã lớp: ", JLabel.RIGHT)); b2.add(txtMaLop = new JTextField());
		b3.add(lblTenLop = new JLabel("Tên lớp: ", JLabel.RIGHT)); b3.add(txtTenLop = new JTextField());
		b4.add(lblGVCN = new JLabel("Giáo viên chủ nhiệm: ", JLabel.RIGHT)); 
		b4.add(cboGVCN = new JComboBox<String>());
		cboGVCN.setEditable(false);
		b44.add(lblSiSo = new JLabel("Sỉ số : ", JLabel.RIGHT)); b44.add(txtSiSo = new JTextField());
		
		lblMaLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblTenLop.setPreferredSize(lblGVCN.getPreferredSize());
		lblSiSo.setPreferredSize(lblGVCN.getPreferredSize());
		
		b5.add(btnThem = new JButton("Thêm"));
		b5.add(btnLuu= new JButton("Lưu"));
		b5.add(btnSua = new JButton("Sửa"));
		b5.add(btnXoa = new JButton("Xóa"));
		b5.add(btnTimGV = new JButton("Tìm theo mã giáo viên"));
		b5.add(btnTimLop = new JButton("Tìm theo mã lớp"));

		String[] headers = {"Mã lớp", "Tên lớp", "Giáo viên CN", "Sỉ số"};
		dataModel = new DefaultTableModel(headers , 0);
		DuaDuLieuVaoBang();
		JScrollPane scroll;
		b6.add(scroll = new JScrollPane(table = new JTable(dataModel)));
		scroll.setBorder(BorderFactory.createTitledBorder("Danh sách lớp h�?c"));

		b7.add(Box.createHorizontalStrut(600));
		
		
		btnThem.addActionListener(this);
		btnLuu.addActionListener(this);
		btnXoa.addActionListener(this);
		btnSua.addActionListener(this);
		btnTimGV.addActionListener(this);
		btnTimLop.addActionListener(this);
	
		
		//Khi chương trình chạy, nạp toàn bộ danh sách lớp h�?c lên bảng
	
		
		setTitle("Thông tin lớp học");
		setSize(1000, 600);
		setVisible(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
	}
	public static void DuaDuLieuVaoBang() throws SQLException {
		connect.connect();
		Statement statement = null;
		statement = connect.getCon().createStatement();
		ResultSet result = null;
		result = statement.executeQuery("SELECT *  FROM LopHoc L JOIN GiaoVien G ON G.maGiaoVien=L.maGiaoVien");
		while(result.next()) {
			dataModel.addRow(new Object[] {result.getString(1), result.getString(2),result.getString(6),result.getString(4)});
			
//			System.out.println();
//			System.out.println(result.getString(2));
//			System.out.println(result.getString(3));
//			System.out.println(result.getString(4));
////			System.out.println(result.getString(5));
//			System.out.println(result.getString(6));
//			
		}
	}
	
	public static void main(String[] args) throws SQLException {
		DuaDuLieuVaoBang();
		new FrmLopHoc();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object o = e.getSource();
		if(o.equals(btnThem)){
			xoaRongTextfields();
		}
		else if(o.equals(btnLuu)){
			
		}
		 else if(o.equals(btnSua)){
				
			}
		else if(o.equals(btnXoa)){
			
	    }
		else if(o.equals(btnTimLop)){
		}
	
 }
	private void xoaRongTextfields() {
		txtMaLop.setText("");
		txtTenLop.setText("");
		cboGVCN.setSelectedItem(null);
		txtSiSo.setText("");
		txtMaLop.requestFocus();
	}
	
		
}
