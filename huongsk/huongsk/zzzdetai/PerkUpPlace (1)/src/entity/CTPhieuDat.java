package entity;

import java.time.LocalDate;

public class CTPhieuDat {
    private PhieuDat maPhieu;
    private Ban maBan;
    private LocalDate ngayDat;

    public CTPhieuDat(PhieuDat maPhieu, Ban maBan) {
        this.maPhieu = maPhieu;
        this.maBan = maBan;
    }

    public CTPhieuDat(PhieuDat maPhieu, Ban maBan, LocalDate ngayDat) {
        this.maPhieu = maPhieu;
        this.maBan = maBan;
        this.ngayDat = ngayDat;
    }


}
