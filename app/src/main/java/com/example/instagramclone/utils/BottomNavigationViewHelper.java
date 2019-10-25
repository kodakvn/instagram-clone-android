package com.example.instagramclone.utils;

import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {
	public static final String TAG = "BottomNavigationViewHelper";

	public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
		bottomNavigationViewEx.enableAnimation(false);
		bottomNavigationViewEx.enableItemShiftingMode(false);
		bottomNavigationViewEx.enableItemShiftingMode(false);
		bottomNavigationViewEx.setTextVisibility(false);
	}
}
