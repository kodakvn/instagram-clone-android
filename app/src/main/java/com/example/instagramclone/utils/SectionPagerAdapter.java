package com.example.instagramclone.utils;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class SectionPagerAdapter extends FragmentPagerAdapter {
	private static final String TAG = "SectionPagerAdapter";
	private final List<Fragment> mFragments = new ArrayList<>();

	public SectionPagerAdapter(FragmentManager fm) {
		super(fm);
	}

	@Override public Fragment getItem(int i) {
		return mFragments.get(i);
	}

	@Override public int getCount() {
		return mFragments.size();
	}

	public void addFragment(Fragment fragment) {
		mFragments.add(fragment);
	}
}
