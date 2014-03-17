package com.example.czarsignup;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

public class NameScreenActivity extends Activity {

	public static final int REQUEST_QUIT = 1;
	public static final int REQUEST_FINISH = 2;

	private final String TAG = "NAMESCREEN";
	
	public static final String NS_BUNDLE_EXTRA = "NSB";
	public static final String INTERESTFIRST = "FN";
	public static final String INTERESTLAST = "LN";
	public static final String INTERESTPHONE = "PN";

	private Button mNextButton;
	private Button mQuitButton;

	private EditText mFirstName;
	private EditText mLastName;
	private EditText mPhoneNumber;

	private InterestInfo mInterest;
	private ArrayList<InterestInfo> mInterests = new ArrayList<InterestInfo>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mInterest = new InterestInfo();
		mInterests = InterestLab.get(getBaseContext()).getInterests();
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.name_page);

		mNextButton = (Button) findViewById(R.id.np_next);
		mNextButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (isFilled()) {
					Intent i = new Intent(NameScreenActivity.this,
							SchoolScreenActivity.class);
					Bundle b = createNSBundle();
					i.putExtra(NS_BUNDLE_EXTRA, b);
					startActivityForResult(i, REQUEST_FINISH);
				} else {

					Dialog dlg = getAlert();
					dlg.show();
				}
			}
		});

		mQuitButton = (Button) findViewById(R.id.np_quit);
		mQuitButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				onDestroy();
				finish();
			}
		});

		mFirstName = (EditText) findViewById(R.id.first_name_et);
		mFirstName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mInterest.setFN(s.toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		mLastName = (EditText) findViewById(R.id.last_name_et);
		mLastName.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mInterest.setLN(s.toString());

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		mPhoneNumber = (EditText) findViewById(R.id.phone_number_et);
		mPhoneNumber.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mInterest.setPhoneNumb(s.toString());
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		// if (data == null) {
		// /Log.i("IS", "NULL");
		// return;
		// }
		Log.d("Result", "" + resultCode);
		
		if (resultCode == REQUEST_QUIT) {
			finish();
		}

		if (resultCode == REQUEST_FINISH) {
			Bundle ret = data.getBundleExtra("SchoolBundle");

			if (mInterest != null) {

				mInterest.setEM(ret.getString("Email"));
				mInterest.setPermission(ret.getString("Permission"));
				mInterest.setSY(ret.getString("Rank"));
				mInterest.setMajor(ret.getString("Major"));
				InterestLab.get(getApplicationContext()).addInterest(mInterest);
				InterestLab.get(getApplicationContext()).saveInterests();
				setResult(Activity.RESULT_OK);
			}
			finish();
		}

	}

	public Bundle createNSBundle() {
		Bundle b = new Bundle();
		
		b.putString(INTERESTFIRST, mInterest.getFN());
		b.putString(INTERESTLAST, mInterest.getLN());
		b.putString(INTERESTPHONE, mInterest.getPhoneNumb());
		return b;

	}

	public boolean isFilled() {
		if (mInterest.getFN() != null && mInterest.getLN() != null
				&& mInterest.getPhoneNumb() != null) {
			return true;
		}
		return false;
	}

	public Dialog getAlert() {
		return new AlertDialog.Builder(this).setPositiveButton("OK", null)
				.setTitle("Please Fill Out All Of The Fields").create();
	}

}
