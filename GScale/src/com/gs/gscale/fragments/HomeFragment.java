package com.gs.gscale.fragments;

import com.gs.gscale.MyApplication;
import com.gs.gscale.managers.CarbonFootprintQuickDataManager;
import com.gs.gscale.model.CarbonFootprintData;

import info.androidhive.slidingmenu.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class HomeFragment extends Fragment {

	public HomeFragment() {
	}

	private TextView mtxt_today;
	private TextView mtxt_yesterday;
	private TextView txt_trees;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_home, container,
				false);

		mtxt_today = (TextView) rootView.findViewById(R.id.txt_today_carbon);
		mtxt_yesterday = (TextView) rootView
				.findViewById(R.id.txt_yesterday_carbon);
		txt_trees = (TextView) rootView.findViewById(R.id.txt_trees);

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();

		CarbonFootprintQuickDataManager tmpQuickData = ((MyApplication) getActivity()
				.getApplication()).getCarbonFootprintQuickDataManager();
		mtxt_today.setText(""
				+ CarbonFootprintData.getDoubleRounded(new Double(
						tmpQuickData.totalFootprint).doubleValue()));
		mtxt_yesterday.setText(""
				+ CarbonFootprintData.getDoubleRounded(new Double(
						tmpQuickData.yesterdayFootprint).doubleValue()));
		txt_trees.setText("Tress to plant : " + tmpQuickData.getTressCount());
	}
}
