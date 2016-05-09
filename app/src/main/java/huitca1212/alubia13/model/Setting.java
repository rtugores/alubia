package huitca1212.alubia13.model;

public class Setting {
	private String title;
	private String subtitle;

	public Setting(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}
}