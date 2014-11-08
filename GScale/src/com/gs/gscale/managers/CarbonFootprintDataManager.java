package com.gs.gscale.managers;

import java.util.Date;
import java.util.List;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.gs.gscale.model.CarbonFootprintData;
import com.gs.gscale.model.CarbonFootprintDataAggregated;

public class CarbonFootprintDataManager {

	private List<CarbonFootprintData> mData;
	
	public static CarbonFootprintDataManager getInstance(Context context)
	{
		String dataJson = PreferenceManager
				.getDefaultSharedPreferences(context).getString("MyData", "");

		if (0 == dataJson.length())
			return new CarbonFootprintDataManager();
		else
			return CarbonFootprintDataManager.fromJson(dataJson);
		
	}

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public static CarbonFootprintDataManager fromJson(String dataJson) {
		Gson gson = new Gson();
		return (CarbonFootprintDataManager) gson.fromJson(dataJson,
				CarbonFootprintDataManager.class);
	}
	
	private void save(Context context)
	{
		PreferenceManager.getDefaultSharedPreferences(context).edit()
		.putString("MyData", this.toJson()).commit();
	}
	
	private boolean addData(Context context, CarbonFootprintData data)
	{
		
		save(context);
		return true;
	}
	
	public boolean addData(Context context, Date StartDate, CarbonFootprintData data)
	{
		
		save(context);
		return true;
	}
	
	public boolean addData(Context context, Date StartDate, Date endDate, CarbonFootprintData data)
	{
		
		save(context);
		return true;
	}
	
	

	public List<CarbonFootprintData> getAllFootPrintData() {
		return mData;
	}

	public List<CarbonFootprintData> getTodayFootPrintData() {
		// ToDO
		return null;
	}

	public List<CarbonFootprintDataAggregated> getWeeklyFootPrintData() {
		// ToDO
		return null;
	}
}