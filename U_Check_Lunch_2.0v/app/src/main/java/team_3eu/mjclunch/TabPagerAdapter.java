package team_3eu.mjclunch;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
/**
 * Created by LeeMJ on 2018. 11. 15..
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter {
    // Count number of tabs
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount) {
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {

        // Returning the current tabs
        switch (position) {
            case 0:
                AF tabFragment1 = new AF();
                return tabFragment1;
            case 1:
                KF tabFragment2 = new KF();
                return tabFragment2;
            case 2:
                CF tabFragment3 = new CF();
                return tabFragment3;
            case 3:
                JF tabFragment4 = new JF();
                return tabFragment4;
            case 4:
                WF tabFragment5 = new WF();
                return tabFragment5;
            case 5:
                OF tabFragment6 = new OF();
                return tabFragment6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}