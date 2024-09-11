package Entity;

import java.util.Objects;

public class LopHoc {
	private String maLop;
	private String tenLop;
	private GiaoVien giaoVien;
	private int siSo;
	public LopHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LopHoc(String maLop, String tenLop, GiaoVien giaoVien, int siSo) {
		super();
		this.maLop = maLop;
		this.tenLop = tenLop;
		this.giaoVien = giaoVien;
		this.siSo = siSo;
	}
	public String getMaLop() {
		return maLop;
	}
	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}
	public String getTenLop() {
		return tenLop;
	}
	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}
	public GiaoVien getGiaoVien() {
		return giaoVien;
	}
	public void setGiaoVien(GiaoVien giaoVien) {
		this.giaoVien = giaoVien;
	}
	public int getSiSo() {
		return siSo;
	}
	public void setSiSo(int siSo) {
		this.siSo = siSo;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maLop);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LopHoc other = (LopHoc) obj;
		return Objects.equals(maLop, other.maLop);
	}
	
}
