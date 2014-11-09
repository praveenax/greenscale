package com.gs.gscale.fragments;

import info.androidhive.slidingmenu.R;

import java.util.Calendar;

import android.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.gs.gscale.MyApplication;
import com.gs.gscale.helpers.CarbonFootprintDataBuilder;
import com.gs.gscale.model.CarbonFootprintData;

public class TravellerFragment extends Fragment implements OnClickListener,
		OnItemSelectedListener{

	private TextView txt_mode;
	private TextView txt_by;
	private TextView txt_measure;
	private TextView txt_unit;
	private TextView txt_carbon;
	private Button btn_start;
	private Button btn_stop;

	private Spinner spim_mode;
	private Spinner spim_by;

	private double carbon_value;

	public TravellerFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_traveller, container,
				false);

		txt_mode = (TextView) rootView.findViewById(R.id.txt_mode);
		txt_by = (TextView) rootView.findViewById(R.id.txt_by);
		txt_measure = (TextView) rootView.findViewById(R.id.txt_measure);
		txt_unit = (TextView) rootView.findViewById(R.id.txt_unit);
		txt_carbon = (TextView) rootView.findViewById(R.id.txt_carbon);

		btn_start = (Button) rootView.findViewById(R.id.btn_start);
		btn_start.setOnClickListener(this);
		btn_stop = (Button) rootView.findViewById(R.id.btn_stop);
		btn_stop.setOnClickListener(this);

		spim_mode = (Spinner) rootView.findViewById(R.id.spin_mode);
		spim_by = (Spinner) rootView.findViewById(R.id.spin_by);

		ArrayAdapter<String> modeAdapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1,
				((MyApplication) getActivity().getApplication())
						.getCarbonMetaEntryManager().getMode("Transport"));
		spim_mode.setAdapter(modeAdapter);
		spim_mode.setSelection(0);

		spim_mode.setOnItemSelectedListener(this);
		spim_mode.setOnItemSelectedListener(this);
		spim_by.setOnItemSelectedListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_start:
			break;
		case R.id.btn_stop:
		default:
			break;
		}
	}

	private int getID() {

		String mode = (null != spim_mode.getSelectedItem()) ? spim_mode
				.getSelectedItem().toString() : "Null";
		String by = (null != spim_by.getSelectedItem()) ? spim_by
				.getSelectedItem().toString() : "Null";

		return ((MyApplication) getActivity().getApplication())
				.getCarbonMetaEntryManager()
				.getID("Transport",
						(View.VISIBLE == spim_mode.getVisibility() ? mode
								: "Null"),
						(View.VISIBLE == spim_by.getVisibility() ? by : "Null"));
	}

	@Override
	public void onItemSelected(AdapterView<?> av, View view, int position,
			long arg3) {

		String[] items;
		String selected = ((Spinner) av).getItemAtPosition(position).toString();

		switch (av.getId()) {
	
		case R.id.spin_mode:
			String catagory = "Transport";
			items = ((MyApplication) getActivity().getApplication())
					.getCarbonMetaEntryManager().getBy(catagory, selected);

			if (null == items) {
				txt_mode.setVisibility(View.GONE);
				txt_by.setVisibility(View.GONE);
				spim_mode.setVisibility(View.GONE);
				spim_by.setVisibility(View.GONE);
			} else {

				ArrayAdapter<String> ByAdapter = new ArrayAdapter<String>(
						this.getActivity(),
						android.R.layout.simple_list_item_1, items);
				spim_by.invalidate();
				spim_by.setAdapter(ByAdapter);
			}
			break;

		case R.id.spin_by:
			break;
		}

		txt_measure.setText(((MyApplication) getActivity().getApplication())
				.getCarbonMetaEntryManager().getMeasurement(getID()));
		txt_unit.setText(((MyApplication) getActivity().getApplication())
				.getCarbonMetaEntryManager().getUnit(getID()));
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		
	}
}
