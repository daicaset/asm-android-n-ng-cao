package com.example.hp8440p.myapplication.Course.Fragment_Manage;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.hp8440p.myapplication.Course.Adapter.AdapterLichHoc;
import com.example.hp8440p.myapplication.Course.Adapter.AdapterLichThi;
import com.example.hp8440p.myapplication.Course.Course_DataBase;
import com.example.hp8440p.myapplication.Course.Oject.LichHoc;
import com.example.hp8440p.myapplication.Course.Oject.LichThi;
import com.example.hp8440p.myapplication.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class LichThi_Fragment extends Fragment {
    Course_DataBase db;
    LichThi lichThi;
    ListView lv;
    public static  List<LichThi> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_lichthi, container, false);
        lv = view.findViewById(R.id.lvLichThi);
        list.clear();
        getLichThi();



        return view ;
    }
    public List<LichThi> getLichThi() {
        Course_DataBase  db = new Course_DataBase(this.getActivity());
        String ngay = null,phong = null;
        Cursor cursor = db.getData("SELECT TenMonHoc FROM MonHoc");
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    String Monhoc = cursor.getString(0);
                    Random random = new Random();
                    int n = 20+random.nextInt(10);
                    int p = random.nextInt(2);
                    int t = 2+random.nextInt(5);
                    phong = "20"+p;
                    ngay = n+"/"+t+"/"+"2019";
                    lichThi = new LichThi(ngay,phong,Monhoc,"9h30-11h30");
                    list.add(lichThi);
                    getData(list);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
        return list;
    }
    public void getData(List<LichThi>list)  {
        AdapterLichThi adapter = new AdapterLichThi(this, list,lv );
        adapter.notifyDataSetChanged();
        lv.setAdapter(adapter);
    }
}
