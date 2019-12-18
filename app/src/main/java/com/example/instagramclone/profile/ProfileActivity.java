package com.example.instagramclone.profile;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.example.instagramclone.R;
import com.example.instagramclone.utils.BottomNavigationViewHelper;
import com.example.instagramclone.utils.GridImageAdapter;
import com.example.instagramclone.utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
	private static final String TAG = "ProfileActivity";
	private static final int ACTIVITY_NUM = 4;
	private Context mContext = ProfileActivity.this;
	private ProgressBar mProgressBar;
	private ImageView profilePhoto;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		Log.d(TAG, "onCreate start");

		setupActivityWidgets();
		setupBottomNavigationView();
		setupToolbar();
		setProfileImage();

		tempGridSetup();
	}

	private void tempGridSetup() {
		ArrayList<String> imgUrls = new ArrayList<>();
		String url = "https://crackberry.com/sites/crackberry.com/files/topic_images/2013/ANDROID.png";
		for (int i=0; i<10; i++) {
			imgUrls.add(url);
		}
		setupImageGrid(imgUrls);
	}

	private void setupImageGrid(ArrayList<String> urls) {
		GridView gridView = (GridView) findViewById(R.id.gridView);
		GridImageAdapter adapter = new GridImageAdapter(mContext, R.layout.layout_grid_imageview, "", urls);
		gridView.setAdapter(adapter);
	}

	private void setProfileImage() {
		String imageUrl = "crackberry.com/sites/crackberry.com/files/topic_images/2013/ANDROID.png";
		UniversalImageLoader.setImage(imageUrl, profilePhoto, mProgressBar, "https://");
	}

	private void setupActivityWidgets() {
		mProgressBar = (ProgressBar) findViewById(R.id.profileProgressBar);
		mProgressBar.setVisibility(View.GONE);
		profilePhoto = (ImageView) findViewById(R.id.profile_photo);
	}

	private void setupToolbar() {
		Toolbar toolbar = (Toolbar) findViewById(R.id.profileToolbar);
		setSupportActionBar(toolbar);

		ImageView profileMenu = (ImageView) findViewById(R.id.profileMenu);
		profileMenu.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.d(TAG, "onclick navigating to account settings");
				Intent intent = new Intent(mContext, AccountSettingsActivity.class);
				startActivity(intent);
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
}
