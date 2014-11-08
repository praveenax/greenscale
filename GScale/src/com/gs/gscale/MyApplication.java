package com.gs.gscale;

import android.app.Application;

import com.gs.gscale.managers.CarbonFootprintDataManager;
import com.gs.gscale.managers.CarbonFootprintQuickDataManager;
import com.gs.gscale.managers.CarbonMetaEntryManager;

public class MyApplication extends Application {

	private CarbonFootprintDataManager mCarbonFootprintDataManager;
	private CarbonMetaEntryManager mCarbonMetaEntryManager;
	private CarbonFootprintQuickDataManager mCarbonFootprintQuickDataManager;
	
	@Override
	public void onCreate() {
		mCarbonMetaEntryManager = new CarbonMetaEntryManager();
		mCarbonFootprintDataManager = CarbonFootprintDataManager.getInstance(this);
		mCarbonFootprintQuickDataManager = CarbonFootprintQuickDataManager.getInstance(this);
	}

	public CarbonFootprintDataManager getCarbonFootprintDataManager() {
		return mCarbonFootprintDataManager;
	}

	public CarbonMetaEntryManager getCarbonMetaEntryManager() {
		return mCarbonMetaEntryManager;
	}
}
