package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Image;
import java.awt.Panel;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;

import connectDB.ConnectDB;
import dao.NhanVienDAO;
import entity.NhanVien;

public class AccountGUI extends JPanel implements MouseListener, FocusListener{
	private JPanel pnlAvatar;
	private JLabel lblAvatar;
	private JLabel lblChangeAvatar;
	private JButton btnChangeAvatar;
	private JLabel lblEmployeeName;
	private JTextField txtEmployeeName;
	private JLabel lblEmployeePhone;
	private JTextField txtEmployeePhone;
	private JLabel lblEmployeeBirthday;
	private JTextField txtEmployeeBirthday;
	private JLabel lblEmployeeAddress;
	private JTextField txtEmployeeAddress;
	private JButton btnUpdate;
	private JButton btnSave;
	private JButton btnChangePass;
	private JButton btnCancel;
	private JButton btnExit;
	private JDialog changePass;
	private JPanel pnlContentDialog;
	private JPasswordField txtOldPassword;
	private JPanel pnliconOldPasswordVisibility;
	private JLabel iconOldPasswordVisibility;
	private JLabel lblOldPass;
	private JLabel lblNewPass;
	private JPasswordField txtNewPassword;
	private JPanel pnliconNewPasswordVisibility;
	private JLabel iconNewPasswordVisibility;
	private JLabel lblConfirmPass;
	private JPasswordField txtConfirmPassword;
	private JPanel pnliconConfirmPasswordVisibility;
	private JLabel iconConfirmPasswordVisibility;
	private JButton btnSavePass;
	private JButton btnExitPass;
	private NhanVienDAO nvDao;
	private JLabel lblEmployeeID;
	private JTextField txtEmployeeID;
	private String path;

