package com.example.socialx;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class pagerAdapter extends FragmentPagerAdapter {
    int tabcount;

    public pagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabcount=behavior;
    }


    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0:
                return new Signin();
            case 1:
                return new Signup();
        }
        return null;
    }

    @Override
    public int getCount() {
        return tabcount;
    }

}
