package tiendhph30203.poly.madaothangvaa.AdapterHoaDon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import tiendhph30203.poly.madaothangvaa.FragmentHoaDon.ChoXacNhanKhachHangFragment;
import tiendhph30203.poly.madaothangvaa.FragmentHoaDon.DaGiaoKhachHangFragment;
import tiendhph30203.poly.madaothangvaa.FragmentHoaDon.DaXacNhanKhachHangFragment;
import tiendhph30203.poly.madaothangvaa.FragmentHoaDon.DangGiaoKhachHangFragment;

public class DonMuaAdapter extends FragmentStateAdapter {
    public DonMuaAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChoXacNhanKhachHangFragment();
            case 1:
                return new DaXacNhanKhachHangFragment();
            case 2:
                return new DangGiaoKhachHangFragment();
            case 3:
                return new DaGiaoKhachHangFragment();
        }
         return new DaGiaoKhachHangFragment();


    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
