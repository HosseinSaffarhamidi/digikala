package ir.adromsh.digikala.Properties;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.List;

import ir.adromsh.digikala.Model.Properties;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class PropertiesActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    TextView txtTitle;
    PropertiesViewModel viewModel =new PropertiesViewModel();
    ImageView imgClose;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_properties);
        setupViews();
        String title=getIntent().getExtras().getString("title");
        txtTitle.setText(title);
        observeForProperties();
    }

    private void observeForProperties() {
        viewModel.getProperties().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Properties>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Properties> properties) {
                        YoYo.with(Techniques.SlideInRight)
                                .duration(1000)
                                .repeat(0)
                                .playOn(findViewById(R.id.rv_properties_propertiesList));
                        recyclerView.setAdapter(new PropertiesAdapter(getApplicationContext(),properties));


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: "+e.toString());
                    }
                });
    }

    private void setupViews() {
        imgClose=findViewById(R.id.img_properties_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtTitle=findViewById(R.id.txt_properties_title);
        recyclerView=findViewById(R.id.rv_properties_propertiesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
