package huitca1212.alubia13.ui.news;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.R;
import huitca1212.alubia13.model.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

	ArrayList<News> news;

	NewsAdapter(ArrayList<News> news) {
		this.news = news;
	}

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
