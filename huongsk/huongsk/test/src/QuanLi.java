import java.io.Serializable;
import java.util.ArrayList;

public class QuanLi implements Serializable {
    private ArrayList<NhanVien> list;
    public QuanLi(){
        list= new ArrayList<NhanVien>();
    }

    public boolean add(NhanVien a){
        if(list.contains(a))
            return false;
        list.add(a);
        return true;
    }
    public NhanVien getNV(String maNV){
        for(NhanVien a:list){
            if(a.getMaNV().equals(maNV))
                return a;
        }
        return null;
    }
    public boolean update(String ho, String tenNV, String maNV, String tuoi, String luong, String gioiTinh){
        NhanVien nv = getNV(maNV);
        if(nv==null) return  false;
        nv.setHo(ho);
        nv.setTenNV(tenNV);
        nv.setTuoi(tuoi);
        nv.setGioiTinh(gioiTinh);
        nv.setLuong(luong);
        return true;
    }
    public boolean remove(String maNV){
        NhanVien nv= getNV(maNV);
        if(nv==null) return false;
        list.remove(nv);
        return true;
    }
    public String toString() {
        String result = "";
        for (NhanVien nv : list) {
            result += nv.toString() + "\n";
        }
        return result;
    }
}
