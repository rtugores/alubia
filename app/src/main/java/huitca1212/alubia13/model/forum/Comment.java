package huitca1212.alubia13.model.forum;

import com.google.gson.annotations.SerializedName;

import com.j256.ormlite.field.DatabaseField;

import android.support.annotation.Keep;

@Keep
public class Comment {
	@SerializedName("fecha") @DatabaseField(columnName = "date") private String date;
	@SerializedName("usuario") @DatabaseField(columnName = "user") private String user;
	@SerializedName("penya") @DatabaseField(columnName = "group")  private String group;
	@SerializedName("comentario") @DatabaseField(columnName = "comment") private String comment;
	@SerializedName("vip") @DatabaseField(columnName = "vip") private String vip;
	@SerializedName("rev") @DatabaseField(columnName = "rev") private String rev;
	@SerializedName("ban") @DatabaseField(columnName = "ban") private String ban;
	@SerializedName("id") @DatabaseField(id = true, columnName = "id", canBeNull = false) private Integer id;

	public String getDate() {
		return date;
	}

	public String getUser() {
		return user;
	}

	public String getGroup() {
		return group;
	}

	public String getComment() {
		return comment;
	}

	public String getVip() {
		return vip;
	}

	public String getRev() {
		return rev;
	}

	public String getBan() {
		return ban;
	}

	public Integer getId() {
		return id;
	}
}