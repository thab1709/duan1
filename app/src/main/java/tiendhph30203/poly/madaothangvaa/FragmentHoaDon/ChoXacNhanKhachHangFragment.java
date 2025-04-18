package tiendhph30203.poly.madaothangvaa.FragmentHoaDon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import tiendhph30203.poly.madaothangvaa.DonMua.Adapter_ChoXacNhanCuaKhachHang;
import tiendhph30203.poly.madaothangvaa.DonMua.HoaDon;
import tiendhph30203.poly.madaothangvaa.DonMua.HoaDonDAO;
import tiendhph30203.poly.projectdatdoan.R;

public class ChoXacNhanKhachHangFragment extends Fragment {
    private RecyclerView recyclerViewDonMua;
    private FloatingActionButton btnThemDonMua;
    HoaDonDAO qlhd;
    ArrayList<HoaDon> list = new ArrayList<>();

    public ChoXacNhanKhachHangFragment() {

    }


    public static ChoXacNhanKhachHangFragment newInstance() {
        ChoXacNhanKhachHangFragment fragment = new ChoXacNhanKhachHangFragment();

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_choxacnhankhachhang, container, false);
        recyclerViewDonMua = view.findViewById(R.id.rcDonMua);


        loadData();
        return view;
    }


    public void loadData() {
        qlhd = new HoaDonDAO(getContext());
        list = (ArrayList<HoaDon>) qlhd.getTrangThai0();
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recyclerViewDonMua.setLayoutManager(linearLayoutManager);
        Adapter_ChoXacNhanCuaKhachHang adapterDonMua = new Adapter_ChoXacNhanCuaKhachHang(list, getContext(),qlhd);
        recyclerViewDonMua.setAdapter(adapterDonMua);
    }
}