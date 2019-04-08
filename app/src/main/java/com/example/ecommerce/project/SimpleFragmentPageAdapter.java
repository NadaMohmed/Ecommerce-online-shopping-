package com.example.ecommerce.project;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class SimpleFragmentPageAdapter extends FragmentPagerAdapter {

    SimpleFragmentPageAdapter (FragmentManager fm )
    {
        super(fm);
    }

    @Override
    public int getCount() {

        return 3 ;
    }

    @Override
    public Fragment getItem(int position) {
        switch(position)
        {
            case 0 :
            {
                return new CameraFragment() ;
            }
            case 1 :
            {
                return new VoiceFragment();
            }
            case 2 :
            {
                return new TextFragment() ;
            }

        }
        return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position)
        {
            case 0 :
                return "CAMERA" ;
            case 1:
                return "VOICE" ;
            case 2 :
                return  "TEXT" ;
        }
        return super.getPageTitle(position);
    }
}

