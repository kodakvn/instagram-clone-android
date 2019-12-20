package com.example.instagramclone.home;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.instagramclone.R;
import com.example.instagramclone.login.LoginActivity;
import com.example.instagramclone.utils.BottomNavigationViewHelper;
import com.example.instagramclone.utils.SectionPagerAdapter;
import com.example.instagramclone.utils.UniversalImageLoader;
import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

	private static final String TAG = "HomeActivity";
	private static final int ACTIVITY_NUM = 0;
	private Context mContext = HomeActivity.this;
	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Log.d(TAG, "onCreate start");
		setupFirebaseAuth();
		initImageLoader();
		setupBottomNavigationView();
		setupViewPager();
	}

	private void initImageLoader() {
		UniversalImageLoader loader = new UniversalImageLoader(this);
		ImageLoader.getInstance().init(loader.getCongiguration());
	}

	private void setupViewPager() {
		SectionPagerAdapter adapter = new SectionPagerAdapter(getSupportFragmentManager());
		adapter.addFragment(new CameraFragment());
		adapter.addFragment(new HomeFragment());
		adapter.addFragment(new MessagesFragment());
		ViewPager viewPager = (ViewPager) findViewById(R.id.container);
		viewPager.setAdapter(adapter);

		TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
		tabLayout.setupWithViewPager(viewPager);

		tabLayout.getTabAt(0).setIcon(R.drawable.ic_camera);
		tabLayout.getTabAt(1).setIcon(R.drawable.ic_instagram);
		tabLayout.getTabAt(2).setIcon(R.drawable.ic_arrow);
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

	/**
	 ************* FIREBASE *************
	 */

	private void checkCurrentUser(FirebaseUser user) {
		Log.d(TAG, "check user is logged in?");
		if (user == null) {
			Intent intent = new Intent(mContext, LoginActivity.class);
			startActivity(intent);
		}
	}

	@Override
	protected void onStart() {
		super.onStart();
		mAuth.addAuthStateListener(mAuthListener);
		checkCurrentUser(mAuth.getCurrentUser());
	}

	@Override
	protected void onStop() {
		super.onStop();
		if (mAuthListener != null) {
			mAuth.removeAuthStateListener(mAuthListener);
		}
	}

	private void setupFirebaseAuth() {
		mAuth = FirebaseAuth.getInstance();
		mAuthListener = new FirebaseAuth.AuthStateListener() {
			@Override
			public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
				FirebaseUser user = mAuth.getCurrentUser();
				checkCurrentUser(user);
			}
		};

	}
}
