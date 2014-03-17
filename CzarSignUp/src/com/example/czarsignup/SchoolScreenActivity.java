package com.example.czarsignup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

public class SchoolScreenActivity extends Activity {

	private final String CLASSMAN = "Rank";
	private final String PERMISSION = "Permission";
	private final String EMAIL = "Email";
	private final String MAJOR = "Major";
	private final String SCHOOLBUND = "SchoolBundle";

	private Button mPrevious;
	private Button mQuit;
	private Button mFinish;
	private Spinner mSpinner;
	private RadioButton mFreshman;
	private RadioButton mSophomore;
	private RadioButton mJunior;
	private RadioButton mSenior;
	private EditText mMajor;
	private EditText mEmail;
	private Bundle NSBundle;
	private String infEmail;
	private String infMajor;
	private String infClassRank;
	private String infPermission;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.school_info_page);

		NSBundle = getIntent().getBundleExtra(
				NameScreenActivity.NS_BUNDLE_EXTRA);
		mPrevious = (Button) findViewById(R.id.prev_sip);

		mPrevious.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		mQuit = (Button) findViewById(R.id.quit_sip);
		mQuit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				setResult(NameScreenActivity.REQUEST_QUIT);
				finish();

			}
		});

		mFinish = (Button) findViewById(R.id.finish_sip);
		mFinish.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (allFilled()) {

					Dialog dlg = getVerif();
					dlg.show();

				} else {
					Dialog dlg = getAlert();
					dlg.show();
				}

			}
		});

		mSpinner = (Spinner) findViewById(R.id.bwtc_spinner);
		ArrayAdapter<CharSequence> mSpinnerAdapter = ArrayAdapter
				.createFromResource(this, R.array.formofcs,
						android.R.layout.simple_spinner_item);
		mSpinnerAdapter
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		mSpinner.setAdapter(mSpinnerAdapter);
		mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent, View arg1,
					int pos, long arg3) {
				infPermission = parent.getItemAtPosition(pos).toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}

		});

		mFreshman = (RadioButton) findViewById(R.id.rb_fresh);
		mFreshman.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				infClassRank = "Freshman";
				mSophomore.setChecked(false);
				mJunior.setChecked(false);
				mSenior.setChecked(false);

			}
		});

		mSophomore = (RadioButton) findViewById(R.id.rb_soph);
		mSophomore.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				infClassRank = "Sophomore";
				mFreshman.setChecked(false);
				mJunior.setChecked(false);
				mSenior.setChecked(false);

			}
		});

		mJunior = (RadioButton) findViewById(R.id.rb_junior);
		mJunior.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				infClassRank = "Junior";
				mFreshman.setChecked(false);
				mSophomore.setChecked(false);
				mSenior.setChecked(false);

			}
		});

		mSenior = (RadioButton) findViewById(R.id.rb_senior);
		mSenior.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				infClassRank = "Senior";
				mFreshman.setChecked(false);
				mSophomore.setChecked(false);
				mJunior.setChecked(false);

			}
		});

		mMajor = (EditText) findViewById(R.id.major_text);
		mMajor.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				infMajor = s.toString();

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

		mEmail = (EditText) findViewById(R.id.text_email);
		mEmail.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				infEmail = s.toString();

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

	public Bundle createSchoolBundle() {
		Bundle args = new Bundle();
		args.putString(CLASSMAN, infClassRank);
		args.putString(EMAIL, infEmail);
		args.putString(PERMISSION, infPermission);
		args.putString(MAJOR, infMajor);
		return args;
	}

	public Dialog getAlert() {
		return new AlertDialog.Builder(this).setPositiveButton("OK", null)
				.setTitle("Please Fill Out All Of The Fields").create();
	}

	public boolean allFilled() {

		if (infClassRank != null && infEmail != null && infMajor != null
				&& infPermission != null) {
			return true;
		}

		return false;
	}

	public Dialog getVerif() {

		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		LayoutInflater inflator = this.getLayoutInflater();
		View v = inflator.inflate(R.layout.ss_finalcheck, null);

		TextView nameView = (TextView) v.findViewById(R.id.ss_intName);
		nameView.setText(NSBundle.getString(NameScreenActivity.INTERESTFIRST)
				+ " " + NSBundle.getString(NameScreenActivity.INTERESTLAST));

		TextView phoneView = (TextView) v.findViewById(R.id.ss_intPhone);
		phoneView.setText(NSBundle.getString(NameScreenActivity.INTERESTPHONE));

		TextView classView = (TextView) v.findViewById(R.id.ss_IntClass);
		classView.setText(infClassRank);

		TextView majorView = (TextView) v.findViewById(R.id.ss_intMajor);
		majorView.setText(infMajor);

		TextView emailView = (TextView) v.findViewById(R.id.ss_intEmail);
		emailView.setText(infEmail);

		TextView focView = (TextView) v.findViewById(R.id.ss_intFOC);
		focView.setText(infPermission);

		builder.setView(v);

		builder.setTitle("Is this Information Correct?");

		builder.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent ret = new Intent();
						Bundle send = createSchoolBundle();
						ret.putExtra(SCHOOLBUND, send);
						setResult(NameScreenActivity.REQUEST_FINISH, ret);
						finish();

					}
				});
		builder.setNegativeButton(R.string.cancel, null);

		return builder.create();
	}
}
