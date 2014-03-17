package com.example.czarsignup;

import java.util.ArrayList;
import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;

public class InterestPagerActivity extends FragmentActivity {

	private ViewPager mViewPager;
	private ArrayList<InterestInfo> mInterests;

	private FragmentManager fm;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		mViewPager = new ViewPager(this);
		mViewPager.setId(R.id.interestViewPager);
		setContentView(mViewPager);

		mInterests = InterestLab.get(this).getInterests();

		fm = getSupportFragmentManager();

		mViewPager.setAdapter(new FragmentStatePagerAdapter(fm) {

			@Override
			public int getCount() {
				return mInterests.size();
			}

			@Override
			public Fragment getItem(int position) {
				InterestInfo info = mInterests.get(position);
				return InterestFragment.newInstance(info.getId());
			}
		});

		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int position) {
				InterestInfo info = mInterests.get(position);

				setTitle(info.getFN() + " " + info.getLN());

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

		UUID interestId = (UUID) getIntent().getSerializableExtra(
				InterestFragment.EXTRA_INTEREST_ID);

		for (int i = 0; i < mInterests.size(); i++) {
			if (mInterests.get(i).getId().equals(interestId)) {
				mViewPager.setCurrentItem(i);
				break;
			}
		}

	}

	public ViewPager getViewPager() {
		return mViewPager;
	}

}
