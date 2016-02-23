package huitca1212.alubia13.business;

import java.util.ArrayList;

public interface AllListenerInterface <T> {
	void onDatabaseSuccess(ArrayList<T> list);

	void onServerSuccess(ArrayList<T> list);

	void onFailure(String result);
}