package com.gs.gscale.managers;

import java.util.ArrayList;
import java.util.List;

import com.gs.gscale.model.CarbonMetaEntry;

public class CarbonMetaEntryManager {

	List<CarbonMetaEntry> myCarbonMetaEntryList = null;

	private List<CarbonMetaEntry> getList() {
		myCarbonMetaEntryList = new ArrayList<CarbonMetaEntry>();
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Public",
				"Bus", "Distance", "KM", 0.8, 1, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Public",
				"Train", "Distance", "KM", 0.8, 2, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Public",
				"Air", "Distance", "KM", 0.8, 1, 3));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Private",
				"2Wheeler", "Distance", "KM", 0.8, 4, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Transport", "Public",
				"4Wheeler", "Distance", "KM", 0.8, 5, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("Electricity", "Null",
				"Null", "Unit", "KWh", 0.8, 6, 2));
		myCarbonMetaEntryList.add(new CarbonMetaEntry("LPG", "Null", "Null",
				"Weight", "KM", 0.8, 7, 2));

		return myCarbonMetaEntryList;
	}

	public CarbonMetaEntryManager() {
		myCarbonMetaEntryList = this.getList();
	}

	public List<String> getcatagory() {

		List<String> categoryList = new ArrayList<String>();

		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if (!((carbonMeta.catagory.equalsIgnoreCase("Null")) || (categoryList
					.contains(carbonMeta.catagory))))
				categoryList.add(carbonMeta.catagory);

		return categoryList;

	}

	public List<String> getMode(String category) {

		List<String> modeList = new ArrayList<String>();

		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if ((!((carbonMeta.mode.equalsIgnoreCase("Null")) || (modeList
					.contains(carbonMeta.mode))))
					&& (carbonMeta.catagory.equalsIgnoreCase(category)))
				modeList.add(carbonMeta.mode);

		if (0 == modeList.size())
			return null;
		return modeList;
	}

	public List<String> getBy(String catagory, String mode) {

		List<String> byList = new ArrayList<String>();

		for (CarbonMetaEntry carbonMeta : myCarbonMetaEntryList)
			if ((!((carbonMeta.mode.equalsIgnoreCase("Null")) || (byList
					.contains(carbonMeta.mode))))
					&& (carbonMeta.catagory.equalsIgnoreCase(catagory))
					&& (carbonMeta.mode.equalsIgnoreCase(mode)))
				byList.add(carbonMeta.mode);

		if (0 == byList.size())
			return null;
		return byList;
	}

	public int getID(String category, String mode, String by) {
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
}
