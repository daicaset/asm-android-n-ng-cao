package com.example.hp8440p.myapplication.Course.Fragment_Manage;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.hp8440p.myapplication.Course.Adapter.AdapterLichHoc;
import com.example.hp8440p.myapplication.Course.Adapter.AdapterMonHocDaDangky;
import com.example.hp8440p.myapplication.Course.Course_DataBase;
import com.example.hp8440p.myapplication.Course.Oject.LichHoc;
import com.example.hp8440p.myapplication.Course.Oject.MonHoc;
import com.example.hp8440p.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MonHocVaTKB_Fragment extends Fragment {
    ListView lv, lvLichHoc;
    Course_DataBase db;
    AdapterMonHocDaDangky adapter;
    LichHoc lichHoc1;
    public static List<LichHoc> TenMonHocList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_monhocvatkb, container, false);
        lv = view.findViewById(R.id.lvMonHocDaDangKy);
        lvLichHoc = view.findViewById(R.id.lvLichHoc);
        getDulieuMonHoc();
        TenMonHocList.clear();
        getMonHoc();

        return view;
    }

    public void getDulieuMonHoc() {
        db = new Course_DataBase(getActivity());
        adapter = new AdapterMonHocDaDangky(R.layout.item_monhoc, this, db.getData());
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }

    public void getDuLieuTKB(List<LichHoc>list)  {
        AdapterLichHoc adapter = new AdapterLichHoc(this, list, lvLichHoc);
        adapter.notifyDataSetChanged();
        lvLichHoc.setAdapter(adapter);
    }

    public List<LichHoc> getMonHoc() {
        db = new Course_DataBase(getActivity());
        String ngay = null,phong = null;
        Cursor cursor = db.getData("SELECT TenMonHoc FROM MonHoc");
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                   String Monhoc = cursor.getString(0);
                  Random random = new Random();
                  int n = 23+random.nextInt(7);
                  int p = random.nextInt(2);
                  int t = 10+random.nextInt(2);
                  phong = "30"+p;
                  ngay = n+"/"+t+"/"+"2018";
                  lichHoc1 = new LichHoc(ngay,phong,Monhoc,"7h30-9h30");
                  TenMonHocList.add(lichHoc1);
                  getDuLieuTKB(TenMonHocList);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return TenMonHocList;
    }

}
