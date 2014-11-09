package com.gs.gscale.managers;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;

import com.gs.gscale.model.CarbonMetaEntry;

public class CarbonMetaEntryManager {

	public List<CarbonMetaEntry> myCarbonMetaEntryList = null;

	private List<CarbonMetaEntry> getList() {
		myCarbonMetaEntryList = new ArrayList<CarbonMetaEntry>();
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Public",
				"Bus", "Distance", "KM", 0.6, 1, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Public",
				"Train", "Distance", "KM", 0.1, 2, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Public",
				"Air", "Distance", "KM", 1.3, 1, 3));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Private",
				"2Wheeler", "Distance", "KM", 2.2, 4, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Private",
				"4Wheeler", "Distance", "KM", 3.75, 5, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Electricity", "Null",
				"Null", "Unit", "KWh", 5.2, 6, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("LPG", "Null", "Null",
				"Weight", "Kg", 18, 7, 2));

		return myCarbonMetaEntryList;
	}

	public CarbonMetaEntryManager(Context context) {
		myCarbonMetaEntryList = this.getList();
	}

	public String[] getcatagory() {

		List<String> categoryList = new ArrayList<String>();

		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if (!((carbonMeta.catagory.equalsIgnoreCase("Null")) || (categoryList
					.contains(carbonMeta.catagory))))
				categoryList.add(carbonMeta.catagory);

		return (String[]) categoryList.toArray(new String[categoryList.size()]);

	}

	public String[] getMode(String category) {

		List<String> modeList = new ArrayList<String>();

		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if ((!((carbonMeta.mode.equalsIgnoreCase("Null")) || (modeList
					.contains(carbonMeta.mode))))
					&& (carbonMeta.catagory.equalsIgnoreCase(category)))
				modeList.add(carbonMeta.mode);

		if (0 == modeList.size())
			return null;
		return (String[]) modeList.toArray(new String[modeList.size()]);
	}

	public String[] getBy(String catagory, String mode) {

		List<String> byList = new ArrayList<String>();

		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if ((!((carbonMeta.mode.equalsIgnoreCase("Null")) || (byList
					.contains(carbonMeta.mode))))
					&& (carbonMeta.catagory.equalsIgnoreCase(catagory))
					&& (carbonMeta.mode.equalsIgnoreCase(mode)))
				byList.add(carbonMeta.by);

		if (0 == byList.size())
			return null;
		return (String[]) byList.toArray(new String[byList.size()]);
	}

	public int getID(String category, String mode, String by) {
		
		if (null == mode)
			mode = "Null";
	
		if (null == by)
			by = "Null";
		
		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if ((carbonMeta.catagory.equalsIgnoreCase(category))
					&& (carbonMeta.mode.equalsIgnoreCase(mode))
					&& (carbonMeta.by.equalsIgnoreCase(by)))
				return carbonMeta.id;
		return 0;
	}

	public double getMulFac(int id) {
		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if (id == carbonMeta.id)
				return carbonMeta.multFactor;
		return 0;
	}

	public int IconResID(int id) {
		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if (id == carbonMeta.id)
				return carbonMeta.iconResID;
		return 0;
	}

	public String getMeasurement(int id) {
		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if (id == carbonMeta.id)
				return carbonMeta.measurement;
		return "Measurement";
	}

	public String getUnit(int id) {
		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if (id == carbonMeta.id)
				return carbonMeta.unit;
		return "Unit";
	}
}
