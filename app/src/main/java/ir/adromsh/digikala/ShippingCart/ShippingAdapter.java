package ir.adromsh.digikala.ShippingCart;

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

import ir.adromsh.digikala.Model.Basket;
import ir.adromsh.digikala.R;

public class ShippingAdapter extends RecyclerView.Adapter<ShippingAdapter.ShippingViewHolder> {
    List<Basket> basketList;
    OnBasketItemClick onBasketItemClick;
    OnDeleteItem onDeleteItem;
    OnPriceCallBack onPriceCallBack;

    public ShippingAdapter(List<Basket> basketList, OnBasketItemClick onBasketItemClick, OnDeleteItem onDeleteItem,OnPriceCallBack onPriceCallBack) {
        this.basketList = basketList;
        this.onBasketItemClick = onBasketItemClick;
        this.onDeleteItem = onDeleteItem;
        this.onPriceCallBack=onPriceCallBack;
    }

    @NonNull
    @Override
    public ShippingViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.shipping_item, viewGroup, false);
        return new ShippingViewHolder(view);
    }

    public void deleteRow(Basket basket) {
        int index = basketList.indexOf(basket);
        basketList.remove(index);
        notifyDataSetChanged();

    }

    @Override
    public void onBindViewHolder(@NonNull final ShippingViewHolder shippingViewHolder, int i) {
        final Basket basket = basketList.get(i);
        Picasso.get().load(basket.getImage()).into(shippingViewHolder.image);
        shippingViewHolder.txtTitle.setText(basket.getTitle());
        shippingViewHolder.txtPrice.setText(basket.getPrice());
        shippingViewHolder.txtGuarantee.setText(basket.getGuarantee());
        shippingViewHolder.txtFinalPrice.setText(basket.getPrice());
        shippingViewHolder.productId = basket.getProductId();
        shippingViewHolder.basketId = basket.getBasketId();

        onPriceCallBack.onPriceBack(basket.getPrice());

        shippingViewHolder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBasketItemClick.onItemClick(shippingViewHolder.productId, shippingViewHolder.basketId);
            }
        });
        shippingViewHolder.txtDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onDeleteItem.onDelete(basket);
            }
        });
    }

    @Override
    public int getItemCount() {
        return basketList.size();
    }

    public class ShippingViewHolder extends RecyclerView.ViewHolder {

        String productId, basketId;
        CardView parent;
        ImageView image;
        TextView txtTitle, txtGuarantee, txtPrice, txtFinalPrice, txtDelete;

        public ShippingViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.card_shippingItem_parent);
            image = itemView.findViewById(R.id.img_shippingItem_image);
            txtTitle = itemView.findViewById(R.id.txt_shippingItem_title);
            txtGuarantee = itemView.findViewById(R.id.txt_shippingItem_guarantee);
            txtPrice = itemView.findViewById(R.id.txt_shippingItem_price);
            txtFinalPrice = itemView.findViewById(R.id.txt_shippingItem_finalPrice);
            txtDelete = itemView.findViewById(R.id.txt_shippingItem_delete);
        }
    }

    public interface OnBasketItemClick {
        void onItemClick(String productId, String basketId);
    }

    public interface OnDeleteItem {
        void onDelete(Basket basket);
    }

    public interface OnPriceCallBack{
        void onPriceBack(String price);
    }
}
