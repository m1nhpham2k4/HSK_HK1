package Bai2;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Label;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.table.DefaultTableModel;

public class MainForm {

	private JFrame myFrame;
	private JPanel pInfo;
	private JLabel lbMaLop;
	private JTextField tfMaLop;
	private JLabel lbTenLop;
	private JTextField tfTenLop;
	private JLabel lbGVCN;
	private JTextField tfGVCN;
	private JPanel pMalop;
	private JPanel pTenLop;
	private JPanel pGVCN;
	private JPanel pNorth;
	private JButton btnGoFirst;
	private JButton btnForward;
	private JButton btnGoEnd;
	private JButton btnNext;
	private JButton btnAdd;
	private JButton btnSave;
	private JButton btnEdit;
	private JButton btnRemove;
	private JPanel pButton;
	private JLabel lbIndexLopHoc;
	private JButton btnGoToStudentList;
	private JPanel pSouth;
	private DefaultTableModel tableModelLopHoc;
	private JTable tableLopHoc;
	private DanhSachLopHoc ql;
	private JScrollPane scpTableLopHoc;
	private JPanel pCenter;

	MainForm(){
		
		
//		Info
		pInfo = new JPanel();
		
//		Ma Lop
		lbMaLop = new JLabel("Mã lớp:");
		tfMaLop = new JTextField(50);
		pMalop = new JPanel();
		pMalop.add(lbMaLop);
		pMalop.add(tfMaLop);
		
//		Ten Lop
		lbTenLop = new JLabel("Tên lớp:");		
		tfTenLop = new JTextField(50);
		pTenLop = new JPanel();
		pTenLop.add(lbTenLop);
		pTenLop.add(tfTenLop);
		
//		GVCN
		lbGVCN = new JLabel("Giáo viên chủ nhiệm:");
		tfGVCN = new JTextField(50);
		pGVCN = new JPanel();
		pGVCN.add(lbGVCN);
		pGVCN.add(tfGVCN);
		
//		Custom Info
		lbMaLop.setPreferredSize(lbGVCN.getPreferredSize());
		lbMaLop.setHorizontalAlignment(SwingConstants.CENTER);
		
		lbTenLop.setPreferredSize(lbGVCN.getPreferredSize());
		lbTenLop.setHorizontalAlignment(SwingConstants.CENTER);
		
		tfGVCN.setEditable(false);
		tfMaLop.setEditable(false);
		tfTenLop.setEditable(false);
		
//		btn info
		
		btnGoFirst = new JButton("First");
		btnForward = new JButton("Previous");
		btnNext = new JButton("Next");
		btnGoEnd = new JButton("Last");
		
		
		lbIndexLopHoc = new JLabel("x/x");		
		btnAdd = new JButton("Thêm");
		btnSave = new JButton("Lưu");
		btnEdit = new JButton("Sửa");
		btnRemove = new JButton("Xóa");
		
//		Container button
		pButton = new JPanel();
		pButton.add(btnGoFirst);
		pButton.add(btnForward);
		pButton.add(lbIndexLopHoc);
		pButton.add(btnNext);
		pButton.add(btnGoEnd);
		pButton.add(btnAdd);
		pButton.add(btnSave);
		pButton.add(btnEdit);
		pButton.add(btnRemove);
		
		
		
//		table
		Object[] colNamesLopHoc = {"Mã lớp", "Tên lớp", "Tên giáo viên chủ nhiệm"};
		tableModelLopHoc = new DefaultTableModel(colNamesLopHoc, 0);
		tableLopHoc = new JTable(tableModelLopHoc);
		scpTableLopHoc = new JScrollPane(tableLopHoc);
		
// 		Read data from database		
		ql = new DanhSachLopHoc();

//		add data to table LopHoc
		
		for (LopHoc ele : ql.docTuBang()) {
			tableModelLopHoc.addRow(ele.getData());
		}
		
		
		
//		pNorth
		pNorth = new JPanel();
		pNorth.add(pMalop);
		pNorth.add(pTenLop);
		pNorth.add(pGVCN);
		pNorth.add(pButton);
		
// 		pCenter
		pCenter = new JPanel();
		pCenter.add(scpTableLopHoc);
		
		
//		pSouth
		btnGoToStudentList = new JButton("Xem danh sách sinh viên của lớp hiện tại");
		
		pSouth = new JPanel();
//		pSouth.setLayout(new FlowLayout(FlowLayout.RIGHT));
		pSouth.add(btnGoToStudentList);
		
		
		
//		Frame
		myFrame = new JFrame();
		myFrame.setLayout(new BorderLayout());
		myFrame.setSize(1200, 1000);
		myFrame.add(pNorth);
		myFrame.add(pCenter);
		myFrame.add(pSouth);
		myFrame.setVisible(true);
	}
}
