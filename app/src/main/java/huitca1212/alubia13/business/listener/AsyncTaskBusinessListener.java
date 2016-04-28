package huitca1212.alubia13.business.listener;

import java.io.IOException;

public abstract class AsyncTaskBusinessListener {
	public void onStart() {
	}

	public String onBackground() throws IOException {
		return null;
	}

	public void onFinish(String result) {
	}
}