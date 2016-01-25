package huitca1212.alubia13.business;

import huitca1212.alubia13.model.Comment;

public interface AllListenerResultBusiness <T> {

	void onServerSuccess(Comment comment);

	void onFailure(String result);
}
