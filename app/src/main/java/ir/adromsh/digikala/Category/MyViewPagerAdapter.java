package ir.adromsh.digikala.Category;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MyViewPagerAdapter extends FragmentPagerAdapter {

    List<String> titles;

    public MyViewPagerAdapter(FragmentManager fm) {
        super(fm);
        titles = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int i) {
        return CatFragment.newInstance(titles.get(i));
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    public void addFramgent(String tilte){
        titles.add(tilte);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}
