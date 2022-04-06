package com.example.hp8440p.myapplication.Course.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.hp8440p.myapplication.Course.Fragment_Manage.LichThi_Fragment;
import com.example.hp8440p.myapplication.Course.Fragment_Manage.MonHocVaTKB_Fragment;
import com.example.hp8440p.myapplication.Course.Oject.LichHoc;
import com.example.hp8440p.myapplication.Course.Oject.LichThi;
import com.example.hp8440p.myapplication.R;

import java.util.List;

public class AdapterLichThi extends BaseAdapter {
    LichThi_Fragment mContext;
    List<LichThi> list;
    ListView lv;

    public AdapterLichThi(LichThi_Fragment mContext, List<LichThi> list, ListView lv) {
        this.mContext = mContext;
        this.list = list;
        this.lv = lv;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public class ViewHoder {
        TextView Ngay, Phong, Mon, Tgian;
        ListView lv;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHoder hoder;
        if (view == null) {
            hoder = new ViewHoder();
            LayoutInflater inflater = (LayoutInflater) mContext.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_lichhoc, null);
            hoder.Ngay = view.findViewById(R.id.tvNgay);
            hoder.Phong = view.findViewById(R.id.tvPhong);
            hoder.Mon = view.findViewById(R.id.tvMon);
            hoder.Tgian = view.findViewById(R.id.tvThoiGian);
            hoder.lv = view.findViewById(R.id.lvLichHoc);
            view.setTag(hoder);
        } else
            hoder = (ViewHoder) view.getTag();
        LichThi lichThi = (LichThi) list.get(i);
        hoder.Ngay.setText(lichThi.getNgay());
        hoder.Phong.setText(lichThi.getPhong());
        hoder.Mon.setText(lichThi.getMon());
        hoder.Tgian.setText(lichThi.getTgian());
        return view;
    }
}
