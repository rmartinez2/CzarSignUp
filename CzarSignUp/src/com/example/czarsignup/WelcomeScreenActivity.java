package com.example.czarsignup;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class WelcomeScreenActivity extends Activity {

	private ImageButton mImageButton;
	private Button mStartButton;
	private String mAnswer = "March4th2001";
	// private boolean mSelected = false;

	private RelativeLayout mLayout;
	private String mPassword;

	public static final int SUCCESS = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		setContentView(R.layout.activity_welcome_screen);

		mLayout = (RelativeLayout) findViewById(R.id.baseline_view);
		mLayout.setOnLongClickListener(new OnLongClickListener() {

			@Override
			public boolean onLongClick(View v) {
				Dialog dlg = getAlert();
				dlg.show();
				return false;
			}
		});

		mImageButton = (ImageButton) findViewById(R.id.welcome_image);
		mImageButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(getBaseContext(), NameScreenActivity.class);
				startActivityForResult(i, SUCCESS);
				// mSelected = true;
			}
		});

		mStartButton = (Button) findViewById(R.id.welcome_start);
		mStartButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent i = new Intent();
				i.setClass(getBaseContext(), NameScreenActivity.class);
				startActivityForResult(i, SUCCESS);
				// mSelected = true;
			}
		});

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == SUCCESS) {

			switch (resultCode) {

			case Activity.RESULT_OK:
				Toast tm = Toast.makeText(this,
						"You've Successfully Signed Up!", Toast.LENGTH_SHORT);
				tm.show();
				return;

			}

		}

	}

	@Override
	protected void onPause() {

		// if (mSelected) {
		super.onPause();
		// mSelected = false;
		// Log.d("Paused", "Gay");
		// } else if (onBPResult()) {
		// super.onPause();
		// } else {
		// super.onResume();
		// }

	}

	@Override
	public void onBackPressed() {

		// if (onBPResult()) {
		super.onBackPressed();
		// } else {
		// super.onResume();
		// }
	}

	public boolean onBPResult() {

		Dialog dlg = new Dialog(this);
		dlg.setContentView(R.layout.pw_dialog);
		dlg.show();

		return false;

	}

	public Dialog getAlert() {
		EditText pwfield;

		AlertDialog.Builder dlg = new AlertDialog.Builder(this);
		LayoutInflater inflater = this.getLayoutInflater();
		View v = inflater.inflate(R.layout.pw_dialog, null);
		dlg.setView(v);

		pwfield = (EditText) v.findViewById(R.id.pw_field);
		pwfield.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				mPassword = s.toString();

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

		dlg.setPositiveButton(R.string.ok,
				new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (mAnswer.compareTo(mPassword) == 0) {
							Intent i = new Intent();
							i.setClass(WelcomeScreenActivity.this,
									InterestListFragmentActivity.class);
							startActivity(i);
						}
					}
				});

		dlg.setNegativeButton(R.string.cancel, null);

		dlg.setTitle("Enter Brother Password");

		return dlg.create();

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.welcome_screen, menu);
		return true;
	}

}
