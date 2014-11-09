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

public class CalculatorFragment extends Fragment implements OnClickListener,
		OnItemSelectedListener, TextWatcher {

	private TextView txt_mode;
	private TextView txt_by;
	private TextView txt_measure;
	private TextView txt_unit;
	private TextView txt_carbon;
	private EditText ed_user_input;
	private Button btn_add;

	private Spinner spim_catagory;
	private Spinner spim_mode;
	private Spinner spim_by;

	private double carbon_value;

	public CalculatorFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_calc, container,
				false);

		txt_mode = (TextView) rootView.findViewById(R.id.txt_mode);
		txt_by = (TextView) rootView.findViewById(R.id.txt_by);
		txt_measure = (TextView) rootView.findViewById(R.id.txt_measure);
		txt_unit = (TextView) rootView.findViewById(R.id.txt_unit);
		txt_carbon = (TextView) rootView.findViewById(R.id.txt_carbon);

		btn_add = (Button) rootView.findViewById(R.id.btn_add);
		btn_add.setOnClickListener(this);

		spim_catagory = (Spinner) rootView.findViewById(R.id.spin_catagory);
		spim_mode = (Spinner) rootView.findViewById(R.id.spin_mode);
		spim_by = (Spinner) rootView.findViewById(R.id.spin_by);

		ArrayAdapter<String> categoryAdapter = new ArrayAdapter<String>(
				this.getActivity(), android.R.layout.simple_list_item_1,
				((MyApplication) getActivity().getApplication())
						.getCarbonMetaEntryManager().getcatagory());
		spim_catagory.setAdapter(categoryAdapter);
		spim_catagory.setSelection(0);

		spim_catagory.setOnItemSelectedListener(this);
		spim_mode.setOnItemSelectedListener(this);
		spim_by.setOnItemSelectedListener(this);

		ed_user_input = (EditText) rootView.findViewById(R.id.ed_input_units);
		ed_user_input.addTextChangedListener(this);
		ed_user_input.setText("0");

		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:

			if (carbon_value != 0) {

				CarbonFootprintDataBuilder builder = new CarbonFootprintDataBuilder();
				CarbonFootprintData newData = builder.setId(0)
						.setModeID(getID())
						.setStartTimeStamp(Calendar.getInstance().getTime())
						.setCarbonData(carbon_value).create();
				((MyApplication) getActivity().getApplication())
						.getCarbonFootprintDataManager().add(getActivity(),
								newData);
				Toast.makeText(
						v.getContext(),
						"Your Carbon foot Print is recorded. Appreciate you being responsible.\r\nPlant and grow trees!!",
						Toast.LENGTH_LONG).show();
			} else
				Toast.makeText(v.getContext(),
						"You are already at 0.\n\nWell Done. No footprint!!",
						Toast.LENGTH_LONG).show();

			break;
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
				.getID(spim_catagory.getSelectedItem().toString(),
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
		case R.id.spin_catagory:

			items = ((MyApplication) getActivity().getApplication())
					.getCarbonMetaEntryManager().getMode(selected);

			if (null == items) {
				txt_mode.setVisibility(View.GONE);
				txt_by.setVisibility(View.GONE);
				spim_mode.setVisibility(View.GONE);
				spim_by.setVisibility(View.GONE);
			} else {

				ArrayAdapter<String> ModeAdapter = new ArrayAdapter<String>(
						this.getActivity(),
						android.R.layout.simple_list_item_1, items);
				spim_mode.invalidate();
				spim_mode.setAdapter(ModeAdapter);

				txt_mode.setVisibility(View.VISIBLE);
				txt_by.setVisibility(View.VISIBLE);
				spim_mode.setVisibility(View.VISIBLE);
				spim_by.setVisibility(View.VISIBLE);

			}
			break;
		case R.id.spin_mode:
			String catagory = spim_catagory.getSelectedItem().toString();
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
		ed_user_input.setText("0");
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	@Override
	public void afterTextChanged(Editable s) {
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

		double ci = 0;

		try {
			ci = Double.valueOf(ed_user_input.getText().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}

		double mf = ((MyApplication) (getActivity().getApplication()))
				.getCarbonMetaEntryManager().getMulFac(getID());

		carbon_value = CarbonFootprintData.getDoubleRounded(ci * mf);

		txt_carbon.setText("Carbon : " + carbon_value + " grams");
	}
}
