package huitca1212.alubia13.utils;

import android.content.Context;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.widget.Toast;

public class Notifications {

	public static void showSnackBar(CoordinatorLayout coordinatorLayout, String text) {
		Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG).show();
	}

	public static void showToast(Context ctx, String text) {
		Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
	}
}
