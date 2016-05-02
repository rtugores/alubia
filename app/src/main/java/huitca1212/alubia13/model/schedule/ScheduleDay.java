package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class ScheduleDay implements Serializable {

	@SerializedName("title") private String title;
	@SerializedName("day") private String day;
	@SerializedName("description") private String description;
	@SerializedName("events") private ArrayList<ScheduleEvent> events;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public ArrayList<ScheduleEvent> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<ScheduleEvent> events) {
		this.events = events;
	}
}