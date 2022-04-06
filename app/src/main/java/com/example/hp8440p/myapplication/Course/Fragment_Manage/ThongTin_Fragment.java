package com.example.hp8440p.myapplication.Course.Fragment_Manage;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp8440p.myapplication.Course.Adapter.AdapterMonHocDaDangky;
import com.example.hp8440p.myapplication.Course.Course_DataBase;
import com.example.hp8440p.myapplication.Course.Oject.ThongTin_sv;
import com.example.hp8440p.myapplication.R;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class ThongTin_Fragment extends Fragment {
    ImageView imgAvatar, imgAvatarSv, imgAvatarChinhSua;
    int REQUEST_CODE = 1;
    Bitmap bitmap;
    EditText txtMaSv, txtCmnnd, txtLop, txtHovaTen, txtNgaySinh;
    TextView tvMaSv, tvCmnnd, tvLop, tvHovaTen, tvNgaySinh, tvGioiTinh, tvNganhHoc, tvChuyenNganhHoc;
    AutoCompleteTextView txtGioiTinh, txtNganh, txtChuyenNganh;
    Button btnXacNhan, btnThemAnh, btnCapNhat;
    Intent data2;
    Course_DataBase db;
    String ImgResult, MaSv, GioiTinh, SoCmnd, NganhHoc, ChuyenNhanh, Lop, HovaTen, NgaySinh, imgAvatarOld;
    String[] gioiTinh = {"Nam", "Nữ"};
    ////ngành
    String[] nganhHocList = {"CÔNG NGHỆ THÔNG TIN", "KINH TẾ", "DU LỊCH - NHÀ HÀNG - KHÁCH SẠN"};
    ///chuyên Ngành
    String[] ChuyenNganhList = {"Ứng dụng phần mềm", "Lập trình Android", "Lập trình web", "Thiết kế đồ họa", "Digital Marketing Online",
            "PR & Tổ chức sự kiện", "Sales", "QT Khách sạn", "QT Nhà hàng", "Hướng dẫn viên du lịch"};
    List<String> thongTinSvList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_thongtinsinhvien, container, false);
        setHasOptionsMenu(true);
        imgAvatarSv = view.findViewById(R.id.imgAvatarSv);
//        tvMaSv, tvCmnnd, tvLop, tvHovaTen, tvNgaySinh,tvGioiTinh,tvNganhHoc,tvChuyenNganhHoc;
        tvMaSv = view.findViewById(R.id.tvMaSv);
        tvCmnnd = view.findViewById(R.id.tvSocmnd);
        tvLop = view.findViewById(R.id.tvLop);
        tvHovaTen = view.findViewById(R.id.tvHoVaTen);
        tvNgaySinh = view.findViewById(R.id.tvNgaySinh);
        tvGioiTinh = view.findViewById(R.id.tvGioiTinh);
        tvNganhHoc = view.findViewById(R.id.tvNganhHoc);
        tvChuyenNganhHoc = view.findViewById(R.id.tvChuyenNganhHoc);
        getData();
        Log.d("", "THongTinList : " + thongTinSvList);


        return view;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.thongtinsv, menu);

        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int i = item.getItemId();

        if (i == R.id.menuThem) {
            DialogThemSv();
            return true;
        }
        if (i == R.id.menuChinhSua) {
            ChinhSuaSv();
        }
        if (i == R.id.menuXoa) {
            XoaSv();
        }
        return super.onOptionsItemSelected(item);
    }