	public AccountGUI() {
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
		nvDao = new NhanVienDAO();
		
		this.setLayout(null);
		this.setBackground(new Color(255, 255, 255));
		
		// avatar
		pnlAvatar = new JPanel();
		pnlAvatar.setLayout(null);
		pnlAvatar.setBackground(new Color(217, 217, 217));
		pnlAvatar.setBounds(146, 84, 179, 228);
		this.add(pnlAvatar);
		
		lblAvatar = new JLabel();
		lblAvatar.setBounds(0, 0, 179, 228);
		pnlAvatar.add(lblAvatar);
		
		lblChangeAvatar = new JLabel("Đổi ảnh:");
		lblChangeAvatar.setBounds(146, 318, 89, 15);
		this.add(lblChangeAvatar);
		
		btnChangeAvatar = new JButton("Chọn file");
		btnChangeAvatar.setBackground(new Color(217, 217, 217));
		btnChangeAvatar.setBorder(null);
		btnChangeAvatar.addMouseListener(this);
		btnChangeAvatar.setBounds(241, 318, 84, 20);
		this.add(btnChangeAvatar);
		
		// Mã nhân viên
		lblEmployeeID = new JLabel("Mã nhân viên");
		lblEmployeeID.setFont(new Font("Arial", 0, 14));
		lblEmployeeID.setBounds(558, 84, 100, 30);
		this.add(lblEmployeeID);
		
		txtEmployeeID = new JTextField();
		txtEmployeeID.setFocusable(false);
		txtEmployeeID.setBackground(new Color(255, 255, 255));
		txtEmployeeID.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtEmployeeID.setBounds(705, 84, 695, 30);
		this.add(txtEmployeeID);
		
		// Tên nhân viên
		lblEmployeeName = new JLabel("Họ & Tên");
		lblEmployeeName.setFont(new Font("Arial", 0, 14));
		lblEmployeeName.setBounds(558, 124, 100, 30);
		this.add(lblEmployeeName);
		
		txtEmployeeName = new JTextField();
		txtEmployeeName.setFocusable(false);
		txtEmployeeName.setBackground(new Color(255, 255, 255));
		txtEmployeeName.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtEmployeeName.setBounds(705, 124, 695, 30);
		this.add(txtEmployeeName);
		
		// Số điện thoại
		lblEmployeePhone = new JLabel("Số điện thoại");
		lblEmployeePhone.setFont(new Font("Arial", 0, 14));
		lblEmployeePhone.setBounds(558, 164, 100, 30);
		this.add(lblEmployeePhone);
		
		txtEmployeePhone = new JTextField();
		txtEmployeePhone.setFocusable(false);
		txtEmployeePhone.setBackground(new Color(255, 255, 255));
		txtEmployeePhone.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtEmployeePhone.setBounds(705, 164, 695, 30);
		this.add(txtEmployeePhone);
		
		// Ngày sinh nhân viên
		lblEmployeeBirthday = new JLabel("Ngày sinh");
		lblEmployeeBirthday.setFont(new Font("Arial", 0, 14));
		lblEmployeeBirthday.setBounds(558, 204, 100, 30);
		this.add(lblEmployeeBirthday);
		
		txtEmployeeBirthday = new JTextField();
		txtEmployeeBirthday.setFocusable(false);
		txtEmployeeBirthday.setBackground(new Color(255, 255, 255));
		txtEmployeeBirthday.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtEmployeeBirthday.setBounds(705, 204, 695, 30);
		this.add(txtEmployeeBirthday);
		
		// Địa chỉ nhân viên
		lblEmployeeAddress = new JLabel("Địa chỉ");
		lblEmployeeAddress.setFont(new Font("Arial", 0, 14));
		lblEmployeeAddress.setBounds(558, 244, 100, 30);
		this.add(lblEmployeeAddress);
		
		txtEmployeeAddress = new JTextField();
		txtEmployeeAddress.setFocusable(false);
		txtEmployeeAddress.setBackground(new Color(255, 255, 255));
		txtEmployeeAddress.setBorder(new MatteBorder(0, 0, 1, 0, new Color(184, 184, 184)));
		txtEmployeeAddress.setBounds(705, 244, 695, 30);
		this.add(txtEmployeeAddress);
		
		btnUpdate = new JButton("Chỉnh Sửa");
		btnUpdate.setBackground(new Color(255, 186, 105));
		btnUpdate.setForeground(new Color(255, 255, 255));
		btnUpdate.setFont(new Font("Arial", Font.BOLD, 14));
		btnUpdate.setBorder(null);
		btnUpdate.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnUpdate.addMouseListener(this);
		btnUpdate.setBounds(146, 1061, 200, 30);
		this.add(btnUpdate);
		
		btnSave = new JButton("Lưu");
		btnSave.setBackground(new Color(0, 218, 205));
		btnSave.setForeground(new Color(255, 255, 255));
		btnSave.setFont(new Font("Arial", Font.BOLD, 14));
		btnSave.setBorder(null);
		btnSave.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSave.addMouseListener(this);
		btnSave.setBounds(358, 1061, 200, 30);
		this.add(btnSave);
		
		btnChangePass = new JButton("Đổi password");
		btnChangePass.setBackground(new Color(2, 92, 0));
		btnChangePass.setForeground(new Color(255, 255, 255));
		btnChangePass.setFont(new Font("Arial", Font.BOLD, 14));
		btnChangePass.setBorder(null);
		btnChangePass.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnChangePass.addMouseListener(this);
		btnChangePass.setBounds(570, 1061, 200, 30);
		this.add(btnChangePass);
		
		btnCancel = new JButton("Hủy");
		btnCancel.setBackground(new Color(255, 124, 124));
		btnCancel.setForeground(new Color(255, 255, 255));
		btnCancel.setFont(new Font("Arial", Font.BOLD, 14));
		btnCancel.setBorder(null);
		btnCancel.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnCancel.addMouseListener(this);
		btnCancel.setBounds(782, 1061, 200, 30);
		this.add(btnCancel);
		btnCancel.setVisible(false);
		
		btnExit = new JButton("Thoát");
		btnExit.setBackground(new Color(217, 217, 217));
		btnExit.setBorder(null);
		btnExit.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnExit.setBounds(1782, 1061, 100, 30);
		this.add(btnExit);
		
		// change pass
		changePass = new JDialog();
		changePass.setSize(693,358);
		changePass.setUndecorated(true);
		changePass.setModal(true);
		changePass.setLocationRelativeTo(null);
		
		pnlContentDialog = new JPanel();
		pnlContentDialog.setBorder(new MatteBorder(1, 1, 1, 1, new Color(ABORT)));
		pnlContentDialog.setLayout(null);
		pnlContentDialog.setBackground(new Color(255, 255, 255));
		changePass.setContentPane(pnlContentDialog);
		
		// old pass
		lblOldPass = new JLabel("Old password");
		lblOldPass.setFont(new Font("Arial", Font.BOLD, 16));
		lblOldPass.setBounds(9, 27, 169, 30);
		pnlContentDialog.add(lblOldPass);
		
		txtOldPassword = new JPasswordField();
		txtOldPassword.setFont(new Font("Arial", 0, 16));
		txtOldPassword.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		txtOldPassword.setBackground(new Color(255, 255, 255));
		txtOldPassword.addFocusListener(this);
		txtOldPassword.setBounds(178, 27, 444, 30);
		pnlContentDialog.add(txtOldPassword);
		
		pnliconOldPasswordVisibility = new JPanel();
		pnliconOldPasswordVisibility.setLayout(null);
		pnliconOldPasswordVisibility.setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
		pnliconOldPasswordVisibility.setBackground(new Color(255, 255, 255));
		pnliconOldPasswordVisibility.setBounds(622, 27, 30, 30);
		pnlContentDialog.add(pnliconOldPasswordVisibility);
		
		iconOldPasswordVisibility = new JLabel();
		iconOldPasswordVisibility.setCursor(new Cursor(Cursor.HAND_CURSOR));
		iconOldPasswordVisibility.setName("Hide");
		iconOldPasswordVisibility.addMouseListener(this);
		iconOldPasswordVisibility.setBounds(7, 7, 38, 38);
		pnliconOldPasswordVisibility.add(iconOldPasswordVisibility);
		
		// new pass
		lblNewPass = new JLabel("New password");
		lblNewPass.setFont(new Font("Arial", Font.BOLD, 16));
		lblNewPass.setBounds(9, 109, 169, 30);
		pnlContentDialog.add(lblNewPass);
		
		txtNewPassword = new JPasswordField();
		txtNewPassword.setFont(new Font("Arial", 0, 16));
		txtNewPassword.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		txtNewPassword.setBackground(new Color(255, 255, 255));
		txtNewPassword.addFocusListener(this);
		txtNewPassword.setBounds(178, 109, 444, 30);
		pnlContentDialog.add(txtNewPassword);
		
		pnliconNewPasswordVisibility = new JPanel();
		pnliconNewPasswordVisibility.setLayout(null);
		pnliconNewPasswordVisibility.setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
		pnliconNewPasswordVisibility.setBackground(new Color(255, 255, 255));
		pnliconNewPasswordVisibility.setBounds(622, 109, 30, 30);
		pnlContentDialog.add(pnliconNewPasswordVisibility);
		
		iconNewPasswordVisibility = new JLabel();
		iconNewPasswordVisibility.setCursor(new Cursor(Cursor.HAND_CURSOR));
		iconNewPasswordVisibility.setName("Hide");
		iconNewPasswordVisibility.addMouseListener(this);
		iconNewPasswordVisibility.setBounds(7, 7, 38, 38);
		pnliconOldPasswordVisibility.add(iconNewPasswordVisibility);
		
		// confirm pass
		lblConfirmPass = new JLabel("Confirm password");
		lblConfirmPass.setFont(new Font("Arial", Font.BOLD, 16));
		lblConfirmPass.setBounds(9, 201, 169, 30);
		pnlContentDialog.add(lblConfirmPass);
		
		txtConfirmPassword = new JPasswordField();
		txtConfirmPassword.setFont(new Font("Arial", 0, 16));
		txtConfirmPassword.setBorder(new MatteBorder(1, 1, 1, 0, Color.BLACK));
		txtConfirmPassword.setBackground(new Color(255, 255, 255));
		txtConfirmPassword.addFocusListener(this);
		txtConfirmPassword.setBounds(178, 201, 444, 30);
		pnlContentDialog.add(txtConfirmPassword);
		
		pnliconConfirmPasswordVisibility = new JPanel();
		pnliconConfirmPasswordVisibility.setLayout(null);
		pnliconConfirmPasswordVisibility.setBorder(new MatteBorder(1, 0, 1, 1, Color.BLACK));
		pnliconConfirmPasswordVisibility.setBackground(new Color(255, 255, 255));
		pnliconConfirmPasswordVisibility.setBounds(622, 201, 30, 30);
		pnlContentDialog.add(pnliconConfirmPasswordVisibility);
		
		iconConfirmPasswordVisibility = new JLabel();
		iconConfirmPasswordVisibility.setCursor(new Cursor(Cursor.HAND_CURSOR));
		iconConfirmPasswordVisibility.setName("Hide");
		iconConfirmPasswordVisibility.addMouseListener(this);
		iconConfirmPasswordVisibility.setBounds(7, 7, 38, 38);
		pnliconOldPasswordVisibility.add(iconConfirmPasswordVisibility);
		
		btnSavePass = new JButton("Lưu");
		btnSavePass.setBackground(new Color(0, 218, 205));
		btnSavePass.setForeground(new Color(255, 255, 255));
		btnSavePass.setFont(new Font("Arial", Font.BOLD, 24));
		btnSavePass.setBorder(null);
		btnSavePass.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnSavePass.addMouseListener(this);
		btnSavePass.setBounds(17, 293, 490, 50);
		pnlContentDialog.add(btnSavePass);
		
		btnExitPass = new JButton("Hủy");
		btnExitPass.setBackground(new Color(217, 217, 217));
		btnExitPass.setFont(new Font("Arial", Font.BOLD, 24));
		btnExitPass.setBorder(null);
		btnExitPass.setCursor(new Cursor(Cursor.HAND_CURSOR));
		btnExitPass.addMouseListener(this);
		btnExitPass.setBounds(518, 293, 156, 50);
		pnlContentDialog.add(btnExitPass);
		
		changePass.setVisible(false);
		
		loadInfo();
	}
	
