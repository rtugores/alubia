package huitca1212.alubia13.ui.news.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.model.news.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

	ArrayList<News> news = new ArrayList<>();

	public static class NewsViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.user_news_textview) TextView date;
		@Bind(R.id.news_textview) TextView comment;

		public NewsViewHolder(View itemView) {
			super(itemView);
			ButterKnife.bind(this, itemView);
		}
	}

	@Override
	public NewsAdapter.NewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext())
				.inflate(R.layout.layout_news_item, parent, false);
		return new NewsViewHolder(itemView);
	}

	@Override
	public void onBindViewHolder(NewsViewHolder newsViewHolder, int position) {
		newsViewHolder.date.setText(news.get(position).getDate());
		newsViewHolder.comment.setText(news.get(position).getComment());
		Linkify.addLinks(newsViewHolder.comment, Linkify.WEB_URLS);
	}

	@Override
	public int getItemCount() {
		return news.size();
	}

	public void updateList(ArrayList<News> news) {
		this.news = news;
		notifyDataSetChanged();
	}
}