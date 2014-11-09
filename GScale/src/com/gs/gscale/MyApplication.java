package com.gs.gscale;

import android.app.Application;

import com.gs.gscale.managers.CarbonFootprintDataManager;
import com.gs.gscale.managers.CarbonFootprintQuickDataManager;
import com.gs.gscale.managers.CarbonMetaEntryManager;

public class MyApplication extends Application {

	private CarbonFootprintDataManager mCarbonFootprintDataManager;
	private CarbonMetaEntryManager mCarbonMetaEntryManager;
	private CarbonFootprintQuickDataManager mCarbonFootprintQuickDataManager;
	
	public CarbonFootprintQuickDataManager getmCarbonFootprintQuickDataManager() {
		return mCarbonFootprintQuickDataManager;
	}

	public void setmCarbonFootprintQuickDataManager(
			CarbonFootprintQuickDataManager mCarbonFootprintQuickDataManager) {
		this.mCarbonFootprintQuickDataManager = mCarbonFootprintQuickDataManager;
	}

	@Override
	public void onCreate() {
		mCarbonMetaEntryManager = new CarbonMetaEntryManager();
		mCarbonFootprintDataManager = CarbonFootprintDataManager.getInstance(this);
		mCarbonFootprintQuickDataManager = CarbonFootprintQuickDataManager.getInstance(this);
		mCarbonFootprintDataManager.setCarbonFootprintQuickDataManager(mCarbonFootprintQuickDataManager);
	}

	public CarbonFootprintQuickDataManager getCarbonFootprintQuickDataManager() {
		return mCarbonFootprintQuickDataManager;
	}

}
