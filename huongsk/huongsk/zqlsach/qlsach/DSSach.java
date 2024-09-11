package qlsach;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DSSach implements Serializable{
	private List<Sach> dsSach;


	public DSSach(){
		dsSach = new ArrayList<Sach>();
	}
	public DSSach(List<Sach> dsSach) {
		this.dsSach = dsSach;
	}
	
	public boolean them(Sach s) {
		if(dsSach.contains(s)) {
			return false;
		}
		dsSach.add(s);
		return true;
	}

	public boolean capNhatSach(Sach sachNew) {
		Sach sachOld = new Sach(sachNew.getMa());
		if(dsSach.contains(sachOld)){
			sachOld = dsSach.get(dsSach.indexOf(sachOld));
			sachOld.setTua(sachNew.getTua());
			sachOld.setTacGia(sachNew.getTacGia());
			sachOld.setNamXB(sachNew.getNamXB());
			sachOld.setNhaXB(sachNew.getNhaXB());
			sachOld.setSoTrang(sachNew.getSoTrang());
			sachOld.setDonGia(sachNew.getDonGia());
			sachOld.setISBN(sachNew.getISBN());
			return true;
		}
		return false;
	}
	public boolean xoa(String ma) {
		for(Sach s :dsSach) {
			if(s.getMa().equalsIgnoreCase(ma)) {
				dsSach.remove(s);
				return true;
			}
		}
		return false;
	}

	
	public Sach getIndex(int index) {
		if(index < 0 && index >= dsSach.size()) {
			return null;
		}
		return dsSach.get(index);
	}
	public List<Sach> getList(){
		return dsSach;
	}
}
