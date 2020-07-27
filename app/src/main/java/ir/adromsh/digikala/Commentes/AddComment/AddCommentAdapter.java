package ir.adromsh.digikala.Commentes.AddComment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hedgehog.ratingbar.RatingBar;

import java.util.List;

import ir.adromsh.digikala.Model.RatingModel;
import ir.adromsh.digikala.R;

public class AddCommentAdapter extends RecyclerView.Adapter<AddCommentAdapter.AddCommentViewHolder> {
    Context context;
    List<RatingModel> ratingModels;

    public AddCommentAdapter(Context context, List<RatingModel> ratingModels) {
        this.context = context;
        this.ratingModels = ratingModels;
    }

    @NonNull
    @Override
    public AddCommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_comment_item, viewGroup, false);
        return new AddCommentViewHolder(view);
    }

    public List<RatingModel> getRatingModels(){
        return ratingModels;
    }

    @Override
    public void onBindViewHolder(@NonNull AddCommentViewHolder addCommentViewHolder, final int i) {
        RatingModel ratingModel = ratingModels.get(i);
        addCommentViewHolder.txtTitle.setText(ratingModel.getTitle());
        addCommentViewHolder.ratingBar.setStar(3);
        ratingModel.setValue(3+"");
        addCommentViewHolder.ratingBar.setOnRatingChangeListener(new RatingBar.OnRatingChangeListener() {
            @Override
            public void onRatingChange(float ratingCount) {
                //Toast.makeText(context, i+"", Toast.LENGTH_SHORT).show();
                RatingModel ratingModelItem = ratingModels.get(i);
                ratingModelItem.setValue(ratingCount+"");
            }
        });
    }

    @Override
    public int getItemCount() {
        return ratingModels.size();
    }

    public class AddCommentViewHolder extends RecyclerView.ViewHolder {
        RatingBar ratingBar;
        TextView txtTitle;

        public AddCommentViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.txt_addCommentItem_title);
            ratingBar = itemView.findViewById(R.id.rating_addCommentItem);
        }
    }


}
