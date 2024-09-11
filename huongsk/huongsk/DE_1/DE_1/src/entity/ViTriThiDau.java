package entity;

public class ViTriThiDau {
	private String maViTri;
	private String tenViTri;
	
	public ViTriThiDau() {
		// TODO Auto-generated constructor stub
	}

	public ViTriThiDau(String maViTri, String tenViTri) {
		this.maViTri = maViTri;
		this.tenViTri = tenViTri;
	}

	public ViTriThiDau(String maViTri) {
		this.maViTri = maViTri;
	}

	public String getMaViTri() {
		return maViTri;
	}

	public void setMaViTri(String maViTri) {
		this.maViTri = maViTri;
	}

	public String getTenViTri() {
		return tenViTri;
	}

	public void setTenViTri(String tenViTri) {
		this.tenViTri = tenViTri;
	}
	
	
	
}
