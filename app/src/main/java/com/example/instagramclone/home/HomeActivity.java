package com.example.instagramclone.home;

import android.content.Context;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.instagramclone.R;
import com.example.instagramclone.utils.BottomNavigationViewHelper;
import com.example.instagramclone.utils.SectionPagerAdapter;
import com.example.instagramclone.utils.UniversalImageLoader;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.nostra13.universalimageloader.core.ImageLoader;

public class HomeActivity extends AppCompatActivity {

	private static final String TAG = "HomeActivity";
	private static final int ACTIVITY_NUM = 0;
	private Context mContext = HomeActivity.this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		Log.d(TAG, "onCreate start");
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
}
