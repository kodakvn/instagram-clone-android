package com.example.instagramclone.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.instagramclone.R;
import com.example.instagramclone.home.HomeActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginActivity extends AppCompatActivity {
	private static final String TAG = "LoginActivity";

	private FirebaseAuth mAuth;
	private FirebaseAuth.AuthStateListener mAuthListener;

	private Context mContext;
	private ProgressBar mProgressBar;
	private EditText mEmail, mPassword;
	private TextView mPleaseWait;

	@Override protected void onCreate(@Nullable Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		mProgressBar = (ProgressBar) findViewById(R.id.progress_bar);
		mPleaseWait = (TextView) findViewById(R.id.pleaseWait);
		mEmail = (EditText) findViewById(R.id.input_email);
		mPassword = (EditText) findViewById(R.id.input_password);
		mContext = LoginActivity.this;

		Log.d(TAG, "onCreate start");

		mPleaseWait.setVisibility(View.GONE);
		mProgressBar.setVisibility(View.GONE);

		setupFirebaseAuth();
		init();
	}

	private boolean isStringNull(String string) {
		if (string == null) return true;
		if (string.equals("")) return true;
		return false;
	}

	/**
	 ************* FIREBASE *************
	 */

	private void init() {
		Button btnLogin = (Button) findViewById(R.id.login_button);
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = mEmail.getText().toString();
				String password = mPassword.getText().toString();

				if (isStringNull(email) || isStringNull(password)) {
					Toast.makeText(mContext, "You must fill out all the fields", Toast.LENGTH_SHORT).show();
				} else {
					mPleaseWait.setVisibility(View.VISIBLE);
					mProgressBar.setVisibility(View.VISIBLE);

					mAuth.signInWithEmailAndPassword(email, password)
							.addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
								@Override
								public void onComplete(@NonNull Task<AuthResult> task) {
									mPleaseWait.setVisibility(View.GONE);
									mProgressBar.setVisibility(View.GONE);

									if (task.isSuccessful()) {
										// Sign in success, update UI with the signed-in user's information
										Log.d(TAG, "signInWithEmail:success");
										Toast.makeText(LoginActivity.this, getString(R.string.auth_succeed), Toast.LENGTH_SHORT).show();
									} else {
										// If sign in fails, display a message to the user.
										Log.w(TAG, "signInWithEmail:failure", task.getException());
										Toast.makeText(LoginActivity.this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
									}
								}
							});
				}
			}
		});

		TextView linkSignUp = (TextView) findViewById(R.id.link_signup);
		linkSignUp.setOnClickListener(new View.OnClickListener() {
			@Override public void onClick(View v) {
				Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
				startActivity(intent);
			}
		});

		if (mAuth.getCurrentUser() != null) {
			Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
			startActivity(intent);
			finish();
		}
	}

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
