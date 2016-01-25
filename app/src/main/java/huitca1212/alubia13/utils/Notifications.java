package huitca1212.alubia13.utils;

import android.content.Context;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

public class Notifications {

	public static void showSnackBar(Context ctx, CoordinatorLayout coordinatorLayout, String text) {
		if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
			Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG).show();
		} else {
			showToast(ctx, text);
		}
	}

	public static void showToast(Context ctx, String text) {
			Toast.makeText(ctx, text, Toast.LENGTH_LONG).show();
	}
}
