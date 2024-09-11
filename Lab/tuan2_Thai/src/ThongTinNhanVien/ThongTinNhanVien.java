package ThongTinNhanVien;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ThongTinNhanVien extends JFrame implements ActionListener{

	private JLabel title;
	private JTextField txtMaNV;
	private JTextField txtHo;
	private JTextField txtTen;
	private JTextField txtTuoi;
	private JRadioButton rd1;
	private JRadioButton rd2;
	private DefaultTableModel model;
	private JTable table;
	private JTextField txtTim;
	private JButton btnTim;
	private JButton btnThem;
	private JButton btnXoaTrang;
	private JButton btnXoa;
	private JButton btnLuu;
	private JTextField txtLuong;

	public ThongTinNhanVien() {
		Font f = new Font("Times New Roman",Font.BOLD,30);
		JPanel pNorth = new JPanel();
		pNorth.add(title = new JLabel("THÔNG TIN NHÂN VIÊN"));
		title.setFont(f);
		title.setForeground(Color.BLUE);
		this.add(pNorth,BorderLayout.NORTH);
		
		JPanel pCenter = new JPanel();
		pCenter.setLayout(new BoxLayout(pCenter,BoxLayout.Y_AXIS));
		
		
		JPanel pCenter1=  new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCenter1.add(new JLabel("Mã Nhân Viên: "));
		pCenter1.add(txtMaNV = new JTextField(40));
		
		JPanel pCenter2=  new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCenter2.add(new JLabel("Họ:"));
		pCenter2.add(Box.createHorizontalStrut(62));
		pCenter2.add(txtHo = new JTextField(40));
		
		JPanel pCenter3=  new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCenter3.add(new JLabel("Tên Nhân Viên:"));
		pCenter3.add(txtTen = new JTextField(40));
		
		JPanel pCenter4=  new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCenter4.add(new JLabel("Tuổi:"));
		pCenter4.add(Box.createHorizontalStrut(55));
		pCenter4.add(txtTuoi = new JTextField(40));
		
		JPanel pCenter5=  new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCenter5.add(new JLabel("Phái:"));
		pCenter5.add(Box.createHorizontalStrut(57));
		pCenter5.add(rd1 = new JRadioButton("Nam"));
		pCenter5.add(rd2 = new JRadioButton("Nữ"));
		ButtonGroup grp1 = new ButtonGroup();
		grp1.add(rd1);
		grp1.add(rd2);
		
		JPanel pCenter6=  new JPanel(new FlowLayout(FlowLayout.LEFT));
		pCenter6.add(new JLabel("Tiền Lương:"));
		pCenter6.add(Box.createHorizontalStrut(14));
		pCenter6.add(txtLuong = new JTextField(40));
		
		
		String[] col = {"Mã Nv","Họ","Tên","Phái","Tuổi","Tiền Lương"};
		model = new DefaultTableModel(col,0);
		table = new JTable(model);
		JScrollPane pCenter7 = new JScrollPane(table);
		
		
		
		pCenter.add(pCenter1);
		pCenter.add(pCenter2);
		pCenter.add(pCenter3);
		pCenter.add(pCenter4);
		pCenter.add(pCenter5);
		pCenter.add(pCenter6);
		pCenter.setBorder(BorderFactory.createTitledBorder(""));
		pCenter.add(pCenter7);
		
		this.add(pCenter,BorderLayout.CENTER);
		
		JPanel pSouth = new JPanel();
		pSouth.setLayout(new BoxLayout(pSouth,BoxLayout.X_AXIS));
		JPanel pSouthLeft = new JPanel();
		pSouthLeft.add(new JLabel("Nhập Mã Số Cần Tìm:"));
		pSouthLeft.add(txtTim = new JTextField(20));
		pSouthLeft.add(btnTim = new JButton("Tìm"));
		pSouthLeft.setBorder(BorderFactory.createLineBorder(Color.black));
		
		JPanel pSouthRight = new JPanel();
		pSouthRight.add(btnThem= new JButton("Thêm"));
		btnThem.addActionListener(this);
		pSouthRight.add(btnXoaTrang= new JButton("Xóa Trắng"));
		btnXoaTrang.addActionListener(this);
		pSouthRight.add(btnXoa= new JButton("Xóa"));
		btnXoa.addActionListener(this);
		pSouthRight.add(btnLuu= new JButton("Lưu"));
		pSouthRight.setBorder(BorderFactory.createLineBorder(Color.black));
		
		pSouth.add(pSouthLeft);
		pSouth.add(Box.createHorizontalStrut(15));
		pSouth.add(pSouthRight);
		
		this.add(pSouth,BorderLayout.SOUTH);
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setSize(800, 700);
		this.setResizable(false);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		new ThongTinNhanVien();

	}
	public void xoaInput()
	{
		txtMaNV.setText("");
		txtHo.setText("");
		txtTen.setText("");
		rd1.setSelected(false); 
		rd2.setSelected(false);
		txtTuoi.setText("");
		txtLuong.setText("");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object source = e.getSource();
		if(source.equals(btnThem))
		{
			if(txtMaNV.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Bạn Chưa Nhập Mã Nhân Viên");
				txtMaNV.requestFocus();
			}else if(txtHo.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Bạn Chưa Nhập Họ");
				txtHo.requestFocus();
			}
			else if(txtTen.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Bạn Chưa Nhập Tên");
				txtTen.requestFocus();
			}
			else if(txtTuoi.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Bạn Chưa Nhập Tuổi");
				txtTuoi.requestFocus();
			}
			else if(rd1.isSelected()==false && rd2.isSelected()==false )
			{
				JOptionPane.showMessageDialog(this,"Bạn Chưa Chọn Giới Tính");
			}else if(txtLuong.getText().equals(""))
			{
				JOptionPane.showMessageDialog(this,"Bạn Chưa Nhập Lương");
				txtLuong.requestFocus();
			}else
			{
				Object[] obj = new Object[6];
				obj[0]=txtMaNV.getText();
				obj[1]=txtHo.getText();
				obj[2]=txtTen.getText();
				if(rd1.isSelected())
					obj[3]="Nam";
				else
					obj[3]="Nữ";
				obj[4]=txtTuoi.getText();
				obj[5]=txtLuong.getText();
				model.addRow(obj);
				xoaInput();
			}
			
		}
		if(source.equals(btnXoa))
		{
			if(table.getSelectedRow()==-1)
			{
				JOptionPane.showMessageDialog(this,"Bạn Chưa Chọn Dòng Cần Xóa");				
			}else if(JOptionPane.showConfirmDialog(this,"Bạn Có Muốn Xóa Dòng Này Không","Cảnh Báo!",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION)
			{
				model.removeRow(table.getSelectedRow());
			}
		}
		if(source.equals(btnXoaTrang))
		{

			if(table.getSelectedRow()==-1)
			{
				JOptionPane.showMessageDialog(this,"Bạn Chưa Chọn Dòng Cần Xóa");	
			}else
			{	
				table.setValueAt("",table.getSelectedRow(),0);
				table.setValueAt("",table.getSelectedRow(),1);
				table.setValueAt("",table.getSelectedRow(),2);
				table.setValueAt("",table.getSelectedRow(),3);
				table.setValueAt("",table.getSelectedRow(),4);
				table.setValueAt("",table.getSelectedRow(),5);
			}
		}
	}

}
