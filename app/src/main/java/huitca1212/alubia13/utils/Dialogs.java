package huitca1212.alubia13.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import huitca1212.alubia13.R;
import huitca1212.alubia13.ui.more.alubiaQuiz.AlubiaQuizSolutionActivity;

public class Dialogs {

	public interface DialogListener {
		void onPositive();

		void onNegative();
	}

	public static void showGenericDialog(final Context ctx, final DialogParams params, final DialogListener listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(params.getTitle());
		builder.setMessage(params.getMessage());
		builder.setPositiveButton(params.getPositiveButton(), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				if (listener != null) {
					listener.onPositive();
				}
			}
		});
		builder.setNegativeButton(params.getNegativeButton(), new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
				if (listener != null) {
					listener.onNegative();
				}
			}
		});
		builder.show();
	}

	public static void showAlubiaQuizRightAnswerDialog(int questionNumber, final int answer, final Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(R.string.alubiaquiz_congrats);
		builder.setMessage(R.string.alubiaquiz_you_are_correct);
		if (questionNumber == 10) {
			builder.setPositiveButton(R.string.common_accept, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					Intent intent = new Intent(ctx, AlubiaQuizSolutionActivity.class);
					String respuesta_s = Integer.toString(answer);
					Bundle b = new Bundle();
					b.putString("RESPUESTA", respuesta_s);
					intent.putExtras(b);
					intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
					ctx.startActivity(intent);
					((Activity)ctx).finish();
				}
			});
		} else {
			builder.setPositiveButton(R.string.common_accept, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
		}
		builder.show();
	}
}
