package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ScheduleEventDetails implements Serializable {
	private static final long serialVersionUID = -7227148394019800555L;
	@SerializedName("title") private String title;
	@SerializedName("description") private String description;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}