package com.example.instagramclone.utils;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.models.User;
import com.example.instagramclone.models.UserAccountSettings;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.annotation.NonNull;

public class FirebaseMethods {
	private static final String TAG = "FirebaseMethods";

	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;
	private FirebaseDatabase mFirebaseDatabase;
//	private DatabaseReference myRef;

	private Context mContext;
	private String userId;

	public FirebaseMethods(Context context) {
		mContext = context;
		mAuth = FirebaseAuth.getInstance();
		mFirebaseDatabase = FirebaseDatabase.getInstance();
//		myRef = mFirebaseDatabase.getReference();

		if (mAuth.getCurrentUser() != null) {
			userId = mAuth.getCurrentUser().getUid();
		}
	}

	public boolean checkIfUsernameExists(String username, DataSnapshot dataSnapshot) {
		User user = new User();

		for (DataSnapshot ds : dataSnapshot.getChildren()) {
			user.setUsername(ds.getValue(User.class).getUsername());
			if (StringManipulation.expandUserName(user.getUsername()).equals(username)) {
				return true;
			}
		}
		return false;
	}

	public void registerNewEmail(final String email, final String password, final String username) {
		mAuth.createUserWithEmailAndPassword(email, password)
				.addOnCompleteListener(new OnCompleteListener<AuthResult>() {
					@Override public void onComplete(@NonNull Task<AuthResult> task) {
						if (task.isSuccessful()) {
							userId = mAuth.getCurrentUser().getUid();
							Log.d(TAG, "authentication state change " + userId);
						} else {
							Toast.makeText(mContext, R.string.auth_failed, Toast.LENGTH_SHORT).show();
						}
					}
				});
	}

	public void addNewUser(String email, String username, String description, String website, String profile_photo) {
		User user = new User(userId, 1, email, StringManipulation.condenseUsername(username));
		mFirebaseDatabase.getReference()
				.child(mContext.getString(R.string.dbname_users))
				.child(userId)
				.setValue(user);

		UserAccountSettings settings = new UserAccountSettings(description, username, 0, 0, 0, "", username, "");
		mFirebaseDatabase.getReference()
				.child(mContext.getString(R.string.dbname_user_account_settings))
				.child(userId)
				.setValue(settings);
	}
}
