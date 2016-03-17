package heiguang.com.mddemo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hiviiup on 16/3/10.
 */
public class ViewPagerAdapter extends FragmentPagerAdapter
{
    FragmentManager fm;
    List<Fragment> mFragments = new ArrayList<>();
    List<String> mPageTitle = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager fm)
    {
        super(fm);
        this.fm = fm;
    }

    public void addFragment(Fragment fragment, String title) {
        mFragments.add(fragment);
        mPageTitle.add(title);
    }

    @Override
    public Fragment getItem(int position)
    {
        return mFragments.get(position);
    }

    @Override
    public int getCount()
    {
        return mFragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position)
    {
        return mPageTitle.get(position);
    }
}
