package ir.adromsh.digikala.ShippingCart;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;

import ir.adromsh.digikala.Detail.DetailActivity;
import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Basket;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ShippingActivity extends AppCompatActivity {

    String basketCount;
    FrameLayout frameLayout;
    ImageView imgBasket;
    TextView txtTotalPrice, txtCartCount;
    RecyclerView recyclerView;
    Button btnFinalBuy;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ShippingViewModel viewModel;
    ShippingAdapter shippingAdapter;
    private int totalPrice=0;
    RelativeLayout totalPriceParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shipping);
        setupViews();
        basketCount = getIntent().getStringExtra("count");
        if(!basketCount.equals("0")){
            frameLayout.setVisibility(View.GONE);
            txtCartCount.setVisibility(View.VISIBLE);
            txtCartCount.setText(basketCount);

        }else{
            frameLayout.setVisibility(View.VISIBLE);
            totalPriceParent.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            btnFinalBuy.setVisibility(View.GONE);
        }

        observeForBasketList(viewModel.getUserEmail());
    }

    private void observeForBasketList(String email) {
        viewModel.getBasketList(email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Basket>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Basket> baskets) {
                        shippingAdapter = new ShippingAdapter(baskets, new ShippingAdapter.OnBasketItemClick() {
                            @Override
                            public void onItemClick(String productId, String basketId) {
                                Intent intent = new Intent(ShippingActivity.this, DetailActivity.class);
                                intent.putExtra("id", productId);
                                startActivity(intent);
                            }
                        }, new ShippingAdapter.OnDeleteItem() {
                            @Override
                            public void onDelete(final Basket basket) {
                                viewModel.deleteBasketItem(basket.getBasketId())
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<Message>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {
                                                compositeDisposable.add(d);
                                            }

                                            @Override
                                            public void onSuccess(Message message) {
                                                shippingAdapter.deleteRow(basket);
                                                int count = Integer.parseInt(txtCartCount.getText().toString());
                                                count--;
                                                if (count == 0) {
                                                    txtCartCount.setVisibility(View.GONE);
                                                } else {
                                                    txtCartCount.setVisibility(View.VISIBLE);
                                                    txtCartCount.setText(count + "");
                                                }
                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }
                                        });
                            }
                        }, new ShippingAdapter.OnPriceCallBack() {
                            @Override
                            public void onPriceBack(String price) {
                                totalPrice+=Integer.parseInt(price);
                                txtTotalPrice.setText(totalPrice+" تومان");
                            }
                        });
                        recyclerView.setAdapter(shippingAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void setupViews() {
        totalPriceParent=findViewById(R.id.rel_shipping_totalPriceParent);
        frameLayout=findViewById(R.id.frame_shipping_emptyBasket);
        viewModel = new ShippingViewModel(this);
        imgBasket = findViewById(R.id.img_shipping_basket);
        txtTotalPrice = findViewById(R.id.txt_shipping_totalPrice);
        txtCartCount = findViewById(R.id.txt_shipping_cartCount);
        recyclerView = findViewById(R.id.rv_shipping_productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnFinalBuy = findViewById(R.id.btn_shipping_buy);
        btnFinalBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Intent.ACTION_VIEW, Uri.parse("https://clicksite.org/app_digi_mellat/example.php"));
                startActivity(intent);
                finish();
            }
        });

    }
}
