package com.example.instagramclone.profile;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.instagramclone.R;
import com.example.instagramclone.utils.BottomNavigationViewHelper;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

public class ProfileActivity extends AppCompatActivity {
	private static final String TAG = "ProfileActivity";
	private static final int ACTIVITY_NUM = 4;
	private Context mContext = ProfileActivity.this;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		Log.d(TAG, "onCreate start");
		//setupBottomNavigationView();
		setupToolbar();
	}

	private void setupToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolbar);
		setSupportActionBar(toolbar);

		toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
			@Override public boolean onMenuItemClick(MenuItem menuItem) {
				switch (menuItem.getItemId()) {
					case R.id.profileMenu:

						break;
					default:
						break;
				}
				return false;
			}
		});
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

	@Override public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.profile_menu, menu);
		return true;
	}
}
