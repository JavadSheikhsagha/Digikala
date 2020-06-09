package jvd.ir.digiknew.SignUp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.regex.Pattern;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.R;

public class SignUpActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable=new CompositeDisposable();

    EditText edtEmail,edtPass;

    Button btnSignUp;

    SignUpViewModel signUpViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        setupViews();
    }

    private void setupViews() {

        signUpViewModel=new SignUpViewModel(this);
        ImageView imgClose= findViewById(R.id.img_close_signUp);
        edtEmail=findViewById(R.id.edt_Login_Email);
        edtPass=findViewById(R.id.edt_Login_Password);
        btnSignUp=findViewById(R.id.btn_SignUp);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isValidEmailId(edtEmail.getText().toString())){
                    edtEmail.setError("لطفا ایمیل را درست وارد کنید.");
                } else if(edtEmail.getText().toString().equals("")){
                    edtEmail.setError("لطفا تمامی فیلد هارا پر کنید.");
                } else if (edtPass.getText().toString().equals("")) {
                    edtPass.setError("کلمه عبور نمیتواند خالی باشد.");
                } else if (isValidEmailId(edtEmail.getText().toString())){
                    SignUp();

                }

            }
        });

        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void SignUp() {
        signUpViewModel.signUp(edtEmail.getText().toString(),edtPass.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Message>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Message message) {
                        Log.i("LOG8", "onSuccess: "+message.getMessage());
                        signUpViewModel.saveUserInfo(message.getMessage());
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG8", "onError: "+e.toString());
                    }
                });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }

    private boolean isValidEmailId(String email){

        return Pattern.compile("^(([\\w-]+\\.)+[\\w-]+|([a-zA-Z]{1}|[\\w-]{2,}))@"
                + "((([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\."
                + "([0-1]?[0-9]{1,2}|25[0-5]|2[0-4][0-9])\\.([0-1]?"
                + "[0-9]{1,2}|25[0-5]|2[0-4][0-9])){1}|"
                + "([a-zA-Z]+[\\w-]+\\.)+[a-zA-Z]{2,4})$").matcher(email).matches();
    }
}
