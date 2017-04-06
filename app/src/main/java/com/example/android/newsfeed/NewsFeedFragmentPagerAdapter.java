
package com.example.android.newsfeed;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.content.Context;

/**
 * Provides the appropriate {@link Fragment} for a view pager.
 */
public class NewsFeedFragmentPagerAdapter extends FragmentPagerAdapter {

    Context context;
    public NewsFeedFragmentPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        if (position == 0) {
            return new PoliticsFragment();
        } else if (position == 1) {
            return new SportsFragment();
        } else if (position == 2) {
            return new TechFragment();
        } else {
            return new AllSectionsFragment();
        }
        }


    @Override
    public CharSequence getPageTitle(int position) {
        if (position == 0) {
            return context.getString(R.string.politics);
        } else if (position == 1) {
            return context.getString(R.string.sports);
        } else if (position == 2) {
            return context.getString(R.string.tech);
        }else {
            return context.getString(R.string.all_sections);
        }

    }

    @Override
    public int getCount() {
        return 4;
    }
}
