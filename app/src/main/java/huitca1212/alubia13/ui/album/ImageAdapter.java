package huitca1212.alubia13.ui.album;

import com.squareup.picasso.Picasso;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import huitca1212.alubia13.R;

public class ImageAdapter extends RecyclerView.Adapter {
	private final String[] IMAGE_STRINGS = Constants.TITLES_PENYAS;
	private String[] imageUrls;
	private LayoutInflater inflater;
	private OnItemClickListener listener;
	private int layoutRes;

	public ImageAdapter(Context context, OnItemClickListener listener, String[] imageUrls, int layoutRes) {
		this.imageUrls = imageUrls;
		this.listener = listener;
		this.layoutRes = layoutRes;
		inflater = LayoutInflater.from(context);
	}

	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemLayout = inflater.inflate(layoutRes, parent, false);
		return new ImageViewHolder(itemLayout, listener);
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
		((ImageViewHolder) holder).onBind(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public int getItemCount() {
		return imageUrls.length;
	}

	public class ImageViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
		private View itemView;
		private TextView text;
		private ImageView image;

		private OnItemClickListener listener;
		private int position;

		public ImageViewHolder(View itemView, OnItemClickListener listener) {
			super(itemView);
			this.itemView = itemView;
			this.listener = listener;
			itemView.setOnClickListener(this);
			text = (TextView) itemView.findViewById(R.id.text);
			image = (ImageView) itemView.findViewById(R.id.image);
		}

		private void onBind(int position) {
			this.position = position;
			if (text != null) {
				text.setText(IMAGE_STRINGS[position]);
			}

			Picasso.with(itemView.getContext())
					.load(imageUrls[position] + ".thb")
					.placeholder(R.drawable.ic_stub)
					.error(R.drawable.ic_error)
					.into(image);
		}

		@Override
		public void onClick(View v) {
			int id = v.getId();
			if (id == itemView.getId()) {
				if (listener != null) {
					listener.onItemClick(position);
				}
			}
		}
	}

	public interface OnItemClickListener {
		void onItemClick(int position);
	}
}