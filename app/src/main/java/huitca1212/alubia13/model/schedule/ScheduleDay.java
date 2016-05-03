package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.ArrayList;

public class ScheduleDay implements Serializable {
	private static final long serialVersionUID = 875034706902629931L;
	@SerializedName("title") @DatabaseField(columnName = "title") private String title;
	@SerializedName("day") @DatabaseField(columnName = "day") private String day;
	@SerializedName("description") @DatabaseField(columnName = "description") private String description;
	@SerializedName("events") @DatabaseField(columnName = "events") private ArrayList<ScheduleEvent> events;

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