package stt20_LeVuThanhDuong_22643441;

import java.io.Serializable;
import java.util.ArrayList;

public class NhanVien_Collection implements Serializable{
	private ArrayList<NhanVien> dsNV;
	
	public NhanVien_Collection() {
		dsNV = new ArrayList<NhanVien>();
	}
	
	public ArrayList<NhanVien> getDsNV() {
		return dsNV;
	}
	
	public NhanVien getElement(int index) {
		if(index <0 || index >dsNV.size())
			return null;
		return dsNV.get(index);
	}
	
	public int getSize() {
		return dsNV.size();
	}

	public boolean themNV(NhanVien nv) {
		if(dsNV.contains(nv))
			return false;
		return dsNV.add(nv);
	}
	
	public boolean xoaNV(String ma) {
		NhanVien nv = new NhanVien(ma);
		return dsNV.remove(nv);
	}
	
	public NhanVien timNV(String ma) {
		NhanVien nv = new NhanVien(ma);
		if(dsNV.contains(nv))
			return dsNV.get(dsNV.indexOf(nv));
		return null;
	}

	public int viTriNV(NhanVien nv) {
		return dsNV.indexOf(nv);
	}


	
	
}
