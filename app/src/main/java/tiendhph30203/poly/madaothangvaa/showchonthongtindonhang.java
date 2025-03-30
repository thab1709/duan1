package tiendhph30203.poly.madaothangvaa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.util.ArrayList;

import tiendhph30203.poly.madaothangvaa.DonMua.GioHang;
import tiendhph30203.poly.madaothangvaa.DonMua.GioHangDAO2;
import tiendhph30203.poly.madaothangvaa.QuanLyKhachHang.KhachHang;
import tiendhph30203.poly.madaothangvaa.QuanLyKhachHang.KhachHangDAO;
import tiendhph30203.poly.madaothangvaa.SanPham.SanPham;
import tiendhph30203.poly.madaothangvaa.SanPham.SanPhamDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class showchonthongtindonhang extends AppCompatActivity {
    Toolbar toolbar;
    SanPham sanPham;
    ArrayList<SanPham> list;
    int numberOrder = 1, tienSanPham = 0;
    TextView tvTenSanPhamChonThongTin, tvGiaSanPhamChonThongTin, tvNoiDungChonThongTinDonHang;
    EditText tvSoLuongShowDetal;
    ImageView imgAnhSanPhamChonThongTin, imgMinus, imgPlus;
    Button btnThemVaoGioHang;
    KhachHang khachHang;
    private SanPhamDAO sanPhamDAO;
    private GioHangDAO2 gioHangDAO2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showchonthongtindonhang);
        toolbar = (Toolbar) findViewById(R.id.idToolBarGioHang);
        tvTenSanPhamChonThongTin = findViewById(R.id.tvTenSanPhamChonThongTin);
        tvGiaSanPhamChonThongTin = findViewById(R.id.tvGiaSanPhamChonThongTin);
        tvSoLuongShowDetal = findViewById(R.id.tvSoLuongShowDetal);
        tvNoiDungChonThongTinDonHang = findViewById(R.id.tvNoiDungChonThongTinDonHang);
        imgAnhSanPhamChonThongTin = findViewById(R.id.imgAnhSanPhamChonThongTin);
        imgMinus = findViewById(R.id.imgMinus);
        imgPlus = findViewById(R.id.imgPlus);
        btnThemVaoGioHang = findViewById(R.id.btnThemVaoGioHang);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sản phẩm");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        sanPhamDAO = new SanPhamDAO(this);
        gioHangDAO2 = new GioHangDAO2(this);
        list = (ArrayList<SanPham>) sanPhamDAO.getAll();
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        Bundle bundle = getIntent().getExtras();
        if (bundle == null) {
            return;
        }
        int masanpham = bundle.getInt("masanpham");
        String tensanpham = bundle.getString("tensanpham", "NULL");
        String giasanpham = bundle.getString("giasanpham", "NULL");
        int soluongtrongkho = bundle.getInt("soluongtrongkho", -1);
        String ngaysanxuat = bundle.getString("ngaysanxuat", "NULL");
        String hansudung = bundle.getString("hansudung", "NULL");
        String linkanhsanpham = bundle.getString("linkanhsanpham", "NULL");

        tvTenSanPhamChonThongTin.setText(tensanpham);
        tvGiaSanPhamChonThongTin.setText(giasanpham + " VNĐ");

        tvNoiDungChonThongTinDonHang.setText(
                "Tên sản phẩm : " + tensanpham
                        + "\n" + "Giá : " + giasanpham + " VNĐ" + "\n"
                        + "Số lượng trong kho: " + soluongtrongkho + "\n"
                        + "Ngày sản xuất : " + ngaysanxuat
                        + "\n" + "Hạn sử dụng : " + hansudung
                        + "\n" + "Thuế : " + 2 + "%"
                        + "\n" + "Phí dịch vụ : " + 2000 + " VNĐ");
        tvSoLuongShowDetal.setText(String.valueOf(numberOrder));

        tvSoLuongShowDetal.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                try {
                    int enteredValue = Integer.parseInt(s.toString());
                    if (enteredValue > soluongtrongkho) {
                        Toast.makeText(showchonthongtindonhang.this, "Số lượng nhập vượt quá số lượng trong kho", Toast.LENGTH_SHORT).show();
                        tvSoLuongShowDetal.setText(String.valueOf(soluongtrongkho));
                    } else if (enteredValue < 1) {
                        Toast.makeText(showchonthongtindonhang.this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                        tvSoLuongShowDetal.setText("1");
                    } else {
                        numberOrder = enteredValue;
                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(showchonthongtindonhang.this, "Số lượng không hợp lệ", Toast.LENGTH_SHORT).show();
                    tvSoLuongShowDetal.setText("1");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        imgPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder < soluongtrongkho) {
                    numberOrder++;
                }
                tvSoLuongShowDetal.setText(String.valueOf(numberOrder));
                if (soluongtrongkho == 0) {
                    Toast.makeText(showchonthongtindonhang.this, "Sản phẩm đã hết hàng", Toast.LENGTH_SHORT).show();
                }
            }
        });

        imgMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (numberOrder > 1) {
                    numberOrder--;
                }
                tvSoLuongShowDetal.setText(String.valueOf(numberOrder));
            }
        });

        SharedPreferences preferences = getSharedPreferences("thongtin", MODE_PRIVATE);
        String userName = (preferences.getString("username", ""));
        String quyen = (preferences.getString("loaitaikhoan", ""));
        if (quyen.equals("nguoidung")) {
            KhachHangDAO khachHangDAO = new KhachHangDAO(this);
            khachHang = khachHangDAO.getUserName(userName);
        }

        btnThemVaoGioHang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GioHang objGioHang = new GioHang();
                if (quyen.equals("nguoidung")) {
                    if (gioHangDAO2.checkValue(String.valueOf(masanpham), String.valueOf(khachHang.getManguoidung())) > 0) {
                        Toast.makeText(showchonthongtindonhang.this, "Sản phẩm đã có trong giỏ hàng", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                if (soluongtrongkho == 0) {
                    Toast.makeText(showchonthongtindonhang.this, "Sản phẩm đã hết hàng", Toast.LENGTH_SHORT).show();
                    return;
                }
                objGioHang.setMasanpham(masanpham);
                objGioHang.setTensanpham(tensanpham);
                if (quyen.equals("nguoidung")) {
                    objGioHang.setManguoidung(khachHang.getManguoidung());
                }
                objGioHang.setLinkanhsanpham(linkanhsanpham);
                objGioHang.setSoluong(numberOrder);
                objGioHang.setGiasanpham(Integer.parseInt(giasanpham));

                long kq = gioHangDAO2.insertGioHang(objGioHang);
                if (kq > 0) {
                    startActivity(new Intent(showchonthongtindonhang.this, MainActivity.class));
                    Toast.makeText(showchonthongtindonhang.this, "Thêm giỏ hàng thành công", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(showchonthongtindonhang.this, "Thêm giỏ hàng thất bại", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
