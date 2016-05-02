package huitca1212.alubia13.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class AlubiaService {

	private static final String BASE_URL = "http://rjapps.x10host.com";

	public static <T> T getDataFromRequest(String url, Class<T> classofT) {
		OkHttpClient client = new OkHttpClient();

		Request request = new Request.Builder()
				.url(BASE_URL + url)
				.build();
		try {
			Response response = client.newCall(request).execute();
			InputStream in = response.body().byteStream();
			String body = slurp(in, 1024);
			GsonBuilder gsonBuilder = new GsonBuilder();
			Gson gson = gsonBuilder.create();
			return gson.fromJson(body, classofT);
		} catch (IOException e) {
			Log.e(AlubiaService.class.getName(), "Exception in service: " + e.getMessage(), e);
			return null;
		} catch (JsonSyntaxException e) {
			Log.e(AlubiaService.class.getName(), "Exception while parsing: " + e.getMessage(), e);
			return null;
		}
	}

	public static String slurp(final InputStream is, final int bufferSize) throws IOException {
		final char[] buffer = new char[bufferSize];
		final StringBuilder out = new StringBuilder();
		Reader in;
		in = new InputStreamReader(is, "UTF-8");
		for (; ; ) {
			int rsz = in.read(buffer, 0, buffer.length);
			if (rsz < 0) {
				break;
			}
			out.append(buffer, 0, rsz);
		}
		return out.toString();
	}
}
