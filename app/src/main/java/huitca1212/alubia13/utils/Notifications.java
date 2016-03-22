package huitca1212.alubia13.utils;

import android.content.Context;
import android.graphics.Color;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class Notifications {

	public static void showSnackBar(CoordinatorLayout coordinatorLayout, String text) {
		Snackbar mySnackbar = Snackbar.make(coordinatorLayout, text, Snackbar.LENGTH_LONG);
		View view = mySnackbar.getView();
		TextView textView = (TextView)view.findViewById(android.support.design.R.id.snackbar_text);
		textView.setTextColor(Color.WHITE);
		mySnackbar.show();
	}

	public static void showToast(Context ctx, String text) {
		Toast.makeText(ctx, text, Toast.LENGTH_SHORT).show();
	}
}
