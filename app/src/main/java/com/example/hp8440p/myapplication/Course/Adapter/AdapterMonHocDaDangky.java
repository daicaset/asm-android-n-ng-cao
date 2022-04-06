package com.example.hp8440p.myapplication.Course.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp8440p.myapplication.Course.Course_DataBase;
import com.example.hp8440p.myapplication.Course.Fragment_Manage.LichThi_Fragment;
import com.example.hp8440p.myapplication.Course.Fragment_Manage.MonHocVaTKB_Fragment;
import com.example.hp8440p.myapplication.Course.Oject.MonHoc;
import com.example.hp8440p.myapplication.R;

import java.util.List;

public class AdapterMonHocDaDangky extends BaseAdapter {

    public AdapterMonHocDaDangky(int layout, MonHocVaTKB_Fragment context, List<MonHoc> list) {
        Layout = layout;
        this.mContext = context;
        this.list = list;
    }

        Course_DataBase db;
    int Layout;
    MonHocVaTKB_Fragment mContext;
    List<MonHoc> list;
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
    public class ViewHolder{
        TextView tvTinChi,tvMonHoc;
        ImageView imgDelete;
    }
    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if (view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) mContext.getActivity().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(Layout,null);
            holder.tvMonHoc = view.findViewById(R.id.tvMonHoc);
            holder.tvTinChi = view.findViewById(R.id.tvTinChi);
            holder.imgDelete = view.findViewById(R.id.imgDelete);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();
        }
            db = new Course_DataBase(mContext.getActivity());
            final MonHoc monHoc = (MonHoc) list.get(i);
            holder.tvTinChi.setText(monHoc.getTinChi());
            Log.d("", "getView: "+monHoc.getTinChi());
            holder.tvMonHoc.setText(monHoc.getTenMonHoc());
            holder.imgDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final MonHoc monHoc = (MonHoc) list.get(i);
                    final AlertDialog.Builder DialogDelete = new AlertDialog.Builder(mContext.getActivity());
                    DialogDelete.setMessage("Bạn có muốn xóa môn học?");
                    DialogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
//                            Log.d("", "Delete: "+monHoc.getIDAuto().toString());
                            db.QueryData("DELETE FROM MonHoc WHERE ID = "+monHoc.getIDAuto()+" ");
                            Toast.makeText(mContext.getActivity(), "Đã xóa thành công", Toast.LENGTH_SHORT).show();
                            mContext.getDulieuMonHoc();
                            MonHocVaTKB_Fragment.TenMonHocList.clear();
                            mContext.getMonHoc();
                        }
                    });
                    DialogDelete.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    DialogDelete.show();
                }
            });

        return view;
    }
}
