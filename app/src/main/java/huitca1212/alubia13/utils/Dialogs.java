package huitca1212.alubia13.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
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

	public static void showAlubiaQuizRightAnswerDialog(final int questionNumber, final int answer, final Activity activity) {
		AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		builder.setTitle(R.string.alubiaquiz_congrats);
		builder.setMessage(R.string.alubiaquiz_you_are_correct);
		builder.setOnCancelListener(new DialogInterface.OnCancelListener() {
			@Override
			public void onCancel(final DialogInterface dialogInterface) {
				checkQuestionNumberToLeave(questionNumber, activity, answer, dialogInterface);
			}
		});
		builder.setPositiveButton(R.string.common_accept, new DialogInterface.OnClickListener() {
			public void onClick(final DialogInterface dialogInterface, int which) {
				checkQuestionNumberToLeave(questionNumber, activity, answer, dialogInterface);
			}
		});
		builder.show();
	}

	private static void checkQuestionNumberToLeave(int questionNumber, Activity activity, int answer, DialogInterface dialogInterface) {
		if (questionNumber == 10) {
			AlubiaQuizSolutionActivity.startActivity(activity, Integer.toString(answer), true);
			activity.finish();
		} else {
			dialogInterface.cancel();
		}
	}
}
