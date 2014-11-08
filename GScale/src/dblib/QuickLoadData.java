package dblib;

import android.content.Context;
import android.preference.PreferenceManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.google.gson.Gson;

public class QuickLoadData {

	private List<CarbonFootprintData> mData;

	public String toJson() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}

	public static QuickLoadData fromJson(String ticketsJson) {
		Gson gson = new Gson();
		return (QuickLoadData) gson.fromJson(ticketsJson, QuickLoadData.class);
	}
}

public QuickLoadData getQuickLoadData(Context context)
{
	QuickLoadData mDataManager = null;

	String dataJson = PreferenceManager.getDefaultSharedPreferences(
			context).getString("MyData", "");

	if (0 == dataJson.length())
		mDataManager = new QuickLoadData();
	else
		mDataManager = QuickLoadData.fromJson(dataJson);
}

public void saveQuickLoadData(Context context, QuickLoadData mDataManager)
{
	String dataJson = PreferenceManager.getDefaultSharedPreferences(
		context).getEditor().putString("MyData", mDataManager.toJson()).commit();
	
}