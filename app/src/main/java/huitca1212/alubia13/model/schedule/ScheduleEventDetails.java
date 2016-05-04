package huitca1212.alubia13.model.schedule;

import com.google.gson.annotations.SerializedName;

import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;

public class ScheduleEventDetails implements Serializable {
	private static final long serialVersionUID = -7227148394019800555L;
	@DatabaseField(generatedId = true, columnName = "id") private Integer id;
	@SerializedName("title") @DatabaseField(columnName = "title") private String title;
	@SerializedName("description") @DatabaseField(columnName = "description") private String description;

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