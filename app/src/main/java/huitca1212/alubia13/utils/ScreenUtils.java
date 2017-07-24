package huitca1212.alubia13.utils;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;

public class ScreenUtils {

	public static int convertDpToPixel(Context context, int dpMeasure) {
		Resources r = context.getResources();
		return (int) TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP,
				dpMeasure,
				r.getDisplayMetrics()
		);
	}
}
