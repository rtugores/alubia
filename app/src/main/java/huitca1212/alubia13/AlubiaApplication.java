package huitca1212.alubia13;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import android.app.Application;
import android.content.Context;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

public class AlubiaApplication extends Application {

	private static Context instance;

	@Override
	public void onCreate() {
		super.onCreate();
		instance = this;

		setPicassoInstance();
	}

	public void setPicassoInstance() {
		OkHttpClient okHttpClient = new OkHttpClient().newBuilder()
				.connectTimeout(3, TimeUnit.SECONDS)
				.build();

		Picasso picasso = new Picasso.Builder(this)
				.downloader(new OkHttp3Downloader(okHttpClient))
				.build();

		Picasso.setSingletonInstance(picasso);
	}

	public static Context getInstance() {
		return instance;
	}
}