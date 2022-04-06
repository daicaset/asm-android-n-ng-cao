package com.example.hp8440p.myapplication.Course;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.hp8440p.myapplication.Course.Fragment_Manage.DanhSach_Fragment;
import com.example.hp8440p.myapplication.Course.Fragment_Manage.ThongTin_Fragment;
import com.example.hp8440p.myapplication.Course.Oject.MonHoc;
import com.example.hp8440p.myapplication.Course.Oject.ThongTin_sv;

import java.util.ArrayList;
import java.util.List;

public class Course_DataBase extends SQLiteOpenHelper {
    public static SQLiteDatabase db;
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "CourseManage";
        private static final String MON_HOC = "CREATE TABLE MonHoc(ID INTEGER PRIMARY KEY AUTOINCREMENT, Nganh TEXT,ChuyenNganh TEXT,TenMonHoc Text,TinChi INTEGER)";
        private static final String THONGTIN_SV = "CREATE TABLE ThongTinSv(ID INTEGER PRIMARY KEY,ImgResult TEXT,MaSv TEXT,GioiTinh TEXT,SoCmnd INTEGER,NganhHoc TEXT," +
                "ChuyenNganhHoc TEXT,Lop TEXT,HoVaTenSv TEXT,NgaySinh TEXT)";

    public Course_DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, null, VERSION);
    }
    public Course_DataBase(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    public void QueryData(String sql) {
        SQLiteDatabase database = getWritableDatabase();
        database.execSQL(sql);
    }

    public Cursor getData(String sql) {
        SQLiteDatabase database = getReadableDatabase();
        return database.rawQuery(sql, null);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MON_HOC);
        db.execSQL(THONGTIN_SV);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    //----------Add------------//
    public int AddMonHoc(MonHoc monHoc){
//        ID INTEGER PRIMARY KEY AUTOINCREMENT, Nganh TEXT,ChuyenNganh TEXT,TenMonHoc Text,TinChi INTEGER
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("Nganh",monHoc.getNganh());
        values.put("ChuyenNganh",monHoc.getChuyenNganh());
        values.put("TenMonHoc",monHoc.getTenMonHoc());
        values.put("TinChi",monHoc.getTinChi());
        if (db.insert("MonHoc", null, values) == -1) {
            return -1;
        }
        return 1;
    }
    public int AddThongTinSv(ThongTin_sv thongtin){
//
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID",thongtin.getID());
        values.put("ImgResult",thongtin.getImgResuit());
        values.put("MaSv",thongtin.getMaSv());
        values.put("GioiTinh",thongtin.getGioiTinh());
        values.put("SoCmnd",thongtin.getSoCmnd());
        values.put("NganhHoc",thongtin.getNganhHoc());
        values.put("ChuyenNganhHoc",thongtin.getChuyenNganhHoc());
        values.put("Lop",thongtin.getLop());
        values.put("HoVaTenSv",thongtin.getHoVaTenSv());
        values.put("NgaySinh",thongtin.getNgaySinh());
        if (db.insert("ThongTinSv", null, values) == -1) {
            return -1;
        }
        return 1;
    }

    //--------Get------------//
    public List<MonHoc> getData() {
        List<MonHoc> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("MonHoc", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
           MonHoc monHoc = new MonHoc();
           monHoc.setIDAuto(cursor.getString(0));
           monHoc.setNganh(cursor.getString(1));
           monHoc.setChuyenNganh(cursor.getString(2));
           monHoc.setTenMonHoc(cursor.getString(3));
           monHoc.setTinChi(cursor.getString(4));
            list.add(monHoc);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }
    public List<ThongTin_sv> getDataThongTinSv(){
//        ID INTEGER PRIMARY KEY AUTOINCREMENT,MaSv TEXT,GioiTinh TEXT,SoCmnd INTEGER,NganhHoc TEXT," +
////            "ChuyenNganhHoc TEXT,Lop TEXT,HoVaTenSv TEXT,NgaySinh TEXT
        List<ThongTin_sv> list = new ArrayList<>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.query("ThongTinSv", null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            ThongTin_sv thongtin = new ThongTin_sv();
            thongtin.setID(cursor.getString(0));
            thongtin.setImgResuit(cursor.getString(1));
            thongtin.setMaSv(cursor.getString(2));
            thongtin.setGioiTinh(cursor.getString(3));
            thongtin.setSoCmnd(cursor.getString(4));
            thongtin.setNganhHoc(cursor.getString(5));
            thongtin.setChuyenNganhHoc(cursor.getString(6));
            thongtin.setLop(cursor.getString(7));
            thongtin.setHoVaTenSv(cursor.getString(8));
            thongtin.setNganhHoc(cursor.getString(9));
            list.add(thongtin);
            cursor.moveToNext();
        }
        cursor.close();
        return list;
    }

}
