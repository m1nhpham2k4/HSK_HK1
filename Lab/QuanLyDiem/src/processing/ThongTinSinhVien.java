package processing;

public class ThongTinSinhVien {
	protected String sobaodanh, hoten, dienthoai;
	double diemlythuyet, diemthuchanh;
	public String getSobaodanh() {
		return sobaodanh;
	}
	public void setSobaodanh(String sobaodanh) {
		this.sobaodanh = sobaodanh;
	}
	public String getHoten() {
		return hoten;
	}
	public void setHoten(String hoten) {
		this.hoten = hoten;
	}
	public String getDienthoai() {
		return dienthoai;
	}
	public void setDienthoai(String dienthoai) {
		this.dienthoai = dienthoai;
	}
	public double getDiemlythuyet() {
		return diemlythuyet;
	}
	public void setDiemlythuyet(double diemlythuyet) {
		this.diemlythuyet = diemlythuyet;
	}
	public double getDiemthuchanh() {
		return diemthuchanh;
	}
	public void setDiemthuchanh(double diemthuchanh) {
		this.diemthuchanh = diemthuchanh;
	}
	public ThongTinSinhVien(String sobaodanh, String hoten, String dienthoai, double diemlythuyet,
			double diemthuchanh) {
		super();
		this.sobaodanh = sobaodanh;
		this.hoten = hoten;
		this.dienthoai = dienthoai;
		this.diemlythuyet = diemlythuyet;
		this.diemthuchanh = diemthuchanh;
	}
	
}
