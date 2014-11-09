package com.gs.gscale.managers;

import java.util.Calendar;
import java.util.Date;

import com.google.gson.Gson;
import com.gs.gscale.model.CarbonFootprintData;

import android.content.Context;
import android.preference.PreferenceManager;

public class CarbonFootprintQuickDataManager {

	public Date lastActivityDate = null;
	public double totalFootprint = 0;
	public double fulltotalFootprint = 0;
	public CarbonFootprintData lastActivity = null;
	public double yesterdayFootprint = 0;

	public static CarbonFootprintQuickDataManager getInstance(Context context) {

		String dataJson = PreferenceManager
				.getDefaultSharedPreferences(context).getString("MyQuickData",
						"");

		if (0 == dataJson.length())
			return new CarbonFootprintQuickDataManager();

		CarbonFootprintQuickDataManager tmpData = CarbonFootprintQuickDataManager
				.fromJson(dataJson);

		Date today = Calendar.getInstance().getTime();

		if (!CarbonFootprintData.isSameDay(today, tmpData.lastActivityDate)) {
			tmpData.yesterdayFootprint = tmpData.totalFootprint;
			tmpData.totalFootprint = 0;
		}

		return tmpData;
	}

	private static CarbonFootprintQuickDataManager fromJson(String dataJson) {
		Gson gson = new Gson();
		return (CarbonFootprintQuickDataManager) gson.fromJson(dataJson,
				CarbonFootprintQuickDataManager.class);
	}

	public void saveQuickLoadData(Context context) {
		PreferenceManager.getDefaultSharedPreferences(context).edit()
				.putString("MyQuickData", this.toJson()).commit();
	}

	private String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public boolean update(Context context, CarbonFootprintData updateData) {

		if ((null != lastActivityDate)
				&& (CarbonFootprintData.isSameDay(lastActivityDate,
						updateData.startTimeStamp)))
			this.totalFootprint += updateData.carbonData;
		else {
			this.lastActivityDate = updateData.startTimeStamp;
			this.yesterdayFootprint = this.totalFootprint;
			this.totalFootprint = updateData.carbonData;
		}

		fulltotalFootprint += updateData.carbonData;

		this.lastActivity = updateData;

		saveQuickLoadData(context);
		return true;
	}

	public int getTressCount() {
		if (0 == fulltotalFootprint)
			return 0;
		else
			return  1 + (int) (fulltotalFootprint / 50);
	}
}
