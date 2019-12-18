package com.example.instagramclone.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.instagramclone.R;
import com.example.instagramclone.utils.UniversalImageLoader;
import com.nostra13.universalimageloader.core.ImageLoader;

public class EditProfileFragment extends Fragment {
    private static final String TAG = "EditProfileFragment";

    private ImageView mProfilePhoto;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_edit_profile, container, false);

        mProfilePhoto = (ImageView) view.findViewById(R.id.profile_photo);

        initImageLoader();
        setProfileImage();

        return view;
    }

    private void initImageLoader() {
    	UniversalImageLoader loader = new UniversalImageLoader(getActivity());
		ImageLoader.getInstance().init(loader.getCongiguration());
	}

    private void setProfileImage() {
		 String imageUrl = "crackberry.com/sites/crackberry.com/files/topic_images/2013/ANDROID.png";
		 UniversalImageLoader.setImage(imageUrl, mProfilePhoto, null, "https://");
	 }
}
