package com.example.instagramclone.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class SecstionStatePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final HashMap<Fragment, Integer> mFragments = new HashMap<>();
    private final HashMap<String, Integer> mFragmentNumbers = new HashMap<>();
    private final HashMap<Integer, String> mFragmentNames = new HashMap<>();

    public SecstionStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return mFragmentList.get(i);
    }

    @Override
    public int getCount() {
        return mFragmentList.size();
    }

    public void addFragment(Fragment f, String name) {
        mFragmentList.add(f);
        mFragments.put(f, mFragmentList.size()-1);
        mFragmentNumbers.put(name, mFragmentList.size()-1);
        mFragmentNames.put(mFragmentList.size()-1, name);
    }

    public Integer getFragmentNumber(String name) {
        if (mFragmentNumbers.containsKey(name)) {
            return mFragmentNumbers.get(name);
        }
        return null;
    }

    public Integer getFragmentNumber(Fragment f) {
        if (mFragments.containsKey(f)) {
            return mFragments.get(f);
        }
        return null;
    }

    public String getFragmentName(Integer number) {
        if (mFragmentNames.containsKey(number)) {
            return mFragmentNames.get(number);
        }
        return null;
    }
}
