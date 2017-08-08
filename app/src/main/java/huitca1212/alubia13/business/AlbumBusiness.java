package huitca1212.alubia13.business;

import android.content.Context;

import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.business.listener.AsyncTaskBusinessListener;
import huitca1212.alubia13.model.album.AlbumWrapper;
import huitca1212.alubia13.service.AlubiaService;
import huitca1212.alubia13.utils.FileUtils;

public class AlbumBusiness {
	private static final String ALBUM_FILENAME = "album_alubia.json";
	private static boolean dbOperationSuccess = false;

	public static void getAlbumContent(Context ctx, AllBusinessListener<AlbumWrapper> listener) {
		getDatabaseAlbumContent(ctx, listener);
	}

	public static void getDatabaseAlbumContent(final Context ctx, final AllBusinessListener<AlbumWrapper> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			AlbumWrapper albumWrapper;

			@Override
			public String onBackground() {
				String data = FileUtils.readFileToData(ctx, ALBUM_FILENAME);
				if (data != null) {
					albumWrapper = AlubiaService.convertStringToObject(data, AlbumWrapper.class);
					if (albumWrapper != null) {
						return DefaultAsyncTask.ASYNC_TASK_OK;
					}
				}
				return DefaultAsyncTask.ASYNC_TASK_ERROR;
			}


			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onDatabaseSuccess(albumWrapper);
					dbOperationSuccess = true;
				}
				getBackendAlbumContent(ctx, listener);
			}
		}).execute();
	}

	public static void getBackendAlbumContent(final Context ctx, final AllBusinessListener<AlbumWrapper> listener) {
		new DefaultAsyncTask(new AsyncTaskBusinessListener() {
			String dataString;
			AlbumWrapper dataObject;

			@Override
			public String onBackground() {
				String url = "/album_alubia.json";
				dataString = AlubiaService.getStringFromRequest(url);
				if (dataString != null && FileUtils.writeDataToFile(ctx, ALBUM_FILENAME, dataString)) {
					dataObject = AlubiaService.convertStringToObject(dataString, AlbumWrapper.class);
					if (dataObject != null) {
						return DefaultAsyncTask.ASYNC_TASK_OK;
					} else {
						return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
					}
				} else {
					return DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR;
				}
			}

			@Override
			public void onFinish(String result) {
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK)) {
					listener.onServerSuccess(dataObject);
				} else {
					if (!dbOperationSuccess) {
						listener.onFailure(result);
					}
				}
			}
		}).execute();
	}
}
