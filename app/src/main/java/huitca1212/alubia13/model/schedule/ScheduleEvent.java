package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ScheduleEvent implements Serializable {
	@SerializedName("name") private String name;
	@SerializedName("place") private String place;
	@SerializedName("details") private ScheduleEventDetails scheduleEventDetails;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public ScheduleEventDetails getScheduleEventDetails() {
		return scheduleEventDetails;
	}

	public void setScheduleEventDetails(ScheduleEventDetails scheduleEventDetails) {
		this.scheduleEventDetails = scheduleEventDetails;
	}
}