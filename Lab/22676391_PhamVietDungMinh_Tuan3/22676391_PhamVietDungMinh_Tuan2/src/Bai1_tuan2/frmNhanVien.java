package Bai1_tuan2;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField.AbstractFormatter;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class frmNhanVien extends JFrame implements ActionListener, MouseListener{
	JLabel ltd, lmnv, lho,ltnv, ltuoi,ltien, lphai, lmaso;
	JTextField tmnv, tho,ttnv, ttuoi,ttien, tmaso;
	JButton tim,them, xoa, xoatrang, luu;
	DefaultTableModel model;
	JRadioButton nu;
	JTable table;
	JPanel pNorth, pSouth, pCenter;
	JComboBox words;
	String[] wordchoose = {"Nam", "Nu"};
	Object[][] cells = {
			{"1111", "Nguyen", "Hoang",wordchoose[1], 25, "4,5000$"}	
	};
	String[] cols = {"Ma NV", "Ho", "Ten", "Phai", "Tuoi","Tien luong"};
	DanhSachNhanVien ds;
	public frmNhanVien() {
		ds = new DanhSachNhanVien(new ArrayList<ThongTinNhanVien>());
		pNorth = new JPanel();
		ltd = new JLabel("THONG TIN NHAN VIEN");
		ltd.setForeground(Color.blue);
		Font f = new Font(Font.SERIF,Font.BOLD,20);
		ltd.setFont(f);
		pNorth.add(ltd);
		add(pNorth, BorderLayout.NORTH);
		
		
		
		
		pCenter = new JPanel();
		
		Box box, box1, box2,box3,box4,box5,box6;
		box1 = new Box(BoxLayout.X_AXIS);
		box2 = new Box(BoxLayout.X_AXIS);
		box3 = new Box(BoxLayout.X_AXIS);
		box4 = new Box(BoxLayout.X_AXIS);
		box5 = new Box(BoxLayout.X_AXIS);
		box6 = new Box(BoxLayout.X_AXIS);
		box = new Box(BoxLayout.Y_AXIS);
		
		lmnv = new JLabel("Ma nhan vien:");
		tmnv = new JTextField(30);
		lho = new JLabel("Ho:");
		tho = new JTextField(15);
		ltnv = new JLabel("Ten nhan vien:");
		ttnv =  new JTextField(10);
		ltuoi = new JLabel("Tuoi:");
		ttuoi = new JTextField(25);
		lphai = new JLabel("Phai:");
		nu = new JRadioButton("Nu:");
		ltien = new JLabel("Tien luong:");
		ttien = new JTextField(30);
		
		box1.add(lmnv);
		box1.add(Box.createHorizontalStrut(5));
		box1.add(tmnv);
		
		
		box2.add(lho);
		box2.add(Box.createHorizontalStrut(64));
		box2.add(tho);
		box2.add(Box.createHorizontalStrut(15));
		box2.add(ltnv);
		box2.add(Box.createHorizontalStrut(15));
		box2.add(ttnv);
		
		box3.add(ltuoi);
		box3.add(Box.createHorizontalStrut(55));
		box3.add(ttuoi);
		box3.add(Box.createHorizontalStrut(15));
		box3.add(lphai);
		box3.add(Box.createHorizontalStrut(15));
		box3.add(nu);
		
		box4.add(ltien);
		box4.add(Box.createHorizontalStrut(21));
		box4.add(ttien);
		
		
		box.add(box1);
//		box1.add(Box.createHorizontalStrut(25));
		box.add(box2);
//		box1.add(Box.createHorizontalStrut(15));
		box.add(box3);
//		box1.add(Box.createHorizontalStrut(15));
		box.add(box4);
		
		model = new DefaultTableModel(cols, 0);
		table = new JTable(model);
		JScrollPane scroll = new JScrollPane(table);
		words = new JComboBox(wordchoose);
		table.getColumnModel().getColumn(3).setCellEditor(new DefaultCellEditor(words));
		
		
		
		
		
		pCenter.add(box);
		pCenter.add(scroll);
		add(pCenter, BorderLayout.CENTER);
		
		
		
		
		
		lmaso = new JLabel("Nhap ma so can tim");
		tmaso = new JTextField(14);
		tim = new JButton("Tim");
		
		box5.add(lmaso);
		box5.add(box.createHorizontalStrut(5));
		box5.add(tmaso);
		box5.add(box.createHorizontalStrut(5));
		box5.add(tim);
		
		them = new JButton("Them");
		xoa = new JButton("Xoa");
		xoatrang = new JButton("Xoa trang");
		luu = new JButton("Luu");
		
		box6.add(them);
		box6.add(xoatrang);
		box6.add(xoa);
		box6.add(luu);
		Box box7 = new Box(BoxLayout.X_AXIS);
		box7.add(box5);
		box7.add(Box.createHorizontalStrut(20));
		box7.add(box6);
		pSouth = new JPanel();
		
		JSplitPane split = new JSplitPane();
		split.setLeftComponent(box5);
		split.setRightComponent(box6);
		pSouth.add(split);
		add(pSouth, BorderLayout.SOUTH);
//		pCenter.add(box7);
		
//		add(box7,BorderLayout.SOUTH);
		
		
		
		tim.addActionListener(this);
		them.addActionListener(this);
		xoa.addActionListener(this);
		xoatrang.addActionListener(this);
		luu.addActionListener(this);
		table.addMouseListener(this)	;
		
		
		setSize(700,500);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
		
		
	}
	
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object src = e.getSource();
		if(src.equals(them)) {
			String maNV = tmnv.getText();
			String ho = tho.getText();
			String ten = ttnv.getText();
			Boolean phai = false;
			if(nu.isSelected())
				phai = true;
			else 
				phai = false;
			int tuoi = Integer.parseInt(ttuoi.getText());
			double tienluong = Double.parseDouble(ttien.getText().replace("$", ",").replace(",", ""));
			ThongTinNhanVien nv = new ThongTinNhanVien(maNV, ho, ten, phai, tuoi, tienluong);
			if(ds.themNV(nv)){
				String select = phai ? "Nu" : "Nam";
				model.addRow(new Object[] {maNV, ho, ten, select, tuoi,String.format("%.2f$", tienluong)});
			}
			else {
				JOptionPane.showMessageDialog(this, "Ma bi trung");
			}
		}
		else if(src.equals(xoa)) {
			int row = table.getSelectedRow();
			if (row != -1) {
				String maNV = (String) table.getModel().getValueAt(row, 0);
				int hoiNhac = JOptionPane.showConfirmDialog(this, "Chắc chắn xóa không?", "Chú ý",
						JOptionPane.YES_NO_OPTION);

				if (hoiNhac == JOptionPane.YES_OPTION) {
					if (ds.removeNV(maNV)) {
						model.removeRow(row);
					}
				}
			} else {
				JOptionPane.showMessageDialog(null, "Chưa chọn hàng để xóa");
			}
		}
		else if(src.equals(xoatrang)) {
			tmnv.setText("");
			tho.setText("");
			ttuoi.setText("");
			ttien.setText("");
			tmaso.setText("");
			ttnv.setText("");
			nu.setSelected(false);
		}
	}





	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int row = table.getSelectedRow();
		tmnv.setText(table.getValueAt(row, 0).toString());
		tho.setText(table.getValueAt(row, 1).toString());
		ttnv.setText(table.getValueAt(row, 2).toString());
		nu.setSelected(false);
		ttuoi.setText(table.getValueAt(row, 4).toString());
		ttien.setText(table.getValueAt(row, 5).toString());
		
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
