package huitca1212.alubia13.model.album;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AlbumItem implements Serializable {
	@SerializedName("title") private String title;
	@SerializedName("links") private List<AlbumLink> links;

	public String getTitle() {
		return title;
	}

	public List<AlbumLink> getLinks() {
		return links;
	}
}