package huitca1212.alubia13.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import huitca1212.alubia13.service.AlubiaService;

public class CommentsWrapper {
	@SerializedName("resultado") private String result;
	@SerializedName("foro") private ArrayList<Comment> comments;

	public String getResult() {
		return result;
	}

	public ArrayList<Comment> getComments() {
		return comments;
	}

	public void setComments(ArrayList<Comment> comments) {
		this.comments = comments;
	}

}
