package huitca1212.alubia13.model.album;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AlbumLink implements Serializable {
	@SerializedName("title") private String title;
	@SerializedName("url") private String url;

	public String getTitle() {
		return title;
	}

	public String getUrl() {
		return url;
	}
}