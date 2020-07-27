package ir.adromsh.digikala.Main;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import ir.adromsh.digikala.CompareProduct.SearchItemAdapter;
import ir.adromsh.digikala.Detail.DetailActivity;
import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SearchActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView imgMic;
    EditText edtSearch;
    MainViewModel viewModel;
    SearchItemAdapter searchItemAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setupViews();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            edtSearch.setText(result.get(0));
        }
    }

    private void setupViews() {
        viewModel = new MainViewModel(this);
        recyclerView = findViewById(R.id.rv_search_productList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        imgMic = findViewById(R.id.img_search_mic);
        imgMic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "fa");
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "نام محصول را بگویید");
                try {
                    startActivityForResult(intent, 100);
                } catch (ActivityNotFoundException e) {
                    Toast.makeText(SearchActivity.this, "متاسفانه گوشی شما از این قابلیت پشتیبانی نمی کند", Toast.LENGTH_SHORT).show();
                }
            }
        });

        edtSearch = findViewById(R.id.edt_search_search);

        edtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, final int start, int before, int count) {
                if (!edtSearch.getText().toString().isEmpty()) {
                    viewModel.search(String.valueOf(s))
                            .subscribeOn(Schedulers.newThread())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(new SingleObserver<List<Product>>() {
                                @Override
                                public void onSubscribe(Disposable d) {

                                }

                                @Override
                                public void onSuccess(List<Product> products) {
                                    searchItemAdapter = new SearchItemAdapter(new SearchItemAdapter.OnSearchedItemClickListener() {
                                        @Override
                                        public void onClickListener(String properties, String image, String id) {
                                            Intent intent=new Intent(SearchActivity.this, DetailActivity.class);
                                            intent.putExtra("id",id);
                                            startActivity(intent);

                                        }
                                    });

                                    searchItemAdapter.onbind(products);
                                    recyclerView.setAdapter(searchItemAdapter);
                                }

                                @Override
                                public void onError(Throwable e) {
                                    Log.i("LOG", "onError: ");
                                }
                            });
                } else {
                    searchItemAdapter.onbind(new ArrayList<Product>());
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
