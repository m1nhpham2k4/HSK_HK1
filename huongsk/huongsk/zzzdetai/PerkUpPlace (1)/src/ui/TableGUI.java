package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.BanDAO;
import dao.SanPhamDAO;
import entity.Ban;
import entity.SanPham;

public class TableGUI extends JPanel implements MouseListener{
	private JLabel lblCreateTable;
	private JPanel pnlTableList;
	private JPanel pnlContainTableList;
	private JTable tableTable;
	private JScrollPane scroll;
	private JPanel pnlTableDetails;
	private JLabel lblCloseTable;
	private JLabel lblTableID;
	private JSpinner txtNumberSeat;
	private JLabel lblNumberSeat;
	private JTextField txtTableID;
	private JLabel lblTableStatus;
	private JComboBox<String> comboTableStatus;
	private JButton btnUpdate;
	private JButton btnSave;
	private JButton btnDelete;
	private BanDAO banDao;
	private DefaultTableModel modelTable;
	private JButton btnOrderTB;

	public TableGUI() {
		this.init();
	}
	
	public void init() {
		// khởi tạo kết nối đến CSDL
		try {
			ConnectDB.getInstance().connect();
		} catch (SQLException e) {
		// TODO Auto-generated catch block
			e.printStackTrace();
		}
		banDao = new BanDAO();
		
		this.setLayout(null);
		this.setBackground(new Color(255, 255, 255));
		
		lblCreateTable = new JLabel();
		lblCreateTable.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCreate.png")));
		lblCreateTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCreateTable.setText("true");
		lblCreateTable.addMouseListener(this);
		lblCreateTable.setBounds(25, 12, 50, 50);
		this.add(lblCreateTable);
		
		pnlTableList = new JPanel();
		pnlTableList.setLayout(null);
		pnlTableList.setBackground(new Color(217, 217, 217));
		pnlTableList.setBounds(13, 78, 1895, 1035);
		this.add(pnlTableList);
		
		pnlContainTableList = new JPanel();
		pnlContainTableList.setLayout(new BorderLayout());
		pnlContainTableList.setBackground(new Color(255, 255, 255));
		pnlContainTableList.setBounds(12, 67, 1869, 956);
		pnlTableList.add(pnlContainTableList);
		
		tableTable = new JTable();
		tableTable.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"STT","Mã bàn","Số ghế","Trạng thái"
			}
		));
