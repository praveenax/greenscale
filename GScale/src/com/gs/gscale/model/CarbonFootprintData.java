package com.gs.gscale.model;

import java.util.Date;

public class CarbonFootprintData {
	public int id;
	public double units;
	public double carbonData;
	public Date startTimeStamp;
	public Date endTimeStamp;
	public int modeID;

	public static CarbonFootprintData getCarbonFootprintData(int id,
			double units, double carbonData, Date startTimeStamp,
			Date endTimeStamp, int modeID) {

		CarbonFootprintData tmpCarbonFootprintData = new CarbonFootprintData();

		tmpCarbonFootprintData.id = id;
		tmpCarbonFootprintData.units = units;
		tmpCarbonFootprintData.carbonData = carbonData;
		tmpCarbonFootprintData.startTimeStamp = startTimeStamp;
		tmpCarbonFootprintData.endTimeStamp = endTimeStamp;
		tmpCarbonFootprintData.modeID = modeID;

		return tmpCarbonFootprintData;
	}
}