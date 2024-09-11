package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import javax.swing.table.DefaultTableModel;

import connectDB.ConnectDB;
import dao.CTHoaDonDAO;
import dao.SanPhamDAO;
import entity.CTHoaDon;
import entity.HoaDon;
import entity.SanPham;

public class ProductGUI extends JPanel implements MouseListener{
	private JPanel pnlProductList;
	private JTextField txtSearchProduct;
	private JLabel lblSearch;
	private JLabel lblCreateProduct;
	private JLabel lblExportExcel;
	private JLabel lblExportPDF;
	private JTable tableProduct;
	private JScrollPane scroll;
	private JPanel pnlContainProductList;
	private ProductDetailGUI productDetail;
	private DefaultTableModel modelProduct;
	private SanPhamDAO spDAO;

	public ProductGUI() {
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
		spDAO = new SanPhamDAO();
		
		this.setLayout(null);
		this.setBackground(new Color(255, 255, 255));
		
		lblCreateProduct = new JLabel();
		lblCreateProduct.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconCreate.png")));
		lblCreateProduct.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblCreateProduct.setText("true");
		lblCreateProduct.addMouseListener(this);
		lblCreateProduct.setBounds(25, 12, 50, 50);
		this.add(lblCreateProduct);
		
		lblExportPDF = new JLabel();
		lblExportPDF.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconPDF.png")));
		lblExportPDF.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblExportPDF.setBounds(116, 12, 50, 50);
		this.add(lblExportPDF);
		
		lblExportExcel = new JLabel();
		lblExportExcel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconExcel.png")));
		lblExportExcel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		lblExportExcel.setBounds(194, 12, 50, 50);
		this.add(lblExportExcel);
		
		pnlProductList = new JPanel();
		pnlProductList.setLayout(null);
		pnlProductList.setBackground(new Color(217, 217, 217));
		pnlProductList.setBounds(13, 78, 1895, 1035);
		this.add(pnlProductList);
		
		txtSearchProduct = new JTextField();
		txtSearchProduct.setBorder(new MatteBorder(1, 0, 1, 1, new Color(0, 0, 0)));
		txtSearchProduct.setBounds(48, 17, 759, 30);
		pnlProductList.add(txtSearchProduct);
		
		lblSearch = new JLabel();
		lblSearch.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getImage("img/IconSearch.png")));
		lblSearch.setBackground(new Color(255, 255, 255));
		lblSearch.setOpaque(true);
		lblSearch.setBorder(new MatteBorder(1, 1, 1, 0, new Color(0, 0, 0)));
		lblSearch.setBounds(18, 17, 30, 30);
		pnlProductList.add(lblSearch);
		
		pnlContainProductList = new JPanel();
		pnlContainProductList.setLayout(new BorderLayout());
		pnlContainProductList.setBackground(new Color(255, 255, 255));
		pnlContainProductList.setBounds(12, 67, 1869, 956);
		pnlProductList.add(pnlContainProductList);
		
		tableProduct = new JTable();
		tableProduct.setModel(new DefaultTableModel(
			new Object[][] {
				
			},
			new String[] {
				"STT","Mã sản phẩm","Tên sản phẩm","Loại","Đơn giá"
			}
		));
