package huitca1212.alubia13.model;

import com.google.gson.annotations.SerializedName;

public class CommentWrapper {
	@SerializedName("resultado") private String result;
	@SerializedName("comentario") private Comment comment;

	public String getResult() {
		return result;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

}
