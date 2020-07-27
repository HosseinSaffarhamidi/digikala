package ir.adromsh.digikala.CompareProduct;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.adromsh.digikala.Model.Properties;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CompareActivity extends AppCompatActivity {

    String imageUrl;
    RecyclerView recyclerView;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    ImageView imgOriginal;
    ImageView imgSecond;
    CompareViewModel viewModel=new CompareViewModel();
    CompareProductAdapter compareProductAdapter;
    List<Properties> secondList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        setupViews();
        imageUrl=getIntent().getExtras().getString("image_url");
        Picasso.get().load(imageUrl).into(imgOriginal);

        viewModel.getProperties()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Properties>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Properties> propertiesList) {
                        compareProductAdapter=new CompareProductAdapter(CompareActivity.this,propertiesList);
                        recyclerView.setAdapter(compareProductAdapter);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: "+e.toString());
                    }
                });
    }

    private void setupViews() {
        secondList=new ArrayList<>();
        imgOriginal=findViewById(R.id.img_compare_orginal);
        imgSecond=findViewById(R.id.img_compare_second);
        imgSecond.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent=new Intent(getApplicationContext(),CompareSearchActivity.class);
                startActivityForResult(intent,100);
            }
        });
        recyclerView=findViewById(R.id.rv_compare_compareList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100 && resultCode==RESULT_OK){
            secondList.clear();
            Picasso.get().load(data.getExtras().getString("image")).into(imgSecond);
            String propertiesList=data.getExtras().getString("properties");
            try {
                JSONArray jsonArray=new JSONArray(propertiesList);
                for (int i = 0; i <jsonArray.length() ; i++) {
                    Properties properties=new Properties();
                    JSONObject jsonObject=jsonArray.getJSONObject(i);
                    properties.setTitle(jsonObject.getString("title"));
                    properties.setValue(jsonObject.getString("value"));
                    properties.setmSecond(jsonObject.getString("second"));
                    secondList.add(properties);
                }

                Log.i("LOG", "onActivityResult: "+secondList.size());
                compareProductAdapter.bindSecondProduct(secondList);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
