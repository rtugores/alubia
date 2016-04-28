package huitca1212.alubia13.business.listener;

public abstract class AllBusinessListener <T> {
	public void onDatabaseSuccess(T object) {
	}

	public void onServerSuccess(T object) {
	}

	public void onFailure(String result) {
	}
}
