package com.gs.gscale.fragments;

import info.androidhive.slidingmenu.R;

import java.util.Calendar;
import java.util.Date;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
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

import com.gs.gscale.MainActivity;
import com.gs.gscale.MyApplication;
import com.gs.gscale.helpers.CarbonFootprintDataBuilder;
import com.gs.gscale.model.CarbonFootprintData;

public class TravellerFragment extends Fragment implements OnClickListener,
		OnItemSelectedListener {

	private TextView txt_mode;
	private TextView txt_by;
	private TextView txt_measure;
	private TextView txt_unit;
	private TextView txt_carbon;
	private TextView txt_distance;
	private Button btn_start;
	private Button btn_stop;
	Location source, destination;
	double distance;
	private Spinner spim_mode;
	private Spinner spim_by;

	private double carbon_value;
	private Date startDate = null;

	public TravellerFragment() {
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_traveller,
				container, false);

		txt_mode = (TextView) rootView.findViewById(R.id.txt_mode);
		txt_by = (TextView) rootView.findViewById(R.id.txt_by);
		txt_measure = (TextView) rootView.findViewById(R.id.txt_measure);
		txt_unit = (TextView) rootView.findViewById(R.id.txt_unit);
		txt_carbon = (TextView) rootView.findViewById(R.id.txt_carbon);
		txt_distance = (TextView) rootView.findViewById(R.id.txt_distance);

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
			startDate = Calendar.getInstance().getTime();
			txt_distance.setText("Running...");
			txt_carbon.setText("Compute when stopped.");

			source = getLocation();
			try {
				txt_distance.setText("Running...[" + source.getLatitude() + " "
						+ source.getLongitude() + "]");
			} catch (NullPointerException E) {
			}

			break;
		case R.id.btn_stop:

			double mf = ((MyApplication) (getActivity().getApplication()))
					.getCarbonMetaEntryManager().getMulFac(getID());
			try {
				destination = getLocation();
				distance = source.distanceTo(destination);
			} catch (Exception E) {
				distance = 0;
			}

			distance = distance / 1000;

			txt_distance.setText("" + distance);
			carbon_value = distance * mf;

			CarbonFootprintDataBuilder builder = new CarbonFootprintDataBuilder();
			CarbonFootprintData carbonData = builder
					.setCarbonData(carbon_value).setId(0).setModeID(getId())
					.setStartTimeStamp(startDate)
					.setEndTimeStamp(Calendar.getInstance().getTime()).create();

			((MyApplication) (getActivity().getApplication()))
					.getCarbonFootprintDataManager().add(getActivity(),
							carbonData);

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

	public Location setLocation() {
		LocationManager locationManager = (LocationManager) getActivity()
				.getSystemService(Context.LOCATION_SERVICE);
		// defining location listener
		System.out.println("entered geolocation");
		Location location = null;
		LocationListener locationListener = new LocationListener() {

			@Override
			public void onLocationChanged(Location location) {
				// TODO Auto-generated method stub
				// makeUseOfNewLocation(location);
				System.out.println("latitude");

			}

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}

		};
		// registering the location listener to location manager
		System.out.println("locationMangerregistering");
		if (location == null) {
			System.out.println("Registering location updates");
			locationManager.requestLocationUpdates(
					LocationManager.GPS_PROVIDER, 10000, 0, locationListener);
			if (locationManager != null) {
				System.out.println("getting the last known location ");
				String locationProvider = LocationManager.GPS_PROVIDER;
				// Or use LocationManager.GPS_PROVIDER

				location = locationManager
						.getLastKnownLocation(locationProvider);
			}
			if (location != null) {
				System.out.println("location updated successfully");
				System.out.println(" " + location.getLatitude());
			}
		}

		return location;
	}

	public Location getLocation() {
		Location location = null;
		// Location access ON or OFF checking
		ContentResolver contentResolver = getActivity().getContentResolver();
		@SuppressWarnings("deprecation")
		boolean gpsStatus = Settings.Secure.isLocationProviderEnabled(
				contentResolver, LocationManager.GPS_PROVIDER);
		if (!gpsStatus) {
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(
					getActivity());

			alertDialog.setTitle("Make your location accessible ...");
			alertDialog
					.setMessage("Your Location is not accessible to us.To show location you have to enable it.");

			alertDialog.setNegativeButton("Enable",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							startActivityForResult(
									new Intent(
											android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS),
									0);
						}
					});

			alertDialog.setPositiveButton("Cancel",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							Toast.makeText(
									getActivity(),
									"Remember to show location you have to eanable it !",
									Toast.LENGTH_SHORT).show();
							dialog.cancel();
						}
					});

			alertDialog.show();
		} else {
			location = setLocation();
		}
		return location;
	}
}
