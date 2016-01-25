package huitca1212.alubia13.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class NewsWrapper {
	@SerializedName("resultado") public String result;
	@SerializedName("novedades") public ArrayList<News> news;

	public String getResult() {
		return result;
	}

	public ArrayList<News> getNews() {
		return news;
	}

	public void setNews(ArrayList<News> news) {
		this.news = news;
	}
}
