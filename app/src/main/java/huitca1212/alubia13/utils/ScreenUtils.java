package huitca1212.alubia13.utils;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Point;
import android.util.TypedValue;
import android.view.Display;
import android.view.WindowManager;

import huitca1212.alubia13.AlubiaApplication;

public class ScreenUtils {

	private static int screenWidth;

	public static int dpToPx(Context context, int dpMeasure) {
		Resources r = context.getResources();
		return (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP,
				dpMeasure,
				r.getDisplayMetrics()
		);
	}

	public static int getScreenWidth() {
		if (screenWidth == 0) {
			WindowManager wm = (WindowManager) AlubiaApplication.getInstance().getSystemService(Context.WINDOW_SERVICE);
			Display display = wm.getDefaultDisplay();
			Point size = new Point();
			display.getSize(size);
			screenWidth = size.x;
			return screenWidth;
		} else {
			return screenWidth;
		}
	}
}
