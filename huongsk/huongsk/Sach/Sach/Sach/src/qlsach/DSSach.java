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
    public boolean xoa(String ma) {
        for(Sach s :dsSach) {
            if(s.getMa().equalsIgnoreCase(ma)) {
                dsSach.remove(s);
                return true;
            }
        }
        return false;
    }
    public Sach tim(String ma) {
        for(Sach s: dsSach) {
            if(s.getMa().equalsIgnoreCase(ma)) {
                return dsSach.get(dsSach.indexOf(s));
            }
        }
        return null;
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
