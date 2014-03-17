package com.example.czarsignup;

import java.util.ArrayList;
import java.util.UUID;

import android.content.Context;

public class InterestLab {

	private static InterestLab sInterestLab;
	private ArrayList<InterestInfo> mInterests;
	private Context mAppContext;
	private CzarSignUpSerializer mSerializer;
	private static final String FILENAME = "interests.json";

	private InterestLab(Context appContext) {
		mAppContext = appContext;
		mSerializer = new CzarSignUpSerializer(mAppContext, FILENAME);

		try {
			mInterests = mSerializer.loadInterests();
		} catch (Exception e) {
			mInterests = new ArrayList<InterestInfo>();
		}

	}

	public static InterestLab get(Context c) {
		if (sInterestLab == null) {
			sInterestLab = new InterestLab(c.getApplicationContext());
		}
		return sInterestLab;
	}

	public ArrayList<InterestInfo> getInterests() {
		return mInterests;
	}

	public void addInterest(InterestInfo info) {
		mInterests.add(info);
	}

	public void delInterest(InterestInfo info) {
		mInterests.remove(info);
	}

	public InterestInfo getInterestInfo(UUID id) {
		for (InterestInfo i : mInterests) {
			if (i.getId().equals(id)) {
				return i;
			}
		}

		return null;
	}

	public boolean saveInterests() {
		try {
			mSerializer.saveInternal(mInterests);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
