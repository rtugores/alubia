package huitca1212.alubia13.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import java.net.URLEncoder;

import huitca1212.alubia13.AlubiaApplication;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.SendReportAsyncTask;
import huitca1212.alubia13.ui.more.alubiaQuiz.AlubiaQuizSolutionActivity;

public class Dialogs {

	public static Dialog newsContactDialog(final Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(R.string.evento);
		builder.setMessage(R.string.eventoTexto);
		builder.setNegativeButton(R.string.whatsApp, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent callIntent = new Intent(Intent.ACTION_DIAL);
				callIntent.setData(Uri.parse("tel:664732632"));
				ctx.startActivity(callIntent);
			}
		});
		builder.setPositiveButton(R.string.email, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				Intent i = new Intent(Intent.ACTION_SEND);
				i.setType("message/rfc822").putExtra(Intent.EXTRA_EMAIL, "huitca1212@gmail.com");
				ctx.startActivity(Intent.createChooser(i, "Enviar mediante"));
			}
		});
		return builder.create();
	}

	public static Dialog forumReportCommentDialog(final Context ctx, final String id) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle("H: Reportar comentario");
		builder.setMessage("H: Estás seguro de que quieres denunciar este comentario?");
		builder.setNegativeButton(R.string.cancelar, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				try {
					String mURL = "http://rjapps.x10host.com/denunciar_comentario.php?id=" + URLEncoder.encode(id, "UTF-8");
					SendReportAsyncTask enviar = new SendReportAsyncTask(AlubiaApplication.getInstance(), mURL);
					enviar.execute(mURL);
				} catch (Exception e) {
					Toast.makeText(ctx, R.string.internet_error, Toast.LENGTH_LONG).show();
				}
			}
		});
		return builder.create();
	}

	public static Dialog alubiaQuizRightAnswerDialog(int questionNumber, final int answer, final Context ctx) {
		AlertDialog.Builder builder = new AlertDialog.Builder(ctx);
		builder.setTitle(R.string.enhorabuena);
		builder.setMessage(R.string.hasAcertado);
		if (questionNumber == 10) {
			builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
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
			builder.setPositiveButton(R.string.aceptar, new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int which) {
					dialog.cancel();
				}
			});
		}
		return builder.create();
	}
}
