package huitca1212.alubia13.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;

public class Animations {


	public static void crossfadeViews(final ViewGroup from, ViewGroup to, Context ctx) {
		int shortAnimationDuration = ctx.getResources().getInteger(android.R.integer.config_shortAnimTime);

		if (Build.VERSION.SDK_INT < Build.VERSION_CODES.HONEYCOMB_MR1) {
			from.setVisibility(View.GONE);
		} else {
			to.setAlpha(0f);

			to.setVisibility(View.VISIBLE);

			to.animate()
					.alpha(1f)
					.setDuration(shortAnimationDuration)
					.setListener(null);

			from.animate()
					.alpha(0f)
					.setDuration(shortAnimationDuration)
					.setListener(new AnimatorListenerAdapter() {
						@Override
						public void onAnimationEnd(Animator animation) {
							from.setVisibility(View.GONE);
						}
					});
		}
	}
}
