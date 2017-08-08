package huitca1212.alubia13.model.album;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class AlbumWrapper implements Serializable {
	@SerializedName("album") private List<AlbumItem> albumItems;

	public List<AlbumItem> getAlbumItems() {
		return albumItems;
	}
}