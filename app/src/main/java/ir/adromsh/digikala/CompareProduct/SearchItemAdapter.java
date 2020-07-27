package ir.adromsh.digikala.CompareProduct;

import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.R;

public class SearchItemAdapter extends RecyclerView.Adapter<SearchItemAdapter.SearchViewHolder> {
    List<Product> products;
    OnSearchedItemClickListener onSearchedItemClickListener;
    public SearchItemAdapter(OnSearchedItemClickListener onSearchedItemClickListener){
        products=new ArrayList<>();
        this.onSearchedItemClickListener=onSearchedItemClickListener;
    }
    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.search_item,viewGroup,false);
        return new SearchViewHolder(view);
    }

    public void onbind(List<Product> products){
        this.products.clear();
        this.products.addAll(products);
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder searchViewHolder, int i) {
        final Product product= products.get(i);
        searchViewHolder.id=product.getId();
        Picasso.get().load(product.getPic()).into(searchViewHolder.image);
        searchViewHolder.txtTitle.setText(product.getTitle());
        searchViewHolder.txtDesc.setText(product.getTitle());
        searchViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSearchedItemClickListener.onClickListener(product.getmProperties(),product.getPic(),product.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView txtTitle,txtDesc;
        ConstraintLayout parent;
        String id;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle=itemView.findViewById(R.id.txt_searchItem_title);
            txtDesc=itemView.findViewById(R.id.txt_searchItem_desc);
            image=itemView.findViewById(R.id.img_searchItem_image);
            parent=itemView.findViewById(R.id.constraing_searchItem_parent);
        }
    }

    public interface OnSearchedItemClickListener{
        void onClickListener(String properties,String image,String id);
    }
}
