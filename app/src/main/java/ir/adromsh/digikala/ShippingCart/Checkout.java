package ir.adromsh.digikala.ShippingCart;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

import ir.adromsh.digikala.Model.Basket;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class Checkout extends AppCompatActivity {

    String orderId;
    RecyclerView recyclerView;
    TextView txtName,txtTransactionCode;
    ShippingViewModel viewModel;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ShippingAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        setupViews();
        Uri uri =getIntent().getData();
        orderId=uri.getQueryParameter("order_id");
        txtTransactionCode.setText(orderId);

        observeForUpdateBasket();


    }

    private void observeForUpdateBasket() {
        viewModel.updateBasket(orderId,viewModel.getUserEmail())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Basket>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Basket> baskets) {
                        Log.i("LOG", "onSuccess: "+baskets.get(0).getTitle());
                        adapter=new ShippingAdapter(baskets, new ShippingAdapter.OnBasketItemClick() {
                            @Override
                            public void onItemClick(String productId, String basketId) {

                            }
                        }, new ShippingAdapter.OnDeleteItem() {
                            @Override
                            public void onDelete(Basket basket) {

                            }
                        }, new ShippingAdapter.OnPriceCallBack() {
                            @Override
                            public void onPriceBack(String price) {

                            }
                        });
                        recyclerView.setAdapter(adapter);
                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.i("LOG", "onError: "+e.toString());
                    }
                });
    }

    private void setupViews() {
        viewModel=new ShippingViewModel(this);
        recyclerView=findViewById(R.id.rv_checkOut_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        txtName=findViewById(R.id.txt_checkOut_userName);
        txtTransactionCode=findViewById(R.id.txt_checkOut_trancactionCode);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
