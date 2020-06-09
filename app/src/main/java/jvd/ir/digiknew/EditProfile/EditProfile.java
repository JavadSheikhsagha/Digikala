package jvd.ir.digiknew.EditProfile;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import jvd.ir.digiknew.Model.Profile;
import jvd.ir.digiknew.R;

public class EditProfile extends AppCompatActivity {

    RecyclerView recyclerView;

    TextView txtEmail, txtUsername;

    ImageView imgBack;

    String email;

    List<Profile> profileList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        setupViews();

        loadProfileMenu();
    }

    private void loadProfileMenu() {
        Profile edtProfile = new Profile();
        edtProfile.setTitle("ویرایش اطلاعات");
        edtProfile.setIcon(R.drawable.ic_person_white_24dp);
        profileList.add(edtProfile);

        Profile orderProfile = new Profile();
        orderProfile.setTitle("سفارشات من");
        orderProfile.setIcon(R.drawable.ic_add_shopping_cart_black_24dp);
        profileList.add(orderProfile);

        Profile favorite = new Profile();
        favorite.setTitle("لیست مورد علاقه");
        favorite.setIcon(R.drawable.ic_like_black_24dp);
        profileList.add(favorite);

        Profile message = new Profile();
        message.setTitle("پیام ها");
        message.setIcon(R.drawable.ic_comment_black_24dp);
        profileList.add(message);

        Profile address = new Profile();
        address.setTitle("آدرس");
        address.setIcon(R.drawable.ic_directions_car_black_24dp);
        profileList.add(address);

        Profile cardNumber = new Profile();
        cardNumber.setTitle("شماره کارت");
        cardNumber.setIcon(R.drawable.ic_shopping_cart_black_24dp);
        profileList.add(cardNumber);

        Profile exit = new Profile();
        exit.setTitle("خروج");
        exit.setIcon(R.drawable.ic_close_black_24dp);
        profileList.add(exit);


        recyclerView.setAdapter(new EditProfileAdapter(this, profileList, new EditProfileAdapter.EditProfileOnClick() {
            @Override
            public void onClick(int position) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent(EditProfile.this, EditingProfile.class);
                        intent.putExtra("email", email);
                        startActivity(intent);

                }
            }
        }));
    }

    private void setupViews() {

        txtEmail = findViewById(R.id.txt_edtProfile_email);
        txtUsername = findViewById(R.id.txt_edtProfile_username);
        recyclerView = findViewById(R.id.rv_edtProfile);
        imgBack = findViewById(R.id.img_edtProfile_back);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        email = getIntent().getExtras().getString("email");
        txtEmail.setText(email);

    }
}
