package huitca1212.alubia13.ui.forum.adapter;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.util.Linkify;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
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
import huitca1212.alubia13.utils.ScreenUtils;

public class ForumAdapter extends RecyclerView.Adapter<ForumAdapter.ForumViewHolder> {
	private static final int TYPE_NORMAL = 0;
	private static final int TYPE_VIP = 1;
	private static final int TYPE_VIP_REPEATED = 2;
	private static final int TYPE_ADMIN = 3;
	private static final int TYPE_ADMIN_REPEATED = 4;
	private static final int TYPE_BAN = 5;
	private static final int TYPE_BAN_REPEATED = 6;
	private static final int TYPE_NORMAL_REPEATED = 7;

	private ArrayList<Comment> comments = new ArrayList<>();
	private String userLogged, invited;
	private ResultBusinessListener listener;

	public ForumAdapter(Context ctx, String invited, ResultBusinessListener listener) {
		this.invited = invited;
		this.listener = listener;
		userLogged = ctx.getSharedPreferences("PREFERENCE", 0).getString("username", "username");
	}

	@Override
	public ForumAdapter.ForumViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment_item, parent, false);
		return new ForumViewHolder(itemView, listener, userLogged, invited);
	}

	@Override
	public int getItemViewType(int position) {
		if (position < 2) {
			return TYPE_NORMAL;
		}
		Comment currentComment = comments.get(position);
		Comment previousComment = comments.get(position - 1);

		if (currentComment.getVip().equals("1")) {
			if (position > 0) {
				if (currentComment.getUser().equals(previousComment.getUser())) {
					return TYPE_VIP_REPEATED;
				}
			}
			return TYPE_VIP;
		} else if (currentComment.getVip().equals("2")) {
			if (position > 0) {
				if (currentComment.getUser().equals(previousComment.getUser())) {
					return TYPE_ADMIN_REPEATED;
				}
			}
			return TYPE_ADMIN;
		} else if (currentComment.getBan().equals("1")) {
			if (position > 0) {
				if (currentComment.getUser().equals(previousComment.getUser())) {
					return TYPE_BAN_REPEATED;
				}
			}
			return TYPE_BAN;
		} else if (position > 0) {
			if (currentComment.getUser().equals(previousComment.getUser())) {
				return TYPE_NORMAL_REPEATED;
			}
		}
		return TYPE_NORMAL;
	}

	@Override
	public void onBindViewHolder(final ForumViewHolder forumViewHolder, int position) {
		Comment comment = comments.get(position);
		forumViewHolder.onBind(comment);
	}

	@Override
	public int getItemCount() {
		return comments.size();
	}

	public boolean update(ArrayList<Comment> commentList) {
		if (comments.size() == 0 || commentList.size() > 0 && comments.get(comments.size() - 1).getId() <= commentList.get(commentList.size() - 1).getId()) {
			comments = commentList;
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

	public static class ForumViewHolder extends RecyclerView.ViewHolder {
		@Bind(R.id.user_comment_textview) TextView user;
		@Bind(R.id.group_textview) TextView group;
		@Bind(R.id.comment_textview) TextView commentText;
		@Bind(R.id.date_textview) TextView date;
		@Bind(R.id.id_textview) TextView id;
		@Bind(R.id.forum_outside_comment_layout) LinearLayout forumOutsideLayout;
		@Bind(R.id.forum_inside_comment_layout) LinearLayout forumInsideLayout;
		@Bind(R.id.divider_view) View divider;
		@Bind(R.id.report_button) Button reportButton;

		private Context context;
		private int marginPadding;
		private String userLogged, invited;

		public ForumViewHolder(View v, final ResultBusinessListener listener, String userLogged, String invited) {
			super(v);
			context = v.getContext();
			this.userLogged = userLogged;
			this.invited = invited;
			marginPadding = ScreenUtils.dpToPx(context, 60);
			ButterKnife.bind(this, v);

			reportButton.setClickable(true);
			reportButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					DialogParams params = new DialogParams();
					params.setTitle(context.getString(R.string.forum_report_comment_title));
					params.setMessage(context.getString(R.string.forum_report_comment_content));
					params.setPositiveButton(context.getString(R.string.common_accept));
					params.setNegativeButton(context.getString(R.string.common_cancel));
					Dialogs.showGenericDialog(context, params, new Dialogs.DialogListener() {
						@Override
						public void onPositive() {
							ForumBusiness.sendReportToBackend(id.getText().toString(), listener);
						}

						@Override
						public void onNegative() {
						}
					});
				}
			});
		}

		private void onBind(Comment comment) {
			id.setText(String.valueOf(comment.getId()));
			user.setText(comment.getUser());
			commentText.setText(comment.getComment());
			Linkify.addLinks(commentText, Linkify.WEB_URLS);
			group.setText(comment.getGroup());
			date.setText(comment.getDate());

			setCommentType(comment);
		}

		private void setCommentType(Comment comment) {
			int itemType = getItemViewType();
			boolean isUser = comment.getUser().equals(userLogged);

			if (invited != null && invited.equals("OK")) {
				reportButton.setVisibility(View.GONE);
			} else if (!isUser) {
				ViewGroup.MarginLayoutParams outsideParams = (ViewGroup.MarginLayoutParams) forumOutsideLayout.getLayoutParams();
				outsideParams.setMargins(0, 0, marginPadding, 0);
				forumOutsideLayout.setGravity(Gravity.START);
				forumOutsideLayout.setLayoutParams(outsideParams);
				forumInsideLayout.setBackgroundResource(R.drawable.comment_left_white);
				reportButton.setVisibility(View.VISIBLE);
			} else {
				ViewGroup.MarginLayoutParams outsideParams = (ViewGroup.MarginLayoutParams) forumOutsideLayout.getLayoutParams();
				outsideParams.setMargins(marginPadding, 0, 0, 0);
				forumOutsideLayout.setGravity(Gravity.END);
				forumOutsideLayout.setLayoutParams(outsideParams);
				forumInsideLayout.setBackgroundResource(R.drawable.comment_right_white);
				reportButton.setVisibility(View.GONE);
			}

			if (itemType == TYPE_VIP || itemType == TYPE_VIP_REPEATED) {
				user.setTextColor(ContextCompat.getColor(context, R.color.dark_purple));
				forumInsideLayout.setBackgroundResource(isUser ? R.drawable.comment_right_yellow : R.drawable.comment_left_yellow);
			} else if (itemType == TYPE_ADMIN || itemType == TYPE_ADMIN_REPEATED) {
				user.setTextColor(ContextCompat.getColor(context, R.color.dark_purple));
				reportButton.setVisibility(View.GONE);
				forumInsideLayout.setBackgroundResource(isUser ? R.drawable.comment_right_blue : R.drawable.comment_left_blue);
			} else if (itemType == TYPE_BAN || itemType == TYPE_BAN_REPEATED) {
				commentText.setText(R.string.forum_error_comment_blocked);
				commentText.setTextColor(AlubiaApplication.getInstance().getResources().getColor(R.color.red));
				reportButton.setVisibility(View.GONE);
			}

			if (itemType == TYPE_VIP_REPEATED || itemType == TYPE_ADMIN_REPEATED || itemType == TYPE_BAN_REPEATED || itemType == TYPE_NORMAL_REPEATED) {
				divider.setVisibility(View.GONE);
				user.setVisibility(View.GONE);
				group.setText("");
			}
		}
	}
}