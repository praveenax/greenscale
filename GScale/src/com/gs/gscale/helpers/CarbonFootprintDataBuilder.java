package com.gs.gscale.helpers;

import java.util.Calendar;
import java.util.Date;

import com.gs.gscale.model.CarbonFootprintData;

public class CarbonFootprintDataBuilder {

	public int id;
	public double units;
	public double carbonData;
	public Date startTimeStamp = null;
	public Date endTimeStamp = null;
	public int modeID;

	public CarbonFootprintDataBuilder setId(int id) {
		this.id = id;
		return this;
	}

	public CarbonFootprintDataBuilder setUnits(double units) {
		this.units = units;
		return this;
	}

	public CarbonFootprintDataBuilder setCarbonData(double carbonData) {
		this.carbonData = carbonData;
		return this;
	}

	public CarbonFootprintDataBuilder setStartTimeStamp(Date startTimeStamp) {
		this.startTimeStamp = startTimeStamp;
		return this;
	}

	public CarbonFootprintDataBuilder setEndTimeStamp(Date endTimeStamp) {
		this.endTimeStamp = endTimeStamp;
		return this;
	}

	public CarbonFootprintDataBuilder setModeID(int modeID) {
		this.modeID = modeID;
		return this;
	}
	
	public CarbonFootprintData create()
	{
		if (null == startTimeStamp)
			startTimeStamp = Calendar.getInstance().getTime();
		
		if (null == endTimeStamp)
			endTimeStamp = (Date) startTimeStamp.clone();
		
		return CarbonFootprintData.getCarbonFootprintData(id, units, carbonData, startTimeStamp, endTimeStamp, modeID);
	}
}
