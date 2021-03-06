package com.blissroms.blissify.categories;

import android.content.ContentResolver;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blissroms.blissify.R;
import com.blissroms.blissify.fragments.statusbar.Icons;
import com.blissroms.blissify.fragments.statusbar.Battery;
import com.blissroms.blissify.fragments.statusbar.Clock;

public class Statusbar extends Fragment {

    private View view;
    private ViewPager viewPager;
    private TabLayout tableLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.blissify_fixed_tablayout, container, false);

        viewPager = view.findViewById(R.id.viewpager);
        viewPager.setAdapter(new StatusbarAdapter(getChildFragmentManager()));
        tableLayout = view.findViewById(R.id.fixed_tabs);
        tableLayout.post(new Runnable() {
            @Override
            public void run() {
                tableLayout.setupWithViewPager(viewPager);
            }
        });

        return view;
    }

    private class StatusbarAdapter extends FragmentPagerAdapter {

        final String[] tabs= getTabsTitle();
        private final Fragment[] frags = new Fragment[tabs.length];

        public StatusbarAdapter(FragmentManager fm) {
            super(fm);
            // Add Fragments Here
            frags[0] = new Battery();
            frags[1] = new Clock();
            frags[2] = new Icons();
        }

        @Override
        public Fragment getItem(int position) {
            return frags[position];
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabs[position];
        }

        // Tab Titles
        private String[] getTabsTitle() {
            String titleString[];
            titleString = new String[]
                    {
                            getString(R.string.statusbar_battery_title),
                            getString(R.string.statusbar_clock_title),
                            getString(R.string.statusbar_icons_title)
                    };
            return titleString;
        }
    }
}
