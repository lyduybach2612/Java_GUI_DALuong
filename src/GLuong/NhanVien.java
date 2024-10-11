package GLuong;

public class NhanVien extends Person{

    private String diaChi;
    private Double luong;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, String diaChi, Double luong) {
        super(maNV, hoTen);
        this.diaChi = diaChi;
        this.luong = luong;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public Double getLuong() {
        return luong;
    }

    public void setLuong(Double luong) {
        this.luong = luong;
    }
}
