package com.example.instagramclone.likes;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramclone.R;
import com.example.instagramclone.utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class LikesActivity extends AppCompatActivity {
	private static final String TAG = "LikesActivity";
	private static final int ACTIVITY_NUM = 3;
	private Context mContext = LikesActivity.this;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Log.d(TAG, "onCreate start");
		setupBottomNavigationView();
	}

	private void setupBottomNavigationView() {
		Log.d(TAG, "setupBottomNavigationView start");
		BottomNavigationViewEx bottomNavigationViewEx = (BottomNavigationViewEx) findViewById(R.id.bottomNavViewBar);
		BottomNavigationViewHelper.setupBottomNavigationView(bottomNavigationViewEx);
		BottomNavigationViewHelper.enableNavigation(mContext, bottomNavigationViewEx);
		Menu menu = bottomNavigationViewEx.getMenu();
		MenuItem menuItem = menu.getItem(ACTIVITY_NUM);
		menuItem.setCheckable(true);
	}
}
