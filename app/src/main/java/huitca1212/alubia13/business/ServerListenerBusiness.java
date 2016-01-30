package huitca1212.alubia13.business;

public interface ServerListenerBusiness <T> {
	void onServerSuccess(T object);

	void onFailure(String result);
}
