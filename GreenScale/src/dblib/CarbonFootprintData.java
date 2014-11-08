package dblib;

import java.sql.Date;

public class CarbonFootprintData {
	public CarbonFootprintData() {
		this.id = 0;
		this.units = 0.0;
		this.carbonData = 0.0;
		this.startTimeStamp = new Date(new java.util.Date().getTime());
		this.endTimeStamp = new Date(new java.util.Date().getTime());
		this.mode = FootprintMode.Car;
		
	}
	
	public CarbonFootprintData(int id, double units, double carbonData, java.util.Date startTime, java.util.Date endTime, FootprintMode mode) {
		this.id = id;
		this.units = units;
		this.carbonData = carbonData;
		this.startTimeStamp = new Date(startTime.getTime());
		this.endTimeStamp = new Date(endTime.getTime());
		this.mode = mode;
	}
	
	public static int CreateCarbonFootprintData(String data){ //Need to figure out what i will be getting from database
		
		
		
		return 0;
	}
	
	public static int StoreCarbonFootprintData(CarbonFootprintData data) {
		return 0;
	}
	
	int id;
	double units;
	double carbonData;
	Date startTimeStamp;
	Date endTimeStamp;
	FootprintMode mode;
	 
}
