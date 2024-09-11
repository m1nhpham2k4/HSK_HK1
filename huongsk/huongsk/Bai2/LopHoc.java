package Bai2;

public class LopHoc {

	private String maLop;
	private String tenLop;
	private String giaoVienCN;
	
	

	public LopHoc() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public LopHoc(String maLop, String tenLop, String giaoVienCN) {
		super();
		setMaLop(maLop);
		setTenLop(tenLop);
		setGiaoVienCN(giaoVienCN);
	}



	public String getMaLop() {
		return this.maLop;
	}

	public void setMaLop(String maLop) {
		this.maLop = maLop;
	}

	public String getTenLop() {
		return this.tenLop;
	}

	public void setTenLop(String tenLop) {
		this.tenLop = tenLop;
	}

	public String getGiaoVienCN() {
		return this.giaoVienCN;
	}

	public void setGiaoVienCN(String giaoVienCN) {
		this.giaoVienCN = giaoVienCN;
	}



	@Override
	public String toString() {
		return "LopHoc [maLop=" + maLop + ", tenLop=" + tenLop + ", giaoVienCN=" + giaoVienCN + "]";
	}
	
	public Object[] getData() {
		return new Object[] {maLop, tenLop, giaoVienCN};
	}
	

}