package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import com.j256.ormlite.field.DatabaseField;

import java.util.ArrayList;

public class ScheduleWrapper {
	@SerializedName("title") @DatabaseField(columnName = "title") private String title;
	@SerializedName("calendar") @DatabaseField(columnName = "calendar") private ArrayList<ScheduleDay> scheduleDays;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<ScheduleDay> getScheduleDays() {
		return scheduleDays;
	}

	public void setScheduleDays(ArrayList<ScheduleDay> scheduleDays) {
		this.scheduleDays = scheduleDays;
	}
}