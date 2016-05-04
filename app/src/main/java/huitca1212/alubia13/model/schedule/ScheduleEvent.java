package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class ScheduleEvent implements Serializable {
	private static final long serialVersionUID = -139948390714806666L;
	@DatabaseField(generatedId = true, columnName = "id") private Integer id;
	@SerializedName("hour") @DatabaseField(columnName = "hour") private String hour;
	@SerializedName("name") @DatabaseField(columnName = "name") private String name;
	@SerializedName("place") @DatabaseField(columnName = "place") private String place;
	@SerializedName("details") @DatabaseField(columnName = "details", foreign = true) private transient ScheduleEventDetails scheduleEventDetails;
	@DatabaseField(columnName = "scheduleDay", foreign = true, index = true) private transient ScheduleDay scheduleDay;

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

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