package huitca1212.alubia13.model.news;

import com.google.gson.annotations.SerializedName;

import com.j256.ormlite.field.DatabaseField;

public class News {
	@SerializedName("fecha") @DatabaseField(columnName = "date") private String date;
	@SerializedName("comentario") @DatabaseField(columnName = "comment") private String comment;
	@SerializedName("id") @DatabaseField(id = true, columnName = "id", canBeNull = false) private Integer id;

	public String getDate() {
		return date;
	}

	public String getComment() {
		return comment;
	}

	public Integer getId() {
		return id;
	}
}

