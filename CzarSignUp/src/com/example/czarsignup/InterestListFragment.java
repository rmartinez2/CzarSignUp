package com.example.czarsignup;

import java.util.ArrayList;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.ActionMode;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class InterestListFragment extends ListFragment {

	private ArrayList<InterestInfo> mInterests = new ArrayList<InterestInfo>();
	private static final int REQUESTINTEREST = 1;

	private ListView mListView;
	private TextView mEmptyView;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setHasOptionsMenu(true);
		getActivity().setTitle(R.string.interest_title);
		mInterests = InterestLab.get(getActivity()).getInterests();

	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		InterestInfo info = ((InterestAdapter) mListView.getAdapter())
				.getItem(position);
		Intent i = new Intent(getActivity(), InterestPagerActivity.class);
		i.putExtra(InterestFragment.EXTRA_INTEREST_ID, info.getId());
		startActivity(i);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.interest_list_view, null);

		mListView = (ListView) v.findViewById(android.R.id.list);
		mEmptyView = (TextView) v.findViewById(R.id.interest_list_empty);

		if (mListView != null) {
			mListView.setEmptyView(mEmptyView);
		}

		mListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);

		mListView.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public boolean onPrepareActionMode(ActionMode arg0, Menu arg1) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onDestroyActionMode(ActionMode arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.list_menu, menu);
				return true;
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {

				switch (item.getItemId()) {

				case R.id.list_menu_item_delete:
					InterestAdapter adapter = (InterestAdapter) mListView
							.getAdapter();
					InterestLab interestLab = InterestLab.get(getActivity());

					for (int i = adapter.getCount() - 1; i >= 0; i--) {
						if (mListView.isItemChecked(i)) {
							interestLab.delInterest(adapter.getItem(i));
						}
					}

					mode.finish();
					adapter.notifyDataSetChanged();
					return true;

				default:
					return false;
				}

			}

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {

			}
		});

		InterestAdapter adapter = new InterestAdapter(mInterests);
		if (mListView.getAdapter() == null) {
			mListView.setAdapter(adapter);
		}

		return v;
	}

	private class InterestAdapter extends ArrayAdapter<InterestInfo> {

		public InterestAdapter(ArrayList<InterestInfo> infos) {
			super(getActivity(), android.R.layout.simple_list_item_1,
					android.R.id.text1, infos);
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = getActivity().getLayoutInflater().inflate(
						R.layout.list_item_interest, null);
			}

			InterestInfo info = getItem(position);

			TextView nameTextView = (TextView) convertView
					.findViewById(R.id.interest_name);
			nameTextView.setText(info.getFN() + " " + info.getLN());

			TextView rankTextView = (TextView) convertView
					.findViewById(R.id.interest_class_rank);
			rankTextView.setText(info.getSY());

			TextView permissionTextView = (TextView) convertView
					.findViewById(R.id.permission_to_contact);
			permissionTextView.setText(info.getPermission());

			return convertView;
		}

	}

	@Override
	public void onResume() {
		super.onResume();
		((InterestAdapter) mListView.getAdapter()).notifyDataSetChanged();
	}

}
