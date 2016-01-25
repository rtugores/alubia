package huitca1212.alubia13.utils;

import java.util.Locale;

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
}
