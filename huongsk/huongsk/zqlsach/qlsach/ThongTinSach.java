package qlsach;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.print.DocFlavor;
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

import static javax.swing.JOptionPane.YES_NO_OPTION;
import static javax.swing.JOptionPane.YES_OPTION;

public class ThongTinSach extends JFrame implements ActionListener,MouseListener{

	private JTextField maSach, tuaSach, namXB, soTrang, tacGia, NhaXB, donGia, ISBN;
	private JButton them, xoaRong, xoa, sua,luu;
	private JTable table;
	private DefaultTableModel model;
	private JComboBox tim;
	private DSSach list = new DSSach();
	private final String tenfile = "D:\\bt\\huongsk\\DuLieu.txt";
	public int  dem=0;
	public ThongTinSach() {
		super("Quản lí sách");

		//pCenter
		JPanel pNor = new JPanel();
		pNor.setLayout(new BoxLayout(pNor, BoxLayout.Y_AXIS));
		pNor.setBorder(BorderFactory.createTitledBorder("Records Editor"));
		//row 1
		JPanel prow1 = new JPanel();
		prow1.setLayout(new BoxLayout(prow1, BoxLayout.X_AXIS));
		JLabel ma = new JLabel("Mã sách:");
		prow1.add(ma);
		prow1.add(Box.createHorizontalStrut(50));
		maSach = new JTextField(10);
		prow1.add(maSach);
		pNor.add(prow1);
		prow1.add(Box.createHorizontalStrut(405));
		pNor.add(Box.createVerticalStrut(10));
		// row 2
		JPanel prow2 = new JPanel();
		prow2.setLayout(new BoxLayout(prow2, BoxLayout.X_AXIS));
		JLabel tua =  new JLabel("Tựa sách:");
		prow2.add(tua);
		prow2.add(Box.createHorizontalStrut(45));
		tuaSach = new JTextField(30);
		prow2.add(tuaSach);
		prow2.add(Box.createHorizontalStrut(40));
		JLabel tacgia = new JLabel("Tác giả:");
		prow2.add(tacgia);
		prow2.add(Box.createHorizontalStrut(50));
		tacGia = new JTextField(30);
		prow2.add(tacGia);
		pNor.add(prow2);
		pNor.add(Box.createVerticalStrut(10));
		// row 3
		JPanel prow3 = new JPanel();
		prow3.setLayout(new BoxLayout(prow3, BoxLayout.X_AXIS));
		JLabel namxb = new JLabel("Năm xuất bản:");
		prow3.add(namxb);
		prow3.add(Box.createHorizontalStrut(20));
		namXB = new JTextField(30);
		prow3.add(namXB);
		prow3.add(Box.createHorizontalStrut(40));
		JLabel nhaxb = new JLabel("Nhà xuất bản:");
		prow3.add(nhaxb);
		prow3.add(Box.createHorizontalStrut(20));
		NhaXB = new JTextField(30);
		prow3.add(NhaXB);
		pNor.add(prow3);
		pNor.add(Box.createVerticalStrut(10));
		// row 4
		JPanel prow4 = new JPanel();
		prow4.setLayout(new BoxLayout(prow4, BoxLayout.X_AXIS));
		JLabel sotrang = new JLabel("Số trang:");
		prow4.add(sotrang);
		prow4.add(Box.createHorizontalStrut(50));
		soTrang = new JTextField(30);
		prow4.add(soTrang);
		prow4.add(Box.createHorizontalStrut(40));
		JLabel dongia = new JLabel("Đơn giá:");
		prow4.add(dongia);
		prow4.add(Box.createHorizontalStrut(50));
		donGia = new JTextField(30);
		prow4.add(donGia);
		pNor.add(prow4);
		pNor.add(Box.createVerticalStrut(10));
		// row 5
		JPanel prow5 = new JPanel();
		prow5.setLayout(new BoxLayout(prow5, BoxLayout.X_AXIS));
		JLabel isbn = new JLabel("International Standard Book Number:");
		prow5.add(isbn);
		prow5.add(Box.createHorizontalStrut(10));
		ISBN = new JTextField(20);
		prow5.add(ISBN);
		prow5.add(Box.createHorizontalStrut(405));
		pNor.add(prow5);
		pNor.add(Box.createVerticalStrut(30));
		add(pNor, BorderLayout.NORTH);
		//cac button 
		//center
		JPanel pCenter = new JPanel();
		them = new JButton("Thêm");
		pCenter.add(them);
		xoaRong = new JButton("Xóa rỗng");
		pCenter.add(xoaRong);
		xoa = new JButton("Xóa");
		pCenter.add(xoa);
		sua = new JButton("Sửa");
		pCenter.add(sua);
		luu = new JButton("Lưu");
		pCenter.add(luu);
		JLabel lable_tim = new JLabel("Tìm theo mã sách: ");
		pCenter.add(lable_tim);
		//comboBox tim ma

		tim = new JComboBox();
		pCenter.add(tim);

		//page end , Jtable
//		JPanel pSouth = new JPanel();
//		pSouth.setBorder(BorderFactory.createTitledBorder("Danh sách các cuốn sách"));
		String [] colnames = {"MaSach","TuaSach","TacGia","NamXuatBan","NhaXuatBan","SoTrang","DonGia","ISBN"};
		model = new DefaultTableModel(colnames,0){
			@Override
			public boolean isCellEditable(int row, int column) {
				return column!=0;
			}
		};
		table = new JTable(model);
		//pSouth.add(new JScrollPane(table));

		//===================================================
		LuuTru l = new LuuTru();
		try {
			list = (DSSach) l.DocFile(tenfile);
		}catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Không tìm thấy file");
		}
		//Đọc dữ liệu cào Jtabel
		updateTable();

