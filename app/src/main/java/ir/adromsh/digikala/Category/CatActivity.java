package ir.adromsh.digikala.Category;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.List;

import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.R;

public class CatActivity extends AppCompatActivity {

    TabLayout tabLayout;
    ViewPager viewPager;
    List<Cat> cats;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);
        cats=getIntent().getParcelableArrayListExtra("cats");
        position=getIntent().getExtras().getInt("position");
        setupViews();


    }

    private void setupViews() {
        tabLayout=findViewById(R.id.tab_cat);
        viewPager=findViewById(R.id.viewPager_cat);
        tabLayout.setupWithViewPager(viewPager);
        MyViewPagerAdapter adapter=new MyViewPagerAdapter(getSupportFragmentManager());

        for (int i = cats.size()-1; i >=0; i--) {
            adapter.addFramgent(cats.get(i).getTitle());
        }

        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem((cats.size()-1)-position);
    }
}
