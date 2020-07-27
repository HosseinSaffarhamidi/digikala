package ir.adromsh.digikala.Category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import ir.adromsh.digikala.Filter.FilterActivity;
import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CatFragment extends Fragment {
    View view;
    String cat;
    RecyclerView recyclerView;
    CatViewModel viewModel=new CatViewModel();
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    public static CatFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title",title);
        CatFragment fragment = new CatFragment();
        fragment.setArguments(args);
        return fragment;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.cat_fragment, container, false);
        }

        setupViews();
        viewModel.getTabItem(cat)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Product> products) {
                        recyclerView.setAdapter(new CatAdapter(getActivity(),products, new CatAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(String id) {
                                Intent intent=new Intent(getActivity(), FilterActivity.class);
                                intent.putExtra("id",id);
                                intent.putExtra("cat",cat);
                                startActivity(intent);
                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: "+e.toString());
                    }
                });

        return view;
    }



    private void setupViews() {
        cat=getArguments().getString("title");
        recyclerView=view.findViewById(R.id.rv_catFragment_catItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Log.i("LOG",cat);
    }

    @Override
    public void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }


}
