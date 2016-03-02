package huitca1212.alubia13.business.listener;

public interface AllBusinessListener <T> extends ServerBusinessListener<T>, DatabaseBusinessListener<T> {
	void onFailure(String result);
}
