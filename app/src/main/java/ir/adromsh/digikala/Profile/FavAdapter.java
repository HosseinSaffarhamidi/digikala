package ir.adromsh.digikala.Profile;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.adromsh.digikala.Model.Favorite;
import ir.adromsh.digikala.R;

public class FavAdapter extends RecyclerView.Adapter<FavAdapter.FavViewHolder> {
    List<Favorite> favorites;
    OnFavoriteClick onFavoriteClick;
    OnDeleteClick onDeleteClick;
    public FavAdapter(List<Favorite> favorites,OnFavoriteClick onFavoriteClick,OnDeleteClick onDeleteClick) {
        this.favorites = favorites;
        this.onDeleteClick=onDeleteClick;
        this.onFavoriteClick=onFavoriteClick;
    }

    @NonNull
    @Override
    public FavViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.fav_item, viewGroup, false);
        return new FavViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavViewHolder favViewHolder, final int i) {
        final Favorite favorite = favorites.get(i);
        favViewHolder.productId = favorite.getProductId();
        favViewHolder.favId = favorite.getFavId();
        Picasso.get().load(favorite.getImage()).into(favViewHolder.image);
        favViewHolder.txtTitle.setText(favorite.getTitle());
        favViewHolder.txtDescription.setText(favorite.getTitle());
        favViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onFavoriteClick.onFavClick(favorite.getProductId());
            }
        });

        favViewHolder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteClick.ondelete(favorite.getFavId());
                favorites.remove(i);
                notifyItemRemoved(i);
                notifyItemRangeChanged(i,favorites.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return favorites.size();
    }

    public class FavViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        String productId;
        String favId;
        TextView txtTitle, txtDescription, txtDelete;
        CardView parent;

        public FavViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.img_favItem_image);
            txtTitle = itemView.findViewById(R.id.txt_favItem_title);
            txtDescription = itemView.findViewById(R.id.txt_favItem_desc);
            txtDelete = itemView.findViewById(R.id.txt_favitem_delete);
            parent = itemView.findViewById(R.id.card_favItem_parent);
        }
    }

    public interface OnFavoriteClick {
        void onFavClick(String productId);
    }

    public interface OnDeleteClick {
        void ondelete(String favId);
    }
}
