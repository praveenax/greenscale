package com.gs.gscale.managers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.preference.PreferenceManager;

import com.google.gson.Gson;
import com.gs.gscale.model.CarbonFootprintData;
import com.gs.gscale.model.CarbonFootprintDataAggregated;
import com.gs.gscale.model.CarbonMetaEntry;

public class CarbonFootprintDataManager {

	private List<CarbonFootprintData> mData;
	private CarbonFootprintQuickDataManager mCarbonFootprintQuickDataManager = null;
	
	public CarbonFootprintDataManager()
	{
		mData = new ArrayList<CarbonFootprintData>();
	}
	
	public void setCarbonFootprintQuickDataManager(
			CarbonFootprintQuickDataManager mCarbonFootprintQuickDataManager) {
		this.mCarbonFootprintQuickDataManager = mCarbonFootprintQuickDataManager;
	}
	
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
		mCarbonFootprintQuickDataManager.update(context, data);
		return true;
	}

	public List<CarbonFootprintData> getAllFootPrintData() {
		return mData;
	}

	public List<CarbonFootprintData> getTodayFootPrintData() {
		List<CarbonFootprintData> selData = new ArrayList<CarbonFootprintData>();
		CarbonFootprintDataAggregated aggr = new CarbonFootprintDataAggregated();
		
		aggr.title = "Today";
		aggr.carbonData = 0;
		
		Date today = Calendar.getInstance().getTime();
		for(CarbonFootprintData data : mData) {
			if (!((today.getDay() == data.startTimeStamp.getDay() || today.getDay() == data.endTimeStamp.getDay())
					&& (today.getMonth() == data.startTimeStamp.getMonth()) && (today
						.getYear() == data.startTimeStamp.getYear()))) {
				selData.add(data);
				aggr.carbonData += data.carbonData;
			}
		}
		
		return selData;
		
		// Is aggregate data required?
	}

	public List<CarbonFootprintDataAggregated> getWeeklyFootPrintData() {
		List<CarbonFootprintDataAggregated> aggr = new ArrayList<CarbonFootprintDataAggregated>();
		for(int i = 0; i <4; i++) {
			CarbonFootprintDataAggregated data = new CarbonFootprintDataAggregated();
			data.title = "Week " + (i + 1);
			data.carbonData = 0;
			aggr.add(data);
		}
		
		Date today = Calendar.getInstance().getTime();
		for(CarbonFootprintData data : mData) {
			int days = this.getDaysDifference(today, data.startTimeStamp);
			if(days >= 0 && days <= 7) {
				aggr.get(0).carbonData += data.carbonData;
			} else if(days > 7 && days <= 14) {
				aggr.get(0).carbonData += data.carbonData;
			} else if(days >14 && days <= 21) {
				aggr.get(0).carbonData += data.carbonData;
			} else if(days > 21 && days <= 28) {
				aggr.get(0).carbonData += data.carbonData;
			}
		}


		return aggr;
	}

	private int getDaysDifference(Date date1, Date date2) {
		long diffms = Math.abs(date1.getTime() - date2.getTime());
		return (int)(diffms / (1000 * 60 * 60 * 24));
	}
	
	public void add(Context context, CarbonFootprintData entry)
	{
		mData.add(entry);
		save(context);
		mCarbonFootprintQuickDataManager.update(context, entry);
	}
}