package stt20_LeVuThanhDuong_22643441;

import java.io.Serializable;
import java.util.Objects;


public class NhanVien implements Serializable{
	private String maNV;
	private String ho;
	private String ten;
	private int tuoi;
	private boolean phai;
	private double luong;
	
	public NhanVien() {
		
	}

	public NhanVien(String maNV, String ho, String ten, int tuoi, boolean phai, double luong) {
		this.maNV = maNV;
		this.ho = ho;
		this.ten = ten;
		this.tuoi = tuoi;
		this.phai = phai;
		this.luong = luong;
	}

	public NhanVien(String maNV) {
		this.maNV = maNV;
	}

	public String getMaNV() {
		return maNV;
	}


	public String getHo() {
		return ho;
	}

	public void setHo(String ho) {
		this.ho = ho;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public boolean isPhai() {
		return phai;
	}

	public void setPhai(boolean phai) {
		this.phai = phai;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
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
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNV, other.maNV);
	}
	
	
	
	
	
}
