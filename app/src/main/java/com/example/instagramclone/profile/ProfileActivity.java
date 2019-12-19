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
	private static final int NUM_GRID_COLUMNS = 3;
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
		imgUrls.add("https://figuya.com/uploads/product/profile_picture/16691/profile_dragon-ball-super-handtuch-super-saiyajin-god-super-saiyajin-son-goku-150-x-75-cm20190808-19143-53pua7.jpeg");
		imgUrls.add("https://imgix.ranker.com/user_node_img/50088/1001748303/original/the-turtle-hermit-way-photo-u1?w=650&q=50&fm=pjpg&fit=crop&crop=faces");
		imgUrls.add("https://genknews.genkcdn.vn/2016/1-1481709438862.jpg");
		imgUrls.add("https://i.pinimg.com/originals/05/c7/b4/05c7b4dd8f2247bf7275ebdd2037cc55.jpg");
		imgUrls.add("https://vignette.wikia.nocookie.net/dragonball/images/6/6b/Tien_DBZ_Ep_152_003.png/revision/latest?cb=20170915030720");
		imgUrls.add("https://sohanews.sohacdn.com/thumb_w/660/2015/6-nhung-su-that-la-lung-ve-dai-ma-vuong-piccolo-trong-dragon-ball-1438689552530-0-0-459-625-crop-1438689637827.png");
		imgUrls.add("https://vignette.wikia.nocookie.net/dragonballzuniverse/images/5/5e/Gohan%2C_the_dude.jpg/revision/latest?cb=20190316033742");
		imgUrls.add("https://res.cloudinary.com/teepublic/image/private/s--tnFsD4Di--/t_Resized%20Artwork/c_fit,g_north_west,h_1054,w_1054/co_ffffff,e_outline:53/co_ffffff,e_outline:inner_fill:53/co_bbbbbb,e_outline:3:1000/c_mpad,g_center,h_1260,w_1260/b_rgb:eeeeee/c_limit,f_jpg,h_630,q_90,w_630/v1517039721/production/designs/2313221_0.jpg");
		imgUrls.add("https://genknews.genkcdn.vn/2019/4/8/photo-1-155471685422440588926.jpg");
		setupImageGrid(imgUrls);
	}

	private void setupImageGrid(ArrayList<String> urls) {
		GridView gridView = (GridView) findViewById(R.id.gridView);

		int gridWidth = getResources().getDisplayMetrics().widthPixels;
		int imageWidth = gridWidth / NUM_GRID_COLUMNS;
		gridView.setColumnWidth(imageWidth);

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
