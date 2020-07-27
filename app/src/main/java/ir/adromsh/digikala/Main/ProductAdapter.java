package ir.adromsh.digikala.Main;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.StrikethroughSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.R;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {
    List<Product> products;
    OnProductClick onProductClick;

    public ProductAdapter(List<Product> products,OnProductClick onProductClick) {
        this.products = products;
        this.onProductClick=onProductClick;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.product_item,viewGroup,false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder productViewHolder, int i) {
        final Product product=products.get(i);
        productViewHolder.id=product.getId();
        Picasso.get().load(product.getPic()).into(productViewHolder.imageView);
        productViewHolder.txtTitle.setText(product.getTitle());

        SpannableString spannableString=new SpannableString(product.getPprice());
        spannableString.setSpan(new StrikethroughSpan(),0,product.getPprice().length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        productViewHolder.txtPrePrice.setText(spannableString);
        productViewHolder.txtPrice.setText(product.getPrice());

        productViewHolder.cardParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onProductClick.onClick(product.getId());
            }
        });
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        String id;
        CardView cardParent;
        TextView txtTitle, txtPrePrice, txtPrice;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img_product_image);
            txtTitle = itemView.findViewById(R.id.img_product_title);
            txtPrePrice = itemView.findViewById(R.id.txt_product_prePrcie);
            txtPrice = itemView.findViewById(R.id.txt_product_price);
            cardParent=itemView.findViewById(R.id.card_productItem_parent);
        }
    }

    public interface OnProductClick{
        void onClick(String id);
    }
}
