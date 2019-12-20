package com.example.instagramclone.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.instagramclone.R;
import com.example.instagramclone.home.HomeActivity;
import com.example.instagramclone.utils.BottomNavigationViewHelper;
import com.example.instagramclone.utils.FirebaseMethods;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
	private static final String TAG = "RegisterActivity";

	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;
	private FirebaseMethods firebaseMethods;

	private Context mContext;
	private String email, username, password;
	private EditText mEmail, mPassword, mUsername;
	private TextView loadingPleaseWait;
	private Button btnRegister;
	private ProgressBar mProgressBar;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		mContext = RegisterActivity.this;
		firebaseMethods = new FirebaseMethods(mContext);
		Log.d(TAG, "onCreate start");

		initWidgets();
		setupFirebaseAuth();
		init();
	}

	private void init() {
		btnRegister.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				email = mEmail.getText().toString();
				username = mUsername.getText().toString();
				password = mPassword.getText().toString();

				if (checkInputs(email, username, password)) {
					mProgressBar.setVisibility(View.VISIBLE);
					loadingPleaseWait.setVisibility(View.VISIBLE);

					firebaseMethods.registerNewEmail(email, password, username);
				}
			}
		});
	}

	private boolean checkInputs(String email, String username, String password) {
		if (isStringNull(email) || isStringNull(username) || isStringNull(password)) {
			Toast.makeText(mContext, "All fields must be filled out", Toast.LENGTH_SHORT).show();
			return false;
		} else {
			return true;
		}
	}

	private void initWidgets() {
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		loadingPleaseWait = (TextView) findViewById(R.id.loadingPleaseWait);
		mEmail = (EditText) findViewById(R.id.input_email);
		mPassword = (EditText) findViewById(R.id.input_password);
		mUsername = (EditText) findViewById(R.id.input_username);
		btnRegister = (Button) findViewById(R.id.register_button);

		mProgressBar.setVisibility(View.GONE);
		loadingPleaseWait.setVisibility(View.GONE);
	}

	private boolean isStringNull(String string) {
		if (string == null) return true;
		if (string.equals("")) return true;
		return false;
	}

	/**
	 ************* FIREBASE *************
	 */

	@Override
	protected void onStart() {
		super.onStart();
		mAuth.addAuthStateListener(mAuthListener);
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
				FirebaseUser user = firebaseAuth.getCurrentUser();
			}
		};

	}
}
