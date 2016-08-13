package webapp.shangru.project.com.websiteapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Shangru on 8/3/16.
 */
public class TabAdapter extends FragmentPagerAdapter {
    public static final String[] TITLES = new String[]{"Test_0", "Test_1", "Test_2", "Test_3", "Test_4"};

    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int arg0) {
        MainFragment fragment = new MainFragment(arg0);
        return fragment;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return TITLES[position % TITLES.length];
    }

    @Override
    public int getCount() {
        return TITLES.length;
    }
}
