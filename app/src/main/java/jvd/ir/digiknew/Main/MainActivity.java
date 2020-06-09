package jvd.ir.digiknew.Main;

import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.navigation.ui.AppBarConfiguration;

import com.google.android.material.navigation.NavigationView;
import com.squareup.picasso.Picasso;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.digiknew.Basket.BasketActivity;
import jvd.ir.digiknew.Cat.CatActivity;
import jvd.ir.digiknew.Detail.DetailActivity;
import jvd.ir.digiknew.EditProfile.EditProfile;
import jvd.ir.digiknew.Login.LoginActivity;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.Banner;
import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.Model.Slider;
import jvd.ir.digiknew.R;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;

    private static final int LOGIN_REQUEST = 356;

    ImageView imgDrawer, imgCart, imgSearch, imgBanner1, imgBanner2, imgBanner3, imgBanner4;

    TextView txtH, txtM, txtS, txtLogin, txtBasketCount;

    DrawerLayout drawer;

    ProductAdapter productAdapter;

    RecyclerView recyclerViewWonderful, recyclerViewNewest;

    MainViewModel mainViewModel;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    ss.com.bannerslider.Slider slider;

    MainBannerAdapter mainBannerAdapter;

    Timer timer;

    public Handler handler;

    String email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupViews();

        observerWonderfulProducts();

        observerBanner();

        observerSlider();

        observeNewProduct();

        getTimer();

        checkLoginData();

        getBasketCount();

    }

    private void getBasketCount() {
        if (!email.equals("")) {
            mainViewModel.getBasketCount(email)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Message>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(final Message message) {
                            int count = Integer.parseInt(message.getMessage());
                            if (count > 0) {
                                txtBasketCount.setVisibility(View.VISIBLE);
                                txtBasketCount.setText(message.getMessage());
                                imgCart.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent intent = new Intent(MainActivity.this, BasketActivity.class);
                                        intent.putExtra("count", message.getMessage());
                                        startActivity(intent);
                                    }
                                });
                            } else {
                                txtBasketCount.setVisibility(View.INVISIBLE);
                                txtBasketCount.setText("0");
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("LOG16", "onError: " + e.toString());
                        }
                    });
        } else {
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void checkLoginData() {
        email = mainViewModel.getEmailData();

        if (!email.equals("")) {
            txtLogin.setText(email);
        }

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.equals("")) {
                    Intent intent = new Intent(MainActivity.this, EditProfile.class);
                    intent.putExtra("email", mainViewModel.getEmailData());
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivityForResult(intent, LOGIN_REQUEST);
                }
            }
        });


    }

    private void observeNewProduct() {
        mainViewModel.getProducts().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(final List<Product> products) {

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

                    }
                });
    }

    private void getTimer() {
        mainViewModel.getTimer().observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(new SingleObserver<MyTimer>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(final MyTimer myTimer) {
                        txtS.setText(myTimer.getSec() + "");
                        txtM.setText(myTimer.getMin() + "");
                        txtH.setText(myTimer.getHour() + "");

                        if (myTimer.getHour() < 10) {
                            txtH.setText("0" + myTimer.getHour());
                        }
                        if (myTimer.getMin() < 10) {
                            txtM.setText("0" + myTimer.getMin());
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
                                                txtS.setText("0" + myTimer.getSec());

                                            }
                                        });

                                    } else {
                                        runOnUiThread(new Runnable() {
                                            @Override
                                            public void run() {
                                                txtS.setText(myTimer.getSec() + "");
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
                                                    txtM.setText("0" + myTimer.getMin());
                                                    txtS.setText(myTimer.getSec() + "");
                                                }
                                            });

                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    txtM.setText(myTimer.getMin() + "");
                                                    txtS.setText(myTimer.getSec() + "");
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
                                                    txtH.setText("0" + myTimer.getHour());
                                                    txtM.setText(myTimer.getMin() + "");
                                                    txtS.setText(myTimer.getSec() + "");
                                                }
                                            });

                                        } else {
                                            runOnUiThread(new Runnable() {
                                                @Override
                                                public void run() {
                                                    txtH.setText(myTimer.getHour() + "");
                                                    txtM.setText(myTimer.getMin() + "");
                                                    txtS.setText(myTimer.getSec() + "");
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
                        Log.e("LOG4", "onError: " + e.toString());
                        //Toast.makeText(MainActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void observerSlider() {

        mainViewModel.getSlider().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Slider>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Slider> sliders) {
                        slider.setAdapter(new MainSliderAdapter(sliders));
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("LOG", "onError: " + e.toString());

                    }
                });
    }

    private void observerBanner() {

        mainViewModel.getBanners().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Banner>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Banner> banners) {

                        Picasso.get().load(banners.get(0).getmUrlBanner()).into(imgBanner1);
                        Picasso.get().load(banners.get(1).getmUrlBanner()).into(imgBanner2);
                        Picasso.get().load(banners.get(2).getmUrlBanner()).into(imgBanner3);
                        Picasso.get().load(banners.get(3).getmUrlBanner()).into(imgBanner4);
                        Log.e("LOG1", "onSuccess: " + banners);

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG", "onError: " + e.toString());
                    }
                });

    }

    private void observerWonderfulProducts() {
        mainViewModel.getProducts().subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Product>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);

                    }

                    @Override
                    public void onSuccess(final List<Product> products) {
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

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        timer.purge();
        timer.cancel();
        super.onDestroy();
    }

    private void setupViews() {

        mainViewModel = new MainViewModel(this);
        handler = new Handler();
        slider = findViewById(R.id.slider_main);
        drawer = findViewById(R.id.drawer_layout);
        imgDrawer = findViewById(R.id.img_toolbar_menu);
        imgCart = findViewById(R.id.img_toolbar_shoppingcart);
        imgSearch = findViewById(R.id.img_toolbar_search);
        imgBanner1 = findViewById(R.id.img_main_image1);
        imgBanner2 = findViewById(R.id.img_main_image2);
        imgBanner3 = findViewById(R.id.img_main_image3);
        imgBanner4 = findViewById(R.id.img_main_image4);
        txtH = findViewById(R.id.txt_hour);
        txtM = findViewById(R.id.txt_min);
        txtS = findViewById(R.id.txt_sec);
        txtBasketCount = findViewById(R.id.txt_main_basketCount);
        recyclerViewWonderful = findViewById(R.id.rv_main_wonderful_list);
        recyclerViewNewest = findViewById(R.id.rv_main_newest_list);


        imgDrawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(Gravity.RIGHT);
            }
        });
        ss.com.bannerslider.Slider.init(new PicassoImageLoadingService(this));


        NavigationView navigationView = findViewById(R.id.nav_view);

        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        recyclerViewWonderful.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));
        recyclerViewNewest.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, true));

        View headerView = navigationView.getHeaderView(0);

        txtLogin = headerView.findViewById(R.id.drawer_txt_Header);


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == LOGIN_REQUEST && data != null && resultCode == RESULT_OK) {
            if (drawer.isDrawerOpen(Gravity.RIGHT)) {
                drawer.closeDrawer(Gravity.RIGHT);

            }

            txtLogin.setText(data.getExtras().getString("email"));
            txtLogin.setBackground(null);
            mainViewModel.saveEmailData(data.getExtras().getString("email"));

        }

    }

    @Override
    public void onBackPressed() {
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
    protected void onResume() {
        super.onResume();
        if (drawer.isDrawerOpen(Gravity.RIGHT)) {
            drawer.closeDrawer(Gravity.RIGHT);
        }
        getBasketCount();
        checkLoginData();
    }
}
