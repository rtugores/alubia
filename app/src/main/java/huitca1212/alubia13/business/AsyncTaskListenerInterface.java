package huitca1212.alubia13.business;

import java.io.IOException;

public interface AsyncTaskListenerInterface {
	void onStart();

	String onBackground() throws IOException;

	void onFinish(String result);
}