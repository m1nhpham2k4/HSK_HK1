package Bai1_tuan2;

import java.util.ArrayList;

public class DanhSachNhanVien {
	private ArrayList<ThongTinNhanVien> nv;

	public DanhSachNhanVien(ArrayList<ThongTinNhanVien> nv) {
		this.nv = nv;
	}
	
	public boolean themNV(ThongTinNhanVien nvien) {
		if(nv.contains(nvien))
			return false;
		nv.add(nvien);
		return true;
	}
	
	public boolean removeNV(String maNV) {
		ThongTinNhanVien nvien = new ThongTinNhanVien(maNV);
		if(nv.contains(nvien)) {
			nv.remove(nvien);
			return true;
		}
		return false;
		
	}
	
	
	public ThongTinNhanVien timkiem(String maNV) {
		ThongTinNhanVien nvien = new ThongTinNhanVien(maNV);
		if(nv.contains(nvien)) {
			return	nv.get(nv.indexOf(nvien));
		}
		return null;
		
	}
	public ArrayList<ThongTinNhanVien> getList(){
		return nv;
	}
}
