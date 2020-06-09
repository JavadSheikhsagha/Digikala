package jvd.ir.digiknew.Basket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.digiknew.Detail.DetailActivity;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.BasketProduct;
import jvd.ir.digiknew.R;

public class BasketActivity extends AppCompatActivity {

    Button btnSubmit;

    RecyclerView recyclerView;

    TextView txtBasketCount,txtPriceTotal;

    ImageView imgBack;

    String basketCount,email,productId;

    BasketViewModel viewModel;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    BasketAdapter basketAdapter;

    int totalPrice=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);

        basketCount=getIntent().getExtras().getString("count");
        productId = getIntent().getExtras().getString("productId");

        setupViews();

        getBasketInfo();

    }

    private void getBasketInfo() {

        viewModel.getBasketInfo(viewModel.getUserEmail())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<BasketProduct>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<BasketProduct> basketProducts) {
                        basketAdapter=new BasketAdapter(basketProducts, new BasketAdapter.onCardClick() {
                            @Override
                            public void onClick(String productId) {
                                Intent intent=new Intent(BasketActivity.this,DetailActivity.class);
                                intent.putExtra("id",productId);
                                startActivity(intent);
                            }
                        }, new BasketAdapter.onDeleteClick() {
                            @Override
                            public void onClick(final BasketProduct basketProduct) {
                                viewModel.deleteBasket(basketProduct.getBasketId())
                                        .subscribeOn(Schedulers.newThread())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(new SingleObserver<Message>() {
                                            @Override
                                            public void onSubscribe(Disposable d) {
                                                compositeDisposable.add(d);
                                            }

                                            @Override
                                            public void onSuccess(Message message) {
                                                basketAdapter.deleteRow(basketProduct);
                                                int count = Integer.parseInt(txtBasketCount.getText().toString());
                                                count--;

                                                if (count == 0) {
                                                    txtBasketCount.setVisibility(View.INVISIBLE);
                                                } else {
                                                    txtBasketCount.setVisibility(View.VISIBLE);
                                                    txtBasketCount.setText(count + "");
                                                }
                                            }

                                            @Override
                                            public void onError(Throwable e) {

                                            }
                                        });
                            }
                        }, new BasketAdapter.onTotalPrice() {
                            @Override
                            public void onPrice(String price) {
                                totalPrice+=Integer.parseInt(price);
                                txtPriceTotal.setText(totalPrice+" تومان ");
                            }
                        });
                        recyclerView.setAdapter(basketAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG17", "onError: "+e.toString() );
                    }
                });
    }

    private void setupViews() {


        viewModel=new BasketViewModel(this);
        btnSubmit = findViewById(R.id.btn_basket_submit);

        recyclerView = findViewById(R.id.rv_basket_rv);

        txtBasketCount = findViewById(R.id.txt_basket_count);
        txtPriceTotal = findViewById(R.id.txt_basket_price);

        imgBack = findViewById(R.id.img_basket_close);

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txtBasketCount.setText(basketCount);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


    @Override
    protected void onDestroy() {
        compositeDisposable.clear();

        super.onDestroy();
    }
}
