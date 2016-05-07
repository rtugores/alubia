package huitca1212.alubia13.ui.forum.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import huitca1212.alubia13.AlubiaApplication;
import huitca1212.alubia13.R;
import huitca1212.alubia13.business.ForumBusiness;
import huitca1212.alubia13.business.listener.ResultBusinessListener;
import huitca1212.alubia13.model.forum.Comment;
import huitca1212.alubia13.utils.DialogParams;
import huitca1212.alubia13.utils.Dialogs;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {

	private static final int TYPE_VIP = 1;
	private static final int TYPE_VIP_REPEATED = 2;
	private static final int TYPE_ADMIN = 3;
	private static final int TYPE_ADMIN_REPEATED = 4;
	private static final int TYPE_BAN = 5;
	private static final int TYPE_BAN_REPEATED = 6;
	private static final int TYPE_NORMAL_REPEATED = 7;

	private Context ctx;
	private ArrayList<Comment> comments = new ArrayList<>();
	private String userLogged, invited;
	private ResultBusinessListener resultListener;
	private int marginPadding;

	public ForumAdapter(Context ctx, String invited, ResultBusinessListener resultListener) {
		this.ctx = ctx;
		this.invited = invited;
		this.resultListener = resultListener;
		userLogged = ctx.getSharedPreferences("PREFERENCE", 0).getString("username", "username");
		marginPadding = convertDpToPixel(60);
	}

	public static class ForumViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.user_comment_textview) TextView user;
		@Bind(R.id.group_textview) TextView group;
		@Bind(R.id.comment_textview) TextView comment;
		@Bind(R.id.date_textview) TextView date;
		@Bind(R.id.id_textview) TextView id;
		@Bind(R.id.forum_outside_comment_layout) RelativeLayout forumOutsideLayout;
		@Bind(R.id.forum_inside_comment_layout) RelativeLayout forumInsideLayout;
		@Bind(R.id.divider_view) View divider;
		@Bind(R.id.report_button) Button reportButton;

		public ForumViewHolder(View v) {
			super(v);
			ButterKnife.bind(this, v);
		}
	}

	@Override
	public ForumAdapter.ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_item, parent, false);
		return new ForumViewHolder(itemView);
	}

	@Override
	public int getItemViewType(int position) {
		if (comments.get(position).getBan().equals("1")) {
			if (position > 0) {
				if (comments.get(position).getUser().equals(comments.get(position - 1).getUser())) {
					return TYPE_BAN_REPEATED;
				}
			}
			return TYPE_BAN;
		}
		if (comments.get(position).getVip().equals("1")) {
			if (position > 0) {
				if (comments.get(position).getUser().equals(comments.get(position - 1).getUser())) {
					return TYPE_VIP_REPEATED;
				}
			}
			return TYPE_VIP;
		} else if (comments.get(position).getVip().equals("2")) {
			if (position > 0) {
				if (comments.get(position).getUser().equals(comments.get(position - 1).getUser())) {
					return TYPE_ADMIN_REPEATED;
				}
			}
			return TYPE_ADMIN;
		} else if (position > 0) {
			if (comments.get(position).getUser().equals(comments.get(position - 1).getUser())) {
				return TYPE_NORMAL_REPEATED;
			}
		}
		return 0;
	}

	@Override
	public void onBindViewHolder(final ForumViewHolder forumViewHolder, int position) {
		forumViewHolder.id.setText(Integer.toString(comments.get(position).getId()));
		forumViewHolder.user.setText(comments.get(position).getUser());
		forumViewHolder.comment.setText(comments.get(position).getComment());
		Linkify.addLinks(forumViewHolder.comment, Linkify.WEB_URLS);
		forumViewHolder.group.setText(comments.get(position).getGroup());
		forumViewHolder.date.setText(comments.get(position).getDate());

		forumViewHolder.reportButton.setClickable(true);
		forumViewHolder.reportButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				DialogParams params = new DialogParams();
				params.setTitle(ctx.getString(R.string.forum_report_comment_title));
				params.setMessage(ctx.getString(R.string.forum_report_comment_content));
				params.setPositiveButton(ctx.getString(R.string.common_accept));
				params.setNegativeButton(ctx.getString(R.string.common_cancel));
				Dialogs.showGenericDialog(ctx, params, new Dialogs.DialogListener() {
					@Override
					public void onPositive() {
						ForumBusiness.sendReportToBackend(forumViewHolder.id.getText().toString(), resultListener);
					}

					@Override
					public void onNegative() {
					}
				});
			}
		});
		setCommentType(forumViewHolder, position);
	}

	@Override
	public int getItemCount() {
		return comments.size();
	}

	private void setCommentType(ForumViewHolder forumViewHolder, int position) {
		int itemType = forumViewHolder.getItemViewType();
		boolean isUser = comments.get(position).getUser().equals(userLogged);
		if (invited != null && invited.equals("OK")) {
			forumViewHolder.reportButton.setVisibility(View.GONE);
		} else if (isUser) {
			ViewGroup.MarginLayoutParams outsideParams = (ViewGroup.MarginLayoutParams)forumViewHolder.forumOutsideLayout.getLayoutParams();
			outsideParams.setMargins(marginPadding, 0, 0, 0);
			forumViewHolder.forumOutsideLayout.setLayoutParams(outsideParams);
			forumViewHolder.forumInsideLayout.setBackgroundResource(R.drawable.comment_right_white);
			forumViewHolder.reportButton.setVisibility(View.GONE);
		}
		if (itemType == TYPE_VIP || itemType == TYPE_VIP_REPEATED) {
			if (isUser) {
				forumViewHolder.forumInsideLayout.setBackgroundResource(R.drawable.comment_right_yellow);
			} else {
				forumViewHolder.forumInsideLayout.setBackgroundResource(R.drawable.comment_left_yellow);
			}
			if (itemType == TYPE_VIP) {
				forumViewHolder.user.setTextColor(AlubiaApplication.getInstance().getResources().getColor(R.color.dark_purple));
			}
		} else if (itemType == TYPE_ADMIN || itemType == TYPE_ADMIN_REPEATED) {
			forumViewHolder.user.setTextColor(AlubiaApplication.getInstance().getResources().getColor(R.color.dark_purple));
			if (isUser) {
				forumViewHolder.forumInsideLayout.setBackgroundResource(R.drawable.comment_right_blue);
			} else {
				forumViewHolder.forumInsideLayout.setBackgroundResource(R.drawable.comment_left_blue);
			}
			forumViewHolder.comment.setTypeface(null, Typeface.BOLD);
			forumViewHolder.reportButton.setVisibility(View.GONE);
		}
		if (itemType == TYPE_VIP_REPEATED || itemType == TYPE_ADMIN_REPEATED || itemType == TYPE_BAN_REPEATED || itemType == TYPE_NORMAL_REPEATED) {
			forumViewHolder.divider.setVisibility(View.GONE);
			forumViewHolder.user.setVisibility(View.GONE);
			forumViewHolder.group.setText("");
		}
		if (itemType == TYPE_BAN || itemType == TYPE_BAN_REPEATED) {
			forumViewHolder.comment.setText(R.string.forum_error_comment_blocked);
			forumViewHolder.comment.setTextColor(AlubiaApplication.getInstance().getResources().getColor(R.color.red));
			forumViewHolder.reportButton.setVisibility(View.GONE);
		}
	}

	private int convertDpToPixel(int dpMeasure) {
		Resources r = ctx.getResources();
		return (int)TypedValue.applyDimension(
				TypedValue.COMPLEX_UNIT_DIP,
				dpMeasure,
				r.getDisplayMetrics()
		);
	}

	public boolean update(ArrayList<Comment> comments) {
		if (this.comments.size() == 0 || this.comments.get(this.comments.size() - 1).getId() <= comments.get(comments.size() - 1).getId()) {
			this.comments = comments;
			notifyDataSetChanged();
			return true;
		}
		return false;
	}

	public void add(Comment comment, RecyclerView recyclerView) {
		comments.add(comment);
		recyclerView.scrollToPosition(comments.size() - 1);
		notifyItemInserted(comments.size() - 1);
	}
}