		String[] MA = new String[list.getList().size()+dem];
		for(int i=0; i<list.getList().size();i++){
			MA[i]= table.getValueAt(i,0).toString();

			tim.addItem(MA[i]);
		}


		add(pCenter,BorderLayout.CENTER);
		add(new JScrollPane(table),BorderLayout.SOUTH);
		//====================================================
		them.addActionListener(this);
		xoaRong.addActionListener(this);
		xoa.addActionListener(this);
		sua.addActionListener(this);
		luu.addActionListener(this);
		tim.addActionListener(this);
		table.addMouseListener(this);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setSize(800,700);
		setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object soure = e.getSource();
		if(soure.equals(them)) {
			maSach.setEditable(true);
			String ma = maSach.getText();
			String tua = tuaSach.getText();
			int nam = Integer.parseInt(namXB.getText());
			int sotrang = Integer.parseInt(soTrang.getText());
			String isbn = ISBN.getText();
			String tacgia = tacGia.getText();
			String nhaxb = NhaXB.getText();
			Double dongia = Double.parseDouble(donGia.getText());
			Sach sach = new Sach(ma, tua, tacgia, nam, nhaxb, sotrang, sotrang, isbn);
			if(!list.them(sach)) {
				JOptionPane.showMessageDialog(this,"Sách đã tồn tại");
			}else {
				model.addRow(new Object[] {sach.getMa(), sach.getTua(),sach.getTacGia(), sach.getNamXB(),sach.getNhaXB(),sach.getSoTrang(),sach.getDonGia(),sach.getISBN()});
				dem++;
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
			maSach.setEditable(true);
		}else if(soure.equals(xoa)) {
			int xn = JOptionPane.showConfirmDialog(this,"Bạn có muốn xóa?","Xác nhận", YES_NO_OPTION);
			if(xn==YES_OPTION){
				int row = table.getSelectedRow();
				model.removeRow(row);
				Sach s = list.getIndex(row);
				list.xoa(s.getMa());
				JOptionPane.showMessageDialog(this,"Xóa thành công");
				maSach.setEditable(true);
			}

		}else if(soure.equals(sua)) {
			String ma= maSach.getText();
			int row = table.getSelectedRow();
			String tua=tuaSach.getText();
			String tacgia=tacGia.getText();
			int nam=Integer.parseInt(namXB.getText()) ;
			String nhaxb=NhaXB.getText();
			int sotrang=Integer.parseInt(soTrang.getText());
			double dongia= Double.parseDouble(donGia.getText());
			String isbn=ISBN.getText();

			Sach sach= new Sach(ma,tua,tacgia,nam,nhaxb,sotrang,dongia,isbn);

			model.setValueAt(sach.getTua(),row,1);
			model.setValueAt(sach.getTacGia(),row,2);
			model.setValueAt( sach.getNamXB(),row,3);
			model.setValueAt(sach.getNhaXB(),row,4);
			model.setValueAt(sach.getSoTrang(),row,5);
			model.setValueAt(sach.getDonGia(),row,6);
			model.setValueAt(sach.getISBN(),row,7);

			list.capNhatSach(sach);


		}else if(soure.equals(luu)) {
			LuuTru l = new LuuTru();
			try {
				l.LuuFile(list, tenfile);
				JOptionPane.showConfirmDialog(this, "Lưu thành công", "Chú Ý", JOptionPane.CLOSED_OPTION);
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	public void updateTable() {
		//model.setRowCount(0);
		for(Sach sach : list.getList()) {
			model.addRow(new Object[]{
					sach.getMa(),
					sach.getTua(),
					sach.getTacGia(),
					sach.getNamXB(),
					sach.getNhaXB(),
					sach.getSoTrang(),
					sach.getDonGia(),
					sach.getISBN()
			});
		}
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row= table.getSelectedRow();
		maSach.setText(model.getValueAt(row,0).toString());
		tuaSach.setText(model.getValueAt(row,1).toString());
		tacGia.setText(model.getValueAt(row,2).toString());
		namXB.setText(model.getValueAt(row,3).toString());
		NhaXB.setText(model.getValueAt(row,4).toString());
		soTrang.setText(model.getValueAt(row,5).toString());
		donGia.setText(model.getValueAt(row,6).toString());
		ISBN.setText(model.getValueAt(row,7).toString());
		maSach.setEditable(false);
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
