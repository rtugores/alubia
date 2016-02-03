package huitca1212.alubia13.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;

import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.ForumBusiness;
import huitca1212.alubia13.business.ResultListenerBussiness;
import huitca1212.alubia13.business.ServerListenerBusiness;
import huitca1212.alubia13.ui.forum.ForumActivity;
import huitca1212.alubia13.ui.more.MoreActivity;
import huitca1212.alubia13.ui.more.alubiaQuiz.AlubiaQuizSolutionActivity;
import huitca1212.alubia13.ui.more.settings.SettingsActivity;

public class Dialogs {

	public static void showNewsContactDialog(final Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(R.string.news_event_title);
		builder.setMessage(R.string.news_event_content);
		builder.setPositiveButton(R.string.news_email, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822").putExtra(Intent.EXTRA_EMAIL, "huitca1212@gmail.com");
				ctx.startActivity(Intent.createChooser(i, "Enviar mediante"));
			}
		});
		builder.setNegativeButton(R.string.news_whatsapp, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse("tel:664732632"));
				ctx.startActivity(callIntent);
			}
		});
		builder.show();
	}

	public static void showForumReportCommentDialog(final Context ctx, final String id, final ResultListenerBussiness listener) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(R.string.forum_report_comment_title);
		builder.setMessage(R.string.forum_report_comment_content);
		builder.setPositiveButton(R.string.common_accept, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				ForumBusiness.sendReportToBackend(id, new ServerListenerBusiness<String>() {
					@Override
					public void onServerSuccess(String result) {
						listener.onResult(DefaultAsyncTask.ASYNC_TASK_OK);
					}

					@Override
					public void onFailure(String result) {
						switch (result) {
							case "-2":
								listener.onResult("-2");
								break;
							case DefaultAsyncTask.ASYNC_TASK_ERROR:
								listener.onResult(DefaultAsyncTask.ASYNC_TASK_ERROR);
								break;
						}
					}
				});
			}
		});
		builder.setNegativeButton(R.string.common_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.dismiss();
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
					dialog.dismiss();
				}
			});
		}
		builder.show();
	}

	public static void showSettingsLogoutDialog(final Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(R.string.settings_logout);
		builder.setMessage(R.string.settings_logout_areyousure);
		builder.setNegativeButton(R.string.common_cancel, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.setPositiveButton(R.string.common_accept, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				ctx.getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE).edit().putString("username", "").commit();
				ctx.getSharedPreferences("PREFERENCE", Activity.MODE_PRIVATE).edit().putBoolean("notregister", true).commit();
				try {
					MoreActivity.moreActivity.finish();
				} catch (NullPointerException e) {
					//NOOP
				}
				try {
					ForumActivity.forumActivity.finish();
				} catch (NullPointerException e) {
					//NOOP
				}
				try {
					SettingsActivity.settingsActivity.finish();
				} catch (NullPointerException e) {
					//NOOP
				}
			}
		});
		builder.show();
	}

}