	// Resize lại ảnh đại diện
	public ImageIcon ResizeImg(String imgPath) {
		ImageIcon myImg = new ImageIcon(imgPath);
		Image img = myImg.getImage();
		Image newImg = img.getScaledInstance(lblAvatar.getWidth(), lblAvatar.getHeight(), Image.SCALE_SMOOTH);
		ImageIcon image = new ImageIcon(newImg);
		return image;
	}
	
	public void loadInfo() {
		NhanVien nv = LoginGUI.nv;
		if(nv!=null) {
			txtEmployeeID.setText(nv.getMaNV());
			txtEmployeeName.setText(nv.getTenNV());
			txtEmployeeAddress.setText(nv.getDiaChi());
			//		txtEmployeeEmail.setText(nv.gete);
			txtEmployeePhone.setText(nv.getSoDT());
			txtEmployeeBirthday.setText(nv.getNgaySinh()+"");
			lblAvatar.setIcon(ResizeImg(nv.getHinhAnh()));
		}
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
	
	public void changeAvatar() {
		try {
			JFileChooser file = new JFileChooser("img");
			file.setDialogTitle("Chọn ảnh đại diện");
			file.showOpenDialog(null);
			File fileImg = file.getSelectedFile();
			String fullPath = fileImg.getAbsolutePath();
			if(fullPath != null) {
				path = getRelativePath(fullPath,"img");
				lblAvatar.setIcon(ResizeImg(path));	
			}
		} catch (Exception e) {
			
		}
	}
	
	public void saveChange() {
		String maNV = txtEmployeeID.getText();
		String hoTen = txtEmployeeName.getText();
		String soDT = txtEmployeePhone.getText();
		String diaChi = txtEmployeeAddress.getText();
		LocalDate ngaySinh = LocalDate.parse(txtEmployeeBirthday.getText());
		String hinhAnh = path;
		
		nvDao.updateNV(new NhanVien(maNV, hoTen, soDT, ngaySinh, diaChi, LoginGUI.nv.getMatKhau(), hinhAnh));
		
		txtEmployeeName.setFocusable(false);
		txtEmployeePhone.setFocusable(false);
		txtEmployeeAddress.setFocusable(false);
		txtEmployeeBirthday.setFocusable(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object obj = e.getSource();
		
		if(obj.equals(btnUpdate)) {
			txtEmployeeName.setFocusable(true);
			txtEmployeePhone.setFocusable(true);
			txtEmployeeAddress.setFocusable(true);
			txtEmployeeBirthday.setFocusable(true);
			
			btnUpdate.setEnabled(false);
			btnUpdate.setBackground(new Color(217, 217, 217));
			btnCancel.setVisible(true);
		}else if(obj.equals(btnCancel)) {
			txtEmployeeName.setFocusable(false);
			txtEmployeePhone.setFocusable(false);
			txtEmployeeAddress.setFocusable(false);
			txtEmployeeBirthday.setFocusable(false);
			
			btnUpdate.setEnabled(true);
			btnUpdate.setBackground(new Color(255, 186, 105));
			btnCancel.setVisible(false);
		}else if(obj.equals(btnSave)) {
			saveChange();
			btnUpdate.setEnabled(true);
			btnUpdate.setBackground(new Color(255, 186, 105));
			btnCancel.setVisible(false);
		}else if(obj.equals(btnChangePass)) {
			changePass.setVisible(true);
		}else if(obj.equals(btnExitPass)) {
			changePass.setVisible(false);
		}else if(obj.equals(btnSavePass)) {
			changePass.setVisible(false);
		}else if(obj.equals(btnChangeAvatar)) {
			changeAvatar();
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

	@Override
	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void focusLost(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}
}