//		tableTable.setEnabled(false);
		tableTable.addMouseListener(this);
		modelTable = (DefaultTableModel) tableTable.getModel();
		
		scroll = new JScrollPane(tableTable);
		pnlContainTableList.add(scroll);
		
		// Chi tiết khách hàng
		pnlTableDetails = new JPanel();
		pnlTableDetails.setLayout(null);
		pnlTableDetails.setBounds(1357, 67, 1869, 956);
		pnlTableList.add(pnlTableDetails);
		pnlTableDetails.setVisible(false);
		
		lblCloseTable = new JLabel();
		lblCloseTable.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCloseWhite.png")));
		lblCloseTable.setBackground(Color.black);
		lblCloseTable.setOpaque(true);
		lblCloseTable.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCloseTable.addMouseListener(this);
		lblCloseTable.setBounds(503, 10, 15, 15);
		pnlTableDetails.add(lblCloseTable);
		
		// Mã bàn
		lblTableID = new JLabel("Mã bàn");
		lblTableID.setFont(new Font("Arial", 0, 14));
		lblTableID.setBounds(14, 42, 115, 30);
		pnlTableDetails.add(lblTableID);
		
		txtTableID = new JTextField();
		txtTableID.setBackground(new Color(255, 255, 255));
		txtTableID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtTableID.setBounds(129, 42, 380, 30);
		pnlTableDetails.add(txtTableID);
		
		// Số lượng ghế
		lblNumberSeat = new JLabel("Số ghế");
		lblNumberSeat.setFont(new Font("Arial", 0, 14));
		lblNumberSeat.setBounds(14, 82, 115, 30);
		pnlTableDetails.add(lblNumberSeat);
		
		txtNumberSeat = new JSpinner();
		txtNumberSeat.setBackground(new Color(255, 255, 255));
		txtNumberSeat.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtNumberSeat.setBounds(129, 82, 380, 30);
		pnlTableDetails.add(txtNumberSeat);
		
		// Trạng thái
		lblTableStatus = new JLabel("Trạng thái");
		lblTableStatus.setFont(new Font("Arial", 0, 14));
		lblTableStatus.setBounds(14, 122, 115, 30);
		pnlTableDetails.add(lblTableStatus);
		
		comboTableStatus = new JComboBox<String>();
		comboTableStatus.setBackground(new Color(255, 255, 255));
		comboTableStatus.setBounds(129, 122, 380, 30);
		pnlTableDetails.add(comboTableStatus);
		
		comboTableStatus.addItem("Đang trống");
		comboTableStatus.addItem("Đang sử dụng");
		comboTableStatus.addItem("Đã đặt trước");
		
		btnUpdate = new JButton("Chỉnh Sửa");
		btnUpdate.setEnabled(false);
		btnUpdate.setBackground(new Color(255, 186, 105));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 14));
		btnUpdate.setBorder(null);
		btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnUpdate.addMouseListener(this);
		btnUpdate.setBounds(14, 540, 495, 30);
		pnlTableDetails.add(btnUpdate);
		
		btnSave = new JButton("Lưu");
		btnSave.setEnabled(false);
		btnSave.setBackground(new Color(0, 218, 205));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Arial", Font.BOLD, 14));
		btnSave.setBorder(null);
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSave.addMouseListener(this);
		btnSave.setBounds(14, 580, 495, 30);
		pnlTableDetails.add(btnSave);
		
		btnDelete = new JButton("Xóa");
		btnDelete.setEnabled(false);
		btnDelete.setBackground(new Color(255, 124, 124));
		btnDelete.setForeground(new Color(255, 255, 255));
		btnDelete.setFont(new Font("Arial", Font.BOLD, 14));
		btnDelete.setBorder(null);
		btnDelete.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnDelete.addMouseListener(this);
		btnDelete.setBounds(14, 500, 495, 30);
		pnlTableDetails.add(btnDelete);


		btnOrderTB = new JButton("Đặt bàn");
		btnOrderTB.setEnabled(false);
		btnOrderTB.setBackground(new Color(0, 0, 255));
		btnOrderTB.setForeground(new Color(255, 255, 255));
		btnOrderTB.setFont(new Font("Arial", Font.BOLD, 14));
		btnOrderTB.setBorder(null);
		btnOrderTB.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnOrderTB.addMouseListener(this);
		btnOrderTB.setBounds(14, 620, 495, 30);
		pnlTableDetails.add(btnOrderTB);


		DocDuLieuDatabaseVaoTable();
	}
	
	public void DocDuLieuDatabaseVaoTable() {
		ArrayList<Ban> list = banDao.getAllTbBan();
		int i=1;
		String trangThai="";
		for (Ban b : list) {
			if(b.getTrangThai()==1) {
				trangThai = "Đang trống";
			}else if(b.getTrangThai()==2) {
				trangThai = "Đang sử dụng";
			}else {
				trangThai = "Đã đặt trước";
			}
			modelTable.addRow(new Object[] {i++ , b.getMaBan(), b.getSoGhe(), trangThai});
		}
	}
	
	public void activeTableForm() {
		pnlTableDetails.setVisible(true);
		int row = tableTable.getSelectedRow();
		String maBan = modelTable.getValueAt(row, 1)+"";
		Ban ban = banDao.getOneBan(maBan);
		txtTableID.setText(ban.getMaBan());
		txtNumberSeat.setValue(ban.getSoGhe());
		if(ban.getTrangThai()==1) {
			comboTableStatus.setSelectedIndex(0);
		}else if(ban.getTrangThai()==2) {
			comboTableStatus.setSelectedIndex(1);
		}else{
			comboTableStatus.setSelectedIndex(2);
		}
	}
	
	public String newTableID() {
		String maSP = banDao.getMaBanLonNhat();
		int i = Integer.parseInt(maSP.substring(1));
		return "B" + ++i;
	}
	
	public void addNewTable() {
		String maBan = txtTableID.getText();
		int soGhe = (int) txtNumberSeat.getValue();
		int trangThai = comboTableStatus.getSelectedIndex();
		String tThai = "";
		Ban ban = new Ban(maBan, soGhe, trangThai);
		banDao.addNewBan(ban);
		if(ban.getTrangThai()==1) {
			tThai = "Đang trống";
		}else if(ban.getTrangThai()==2) {
			tThai = "Đang sử dụng";
		}else {
			tThai = "Đã đặt trước";
		}
		modelTable.addRow(new Object[] {tableTable.getRowCount()+1, ban.getMaBan(), ban.getSoGhe(), tThai});
		JOptionPane.showMessageDialog(this, "Đã thêm một bàn mới");
		btnSave.setEnabled(false);
		pnlContainTableList.setBounds(12, 67, 1869, 956);
		pnlTableDetails.setVisible(false);
	}
	
	public void updateTable() {
		int row = tableTable.getSelectedRow();
		String maBan = txtTableID.getText();
		int soGhe = (int) txtNumberSeat.getValue();
		int trangThai = comboTableStatus.getSelectedIndex();
		String tThai = "";
		Ban ban = new Ban(maBan, soGhe, trangThai);
		banDao.updateBan(ban);
		if(ban.getTrangThai()==0) {
			tThai = "Đang trống";
		}else if(ban.getTrangThai()==1) {
			tThai = "Đang sử dụng";
		}else {
			tThai = "Đã đặt trước";
		}
		modelTable.setValueAt(ban.getSoGhe(), row, 2);
		modelTable.setValueAt(tThai, row, 3);
		JOptionPane.showMessageDialog(this, "Đã cập nhật bàn");
	}
	
	public void deleteTable() {
		String maBan = txtTableID.getText();
		if(banDao.deleteBan(maBan)) {
			int row = tableTable.getSelectedRow();
			modelTable.removeRow(row);
			pnlContainTableList.setBounds(12, 67, 1869, 956);
			pnlTableDetails.setVisible(false);
		}else {
			JOptionPane.showMessageDialog(this, "Không thể xóa bàn này này");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(lblCreateTable)) {
			btnSave.setEnabled(true);
			btnUpdate.setVisible(false);
			btnDelete.setVisible(false);
			txtTableID.setText("");
			txtNumberSeat.setValue(1);;
			comboTableStatus.setSelectedIndex(0);
			pnlContainTableList.setBounds(12, 67, 1332, 956);
			pnlTableDetails.setVisible(true);
			txtTableID.setText(newTableID());
		}else if(obj.equals(tableTable)) {
			pnlContainTableList.setBounds(12, 67, 1332, 956);
			btnUpdate.setVisible(true);
			btnDelete.setVisible(true);
			btnUpdate.setEnabled(true);
			btnDelete.setEnabled(true);
			btnOrderTB.setEnabled(true);
			btnOrderTB.setEnabled(true);

			activeTableForm();
		}else if(obj.equals(lblCloseTable)) {
			pnlContainTableList.setBounds(12, 67, 1869, 956);
			pnlTableDetails.setVisible(false);
		}else if(obj.equals(btnSave)) {
			if(btnSave.isEnabled()==true) {
				if(btnUpdate.isVisible()==false) {
					System.out.println("them");
					addNewTable();
				}else {
					updateTable();
				}				
			}
		}else if(obj.equals(btnDelete)) {
			deleteTable();
		}else if(obj.equals(btnUpdate)) {
			updateTable();
			btnSave.setEnabled(true);
		}else if(obj.equals(btnOrderTB)){
			comboTableStatus.setSelectedIndex(2);
			updateTable();
			btnSave.setEnabled(true);
		}
		
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