//		tableProduct.setEnabled(false);
		tableProduct.addMouseListener(this);
		modelProduct = (DefaultTableModel)tableProduct.getModel();
		DocDuLieuDatabaseVaoTable();
		
		scroll = new JScrollPane(tableProduct);
		pnlContainProductList.add(scroll);
		
		productDetail = new ProductDetailGUI();	
		productDetail.setVisible(false);
		productDetail.btnExit.addMouseListener(this);
		productDetail.btnSave.addMouseListener(this);
		productDetail.btnUpdate.addMouseListener(this);
		productDetail.btnDelete.addMouseListener(this);
		productDetail.btnChangeImg.addMouseListener(this);
	}
	
	public void DocDuLieuDatabaseVaoTable() {
		ArrayList<SanPham> list = spDAO.getAllTbSanPham();
		int i=1;
		for (SanPham sp : list) {
			modelProduct.addRow(new Object[] {i++ , sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia()});
		}
	}
	
	public void activeProductDetailsGUI() {
		productDetail.setVisible(true);
		int row = tableProduct.getSelectedRow();
		System.out.println(row);
		SanPham sanPham = spDAO.getOneSanPham(modelProduct.getValueAt(row, 1)+"");
		productDetail.txtProductID.setText(sanPham.getMaSP());
		productDetail.txtProductName.setText(sanPham.getTenSP());
		productDetail.txtProductUnitPrice.setText(sanPham.getDonGia()+"");
		
		if(sanPham.getLoaiSP().equalsIgnoreCase("Bánh")) {
			productDetail.comboProductType.setSelectedIndex(1);	
		}else{
			productDetail.comboProductType.setSelectedIndex(0);
		}
		if(sanPham.isTrangThai()==true) {
			productDetail.comboProductStatus.setSelectedIndex(0);
		}else {
			productDetail.comboProductStatus.setSelectedIndex(1);
		}
		productDetail.lblImg.setIcon(ResizeImg(sanPham.getHinhAnh()));	
	}
	
	public ImageIcon ResizeImg(String imgPath) {
		ImageIcon myImg = new ImageIcon(imgPath);
		Image img = myImg.getImage();
		Image newImg = img.getScaledInstance(productDetail.lblImg.getWidth(), productDetail.lblImg.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
	
	public String newProductID() {
		String maSP = spDAO.getMaSPLonNhat();
		int i = Integer.parseInt(maSP.substring(2));
		if(i<10) {
			return "SP0000" + ++i;
		}else if(i<100) {
			return "SP000" + ++i;
		}else if(i<1000) {
			return "SP00" + ++i;
		}else if(i<10000) {
			return "SP0" + ++i;
		}else {
			return "SP" + ++i;
		}
	}
	
	public boolean validData() {
		String tenSP = productDetail.txtProductName.getText();
		String donGia = productDetail.txtProductUnitPrice.getText();
		
		if(tenSP.equals("")) {
			JOptionPane.showMessageDialog(this, "Không để trống tên sản phẩm");
			return false;
		}else if(!tenSP.matches("[a-zA-ZÀ-Ỹà-ỹ\\\\s ]+")) {
			JOptionPane.showMessageDialog(this, "Tên sản phẩm chỉ chứa kí tự chữ");
			return false;
		}else if(!donGia.matches("[0-9]+")) {
			JOptionPane.showMessageDialog(this, "Đơn giá phải là số");
			return false;
		}else if(Double.parseDouble(donGia)<1000) {
			JOptionPane.showMessageDialog(this, "Đơn giá không bé hơn 1000");
			return false;
		}
		
		return true;
	}
	
	public String getRelativePath(String fullPath, String folderName) {
	    // Tìm vị trí của chuỗi folderName trong fullPath
	    int index = fullPath.indexOf(folderName);

	    // Kiểm tra nếu folderName không tồn tại trong fullPath
	    if (index == -1) {
	        return null; // hoặc bạn có thể trả về một giá trị mặc định hoặc thông báo lỗi khác
	    }

	    // Trả về chuỗi từ vị trí của folderName trở đi
	    return fullPath.substring(index);
	}
	
	private void changeImg() {
		try {
			JFileChooser file = new JFileChooser("img");
			file.setDialogTitle("Chọn ảnh sản phẩm");
			file.showOpenDialog(null);
			File fileImg = file.getSelectedFile();
			String fullPath = fileImg.getAbsolutePath();
			if(fullPath != null) {
				productDetail.imgPath = getRelativePath(fullPath,"img");
				productDetail.lblImg.setIcon(ResizeImg(productDetail.imgPath));	
			}
		} catch (Exception e) {
			
		}
	}
	
	public void addNewProduct() {
		if(validData()) {
			String maSP = productDetail.txtProductID.getText();
			String tenSP = productDetail.txtProductName.getText();
			double donGia = Double.parseDouble(productDetail.txtProductUnitPrice.getText());
			String loaiSP = productDetail.comboProductType.getItemAt(productDetail.comboProductType.getSelectedIndex());
			boolean trangThai = true;
			if(productDetail.comboProductStatus.getSelectedIndex()==1) {
				trangThai = false;
			}
			String hinhAnh = productDetail.imgPath;
			SanPham sp = new SanPham(maSP, tenSP, loaiSP, donGia, hinhAnh, trangThai);
			spDAO.addNewSP(sp);
			
			modelProduct.addRow(new Object[] {tableProduct.getRowCount(), sp.getMaSP(), sp.getTenSP(), sp.getLoaiSP(), sp.getDonGia()});
			productDetail.btnSave.setEnabled(false);
			productDetail.setVisible(false);
			
			productDetail.txtProductName.setFocusable(false);
			productDetail.txtProductUnitPrice.setFocusable(false);
			productDetail.comboProductType.setEnabled(false);
			productDetail.comboProductStatus.setEnabled(false);
		}
	}
	
	private void deleteProduct() {
		String maSP = productDetail.txtProductID.getText();
		if(spDAO.deleteSP(maSP)) {
			System.out.println(maSP);
			int row = tableProduct.getSelectedRow();
			modelProduct.removeRow(row);
			JOptionPane.showMessageDialog(this, "Đã xóa sản phẩm có mã "+ maSP);
		}else {
			JOptionPane.showMessageDialog(this, "Không thể xóa sản phẩm này");
		}
		
		productDetail.setVisible(false);
	}
	
	private void updateProduct() {
		if(validData()) {
			String maSP = productDetail.txtProductID.getText();
			String tenSP = productDetail.txtProductName.getText();
			double donGia = Double.parseDouble(productDetail.txtProductUnitPrice.getText());
			String loaiSP = productDetail.comboProductType.getItemAt(productDetail.comboProductType.getSelectedIndex());
			boolean trangThai = true;
			if(productDetail.comboProductStatus.getSelectedIndex()==1) {
				trangThai = false;
			}
			String hinhAnh = productDetail.imgPath;
			
			spDAO.updateSP(new SanPham(maSP, tenSP, loaiSP, donGia, hinhAnh, trangThai));
			JOptionPane.showMessageDialog(this, "Đã cập nhật sản phẩm có mã "+ maSP);
			
			productDetail.txtProductName.setFocusable(false);
			productDetail.txtProductUnitPrice.setFocusable(false);
			productDetail.comboProductType.setEnabled(false);
			productDetail.comboProductStatus.setEnabled(false);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(lblCreateProduct)) {
			if(lblCreateProduct.getText()=="true") {
				productDetail.btnSave.setEnabled(true);
				productDetail.btnDelete.setVisible(false);
				productDetail.btnUpdate.setVisible(false);
				productDetail.txtProductName.setFocusable(true);
				productDetail.txtProductUnitPrice.setFocusable(true);
				productDetail.comboProductType.setEnabled(true);
				productDetail.comboProductStatus.setEnabled(true);
				lblCreateProduct.setText("false");
				productDetail.setVisible(true);
				productDetail.txtProductID.setText(newProductID());
			}
		}else if(obj.equals(tableProduct)) {
			productDetail.btnDelete.setVisible(true);
			productDetail.btnUpdate.setVisible(true);
			productDetail.btnDelete.setEnabled(true);
			productDetail.btnUpdate.setEnabled(true);
			lblCreateProduct.setText("false");
			activeProductDetailsGUI();		
		}else if(obj.equals(productDetail.btnExit)) {
			productDetail.btnSave.setEnabled(false);
			productDetail.txtProductName.setFocusable(false);
			productDetail.txtProductUnitPrice.setFocusable(false);
			productDetail.comboProductType.setEnabled(false);
			productDetail.comboProductStatus.setEnabled(false);
			lblCreateProduct.setText("true");
			productDetail.setVisible(false);
		}else if(obj.equals(productDetail.btnSave)) {
			if(productDetail.btnUpdate.isVisible()==false) {
				addNewProduct();
			}else {
				updateProduct();
			}
		}else if(obj.equals(productDetail.btnDelete)) {
			deleteProduct();
		}else if(obj.equals(productDetail.btnUpdate)) {
			productDetail.txtProductName.setFocusable(true);
			productDetail.txtProductUnitPrice.setFocusable(true);
			productDetail.comboProductType.setEnabled(true);
			productDetail.comboProductStatus.setEnabled(true);
			productDetail.btnSave.setEnabled(true);
		}else if(obj.equals(productDetail.btnChangeImg)) {
			changeImg();
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
