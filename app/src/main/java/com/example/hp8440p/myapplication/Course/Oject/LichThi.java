package com.example.hp8440p.myapplication.Course.Oject;

public class LichThi {
    String Ngay,Phong,Mon,Tgian;

    public LichThi() {
    }

    public String getNgay() {
        return Ngay;
    }
    public void setNgay(String ngay) {
        Ngay = ngay;
    }

    public String getPhong() {
        return Phong;
    }

    public void setPhong(String phong) {
        Phong = phong;
    }

    public String getMon() {
        return Mon;
    }

    public void setMon(String mon) {
        Mon = mon;
    }

    public String getTgian() {
        return Tgian;
    }

    public void setTgian(String tgian) {
        Tgian = tgian;
    }

    public LichThi(String ngay, String phong, String mon, String tgian) {

        Ngay = ngay;
        Phong = phong;
        Mon = mon;
        Tgian = tgian;
    }
}
