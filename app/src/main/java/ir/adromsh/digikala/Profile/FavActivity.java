package ir.adromsh.digikala.Profile;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import ir.adromsh.digikala.Detail.DetailActivity;
import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Favorite;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class FavActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    String email;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    EditProfileViewModel viewModel=new EditProfileViewModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav);
        setupViews();
        email=getIntent().getExtras().getString("email");
        viewModel.getFavorites(email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Favorite>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Favorite> favorites) {
                        recyclerView.setAdapter(new FavAdapter(favorites, new FavAdapter.OnFavoriteClick() {
                            @Override
                            public void onFavClick(String productId) {
                                Intent intent=new Intent(FavActivity.this, DetailActivity.class);
                                intent.putExtra("id",productId);
                                startActivity(intent);
                            }
                        }, new FavAdapter.OnDeleteClick() {
                            @Override
                            public void ondelete(String favId) {
                                viewModel.deleteFav(favId)
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<Message>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {
                                                compositeDisposable.add(d);
                                            }

                                            @Override
                                            public void onSuccess(Message message) {
                                                Log.i("LOG", "onSuccess: "+message.getStatus());
                                            }

                                            @Override
                                            public void onError(Throwable e) {
                                                Log.i("LOG", "onError: "+e.toString());
                                            }
                                        });
                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: "+e.toString());
                    }
                });
    }

    private void setupViews() {
        recyclerView=findViewById(R.id.rv_fav);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
