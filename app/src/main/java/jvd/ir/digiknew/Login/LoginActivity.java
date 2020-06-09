package jvd.ir.digiknew.Login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.digiknew.Main.MainActivity;
import jvd.ir.digiknew.R;
import jvd.ir.digiknew.SignUp.SignUpActivity;

public class LoginActivity extends AppCompatActivity {

    TextView txt_Sign;

    ImageView img_Close;

    CheckBox showPass;

    EditText edtPass,edtEmail;

    Button btnLogin;

    LoginViewModel loginViewModel;

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        setupViews();

    }

    private void login() {
        loginViewModel.login(edtEmail.getText().toString(),edtPass.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Message>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Message message) {

                        if (message.getMessage().equals("Not Found!")){
                            Toast.makeText(LoginActivity.this, "ایمیل یا کلمه عبور اشتباه است.", Toast.LENGTH_SHORT).show();

                        } else {

                           loginViewModel.saveUserEmail(message.getMessage());
                            finish();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("LOG", "onError: "+e.toString() );
                    }
                });
    }

    private void setupViews() {

        loginViewModel=new LoginViewModel(this);

        txt_Sign=findViewById(R.id.txt_SignUp);
        img_Close=findViewById(R.id.img_close_login);
        showPass=findViewById(R.id.cb_Login_ShowPass);
        edtPass=findViewById(R.id.edt_Login_Password);
        edtEmail=findViewById(R.id.edt_Login_Email);
        btnLogin=findViewById(R.id.btn_Login);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        img_Close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        txt_Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();
            }
        });

        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (showPass.isChecked()){
                    edtPass.setInputType(InputType.TYPE_CLASS_TEXT);
                } else {
                    edtPass.setInputType(InputType.TYPE_TEXT_VARIATION_PASSWORD|InputType.TYPE_CLASS_TEXT);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }
}
