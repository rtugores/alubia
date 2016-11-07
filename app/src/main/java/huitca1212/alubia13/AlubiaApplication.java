package huitca1212.alubia13;

import android.app.Application;
import android.content.Context;

public class AlubiaApplication extends Application {

	private static Context instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;
	}

	public static Context getInstance() {
		return instance;
	}

}