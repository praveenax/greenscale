package com.gs.gscale.model;

import java.text.DecimalFormat;
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
		tmpCarbonFootprintData.units = CarbonFootprintData.getDoubleRounded(units);
		tmpCarbonFootprintData.carbonData = carbonData;
		tmpCarbonFootprintData.startTimeStamp = startTimeStamp;
		tmpCarbonFootprintData.endTimeStamp = endTimeStamp;
		tmpCarbonFootprintData.modeID = modeID;

		return tmpCarbonFootprintData;
	}

	public static Boolean isSameDay(Date d1, Date d2) {
		return ((d1.getDay() == d2.getDay())
				&& (d1.getMonth() == d2.getMonth()) && (d1.getYear() == d2
				.getYear()));
	}

	public static double getDoubleRounded(double d) {
		DecimalFormat df = new DecimalFormat("###.##");
		return Double.parseDouble(df.format(d));
	}
}
