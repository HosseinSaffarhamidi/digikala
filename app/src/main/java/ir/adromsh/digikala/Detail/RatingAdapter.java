package ir.adromsh.digikala.Detail;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akexorcist.roundcornerprogressbar.RoundCornerProgressBar;

import java.util.List;

import ir.adromsh.digikala.Model.RatingModel;
import ir.adromsh.digikala.R;

public class RatingAdapter extends RecyclerView.Adapter<RatingAdapter.RatingViewHolder> {

    List<RatingModel> ratingModels;

    public RatingAdapter(List<RatingModel> ratingModels){
        this.ratingModels=ratingModels;
    }

    @NonNull
    @Override
    public RatingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.rating_item,viewGroup,false);
        return new RatingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RatingViewHolder ratingViewHolder, int i) {
        RatingModel ratingModel=ratingModels.get(i);
        ratingViewHolder.txtTitle.setText(ratingModel.getTitle());
        ratingViewHolder.progressBar.setProgress(Float.parseFloat(ratingModel.getValue()));
    }

    @Override
    public int getItemCount() {
        return ratingModels.size();
    }

    public class RatingViewHolder extends RecyclerView.ViewHolder {
        RoundCornerProgressBar progressBar;
        TextView txtTitle;
        public RatingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar=itemView.findViewById(R.id.progress_ratingItem);
            txtTitle=itemView.findViewById(R.id.txt_ratingItem_title);
        }
    }
}
