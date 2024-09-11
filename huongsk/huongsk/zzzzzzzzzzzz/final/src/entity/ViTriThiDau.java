package entity;


import java.util.Objects;

public class ViTriThiDau {
    private String maViTri;
    private String tenViTri;

    public ViTriThiDau(String maViTri) {
        this.maViTri = maViTri;
    }
    public ViTriThiDau(){

    }

    public ViTriThiDau(String maViTri, String tenViTri) {
        this.maViTri = maViTri;
        this.tenViTri = tenViTri;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ViTriThiDau that = (ViTriThiDau) o;
        return Objects.equals(maViTri, that.maViTri);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maViTri);
    }
}
