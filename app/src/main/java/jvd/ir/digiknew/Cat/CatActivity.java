package jvd.ir.digiknew.Cat;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.google.android.material.tabs.TabLayout;

import jvd.ir.digiknew.R;

public class CatActivity extends AppCompatActivity {

    ImageView imgBack;
    TabLayout tabLayout;
    ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cat);

        setupViews();
    }

    private void setupViews() {

        tabLayout=findViewById(R.id.tab_cats_tabLayout);
        viewPager=findViewById(R.id.vp_cats_viewPager);
        imgBack=findViewById(R.id.img_cats_back);



        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        CatViewPager catViewPager=new CatViewPager(getSupportFragmentManager());
        catViewPager.addFragment("ورزشی");
        catViewPager.addFragment("سرگرمی");
        catViewPager.addFragment("هنر");
        catViewPager.addFragment("کالای دیجیتال");
        catViewPager.addFragment("مادر و کودک");

        viewPager.setAdapter(catViewPager);
        viewPager.setCurrentItem(0);


        tabLayout.setupWithViewPager(viewPager);
    }
}
