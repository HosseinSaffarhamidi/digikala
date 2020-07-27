package ir.adromsh.digikala.Main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import ir.adromsh.digikala.Category.CatActivity;
import ir.adromsh.digikala.Detail.DetailActivity;
import ir.adromsh.digikala.Login.LoginActivity;
import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Banner;
import ir.adromsh.digikala.Model.Cat;
import ir.adromsh.digikala.Model.Product;
import ir.adromsh.digikala.Profile.ProfileActivity;
import ir.adromsh.digikala.R;
import ir.adromsh.digikala.ShippingCart.ShippingActivity;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import ss.com.bannerslider.Slider;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private static final int LOGIN_REQUEST = 356;
    DrawerLayout drawer;
    RecyclerView recyclerViewWonderful;
    RecyclerView recyclerViewNewest;
    RecyclerView recyclerViewCats;
    MainViewModel mainViewModel;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ImageView image1, image2, image3, image4, imgCart;
    Slider slider;
    Timer timer;
    TextView txtHour, txtMin, txtSec;
    TextView txtLogin, txtCartCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        getTimer();
        observeForWonderfulProduct();
        observeForBanners();
        observeForNewestProduct();
        observeForCats();
        checkLoginMode();


    }

    private void observeForCats() {
        mainViewModel.getCats()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Cat>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(final List<Cat> cats) {
                        recyclerViewCats.setAdapter(new CatsAdapter(cats, new CatsAdapter.OnCatClickListener() {
                            @Override
                            public void onClick(int position, String title) {
                                Intent intent = new Intent(MainActivity.this, CatActivity.class);
                                Log.i("LOG", "onClick: " + position);
                                intent.putExtra("position", position);
                                intent.putExtra("title", title);
                                intent.putParcelableArrayListExtra("cats", (ArrayList<? extends Parcelable>) cats);
                                startActivity(intent);
                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void checkLoginMode() {
        String email = mainViewModel.getUserEmail();
        if (!email.equals("")) {
            txtLogin.setText(email);
            mainViewModel.getBasketCount(email)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Message>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(Message message) {
                            if (!message.getMessage().equals("0")) {
                                txtCartCount.setVisibility(View.VISIBLE);
                                txtCartCount.setText(message.getMessage());
                            }else{
                                txtCartCount.setVisibility(View.GONE);

                            }
                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
        }
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        }
        checkLoginMode();
    }

    private void observeForNewestProduct() {
        mainViewModel.getProdcts().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Product> products) {
                        recyclerViewNewest.setAdapter(new ProductAdapter(products, new ProductAdapter.OnProductClick() {
                            @Override
                            public void onClick(String id) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG", "onError: " + e.toString());
                    }
                });
    }

    private void getTimer() {
        mainViewModel.getTimer().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<MyTimer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(final MyTimer myTimer) {
                        if (myTimer.getHour() < 10) {
                            txtHour.setText("0" + myTimer.getHour());
                        } else {
                            txtHour.setText(myTimer.getHour() + "");
                        }
                        if (myTimer.getMin() < 10) {
                            txtMin.setText("0" + myTimer.getMin());
                        } else {
                            txtMin.setText(myTimer.getMin() + "");
                        }
                        if (myTimer.getSec() < 10) {
                            txtSec.setText("0" + myTimer.getSec());
                        } else {
                            txtSec.setText(myTimer.getSec() + "");
                        }


                        timer = new Timer();
                        timer.scheduleAtFixedRate(new TimerTask() {
                            @Override
                            public void run() {

                                if (myTimer.getSec() != 0) {
                                    myTimer.setSec(myTimer.getSec() - 1);
                                    if (myTimer.getSec() < 10) {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                txtSec.setText("0" + myTimer.getSec());
                                            }
                                        });

                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                txtSec.setText(myTimer.getSec() + "");
                                            }
                                        });

                                    }
                                } else {
                                    if (myTimer.getMin() != 0) {
                                        myTimer.setMin(myTimer.getMin() - 1);
                                        myTimer.setSec(59);

                                        if (myTimer.getMin() < 10) {
                                            myTimer.setMin(myTimer.getMin() - 1);
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    txtMin.setText("0" + myTimer.getMin());
                                                    txtSec.setText(myTimer.getSec() + "");
                                                }
                                            });

                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    txtMin.setText(myTimer.getMin() + "");
                                                    txtSec.setText(myTimer.getSec() + "");
                                                }
                                            });

                                        }

                                    } else {
                                        myTimer.setMin(59);
                                        myTimer.setSec(59);
                                        myTimer.setHour(myTimer.getHour() - 1);

                                        if (myTimer.getHour() < 10) {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    txtHour.setText("0" + myTimer.getHour());
                                                    txtMin.setText(myTimer.getMin() + "");
                                                    txtSec.setText(myTimer.getSec() + "");
                                                }
                                            });

                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    txtHour.setText(myTimer.getHour() + "");
                                                    txtMin.setText(myTimer.getMin() + "");
                                                    txtSec.setText(myTimer.getSec() + "");
                                                }
                                            });

                                        }
                                    }

                                }
                            }
                        }, 0, 1000);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG", "onError: " + e.toString());
                    }
                });
    }

    private void observeForBanners() {
        mainViewModel.getBanners().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Banner> banners) {
                        List<Banner> sliderBanner = new ArrayList<>();
                        List<Banner> innerBanner = new ArrayList<>();
                        for (int i = 0; i < banners.size(); i++) {
                            if (banners.get(i).getType().equals("0")) {
                                sliderBanner.add(banners.get(i));
                            } else {
                                innerBanner.add(banners.get(i));
                            }

                        }

                        slider.setAdapter(new MainSliderAdapter(sliderBanner));
                        Picasso.get().load(innerBanner.get(0).getPic()).into(image1);
                        Picasso.get().load(innerBanner.get(1).getPic()).into(image2);
                        Picasso.get().load(innerBanner.get(2).getPic()).into(image3);
                        Picasso.get().load(innerBanner.get(3).getPic()).into(image4);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });
    }

    private void observeForWonderfulProduct() {
        mainViewModel.getProdcts().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Product> products) {
                        recyclerViewWonderful.setAdapter(new ProductAdapter(products, new ProductAdapter.OnProductClick() {
                            @Override
                            public void onClick(String id) {
                                Intent intent = new Intent(MainActivity.this, DetailActivity.class);
                                intent.putExtra("id", id);
                                startActivity(intent);
                            }
                        }));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: " + e.toString());
                    }
                });
    }


    private void setupViews() {
        txtCartCount = findViewById(R.id.txt_main_cardCount);
        ImageView imgCart = findViewById(R.id.img_main_cart);

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ShippingActivity.class);
                intent.putExtra("count",txtCartCount.getText().toString());
                startActivity(intent);
            }
        });

        recyclerViewCats = findViewById(R.id.rv_main_cats);
        recyclerViewCats.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        mainViewModel = new MainViewModel(this);
        txtHour = findViewById(R.id.txt_main_timerHour);
        txtMin = findViewById(R.id.txt_main_timerMin);
        txtSec = findViewById(R.id.txt_main_timerSec);
        image1 = findViewById(R.id.img_main_image1);
        image2 = findViewById(R.id.img_main_image2);
        image3 = findViewById(R.id.img_main_image3);
        image4 = findViewById(R.id.img_main_image4);
        slider = findViewById(R.id.slider_main);
        Slider.init(new PicassoImageLoadingService(MainActivity.this));
        ImageView imgMenu = findViewById(R.id.img_toolbar_menu);
        imgMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
        ImageView imgSearch = findViewById(R.id.img_toolbar_search);
        imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,SearchActivity.class);
                startActivity(intent);
            }
        });
        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        final View headerView = navigationView.getHeaderView(0);
        txtLogin = headerView.findViewById(R.id.txt_navigation_login);
        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainViewModel.getUserEmail().equals("")) {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                    intent.putExtra("email", mainViewModel.getUserEmail());
                    startActivity(intent);

                }


            }
        });

        recyclerViewWonderful = findViewById(R.id.rv_main_wonderfulList);
        recyclerViewWonderful.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));

        recyclerViewNewest = findViewById(R.id.rv_main_newestList);
        recyclerViewNewest.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_tools) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        timer.purge();
        timer.cancel();
        super.onDestroy();
    }
}
