package com.example.hp8440p.myapplication.Course.Fragment_Manage;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.AutoText;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp8440p.myapplication.Course.Course_Activity;
import com.example.hp8440p.myapplication.Course.Course_DataBase;
import com.example.hp8440p.myapplication.Course.Oject.MonHoc;
import com.example.hp8440p.myapplication.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class DanhSach_Fragment extends Fragment {

    Course_DataBase db;
    Button btnDangKy;
    TextView tvNganh, tvChuyenNganh,tvXacNhan;
    EditText txtTinChi;
    ListView lvMonHoc;
    AutoCompleteTextView TvmonHoc;
    Spinner spnListNganh, spnChuyenNganh;
    public static String Nganh, ChuyenNganh, MonHoc;
    public static String TinChi;
    String TenMonHoc;
    public static List<String> listCheckMonHoc = new ArrayList<>();


    //----------------Ngành------//
    public static String[] nganhHocList = {"CÔNG NGHỆ THÔNG TIN", "KINH TẾ", "DU LỊCH - NHÀ HÀNG - KHÁCH SẠN"};
    //-----------------Chuyên ngành---------------------//
    String[] nganhCNTTList = {"Ứng dụng phần mềm", "Lập trình Android", "Lập trình web", "Thiết kế đồ họa"};
    String[] nganhKTList = {"Digital Marketing Online", "PR & Tổ chức sự kiện", "Sales"};
    String[] nganhQuanLyList = {"QT Khách sạn", "QT Nhà hàng", "Hướng dẫn viên du lịch"};
    //-----------Môn Học-----------------//
    //---------------CNTT---------//
    String[] UdpmList = {"Kĩ năng học tập", "Chỉnh sửa hình ảnh với Photoshop", "Tiếng Anh", "Cơ bản về HTML/ CSS", "Cơ bản về cơ sở dữ liệu", "Java 1", "Java 2", "Java 3", "Quản trị server windows 2008", "Chuyên đề các xu thế mới của công nghệ Java ,framework"};
    String[] ltAList = {"Nhập môn lập trình", "Photoshop", "Cơ sở dữ liệu", "Java 1", "Java 2", "Thiết kế web với HTML5/CSS3", "Lập trình Android", "Lập trình Android nâng cao", "Dự án mẫu", "Thiết kế game với Unity", "IOT",};
    String[] ltWList = {"Kiến thức học tập cơ sở", "Thiết kế hình ảnh với Photoshop", "Kiến thức cơ bản về HTML/CSS/IS", "Thiết kế CSS nâng cao", "Quản trị website", "Tiếng Anh", "Lập trình cơ bản với JavaScript", "PHP nâng cao", " SEO website", "Điện toán đám mây"};
    String[] tkDHList = {"Kỹ năng học tập", "Tin học cơ sở", "Tin học văn phòng", "Thiết kế hình ảnh với Photoshop", "Thiết kế layout trang web", "Thiết kế đa truyền thông với Flash", "Thiết kế Web với HTML5 & CSS3", "Thiết kế đồ họa động với 3D Studio Max", "Autocad 2D", "Thiết kế tạo hình nhân vật trong 3D", "Chế bản điện tử với InDesign"};
    //---------------KT----------//
    String[] DMOList = {"Kỹ năng học tập", "Marketing căn bản", "Tin học văn phòng", "SEO & Marketing trên công cụ tìm kiếm", "Tổng quan thương mại điện tử", "Tiếng Anh (TopNotch) 2.2", "Xây dựng trang web", "Email Marketing", "Marketing mạng xã hội", "Xây dựng trang web 2"};
    String[] PrList = {"Kỹ năng học tập", "Marketing căn bản", "Nhập môn Quan hệ", "Kỹ năng phỏng vấn và trả lời phỏng vấn", "Quản trị sự kiện", "Kỹ năng thuyết trình trước công chúng", "Kỹ năng sáng tạo", "Kỹ năng hoạt náo", "Truyền thông marketing tích hợp", "Khởi sự doanh nghiệp"};
    String[] SalesList = {"Marketing căn bản", "Kiến thức về Nghiên cứu Marketing và Thực hành Quản trị bán hàng", "Kiến thức về Quảng cáo", "Kiến thức về Nghiên cứu Marketing", "Kiến thức về Quan hệ công chúng", "Kiến thức cơ bản về quản trị doanh nghiệp", "Nhập môn quản trị doanh nghiệp", "Tiếng Anh", "Kiến thức về kỹ năng bán hàng", "Phát triển bản thân qua môn học khởi sự doanh nghiệp"};
    //----------------QuanLy--------//
    String[] QtksList = {"Kỹ năng học tập", "Tổng quan Du lịch – Nhà hàng – Khách sạn", "Tâm lý và kỹ năng giao tiếp, ứng xử với du khách", "Tin học văn phòng", "Marketing online", "Tiếng Anh chuyên ngành", "Khởi sự doanh nghiệp", "Chính trị, pháp luật", "Nghiệp vụ lễ tân", "Quản trị lễ tân"};
    String[] QtnhList = {"Kỹ năng học tập", "Tổng quan Du lịch – Khách sạn – Nhà hàng", "Tin học văn phòng", "Tổ chức sự kiện", "Tiếng Anh chuyên ngành du lịch lữ hành", "Nghiệp vụ lễ tân", "Nghiệp vụ bar 1", "Quản trị nguồn nhân lực", "Văn hóa ẩm thực", "Nghiệp vụ thanh toán"};
    String[] QtdlList = {"Kỹ năng học tập", "Tâm lý và kỹ năng giao tiếp, ứng xử với du khách", "Chính trị, pháp luật", "Khởi sự doanh nghiệp", "Quản lý chất lượng dịch vụ trong du lịch", "Tiếng Anh chuyên ngành du lịch lữ hành", "Địa lý du lịch và tài nguyên Việt Nam", "Thực tập nghiệp vụ hướng dẫn", "Ứng dụng CNTT trong kinh doanh du lịch", "Marketing online"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_danhsachmonhc, container, false);
        AnhXa(view);
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, nganhHocList);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spnListNganh.setAdapter(adapter);
        tvXacNhan = view.findViewById(R.id.tvXacNhan);
        @SuppressLint("ResourceType") Animation animation = AnimationUtils.loadAnimation(getActivity(),R.animator.animal);
        tvXacNhan.startAnimation(animation);

        spnListNganh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    AdapterSpn(nganhCNTTList, spnChuyenNganh);
                    AddMonHoc(UdpmList, ltAList, ltWList, tkDHList);
                }
                if (i == 1) {
                    AdapterSpn(nganhKTList, spnChuyenNganh);
                    AddMonHoc(DMOList, PrList, SalesList, null);
                }
                if (i == 2) {
                    AdapterSpn(nganhQuanLyList, spnChuyenNganh);
                    AddMonHoc(QtksList, QtnhList, QtdlList, null);
                }
                AddData();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData();
            }
        });
        return view;
    }

    //------------------FucTion-------------------//

    public void AddMonHoc(final String[] list1, final String[] list2, final String[] list3, final String[] list4) {
        spnChuyenNganh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i == 0) {
                    Adapter(list1, lvMonHoc);
                    AddMonHocChiTiet(list1);
                }
                if (i == 1) {
                    Adapter(list2, lvMonHoc);
                    AddMonHocChiTiet(list2);
                }
                if (i == 2) {
                    Adapter(list3, lvMonHoc);
                    AddMonHocChiTiet(list3);
                }
                if (i == 3) {
                    Adapter(list4, lvMonHoc);
                    AddMonHocChiTiet(list4);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
            }
        });
    }

    private void AddMonHocChiTiet(String[] list) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, list);
        TvmonHoc.setAdapter(adapter);
    }

    public void AdapterSpn(String[] list, Spinner spinner) {
        ArrayAdapter adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        spinner.setAdapter(adapter);
    }

    public void Adapter(String[] list, ListView lv) {
        ArrayAdapter<String> adapterMonHoc = new ArrayAdapter(getContext(), android.R.layout.simple_list_item_1, list);
        adapterMonHoc.setDropDownViewResource(android.R.layout.simple_list_item_single_choice);
        lv.setAdapter(adapterMonHoc);
    }

    public void AddData() {
        db = new Course_DataBase(getContext());
        Nganh = (String) spnListNganh.getSelectedItem();
        ChuyenNganh = (String) spnChuyenNganh.getSelectedItem();
        MonHoc = TvmonHoc.getText().toString();
        TinChi = txtTinChi.getText().toString();
        tvNganh.setText(Nganh);
        tvChuyenNganh.setText(ChuyenNganh);
        if (MonHoc.equals("")) {
            Toast.makeText(getActivity(), "Không bỏ trống Môn học", Toast.LENGTH_SHORT).show();
        } else if (TinChi.equals("")) {
            Toast.makeText(getActivity(), "Không bỏ trống Tín chỉ", Toast.LENGTH_SHORT).show();
        } else if (listCheckMonHoc.contains(MonHoc)==true) {
            Toast.makeText(getContext(), "Bạn đã đăng ký môn này nên không cần đăng ký lại.", Toast.LENGTH_LONG).show();
        } else {
            com.example.hp8440p.myapplication.Course.Oject.MonHoc monHoc = new MonHoc(Nganh, ChuyenNganh, MonHoc, null, TinChi);
             if (db.AddMonHoc(monHoc) > 0) {
                 CheckMonHoc();
                Toast.makeText(getActivity(), ">Bạn đã đăng ký thành công môn học" + "\n" + ">Thông tin chi tiết :" + "\n" + ">" + Nganh + "\n" + ">" + ChuyenNganh + "\n" + ">" + MonHoc + "\n" + ">" + TinChi, Toast.LENGTH_LONG).show();
            }
        }
    }
    public void CheckMonHoc(){
        db = new Course_DataBase(getActivity());
        Cursor cursor = db.getData("SELECT TenMonHoc FROM MonHoc");
        if (cursor!=null && cursor.getCount()>0){
            if (cursor.moveToFirst()){
                do {
                    TenMonHoc = cursor.getString(0);
                    listCheckMonHoc.add(TenMonHoc);
                }while (cursor.moveToNext());
            }
        }
        cursor.close();
    }

    public void AnhXa(View view) {
        spnListNganh = view.findViewById(R.id.SpnNganhHoc);
        spnChuyenNganh = view.findViewById(R.id.SpnChuyenNganh);
        lvMonHoc = view.findViewById(R.id.lvMonHoc);
        tvNganh = view.findViewById(R.id.tvNganh);
        tvChuyenNganh = view.findViewById(R.id.tvChuyenNganh);
        TvmonHoc = view.findViewById(R.id.tvMonHoc);
        txtTinChi = view.findViewById(R.id.txtTinChi);
        btnDangKy = view.findViewById(R.id.btnDangKy);
    }
}
