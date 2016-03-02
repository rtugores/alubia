package huitca1212.alubia13.business.listener;

import java.io.IOException;

public interface AsyncTaskBusinessListener {
	void onStart();

	String onBackground() throws IOException;

	void onFinish(String result);
}