package huitca1212.alubia13.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AlubiaService {

	private static String BASE_URL = "http://rjapps.x10host.com";

	public static <T> T getDataFromRequest(String url, Class<T> classofT) {
		String jsonResult;
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(BASE_URL + url)
				.build();
		try {
			Response response = client.newCall(request).execute();
			jsonResult = response.body().string();
		} catch (IOException e) {
			return null;
		}
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		T data = gson.fromJson(jsonResult, classofT);
		return data;
	}

}
