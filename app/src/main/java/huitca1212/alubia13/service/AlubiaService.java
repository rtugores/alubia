package huitca1212.alubia13.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import android.util.Log;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AlubiaService {
	private static final String BASE_URL = "http://rjapps.x10host.com";

	public static <T> T getObjectFromRequest(String url, Class<T> classofT) {
		String body = getStringFromRequest(url);
		return convertStringToObject(body, classofT);
	}

	public static String getStringFromRequest(String url) {
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder()
				.url(BASE_URL + url)
				.build();
		try {
			Response response = client.newCall(request).execute();
			return response.body().string();
		} catch (IOException e) {
			Log.e(AlubiaService.class.getName(), "Exception in service: " + e.getMessage(), e);
			return null;
		}
	}

	public static <T> T convertStringToObject(String body, Class<T> classofT) {
		try {
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			return gson.fromJson(body, classofT);
		} catch (JsonSyntaxException e) {
			Log.e(AlubiaService.class.getName(), "Exception while parsing: " + e.getMessage(), e);
			return null;
		}
	}
}