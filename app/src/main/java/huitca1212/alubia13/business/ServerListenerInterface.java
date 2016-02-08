package huitca1212.alubia13.business;

public interface ServerListenerInterface <T> {
	void onServerSuccess(T object);

	void onFailure(String result);
}
