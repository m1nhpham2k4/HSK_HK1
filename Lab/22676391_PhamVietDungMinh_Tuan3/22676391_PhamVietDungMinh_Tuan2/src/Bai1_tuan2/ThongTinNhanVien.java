package Bai1_tuan2;

import java.util.Objects;

public class ThongTinNhanVien {
	protected String maNV, ho, tennhanvien;
	protected boolean phai;
	protected int tuoi;
	protected double tienluong;
	
	
	public ThongTinNhanVien() {
		super();
	}
	public ThongTinNhanVien(String maNV, String ho, String tennhanvien, boolean phai, int tuoi,
			double tienluong) {
		super();
		this.maNV = maNV;
		this.ho = ho;
		this.tennhanvien = tennhanvien;
		this.phai = phai;
		this.tuoi = tuoi;
		this.tienluong = tienluong;
	}
	
	public ThongTinNhanVien(String maNV) {
		this(maNV,"","",true,0,0.0);
	}
	
	
	public String getMaNV() {
		return maNV;
	}
	
	
	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}
	public String getHo() {
		return ho;
	}
	public void setHo(String ho) {
		this.ho = ho;
	}
	public String getTennhanvien() {
		return tennhanvien;
	}
	public void setTennhanvien(String tennhanvien) {
		this.tennhanvien = tennhanvien;
	}
	public boolean getPhai() {
		return phai;
	}
	public void setPhai(boolean phai) {
		this.phai = phai;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public double getTienluong() {
		return tienluong;
	}
	public void setTienluong(double tienluong) {
		this.tienluong = tienluong;
	}

	
	
	
	@Override
	public int hashCode() {
		return Objects.hash(maNV);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ThongTinNhanVien other = (ThongTinNhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	
	

	
	

}
