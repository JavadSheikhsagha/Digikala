package jvd.ir.digiknew.Detail;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.digiknew.Basket.BasketActivity;
import jvd.ir.digiknew.Chart.ChartActivity;
import jvd.ir.digiknew.Comments.CommentAvitivity;
import jvd.ir.digiknew.CompareProduct.CompareActivity;
import jvd.ir.digiknew.Login.LoginActivity;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.Detail;
import jvd.ir.digiknew.Model.Product;
import jvd.ir.digiknew.Properties.PropertiesActivity;
import jvd.ir.digiknew.R;

public class DetailActivity extends AppCompatActivity {

    ImageView imgMore,imgForward,imgCart,imgShare,imgFavorite,imgProduct;
    TextView txtTitle,txtSubTitle,txtColor,txtPrice,txtPrePrice,txtDetail,txtRate,txtGuarantee,txtMoreDetail,txtActionBar,txtBasketSize;
    RatingBar ratingBar;
    CardView cardProperties,cardComments;
    Button btnAdd;
    RecyclerView rv_rate;
    NestedScrollView nestedScrollView;
    RelativeLayout actionBar;


    DetailViewModel detailViewModel;


    Thread thread;
    Handler handler;


    String PRODUCT_ID;
    String PRODUCT_NAME;
    String basketCount;
    String email;


    CompositeDisposable compositeDisposable=new CompositeDisposable();


    int toolbarMergColor,drawableMergeColor;

    int toolbarTitleYPosition=55;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        SetupViews();

        email=detailViewModel.getUserEmail();

        txtActionBar.setTranslationY(200);

        getIntentId();

        getDetail();

