package huitca1212.alubia13.ui.news;

import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.AllListenerBusiness;
import huitca1212.alubia13.business.DatabaseFunctions;
import huitca1212.alubia13.business.DefaultAsyncTask;
import huitca1212.alubia13.business.NewsBusiness;
import huitca1212.alubia13.model.News;
import huitca1212.alubia13.model.NewsWrapper;
import huitca1212.alubia13.utils.Dialogs;

public class NewsActivity extends AppCompatActivity implements View.OnClickListener {
	public static GoogleAnalytics analytics;
	public static Tracker tracker;
	public NewsWrapper data;
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
		setAnalytics();
		accessWebService();
	}

	@Override
	public void onClick(View v) {
		Dialogs.newsContactDialog(this).show();
	}

	public void accessWebService() {
		data = new NewsWrapper();
		progressbarView.setVisibility(View.VISIBLE);
		NewsBusiness.getNewsContent(new AllListenerBusiness<News>() {
			@Override
			public void onDatabaseSuccess(ArrayList<News> list) {
				if (list.size() > 0) {
					data.setNews(list);
				}
			}

			@Override
			public void onServerSuccess(ArrayList<News> list) {
				if (list.size() > 0) {
					data.setNews(list);
				}
			}

			@Override
			public void onFailure(String result) {
				progressbarView.setVisibility(View.GONE);
				if (result.equals(DefaultAsyncTask.ASYNC_TASK_OK) && data.getNews() != null) {
					drawNewsList();
				} else if (result.equals(DefaultAsyncTask.ASYNC_TASK_SERVER_ERROR)) {
					if (Build.VERSION.SDK_INT > Build.VERSION_CODES.JELLY_BEAN) {
						Snackbar.make(coordinatorLayout, getString(R.string.internet_news_advise), Snackbar.LENGTH_LONG).show();
					} else {
						Toast.makeText(NewsActivity.this, R.string.internet_news_advise, Toast.LENGTH_LONG).show();
					}
				}
			}
		});
	}

	public void setAnalytics() {
		analytics = GoogleAnalytics.getInstance(this);
		analytics.setLocalDispatchPeriod(1800);
		tracker = analytics.newTracker("UA-42496077-1");
		tracker.enableExceptionReporting(true);
		tracker.enableAdvertisingIdCollection(true);
		tracker.enableAutoActivityTracking(true);
	}

	public void drawNewsList() {
		NewsAdapter adapter = new NewsAdapter(data.getNews());
		recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
		recyclerView.setAdapter(adapter);
		DatabaseFunctions.setDatabaseNewsValues(data.getNews());
		adapter.updateList(data.getNews());
	}
}