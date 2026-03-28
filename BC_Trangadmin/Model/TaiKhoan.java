package Model;

public class TaiKhoan {

    private int maTK;
    private String tenDangNhap;
    private String matKhau;
    private String tenVaiTro;

    public TaiKhoan(int maTK, String tenDangNhap, String tenVaiTro) {
        this.maTK = maTK;
        this.tenDangNhap = tenDangNhap;
        this.tenVaiTro = tenVaiTro;
    }

    public int getMaTK() {
        return maTK;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public String getTenVaiTro() {
        return tenVaiTro;
    }
}