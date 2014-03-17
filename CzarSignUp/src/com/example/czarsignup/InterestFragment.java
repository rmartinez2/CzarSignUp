package com.example.czarsignup;

import java.util.UUID;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class InterestFragment extends Fragment {
	public static final String EXTRA_INTEREST_ID = "com.example.czarsignup.interest_id";

	private InterestInfo info;
	private TextView mInterestPhone;
	private TextView mInterestEmail;
	private TextView mInterestMajor;
	private TextView mInterestClass;
	private TextView mInterestFirstName;
	private TextView mInterestLastName;
	private TextView mInterestFOC;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_interest, null);

		mInterestFirstName = (TextView) v
				.findViewById(R.id.interest_fragment_first_name);
		mInterestFirstName.setText(info.getFN());

		mInterestLastName = (TextView) v
				.findViewById(R.id.interest_fragment_last_name);
		mInterestLastName.setText(info.getLN());

		mInterestClass = (TextView) v
				.findViewById(R.id.interest_fragment_class);
		mInterestClass.setText(info.getSY());

		mInterestEmail = (TextView) v
				.findViewById(R.id.interest_fragment_email);
		mInterestEmail.setText(info.getEM());

		mInterestMajor = (TextView) v
				.findViewById(R.id.interest_fragment_major);
		mInterestMajor.setText(info.getMajor());

		mInterestPhone = (TextView) v
				.findViewById(R.id.interest_fragment_phone);
		mInterestPhone.setText(info.getPhoneNumb());

		mInterestFOC = (TextView) v.findViewById(R.id.interest_fragment_foc);
		mInterestFOC.setText(info.getPermission());

		return v;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UUID interestId = (UUID) getArguments().getSerializable(
				EXTRA_INTEREST_ID);
		info = InterestLab.get(getActivity()).getInterestInfo(interestId);
		setHasOptionsMenu(true);

	}

	public static InterestFragment newInstance(UUID id) {
		Bundle args = new Bundle();
		args.putSerializable(EXTRA_INTEREST_ID, id);

		InterestFragment fragment = new InterestFragment();
		fragment.setArguments(args);
		return fragment;
	}
}