//     db.QueryData("DELETE FROM ThongTinSv WHERE ID = 1");

    public void XoaSv() {

        final AlertDialog.Builder DialogDelete = new AlertDialog.Builder(getActivity());
        DialogDelete.setMessage("Xóa tất cả thông tin?");
        DialogDelete.setPositiveButton("Có", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ThongTin_sv thongTin_sv = new ThongTin_sv();
                db.QueryData("DELETE FROM ThongTinSv WHERE ID = 1 ");
                Toast.makeText(getActivity(), "Đã xóa thành công. Mời bạn thoát ra và đăng nhập lại để cập nhật thông tin", Toast.LENGTH_SHORT).show();
                imgAvatarSv.setImageURI(Uri.parse(""));
                tvMaSv.setText("");
                tvCmnnd.setText("");
                tvLop.setText("");
                tvHovaTen.setText("");
                tvNgaySinh.setText("");
                tvGioiTinh.setText("");
                tvNganhHoc.setText("");
                tvChuyenNganhHoc.setText("");
            }
        });
        DialogDelete.setNegativeButton("Hủy", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        DialogDelete.show();
    }

    private void ChinhSuaSv() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_chinhsuasv);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        AnhXaDialogChinhSua(dialog);
        imgAvatarChinhSua.setImageURI(Uri.parse(ImgResult));
        txtMaSv.setText(MaSv);
        txtCmnnd.setText(SoCmnd);
        txtLop.setText(Lop);
        txtHovaTen.setText(HovaTen);
        txtNgaySinh.setText(NgaySinh);
        txtGioiTinh.setText(GioiTinh);
        txtNganh.setText(NganhHoc);
        txtChuyenNganh.setText(ChuyenNhanh);
        dialog.show();

        imgAvatarChinhSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        btnCapNhat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String ImgViewNew = null;
                try {
                    ImgViewNew = data2.getDataString();
                } catch (Exception ex) {

                }
                String MaSvNew = txtMaSv.getText().toString();
                String CmnndNew = txtCmnnd.getText().toString();
                String LopNew = txtLop.getText().toString();
                String HovaTenNew = txtHovaTen.getText().toString();
                String NgaySinhNew = txtNgaySinh.getText().toString();
                String GioiTinhNew = txtGioiTinh.getText().toString();
                String NganhNew = txtNganh.getText().toString();
                String ChuyenNganhNew = txtChuyenNganh.getText().toString();
                db.QueryData("UPDATE ThongTinSv SET MaSv = '" + MaSvNew + "' WHERE ID = 1");
                db.QueryData("UPDATE ThongTinSv SET GioiTinh = '" + GioiTinhNew + "' WHERE ID = 1");
                db.QueryData("UPDATE ThongTinSv SET SoCmnd = '" + CmnndNew + "' WHERE ID = 1");
                db.QueryData("UPDATE ThongTinSv SET NganhHoc = '" + NganhNew + "' WHERE ID = 1");
                db.QueryData("UPDATE ThongTinSv SET ChuyenNganhHoc = '" + ChuyenNganhNew + "' WHERE ID = 1");
                db.QueryData("UPDATE ThongTinSv SET Lop = '" + LopNew + "' WHERE ID = 1");
                db.QueryData("UPDATE ThongTinSv SET HoVaTenSv = '" + HovaTenNew + "' WHERE ID = 1");
                db.QueryData("UPDATE ThongTinSv SET NgaySinh = '" + NgaySinhNew + "' WHERE ID = 1");
                db.QueryData("UPDATE ThongTinSv SET ImgResult = '" + ImgViewNew + "' WHERE ID = 1");

                Toast.makeText(getActivity(), "Chỉnh sửa thành công", Toast.LENGTH_SHORT).show();
                getData();
                dialog.cancel();
            }
        });

    }

    public void DialogThemSv() {
        final Dialog dialog = new Dialog(getActivity());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_themsv);
        Window window = dialog.getWindow();
        WindowManager.LayoutParams wlp = window.getAttributes();
        wlp.gravity = Gravity.CENTER;
        wlp.flags &= ~WindowManager.LayoutParams.FLAG_BLUR_BEHIND;
        window.setAttributes(wlp);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        AnhXaDialogThem(dialog);
        dialog.show();
        btnThemAnh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
        getData();
        if (thongTinSvList.contains(tvMaSv.getText().toString()) == true) {
            txtNgaySinh.setEnabled(false);
            txtHovaTen.setEnabled(false);
            txtGioiTinh.setEnabled(false);
            txtLop.setEnabled(false);
            txtChuyenNganh.setEnabled(false);
            txtNganh.setEnabled(false);
            txtCmnnd.setEnabled(false);
            txtMaSv.setEnabled(false);
            btnThemAnh.setEnabled(false);
            btnXacNhan.setEnabled(false);

            Toast.makeText(getActivity(), "Bạn đã nhập thông tin rồi, nên không cần thêm nữa", Toast.LENGTH_SHORT).show();
        } else {
            txtNgaySinh.setEnabled(true);
            txtHovaTen.setEnabled(true);
            txtGioiTinh.setEnabled(true);
            txtLop.setEnabled(true);
            txtChuyenNganh.setEnabled(true);
            txtNganh.setEnabled(true);
            txtCmnnd.setEnabled(true);
            txtMaSv.setEnabled(true);
            btnThemAnh.setEnabled(true);
            btnXacNhan.setEnabled(true);

        }
        btnXacNhan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddData(dialog);
            }
        });
    }


    public void AnhXaDialogThem(final Dialog dialog) {
        txtMaSv = dialog.findViewById(R.id.txtMasv);
        txtGioiTinh = dialog.findViewById(R.id.txtGioiTinh);
        AdapterAutoCompleteText(gioiTinh, txtGioiTinh);
        txtCmnnd = dialog.findViewById(R.id.txtCmnd);
        txtNganh = dialog.findViewById(R.id.txtNganh);
        AdapterAutoCompleteText(nganhHocList, txtNganh);
        txtChuyenNganh = dialog.findViewById(R.id.txtChuyenNganh);
        AdapterAutoCompleteText(ChuyenNganhList, txtChuyenNganh);
        txtLop = dialog.findViewById(R.id.txtLop);
        txtHovaTen = dialog.findViewById(R.id.txtHovaTen);
        btnThemAnh = dialog.findViewById(R.id.btnThemAnh);
        txtNgaySinh = dialog.findViewById(R.id.txtNgaySinh);
        btnXacNhan = dialog.findViewById(R.id.btnXacNhan);
        imgAvatar = dialog.findViewById(R.id.imgAvatar);
    }

    public void AnhXaDialogChinhSua(final Dialog dialog) {
        txtMaSv = dialog.findViewById(R.id.txtMasv);
        txtGioiTinh = dialog.findViewById(R.id.txtGioiTinh);
        AdapterAutoCompleteText(gioiTinh, txtGioiTinh);
        txtCmnnd = dialog.findViewById(R.id.txtCmnd);
        txtNganh = dialog.findViewById(R.id.txtNganh);
        AdapterAutoCompleteText(nganhHocList, txtNganh);
        txtChuyenNganh = dialog.findViewById(R.id.txtChuyenNganh);
        AdapterAutoCompleteText(ChuyenNganhList, txtChuyenNganh);
        txtLop = dialog.findViewById(R.id.txtLop);
        txtHovaTen = dialog.findViewById(R.id.txtHovaTen);
        btnThemAnh = dialog.findViewById(R.id.btnThemAnh);
        txtNgaySinh = dialog.findViewById(R.id.txtNgaySinh);
        btnCapNhat = dialog.findViewById(R.id.btnCapNhat);
        imgAvatarChinhSua = dialog.findViewById(R.id.imgAvatarChinhSua);
    }

    private void AdapterAutoCompleteText(String[] list, AutoCompleteTextView autoCompleteTextView) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_dropdown_item_1line, list);
        autoCompleteTextView.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            try {
                InputStream inputStream = getActivity().getContentResolver().openInputStream(data.getData());
                bitmap = BitmapFactory.decodeStream(inputStream);
                if (imgAvatar == null) {
                    imgAvatar.setImageURI(Uri.parse("content://media/external/images/media/168"));
                } else {
                    imgAvatar.setImageBitmap(bitmap);
                }
                data2 = data;
                Log.d("****************", "onActivityResult: " + data.getDataString());

            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void AddData(Dialog dialog) {
        db = new Course_DataBase(getContext());
        imgAvatarOld = data2.getDataString();
        String MaSv = txtMaSv.getText().toString();
        String SoCmnd = txtCmnnd.getText().toString();
        String NganhHoc = txtNganh.getText().toString();
        String ChuyenNganhHoc = txtChuyenNganh.getText().toString();
        String Lop = txtLop.getText().toString();
        String GioiTinh = txtGioiTinh.getText().toString();
        String HovaTenSv = txtHovaTen.getText().toString();
        String NgaySinh = txtNgaySinh.getText().toString();
        if (MaSv.equals("") || imgAvatar == null || SoCmnd.equals("") || NganhHoc.equals("") || ChuyenNganhHoc.equals("") || Lop.equals("") || GioiTinh.equals("") || HovaTenSv.equals("") || NgaySinh.equals("")) {
            Toast.makeText(getActivity(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
        } else {
            ThongTin_sv thongTin_sv = new ThongTin_sv("1", imgAvatarOld, MaSv, GioiTinh, SoCmnd, NganhHoc, ChuyenNganhHoc, Lop, HovaTenSv, NgaySinh);
            if (db.AddThongTinSv(thongTin_sv) > 0) {
                {
                    Toast.makeText(getActivity(), "Thêm thông tin thành công", Toast.LENGTH_SHORT).show();

                }
                getData();
                dialog.cancel();
            }
        }
    }

    public void getData() {
        db = new Course_DataBase(getActivity());
        Cursor cursor = db.getData("SELECT * FROM ThongTinSv");
        if (cursor != null && cursor.getCount() > 0) {
            if (cursor.moveToFirst()) {
                do {
                    ImgResult = cursor.getString(1);
                    MaSv = cursor.getString(2);
                    GioiTinh = cursor.getString(3);
                    SoCmnd = cursor.getString(4);
                    NganhHoc = cursor.getString(5);
                    ChuyenNhanh = cursor.getString(6);
                    Lop = cursor.getString(7);
                    HovaTen = cursor.getString(8);
                    NgaySinh = cursor.getString(9);
                    thongTinSvList.add(MaSv);
                    //--------SetImgSv-------------//
                    Log.d("*****************", "ImgResult: " + ImgResult.toString());
                    imgAvatarSv.setImageURI(Uri.parse(ImgResult));
                    tvMaSv.setText(MaSv);
                    tvCmnnd.setText(SoCmnd);
                    tvLop.setText(Lop);
                    tvHovaTen.setText(HovaTen);
                    tvNgaySinh.setText(NgaySinh);
                    tvGioiTinh.setText(GioiTinh);
                    tvNganhHoc.setText(NganhHoc);
                    tvChuyenNganhHoc.setText(ChuyenNhanh);
                } while (cursor.moveToNext());
            }
        }
        cursor.close();
    }
}
