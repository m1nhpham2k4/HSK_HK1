package Bai2;

public class SinhVien {

	private String maSV;
	private String hoTen;
	private String diaChi;
	private String email;
	private String maLop;
	
	public SinhVien() {
		// TODO Auto-generated constructor stub
	}
	
	
	

	public SinhVien(String maSV, String hoTen, String diaChi, String email, String maLop) {
		super();
		setMaSV(maSV);
		setHoTen(hoTen);
		setDiaChi(diaChi);
		setEmail(email);
		setMaLop(maLop);
	}




	public String getMaSV() {
		return this.maSV;
	}

	public void setMaSV(String maSV) {
		this.maSV = maSV;
	}

	public String getHoTen() {
		return this.hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getDiaChi() {
		return this.diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMaLop() {
		return this.maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

}