        getBasketCount();
    }

    private void getBasketCount() {

        if (!email.equals("")) {
            detailViewModel.getBasketCount(email)
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
                                //Log.i("LOG16", "onSuccess: " + message.getStatus());
                                basketCount = message.getMessage();
                                txtBasketSize.setText(basketCount);
                                txtBasketSize.setVisibility(View.VISIBLE);
                            }

                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.e("LOG16", "onError: " + e.toString());
                        }
                    });
        }
    }

    private void getIntentId() {
        PRODUCT_ID=getIntent().getExtras().getString("id");
    }

    private void getDetail() {
        detailViewModel.getDetail(PRODUCT_ID)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Detail>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Detail> details) {
                        Picasso.get().load(details.get(0).getImage()).into(imgProduct);
                        //PRODUCT_ID=details.get(0).getId();
                        PRODUCT_NAME=details.get(0).getTitle();
                        txtTitle.setText(details.get(0).getTitle());
                        txtPrice.setText(details.get(0).getPrice());
                        txtColor.setText(details.get(0).getColor());
                        txtSubTitle.setText(details.get(0).getTitle());
                        txtActionBar.setText(details.get(0).getTitle());
                        //txtPrePrice.setText(details.get(0).getPrice());
                        txtRate.setText(details.get(0).getId());
                        txtColor.setText(details.get(0).getColor());
                        txtGuarantee.setText(details.get(0).getGarantee());
                        txtRate.setText(details.get(0).getColor());
                        ratingBar.setMax(5);
                        ratingBar.setProgress(Integer.parseInt(details.get(0).getColor()));
                        txtDetail.setText(details.get(0).getIntroduction());

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG9", "onError: "+e.toString() );
                    }
                });
    }

    private void SetupViews() {


        detailViewModel=new DetailViewModel(this);

        nestedScrollView=findViewById(R.id.scroll_detail_nested);

        btnAdd=findViewById(R.id.btn_detail_add);

        rv_rate=findViewById(R.id.rv_detail_rate);

        actionBar=findViewById(R.id.bar_detail);

        imgProduct=findViewById(R.id.img_detail_slider);
        imgCart=findViewById(R.id.ic_toolbar_cart);
        imgForward=findViewById(R.id.ic_toolbar_forward);
        imgMore=findViewById(R.id.ic_toolbar_more);
        imgFavorite=findViewById(R.id.ic_fav);
        imgShare=findViewById(R.id.ic_share);

        txtBasketSize = findViewById(R.id.txt_basket_size);
        txtSubTitle=findViewById(R.id.txt_detail_subTitle);
        txtTitle=findViewById(R.id.txt_detail_title);
        txtColor=findViewById(R.id.txt_detail_color);
        txtGuarantee=findViewById(R.id.txt_detail_guarantee);
        txtPrice=findViewById(R.id.txt_detail_price);
        txtPrePrice=findViewById(R.id.txt_detail_pPrice);
        txtDetail=findViewById(R.id.txt_detail_detail);
        txtRate=findViewById(R.id.txt_detail_rate);
        txtMoreDetail=findViewById(R.id.txt_detail_moreDetail);
        txtActionBar=findViewById(R.id.txt_detail_actionBar);

        cardComments=findViewById(R.id.card_detail_comments);
        cardProperties=findViewById(R.id.card_detail_details);

        ratingBar=findViewById(R.id.rat_detail_rate);

        rv_rate.setLayoutManager(new LinearLayoutManager(this));

        scrollThingy();

        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.equals("")){
                    Intent intent = new Intent(DetailActivity.this, BasketActivity.class);
                    intent.putExtra("count",basketCount);
                    intent.putExtra("productId",PRODUCT_ID);
                    startActivity(intent);
                } else {
                    startActivity(new Intent(DetailActivity.this,LoginActivity.class));
                }
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!email.equals("")){
                    addToBasket();

                } else {
                    Intent intent=new Intent(DetailActivity.this,LoginActivity.class);
                    startActivity(intent);
                }

            }
        });

        cardProperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this, PropertiesActivity.class);
                intent.putExtra("id",PRODUCT_ID);
                startActivity(intent);
            }
        });

        cardComments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(DetailActivity.this, CommentAvitivity.class);
                intent.putExtra("id",PRODUCT_ID);
                intent.putExtra("name",PRODUCT_NAME);
                startActivity(intent);
            }
        });

        imgForward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        imgMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu=new PopupMenu(DetailActivity.this,imgMore);
                popupMenu.getMenuInflater().inflate(R.menu.detail_more_menu,popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if (item.getItemId()==R.id.popup_chart){

                            Intent intent=new Intent(getApplicationContext(), ChartActivity.class);
                            startActivity(intent);

                        } else {
                            Intent intent=new Intent(DetailActivity.this, CompareActivity.class);
                            startActivity(intent);
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        imgFavorite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (email.equals("")){
                    Intent intent=new Intent(DetailActivity.this,LoginActivity.class);
                    startActivity(intent);
                } else{

                    final FavoriteBottomSheet favoriteBottomSheet=new FavoriteBottomSheet();
                    favoriteBottomSheet.setOnAddButtonClick(new FavoriteBottomSheet.OnAddButtonClick() {
                        @Override
                        public void onClick() {

                            final NewFavDialog newFavDialog=new NewFavDialog();
                            newFavDialog.setOnAddButtonClick(new NewFavDialog.onAddButtonClick() {
                                @Override
                                public void onClick(String folderName) {
                                    //Toast.makeText(DetailActivity.this, folderName , Toast.LENGTH_SHORT).show();
                                    newFavDialog.dismiss();
                                }
                            });

                            newFavDialog.show(getSupportFragmentManager(),null);
                        }
                    });
                    favoriteBottomSheet.show(getSupportFragmentManager(),null);
                }


            }
        });

    }

    private void addToBasket() {
        detailViewModel.addToBasket(PRODUCT_ID,email)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Message>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Message message) {

                            Toast.makeText(DetailActivity.this, "محصول با موفقیت به سبد خرید اضافه شد", Toast.LENGTH_SHORT).show();
                            int currentCount= Integer.parseInt(txtBasketSize.getText().toString());
                            currentCount++;
                            txtBasketSize.setText(currentCount+"");
                            txtBasketSize.setVisibility(View.VISIBLE);


                        Log.i("LOG15", "onSuccess: "+message.getStatus());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG15", "onError: "+e.toString());
                    }
                });
    }

    private void scrollThingy() {

        nestedScrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
            @Override
            public void onScrollChange(NestedScrollView v, int scrollX, final int scrollY, int oldScrollX, final int oldScrollY) {


                thread=new Thread(new Runnable() {
                    @Override
                    public void run() {

                        if(scrollY>600 && scrollY>oldScrollY && scrollY<1000){
                            if (toolbarTitleYPosition>-47){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        txtActionBar.setTranslationY(toolbarTitleYPosition-5);
                                        toolbarTitleYPosition=toolbarTitleYPosition-5;
                                    }
                                });
                            }
                        } else if (scrollY<600 && scrollY<oldScrollY && scrollY>400){
                            if (toolbarTitleYPosition<150){
                                runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        txtActionBar.setTranslationY(toolbarTitleYPosition+5);
                                        toolbarTitleYPosition=toolbarTitleYPosition+5;
                                    }
                                });
                            }
                        } else if (scrollY<400){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    toolbarTitleYPosition=150;
                                    txtActionBar.setTranslationY(toolbarTitleYPosition);
                                }
                            });
                        } else if (scrollY>1000){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    toolbarTitleYPosition=-47;
                                    txtActionBar.setTranslationY(toolbarTitleYPosition);
                                }
                            });

                        }

                        if(scrollY>50 && scrollY<1000){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    float ratio=(scrollY/1000f);

                                    toolbarMergColor= ColorUtils.blendARGB(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite),ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark),ratio);
                                    actionBar.setBackgroundColor(toolbarMergColor);

                                    drawableMergeColor=ColorUtils.blendARGB(ContextCompat.getColor(getApplicationContext(),R.color.colorGrey500),ContextCompat.getColor(getApplicationContext(),R.color.colorWhite),ratio);
                                    DrawableCompat.setTint(imgForward.getDrawable(),drawableMergeColor);
                                    DrawableCompat.setTint(imgCart.getDrawable(),drawableMergeColor);
                                    DrawableCompat.setTint(imgMore.getDrawable(),drawableMergeColor);
                                }
                            });

                        } else if (scrollY<50 && oldScrollY>scrollY){

                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    actionBar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorWhite));
                                }
                            });

                        } else if (scrollY>1000){
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    actionBar.setBackgroundColor(ContextCompat.getColor(getApplicationContext(),R.color.colorPrimaryDark));
                                }
                            });

                        }
                    }
                });
                thread.start();
            }
        });

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }







}
