package jvd.ir.digiknew.Cat;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class CatViewPager extends FragmentPagerAdapter {

    List<String> titles;


    public CatViewPager(@NonNull FragmentManager fm) {
        super(fm);
        titles=new ArrayList<>();
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        return CatFragment.newInstance(titles.get(i));
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    public void addFragment(String title){
        titles.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
