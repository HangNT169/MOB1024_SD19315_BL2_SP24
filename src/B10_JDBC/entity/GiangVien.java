/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package B10_JDBC.entity;

/**
 *
 * @author hangnt
 */
public class GiangVien {

    private String maGV;
    private String ten;
    private String loai;
    private int tuoi;
    private int bac;
    private boolean gioiTinh;

    public GiangVien(String maGV, String ten, String loai, int tuoi, int bac, boolean gioiTinh) {
        this.maGV = maGV;
        this.ten = ten;
        this.loai = loai;
        this.tuoi = tuoi;
        this.bac = bac;
        this.gioiTinh = gioiTinh;
    }

    public GiangVien() {
    }

    public String getMaGV() {
        return maGV;
    }

    public void setMaGV(String maGV) {
        this.maGV = maGV;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public int getBac() {
        return bac;
    }

    public void setBac(int bac) {
        this.bac = bac;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

}
