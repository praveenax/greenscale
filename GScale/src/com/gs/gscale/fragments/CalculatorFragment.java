package com.gs.gscale.fragments;

import java.util.Calendar;

import com.gs.gscale.MyApplication;
import com.gs.gscale.helpers.CarbonFootprintDataBuilder;
import com.gs.gscale.model.CarbonFootprintData;

import info.androidhive.slidingmenu.R;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class CalculatorFragment extends Fragment implements OnClickListener {

	private TextView txt_mode;
	private TextView txt_by;
	private TextView txt_measure;
	private TextView txt_unit;
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

		ed_user_input = (EditText) rootView.findViewById(R.id.ed_input_units);
		btn_add = (Button) rootView.findViewById(R.id.btn_add);

		spim_catagory = (Spinner) rootView.findViewById(R.id.spin_catagory);
		spim_mode = (Spinner) rootView.findViewById(R.id.spin_mode);
		spim_by = (Spinner) rootView.findViewById(R.id.spin_by);

		btn_add.setOnClickListener(this);

		return rootView;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btn_add:

			if (carbon_value != 0) {

				CarbonFootprintDataBuilder builder = new CarbonFootprintDataBuilder();
				CarbonFootprintData newData;
				newData = builder.setId(0).setModeID(getID())
						.setStartTimeStamp(Calendar.getInstance().getTime())
						.create();
				((MyApplication) getActivity().getApplication())
						.getCarbonFootprintDataManager().add(getActivity(),
								newData);
				Toast.makeText(
						v.getContext(),
						"Your Carbon foot Print is recorded. Appreciate you being responsible.\r\nPlant and grow trees!!",
						Toast.LENGTH_LONG).show();
			} else
				Toast.makeText(v.getContext(),
						"You are already at 0. Well Done. No footprint!!",
						Toast.LENGTH_LONG).show();

			break;
		default:
			break;
		}
	}

	private int getID() {
		// TODO Auto-generated method stub
		return 0;
	}
}
