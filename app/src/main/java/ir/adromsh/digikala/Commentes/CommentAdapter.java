package ir.adromsh.digikala.Commentes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.adromsh.digikala.Detail.RatingAdapter;
import ir.adromsh.digikala.Model.Comment;
import ir.adromsh.digikala.Model.RatingModel;
import ir.adromsh.digikala.R;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.CommentViewHolder> {
    List<Comment> comments;
    Context context;
    List<RatingModel> ratingModelList;
    OnLikeOrDislikeClick onLikeOrDislikeClick;
    String id;

    public CommentAdapter(List<Comment> comments, Context context,OnLikeOrDislikeClick onLikeOrDislikeClick) {
        this.comments = comments;
        this.context = context;
        ratingModelList = new ArrayList<>();
        this.onLikeOrDislikeClick=onLikeOrDislikeClick;
    }

    public void changeLikeOrDislikeCount(Comment comment,String likeOrDisLike){
            int position=comments.indexOf(comment);
            Comment findComment=comments.get(position);
            if(likeOrDisLike.equals("like")){
                int currentLike=Integer.parseInt(findComment.getLikecount());
                currentLike++;
                findComment.setLikecount(currentLike+"");
            }else{
                int currentDisLike=Integer.parseInt(findComment.getDislikecount());
                currentDisLike++;
                findComment.setDislikecount(currentDisLike+"");
            }

            notifyDataSetChanged();

    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.comment_item, viewGroup, false);
        return new CommentViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        final Comment comment = comments.get(i);
        id=comment.getId();
        ratingModelList.clear();
        String param = comment.getParam();
        try {
            JSONArray jsonArray = new JSONArray(param);
            for (int j = 0; j < jsonArray.length(); j++) {
                RatingModel ratingModel = new RatingModel();
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                ratingModel.setTitle(jsonObject.getString("title"));
                ratingModel.setValue(jsonObject.getString("value"));
                ratingModelList.add(ratingModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (comment.getSuggest().equals("1")) {
            commentViewHolder.txtIsSuggested.setVisibility(View.VISIBLE);
        } else {
            commentViewHolder.txtIsSuggested.setVisibility(View.GONE);
        }

        commentViewHolder.txtUsername.setText(comment.getUser());
        commentViewHolder.txtPassage.setText(comment.getPassage());
        commentViewHolder.txtLike.setText(comment.getLikecount());
        commentViewHolder.txtDislike.setText(comment.getDislikecount());

        commentViewHolder.recyclerView.setAdapter(new RatingAdapter(ratingModelList));
        commentViewHolder.txtPositive.setText(comment.getPositive());
        commentViewHolder.txtNegative.setText(comment.getNegative());

        commentViewHolder.linearLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLikeOrDislikeClick.onClikc(comment,"like");
            }
        });

        commentViewHolder.linearDisLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onLikeOrDislikeClick.onClikc(comment,"dislike");
            }
        });


    }

    @Override
    public int getItemCount() {
        return comments.size();
    }

    public class CommentViewHolder extends RecyclerView.ViewHolder {
        RecyclerView recyclerView;
        LinearLayout linearLike, linearDisLike;
        TextView txtIsSuggested, txtUsername, txtLike, txtDislike, txtPassage, txtPositive, txtNegative;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtIsSuggested = itemView.findViewById(R.id.txt_commentItem_isSuggested);
            txtUsername = itemView.findViewById(R.id.txt_commentItem_username);
            linearLike = itemView.findViewById(R.id.linear_commentItem_like);
            linearDisLike = itemView.findViewById(R.id.linear_commentItem_dislike);
            txtLike = itemView.findViewById(R.id.txt_commentItem_like);
            txtDislike = itemView.findViewById(R.id.txt_commentItem_dislike);
            txtPassage = itemView.findViewById(R.id.txt_commentItem_description);
            txtPositive = itemView.findViewById(R.id.txt_commentItem_positiveTitle);
            txtNegative = itemView.findViewById(R.id.txt_commentItem_negative);
            recyclerView = itemView.findViewById(R.id.rv_commentItem_commentRate);
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }
    }

    public interface OnLikeOrDislikeClick {
        void onClikc(Comment comment, String likeOrDislike);
    }

}
