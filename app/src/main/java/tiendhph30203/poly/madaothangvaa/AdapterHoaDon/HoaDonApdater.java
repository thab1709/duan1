package tiendhph30203.poly.madaothangvaa.AdapterHoaDon;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import tiendhph30203.poly.madaothangvaa.FragmentHoaDon.ChoXacNhanFragment;
import tiendhph30203.poly.madaothangvaa.FragmentHoaDon.DaGiaoFragment;
import tiendhph30203.poly.madaothangvaa.FragmentHoaDon.DaXacNhanFragment;
import tiendhph30203.poly.madaothangvaa.FragmentHoaDon.DangGiaoFragment;

public class HoaDonApdater extends FragmentStateAdapter {

    public HoaDonApdater(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }


    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                return new ChoXacNhanFragment();
            case 1:
                return new DaXacNhanFragment();
            case 2:
                return new DangGiaoFragment();
            case 3:
                return new DaGiaoFragment();
        }
        return new DaGiaoFragment();
    }


    @Override
    public int getItemCount() {
        return 4;
    }
}
