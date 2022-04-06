package com.example.hp8440p.myapplication.Course.Oject;

public class MonHoc {
//    ID INTEGER PRIMARY KEY AUTOINCREMENT, Nganh TEXT,ChuyenNganh TEXT,TenMonHoc Text,TinChi INTEGER
    String Nganh,ChuyenNganh,TenMonHoc,IDAuto,TinChi;


    public MonHoc() {
    }

    public String getNganh() {

        return Nganh;
    }

    public void setNganh(String nganh) {
        Nganh = nganh;
    }

    public String getChuyenNganh() {
        return ChuyenNganh;
    }

    public void setChuyenNganh(String chuyenNganh) {
        ChuyenNganh = chuyenNganh;
    }

    public String getTenMonHoc() {
        return TenMonHoc;
    }

    public void setTenMonHoc(String tenMonHoc) {
        TenMonHoc = tenMonHoc;
    }

    public String getIDAuto() {
        return IDAuto;
    }

    public void setIDAuto(String IDAuto) {
        this.IDAuto = IDAuto;
    }

    public String getTinChi() {
        return TinChi;
    }

    public void setTinChi(String tinChi) {
        TinChi = tinChi;
    }

    public MonHoc(String nganh, String chuyenNganh, String tenMonHoc, String IDAuto, String tinChi) {

        Nganh = nganh;
        ChuyenNganh = chuyenNganh;
        TenMonHoc = tenMonHoc;
        this.IDAuto = IDAuto;
        TinChi = tinChi;
    }
}
