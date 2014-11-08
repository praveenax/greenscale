package com.gs.gscale.model;

public class CarbonMetaEntry {

	public String catagory;
	public String mode;
	public String by;
	public String measurement;
	public String unit;
	public double multFactor;
	public int id;
	public int iconResID;

	public CarbonMetaEntry(String catagory, String mode, String by,
			String measurement, String unit, double multFactor, int iD,
			int iconResID) {
		super();
		this.catagory = catagory;
		this.mode = mode;
		this.by = by;
		this.measurement = measurement;
		this.unit = unit;
		this.multFactor = multFactor;
		this.id = iD;
		this.iconResID = iconResID;
	}
}