package com.example.czarsignup;

import android.app.Fragment;

public class InterestListFragmentActivity extends
		InterestSingleFragmentActivity {

	@Override
	protected Fragment createFragment() {
		return new InterestListFragment();
	}

}
