package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.field.ForeignCollectionField;

import java.util.ArrayList;
import java.util.Collection;

public class ScheduleWrapper {
	@DatabaseField(generatedId = true, columnName = "id") private Integer id;
	@SerializedName("title") @DatabaseField(columnName = "title") private String title;
	@SerializedName("calendar") @ForeignCollectionField(eager = true, columnName = "calendar") private Collection<ScheduleDay> scheduleDays;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public ArrayList<ScheduleDay> getScheduleDays() {
		return (ArrayList<ScheduleDay>)scheduleDays;
	}

	public void setScheduleDays(Collection<ScheduleDay> scheduleDays) {
		this.scheduleDays = scheduleDays;
	}
}