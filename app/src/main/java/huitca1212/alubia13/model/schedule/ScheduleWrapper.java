package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ScheduleWrapper {
	@SerializedName("title") private String title;
	@SerializedName("calendar") private ArrayList<ScheduleDay> scheduleDays;

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
