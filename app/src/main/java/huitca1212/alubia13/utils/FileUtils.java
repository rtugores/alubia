package huitca1212.alubia13.utils;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class FileUtils {

	public static String readFileToData(Context context, String filename) {
		FileInputStream fis = null;
		BufferedReader br = null;
		try {
			fis = context.openFileInput(filename);
			br = new BufferedReader(new InputStreamReader(fis, "utf8"));
			StringBuilder builder = new StringBuilder();
			int ch;

			while ((ch = br.read()) != -1) {
				builder.append((char) ch);
			}
			return builder.toString();
		} catch (FileNotFoundException e) {
			// Goes here when there is no file yet
			return null;
		} catch (IOException e) {
			Log.e(FileUtils.class.getName(), "Exception in read to file: " + e.getMessage(), e);
			return null;
		} finally {
			try {
				if (br != null) {
					br.close();
				}
				if (fis != null) {
					fis.close();
				}
			} catch (IOException e) {
				//NOOP
			}
		}
	}

	public static boolean writeDataToFile(Context context, String fileName, String dataString) {
		FileOutputStream fos = null;
		BufferedWriter bw = null;
		try {
			fos = context.openFileOutput(fileName, Context.MODE_PRIVATE);
			bw = new BufferedWriter(new OutputStreamWriter(fos, "utf8"));
			bw.write(dataString);
			return true;
		} catch (IOException e) {
			Log.e(FileUtils.class.getName(), "Exception in write to file: " + e.getMessage(), e);
			return false;
		} finally {
			try {
				if (bw != null) {
					bw.close();
				}
				if (fos != null) {
					fos.close();
				}
			} catch (IOException e) {
				//NOOP
			}
		}
	}
}
