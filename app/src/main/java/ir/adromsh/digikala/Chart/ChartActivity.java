package ir.adromsh.digikala.Chart;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;
import java.util.List;

import ir.adromsh.digikala.Model.HistoryModel;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class ChartActivity extends AppCompatActivity {

    LineChart lineChart;
    List<HistoryModel> myHistory;
    String id,title;
    ChartViewModel viewModel=new ChartViewModel();
    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);
        setupViews();
        id=getIntent().getExtras().getString("id");
        title=getIntent().getExtras().getString("title");
        viewModel.getHistory(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<HistoryModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<HistoryModel> historyModels) {
                        if(historyModels.size()>0){
                            myHistory=historyModels;
                            final List<Entry> values = new ArrayList<>();
                            for (int i = 0; i <historyModels.size() ; i++) {
                                values.add(new Entry(i,Integer.parseInt(historyModels.get(i).getPrice())));
                            }
                            LineDataSet lineDataSet = new LineDataSet(values, "نمودار قیمت "+title);
                            lineDataSet.setColor(ContextCompat.getColor(ChartActivity.this, R.color.colorAccent));
                            lineDataSet.setLineWidth(3f);
                            lineDataSet.setValueTextSize(10f);
                            lineDataSet.setValueTextColor(ContextCompat.getColor(ChartActivity.this, R.color.colorBlack));
                            lineDataSet.setCircleColor(ContextCompat.getColor(ChartActivity.this, R.color.colorAccent));
                            lineDataSet.setFillColor(ContextCompat.getColor(ChartActivity.this, R.color.colorAccent));
                            lineDataSet.setDrawFilled(true);
                            lineDataSet.setFillDrawable(ContextCompat.getDrawable(ChartActivity.this, R.drawable.chart_gradient));
                            List<ILineDataSet> dataSets = new ArrayList<>();
                            dataSets.add(lineDataSet);
                            LineData lineData = new LineData(dataSets);
                            lineChart.animateX(1000);
                            final XAxis xAxis=lineChart.getXAxis();

                            xAxis.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getFormattedValue(float value) {
                                    xAxis.setLabelCount(4,true);
                                    return myHistory.get((int)value).getDate();
                                }
                            });
                            /*xAxis.setValueFormatter(new ValueFormatter() {
                                @Override
                                public String getFormattedValue(float value) {
                                    Log.i("LOG", "getFormattedValue: "+value);
                                    return value+"fdsf";
                                }
                            });*/
                            lineChart.setData(lineData);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: "+e.toString());
                    }
                });

    }

    private void setupViews() {
        lineChart = findViewById(R.id.chart_chart);
        /*List<Entry> values = new ArrayList<>();


        values.add(new Entry(0, 60f));
        values.add(new Entry(1, 50f));
        values.add(new Entry(2, 53f));
        values.add(new Entry(3, 58f));
        values.add(new Entry(4, 48f));
        values.add(new Entry(10, 60f));*/
        /*List<Entry> keys=new ArrayList<>();
        keys.add(new Entry(0,20f));
        keys.add(new Entry(1,12f));
        keys.add(new Entry(2,46f));
        keys.add(new Entry(3,19f));
        keys.add(new Entry(4,48f));
        keys.add(new Entry(10,77f));*/
//        LineDataSet lineDataSet = new LineDataSet(values, "line data set");
//        LineDataSet lineDataSet2=new LineDataSet(keys,"line keys set");
//        lineDataSet.setColor(ContextCompat.getColor(this, R.color.colorAccent));
//        lineDataSet.setLineWidth(3f);
//        lineDataSet.setValueTextSize(10f);
//        lineDataSet.setValueTextColor(ContextCompat.getColor(this, R.color.colorBlack));
//        lineDataSet.setCircleColor(ContextCompat.getColor(this, R.color.colorAccent));
//        lineDataSet.setFillColor(ContextCompat.getColor(this, R.color.colorAccent));
//        lineDataSet.setDrawFilled(true);
//        lineDataSet.setFillDrawable(ContextCompat.getDrawable(this, R.drawable.chart_gradient));
//        lineDataSet2.setDrawFilled(true);
//        lineDataSet2.setFillDrawable(ContextCompat.getDrawable(this,R.drawable.chart_gradient));
//        List<ILineDataSet> dataSets = new ArrayList<>();
//        dataSets.add(lineDataSet);
//        dataSets.add(lineDataSet2);
//        LineData lineData = new LineData(dataSets);
//        lineChart.animateX(1000);
//        lineChart.setData(lineData);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

}
