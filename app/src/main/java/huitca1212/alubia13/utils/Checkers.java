package huitca1212.alubia13.utils;

import android.content.Context;
import android.widget.EditText;

import java.util.Locale;

import huitca1212.alubia13.R;

public class Checkers {
	public static boolean hasStringBadWords(String thatString){
		String thatStringLowercase = thatString.toLowerCase(Locale.getDefault());
		return thatStringLowercase.contains("joder") || thatStringLowercase.contains("puta") ||
				thatStringLowercase.contains("ostia") || thatStringLowercase.contains("polla") ||
				thatStringLowercase.contains("imbecil") || thatStringLowercase.contains("poya") ||
				thatStringLowercase.contains("subnormal") || thatStringLowercase.contains("mierda") ||
				thatStringLowercase.contains("cabron") || thatStringLowercase.contains("coño") ||
				thatStringLowercase.contains("maric") || thatStringLowercase.contains("marik");
	}

	public static boolean isRightComment(Context ctx, String comment, EditText commentBox) {
		if (comment.matches("")) {
			commentBox.setText("");
			return false;
		}
		if (Checkers.hasStringBadWords(comment)) {
			Notifications.showToast(ctx, ctx.getString(R.string.forum_error_bad_words));
			return false;
		}
		return true;
	}
}
