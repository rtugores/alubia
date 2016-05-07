package huitca1212.alubia13.ui.news;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.DatabaseFunctions;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.NewsBusiness;
import huitca1212.alubia13.business.listener.AllBusinessListener;
import huitca1212.alubia13.model.news.News;
import huitca1212.alubia13.model.news.NewsWrapper;
import huitca1212.alubia13.ui.news.adapter.NewsAdapter;
import huitca1212.alubia13.utils.AdsAndAnalytics;
import huitca1212.alubia13.utils.DialogParams;
import huitca1212.alubia13.utils.Dialogs;
import huitca1212.alubia13.utils.Notifications;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {
	private NewsWrapper data;
	private NewsAdapter adapter;
	@Bind(R.id.progressbar_view) LinearLayout progressbarView;
	@Bind(R.id.coordinator_layout) CoordinatorLayout coordinatorLayout;
	@Bind(R.id.recycler_view) RecyclerView recyclerView;
	@Bind(R.id.send_news) Button sendNews;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_news);
		ButterKnife.bind(this);

		sendNews.setOnClickListener(this);

		setDefaultAdapter();
		AdsAndAnalytics.setAnalytics(this);
		accessWebService();
	}

	@Override
	public void onClick(View v) {
		DialogParams params = new DialogParams();
		params.setTitle(getString(R.string.news_event_title));
		params.setMessage(getString(R.string.news_event_content));
		params.setPositiveButton(getString(R.string.news_email));
		params.setNegativeButton(getString(R.string.news_whatsapp));
		Dialogs.showGenericDialog(this, params, new Dialogs.DialogListener() {
			@Override
			public void onPositive() {
				Intent i = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + "huitca1212@gmail.com"));
				startActivity(Intent.createChooser(i, "Enviar mediante"));
			}

			@Override
			public void onNegative() {
				Intent i = new Intent(Intent.ACTION_DIAL);
				i.setData(Uri.parse("tel:" + "664732632"));
				startActivity(i);
			}
		});
	}

	private void setDefaultAdapter() {
		adapter = new NewsAdapter();
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(adapter);
	}

	public void accessWebService() {
		data = new NewsWrapper();
		progressbarView.setVisibility(View.VISIBLE);
		NewsBusiness.getNewsContent(new AllBusinessListener<ArrayList<News>>() {
			@Override
			public void onDatabaseSuccess(ArrayList<News> list) {
				if (list.size() > 0) {
					progressbarView.setVisibility(View.GONE);
					data.setNews(list);
					drawNewsList(false);
				}
			}

			@Override
			public void onServerSuccess(ArrayList<News> list) {
				progressbarView.setVisibility(View.GONE);
				if (list.size() > 0) {
					data.setNews(list);
					drawNewsList(true);
				}
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					Notifications.showSnackBar(coordinatorLayout, getString(R.string.common_no_internet));
				}
			}
		});
	}

	public void drawNewsList(boolean saveInDb) {
		if (saveInDb) {
			DatabaseFunctions.setDatabaseNewsValues(data.getNews());
		}
		adapter.updateList(data.getNews());
	}
}