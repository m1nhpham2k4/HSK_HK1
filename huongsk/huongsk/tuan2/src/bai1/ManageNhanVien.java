package bai1;

import java.util.ArrayList;

public class ManageNhanVien {
    private ArrayList<NhanVien> list;

    public ManageNhanVien() {
        list = new ArrayList<NhanVien>();
    }

    public boolean add(NhanVien ele) {
        if (list.contains(ele))
            return false;
        list.add(ele);
        return true;
    }

    private NhanVien getNVByMaNV(String maNV)
    {
        for (NhanVien nv : list) {
            if (nv.getMaNV().equals(maNV)) return nv;
        }
        return null;
    }

    public boolean update(String maNV, String ho, String ten, String phai, String tuoi, String tienLuong)
    {
        NhanVien nv = getNVByMaNV(maNV);
        if (nv == null) return false;
        nv.setHo(ho);
        nv.setTen(ten);
        nv.setTuoi(tuoi);
        nv.setPhai(phai);
        nv.setTienLuong(tienLuong);
        return true;
    }

    public boolean remove(String maNV)
    {
        NhanVien nv = getNVByMaNV(maNV);
        if (nv == null) return false;
        list.remove(nv);
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (NhanVien nv : list) {
            result += nv.toString() + "\n";
        }
        return result;
    }

}
