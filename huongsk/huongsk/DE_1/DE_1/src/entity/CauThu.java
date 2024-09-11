package entity;

import java.util.Objects;

public class CauThu {
	private String maCauThu;
	private String tenCauThu;
	private int tuoi;
	private ViTriThiDau viTri;
	
	
	public CauThu(String maCauThu, String tenCauThu, int tuoi, ViTriThiDau viTri) {
		this.maCauThu = maCauThu;
		this.tenCauThu = tenCauThu;
		this.tuoi = tuoi;
		this.viTri = viTri;
	}
	public CauThu(String maCauThu) {
		super();
		this.maCauThu = maCauThu;
	}
	
	public CauThu() {
		// TODO Auto-generated constructor stub
	}
	public String getMaCauThu() {
		return maCauThu;
	}
	public void setMaCauThu(String maCauThu) {
		this.maCauThu = maCauThu;
	}
	public String getTenCauThu() {
		return tenCauThu;
	}
	public void setTenCauThu(String tenCauThu) {
		this.tenCauThu = tenCauThu;
	}
	public int getTuoi() {
		return tuoi;
	}
	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}
	public ViTriThiDau getViTri() {
		return viTri;
	}
	public void setViTri(ViTriThiDau viTri) {
		this.viTri = viTri;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maCauThu);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CauThu other = (CauThu) obj;
		return Objects.equals(maCauThu, other.maCauThu);
	}
	
	
}
