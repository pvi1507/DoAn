/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author DANG
 */
public class MonAn {

    private int maMon;
    private String tenMon;
    private double donGia;
    private int maLoai;
    private String hinhAnh;
    private boolean trangThai;

    public MonAn() {
    }

    public MonAn(int maMon, String tenMon, double donGia, int maLoai, boolean trangThai, String hinhAnh) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.donGia = donGia;
        this.maLoai = maLoai;
        this.hinhAnh = hinhAnh;
        this.trangThai = trangThai;
    }

    public int getMaMon() {
        return maMon;
    }

    public void setMaMon(int maMon) {
        this.maMon = maMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(int maLoai) {
        this.maLoai = maLoai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
