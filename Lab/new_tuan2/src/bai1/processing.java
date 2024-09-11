package bai1;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.html.HTMLEditorKit.Parser;

public class processing extends JFrame implements ActionListener{

	JLabel ltd, lmnv,lho,ltnv,ltuoi,lphai,ltien,lnhap;
	JTextField tmnv, tho, ttnv, ttuoi,ttien,tnhap;
	JRadioButton nam,nu;
	JButton tim, them, xoa,xoatrang,luu;
	JPanel pNorth, pSouth,pCenter;
	Box box, box1,box2,box3,box4,box5, box6;
	JTable table;
	DefaultTableModel model;
	JComboBox wordChoose;
	String[] words = {"Nam", "Nữ"};
	String[] colnames = {"Ma NV", "Ho", "Ten", "Phai", "Tuoi","Tien Luong"};
	Object[][] cells = {
			{"1111","Nguyễn","Hoàng",words[0],new Integer(26),"4,500$"},
			{"2222","Nguyễn","Thu",words[1],"28","4,500$"},
			{"3333","Nguyễn","Hoàng",words[0],"30","4,500$"},
			{"4444","Trần","Lan",words[1],"27","4,500$"},
	};
	ButtonGroup gr;
	DanhSachNhanVien dsNhanVien = new DanhSachNhanVien(new ArrayList<>());
	public processing() {

		
		ltd = new JLabel("THÔNG TIN NHÂN VIÊN");
		Font f = new Font(Font.SERIF, Font.BOLD, 20);
		ltd.setForeground(Color.blue);
		ltd.setFont(f);
		pNorth = new JPanel();
		pNorth.add(ltd);
		add(pNorth, BorderLayout.NORTH);
		
		pCenter = new JPanel();
		lmnv = new JLabel("Mã nhân viên");
		lho = new JLabel("Họ");
		ltnv = new JLabel("Tên nhân viên");
		ltuoi = new JLabel("Tuổi");
		lphai = new JLabel("Phái");
		ltien = new JLabel("Tiền lương");
		
		tmnv = new JTextField(30);
		ttnv = new JTextField(10);
		tho = new JTextField(15);
		ttuoi = new JTextField(18);
		ttien = new JTextField(30);
		nam = new JRadioButton("Nam");
		nu = new JRadioButton("Nữ");
		gr = new ButtonGroup();
		gr.add(nam);
		gr.add(nu);
		
		box1 = new Box(BoxLayout.X_AXIS);
		box1.add(lmnv);
		box1.add(Box.createHorizontalStrut(5));
		box1.add(tmnv);
		
		box2 = new Box(BoxLayout.X_AXIS);
		box2.add(lho);
		box2.add(Box.createHorizontalStrut(65));
		box2.add(tho);
		box2.add(Box.createHorizontalStrut(10));
		box2.add(ltnv);
		box2.add(Box.createHorizontalStrut(4));
		box2.add(ttnv);
		
		box3 = new Box(BoxLayout.X_AXIS);
		box3.add(ltuoi);
		box3.add(Box.createHorizontalStrut(55));
		box3.add(ttuoi);
		box3.add(Box.createHorizontalStrut(5));
		box3.add(lphai);
		box3.add(Box.createHorizontalStrut(5));
		box3.add(nam);
		box3.add(Box.createHorizontalStrut(5));
		box3.add(nu);
		
		
		box4 = new Box(BoxLayout.X_AXIS);
		box4.add(ltien);
		box4.add(Box.createHorizontalStrut(17));
		box4.add(ttien);
		
		box = new Box(BoxLayout.Y_AXIS);
		box.add(box1);
		box.add(box2);
		box.add(box3);
		box.add(box4);
		
		wordChoose = new JComboBox(words);
		model = new DefaultTableModel(cells, colnames);
		table = new JTable(model);
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(wordChoose));
		JScrollPane jscpane = new JScrollPane(table);
		box.add(jscpane);
		pCenter.add(box);
		
		add(pCenter, BorderLayout.CENTER);
		
		
		pSouth = new JPanel();
		
		box5 = new Box(BoxLayout.X_AXIS);
		lnhap = new JLabel("Nhập mã số cần tìm");
		tnhap = new JTextField(10);
		tim = new JButton("Tìm");
		box5.add(lnhap);
		box5.add(tnhap);
		box5.add(tim);
		
		box6 = new Box(BoxLayout.X_AXIS);
		them = new JButton("Thêm");
		xoatrang = new JButton("Xóa trắng");
		xoa = new JButton("Xóa");
		luu = new JButton("Lưu");
		box6.add(them);
		box6.add(xoatrang);
		box6.add(xoa);
		box6.add(luu);
		
		Box box_1 = new Box(BoxLayout.X_AXIS);
		box5.setBorder(BorderFactory.createLineBorder(Color.black));
		box6.setBorder(BorderFactory.createLineBorder(Color.black));
		box_1.add(box5);
		
		box_1.add(Box.createHorizontalStrut(20));
		box_1.add(box6);
		pSouth.add(box_1);
		add(pSouth, BorderLayout.SOUTH);
		
		them.addActionListener(this);
        xoa.addActionListener(this);
        tim.addActionListener(this);
        xoatrang.addActionListener(this);
        luu.addActionListener(this);
		
		setSize(800,800);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public static void main(String[] args) {
		new processing();
	}
//	JTextField tmnv, tho, ttnv, ttuoi,ttien,tnhap;
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src.equals(them)) {
			String maNV = tmnv.getText();
			String ho = tho.getText();
			String ten = ttnv.getText();
			int tuoi = Integer.parseInt(ttuoi.getText());
			String phai = "";
			if(nam.isSelected())
				phai = "Nam";
			else if(nu.isSelected())
				phai = "Nữ";
			System.out.println(phai);
			Double tien = Double.parseDouble(ttien.getText().replace(",", "").replace("$", ""));
			
			ThongTinNhanVien nv = new ThongTinNhanVien(maNV, ho, ten, phai, tuoi, tien);
			if(dsNhanVien.them(nv)) {
				model.addRow(new Object[] {maNV, ho, ten, phai, tuoi, tien});
				JOptionPane.showMessageDialog(this, "Add success");
			}else {
				JOptionPane.showMessageDialog(this, "Error add");
			}
		}
		else if(src.equals(xoa)) {
			String ma_nv = tnhap.getText();
			boolean xoanv = dsNhanVien.xoa(ma_nv);
			if(xoanv) {
				for(int i = 0;i < table.getRowCount();i++) {
					if(table.getValueAt(i, 0).equals(ma_nv)) {
						model.removeRow(i);
						break;
					}
					JOptionPane.showMessageDialog(this, "Remove success");
				}
			}
			else
				JOptionPane.showMessageDialog(this, "Error remove");
		}
		else if(src.equals(tim)) {
			String ma_nv = tnhap.getText();
			int flag = 0;
			for(int i = 0;i < table.getRowCount();i++) {
				if(table.getValueAt(i, 0).equals(ma_nv))
					flag = 1;
			}
			if(flag == 1)
				JOptionPane.showMessageDialog(this, "Tim thanh cong");
			else
				JOptionPane.showMessageDialog(this, "Tim khong thanh cong");
		}
	}
	
}
