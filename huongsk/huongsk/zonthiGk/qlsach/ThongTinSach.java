package qlsach;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;

public class ThongTinSach extends JFrame implements ActionListener,MouseListener{

	private JTextField maSach, tuaSach, namXB, soTrang, tacGia, NhaXB, donGia, ISBN;
	private JButton them, xoaRong, xoa, sua,luu;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox tim;
	private DSSach list = new DSSach();
	private final String tenfile = "D:\\bt\\huongsk\\qlsach\\DuLieu.txt";
	LuuTru l = new LuuTru();
	public ThongTinSach() {
		super("Quản lí sách");
		Box b,b1,b2,b3,b4,b5;
		b= Box.createVerticalBox();
		b.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		//row1
		b.add(b1= Box.createHorizontalBox());
		JLabel lMa;
		b1.add(lMa= new JLabel("Mã sách:"));
		b1.add(maSach= new JTextField());
		b1.add(Box.createHorizontalStrut(395));

		//row2
		b.add(Box.createVerticalStrut(20));
		b.add(b2= Box.createHorizontalBox());
		JLabel lTuaSach;
		b2.add(lTuaSach= new JLabel("Tựa sách:"));
		b2.add(tuaSach= new JTextField());
		JLabel lTacGia;
		b2.add(Box.createHorizontalStrut(15));
		b2.add(lTacGia= new JLabel("Tác giả:"));
		b2.add(tacGia= new JTextField());


		//row3
		b.add(Box.createVerticalStrut(20));
		b.add(b3= Box.createHorizontalBox());
		JLabel lNamXB;
		b3.add(lNamXB= new JLabel("Năm xuất bản:"));
		b3.add(namXB= new JTextField());
		JLabel lNhaXB;
		b3.add(Box.createHorizontalStrut(15));
		b3.add(lNhaXB= new JLabel("Nhà xuất bản:"));
		b3.add(NhaXB= new JTextField());

		//row4
		b.add(Box.createVerticalStrut(20));
		b.add(b4= Box.createHorizontalBox());
		JLabel lSotrang;
		b4.add(lSotrang= new JLabel("Số trang:"));
		b4.add(soTrang= new JTextField());
		JLabel lDonGia;
		b4.add(Box.createHorizontalStrut(15));
		b4.add(lDonGia= new JLabel("Đơn giá:"));
		b4.add(donGia= new JTextField());


		//row5
		b.add(Box.createVerticalStrut(20));
		b.add(b5= Box.createHorizontalBox());
		JLabel lISBN;
		b5.add(lISBN= new JLabel("International Standard Book Number"));
		b5.add(ISBN= new JTextField());
		b5.add(Box.createHorizontalStrut(395));
		b.add(Box.createVerticalStrut(5));


		lMa.setPreferredSize(lNamXB.getPreferredSize());
		lTuaSach.setPreferredSize(lNamXB.getPreferredSize());
		lSotrang.setPreferredSize(lNamXB.getPreferredSize());
		lTacGia.setPreferredSize(lNhaXB.getPreferredSize());
		lDonGia.setPreferredSize(lNhaXB.getPreferredSize());


		add(b,BorderLayout.NORTH);


		//center
		JPanel c= new JPanel();
		c.add(them= new JButton("Thêm"));
		c.add(xoaRong= new JButton("Xóa Rỗng"));
		c.add(xoa= new JButton("Xóa"));
		c.add(sua = new JButton("Sửa"));
		c.add(luu= new JButton("Lưu"));
		JLabel find= new JLabel("Tìm theo mã sách");
		String cb[]= {"1","2","3"};
		tim= new JComboBox<String>(cb);
		c.add(find);
		c.add(tim);

		add(c,BorderLayout.CENTER);


		//table


		String tieude[]={"Mã sách","Tựa sách","Tác giả","Năm xuất bản","Nhà xuất bản","So Trang","Đơn giá","ISBN"};
		model=new DefaultTableModel(tieude,0){
			@Override
			public boolean isCellEditable(int row, int column) {
				return row!=0;
			}
		};

		table= new JTable(model);

		add(new JScrollPane(table),BorderLayout.SOUTH);





		//Đọc dữ liệu cào Jtabel
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,700);
		setVisible(true);
		//====================================================
		them.addActionListener(this::actionPerformed);
		xoaRong.addActionListener(this::actionPerformed);
		xoa.addActionListener(this::actionPerformed);
		sua.addActionListener(this::actionPerformed);
		luu.addActionListener(this::actionPerformed);
		tim.addActionListener(this::actionPerformed);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object soure = e.getSource();
		if(soure.equals(them)) {

			String ma=maSach.getText();
			String tua=maSach.getText();
			String tacgia=tacGia.getText();
			int nam= Integer.parseInt(namXB.getText());
			String nhaxb=NhaXB.getText();
			int trang =Integer.parseInt(soTrang.getText());
			double dongia=Double.parseDouble(donGia.getText());
			String isbn=ISBN.getText();
			Sach sach= new Sach(ma,tua,tacgia,nam,nhaxb,trang,dongia,isbn);
			if(checkvalue()){
				if(!list.them(sach)){
					JOptionPane.showMessageDialog(this,"mã sách đã có");
					ISBN.requestFocus();
				}
				else {
					model.addRow(new Object[]{sach.getMa(),sach.getTua(),sach.getTacGia(),sach.getNamXB(),sach.getNhaXB(),sach.getSoTrang(),sach.getDonGia(),sach.getISBN()});
				}
			}
		}else if(soure.equals(xoaRong)) {
			maSach.setText("");
			tuaSach.setText("");
			tacGia.setText("");
			namXB.setText("");
			NhaXB.setText("");
			soTrang.setText("");
			donGia.setText("");
			ISBN.setText("");
		}else if(soure.equals(xoa)) {
			int row=table.getSelectedRow();
			model.removeRow(row);
			Sach s=list.getIndex(row);
			list.xoa(s.getMa());
		}else if(soure.equals(sua)) {
			
			
			
			
		}else if(soure.equals(luu)) {
		}


	}
	public boolean checkvalue(){
		if(maSach.getText().isEmpty()){
			JOptionPane.showMessageDialog(this,"chưa nhập đủ thông tin");
			maSach.requestFocus();
			return false;
		}
		else if(tuaSach.getText().isEmpty()){
			JOptionPane.showMessageDialog(this,"chưa nhập đủ thông tin");
			tuaSach.requestFocus();return false;
		}
		else if(tacGia.getText().isEmpty()){
			JOptionPane.showMessageDialog(this,"chưa nhập đủ thông tin");
			tacGia.requestFocus();return false;
		}
		else if(namXB.getText().isEmpty()){
			JOptionPane.showMessageDialog(this,"chưa nhập đủ thông tin");
			namXB.requestFocus();return false;
		}
		else if(NhaXB.getText().isEmpty()){
			JOptionPane.showMessageDialog(this,"chưa nhập đủ thông tin");
			NhaXB.requestFocus();return false;
		}
		else if(soTrang.getText().isEmpty()){
			JOptionPane.showMessageDialog(this,"chưa nhập đủ thông tin");
			soTrang.requestFocus();return false;
		}
		else if(donGia.getText().isEmpty()){
			JOptionPane.showMessageDialog(this,"chưa nhập đủ thông tin");
			maSach.requestFocus();return false;
		}
		else if(ISBN.getText().isEmpty()){
			JOptionPane.showMessageDialog(this,"chưa nhập đủ thông tin");
			ISBN.requestFocus();return false;
		}
		return true;
	}
	public void updateTable() {

	}
	@Override
	public void mouseClicked(MouseEvent e) {
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
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
