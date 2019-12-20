package com.example.instagramclone.utils;

import android.content.Context;
import android.content.Intent;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.instagramclone.home.HomeActivity;
import com.example.instagramclone.likes.LikesActivity;
import com.example.instagramclone.profile.ProfileActivity;
import com.example.instagramclone.R;
import com.example.instagramclone.search.SearchActivity;
import com.example.instagramclone.share.ShareActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class BottomNavigationViewHelper {
	public static final String TAG = "BottomNavigationViewHelper";

	public static void setupBottomNavigationView(BottomNavigationViewEx bottomNavigationViewEx) {
		bottomNavigationViewEx.enableAnimation(false);
		bottomNavigationViewEx.enableItemShiftingMode(false);
		bottomNavigationViewEx.enableItemShiftingMode(false);
		bottomNavigationViewEx.setTextVisibility(false);
	}

	public static void enableNavigation(final Context context, BottomNavigationViewEx view) {
		view.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
			@Override public boolean onNavigationItemSelected(@NonNull MenuItem item) {
				switch (item.getItemId()) {
					case R.id.ic_house:
						Intent intent1 = new Intent(context, HomeActivity.class);
						context.startActivity(intent1);
						break;
					case R.id.ic_search:
						Intent intent2 = new Intent(context, SearchActivity.class);
						context.startActivity(intent2);
						break;
					case R.id.ic_circle:
						Intent intent3 = new Intent(context, ShareActivity.class);
						context.startActivity(intent3);
						break;
					case R.id.ic_alert:
						Intent intent4 = new Intent(context, LikesActivity.class);
						context.startActivity(intent4);
						break;
					case R.id.ic_android:
						Intent intent5 = new Intent(context, ProfileActivity.class);
						context.startActivity(intent5);
						break;
				}
				return false;
			}
		});
	}
}
