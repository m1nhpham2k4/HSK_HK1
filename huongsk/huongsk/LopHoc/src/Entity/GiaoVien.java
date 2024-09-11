package Entity;

import java.util.Objects;

public class GiaoVien {
	private String maGiaoVien;
	private String tenGiaoVien;
	public String getMaGiaoVien() {
		return maGiaoVien;
	}
	public void setMaGiaoVien(String maGiaoVien) {
		this.maGiaoVien = maGiaoVien;
	}
	public String getTenGiaoVien() {
		return tenGiaoVien;
	}
	public void setTenGiaoVien(String tenGiaoVien) {
		this.tenGiaoVien = tenGiaoVien;
	}
	@Override
	public int hashCode() {
		return Objects.hash(maGiaoVien);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		GiaoVien other = (GiaoVien) obj;
		return Objects.equals(maGiaoVien, other.maGiaoVien);
	}
	public GiaoVien(String maGiaoVien, String tenGiaoVien) {
		super();
		this.maGiaoVien = maGiaoVien;
		this.tenGiaoVien = tenGiaoVien;
	}
	public GiaoVien() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
