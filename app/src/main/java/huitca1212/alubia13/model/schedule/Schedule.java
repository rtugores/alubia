package huitca1212.alubia13.model.schedule;

public class Schedule {

	private String title;
	private String subtitle;

	public Schedule(String title) {
		this.title = title;
		this.subtitle = "";
	}

	public Schedule(String title, String subtitle) {
		this.title = title;
		this.subtitle = subtitle;
	}

	public String getTitle() {
		return title;
	}

	public String getSubtitle() {
		return subtitle;
	}
}