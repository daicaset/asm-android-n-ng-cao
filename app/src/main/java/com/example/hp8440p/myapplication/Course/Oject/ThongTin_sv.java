package com.example.hp8440p.myapplication.Course.Oject;

public class ThongTin_sv {
    public ThongTin_sv() {
    }

    public String getID() {

        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getImgResuit() {
        return ImgResuit;
    }

    public void setImgResuit(String imgResuit) {
        ImgResuit = imgResuit;
    }

    public String getMaSv() {
        return MaSv;
    }

    public void setMaSv(String maSv) {
        MaSv = maSv;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        GioiTinh = gioiTinh;
    }

    public String getSoCmnd() {
        return SoCmnd;
    }

    public void setSoCmnd(String soCmnd) {
        SoCmnd = soCmnd;
    }

    public String getNganhHoc() {
        return NganhHoc;
    }

    public void setNganhHoc(String nganhHoc) {
        NganhHoc = nganhHoc;
    }

    public String getChuyenNganhHoc() {
        return ChuyenNganhHoc;
    }

    public void setChuyenNganhHoc(String chuyenNganhHoc) {
        ChuyenNganhHoc = chuyenNganhHoc;
    }

    public String getLop() {
        return Lop;
    }

    public void setLop(String lop) {
        Lop = lop;
    }

    public String getHoVaTenSv() {
        return HoVaTenSv;
    }

    public void setHoVaTenSv(String hoVaTenSv) {
        HoVaTenSv = hoVaTenSv;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        NgaySinh = ngaySinh;
    }

    public ThongTin_sv(String ID, String imgResuit, String maSv, String gioiTinh, String soCmnd, String nganhHoc, String chuyenNganhHoc, String lop, String hoVaTenSv, String ngaySinh) {

        this.ID = ID;
        ImgResuit = imgResuit;
        MaSv = maSv;
        GioiTinh = gioiTinh;
        SoCmnd = soCmnd;
        NganhHoc = nganhHoc;
        ChuyenNganhHoc = chuyenNganhHoc;
        Lop = lop;
        HoVaTenSv = hoVaTenSv;
        NgaySinh = ngaySinh;
    }

    //    ID INTEGER PRIMARY KEY AUTOINCREMENT,MaSv TEXT,GioiTinh TEXT,SoCmnd INTEGER,NganhHoc TEXT," +
//            "ChuyenNganhHoc TEXT,Lop TEXT,HoVaTenSv TEXT,NgaySinh TEXT
    String ID, ImgResuit, MaSv, GioiTinh, SoCmnd, NganhHoc, ChuyenNganhHoc, Lop, HoVaTenSv, NgaySinh;

}
