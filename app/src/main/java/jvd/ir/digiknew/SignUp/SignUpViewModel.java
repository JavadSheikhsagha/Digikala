package jvd.ir.digiknew.SignUp;

import android.content.Context;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Login.UserLoginInfo;

public class SignUpViewModel {

    UserLoginInfo userLoginInfo;
    SignUpRepository signUpRepository=new SignUpRepository();
    Context context;
    public SignUpViewModel(Context context){
        this.context=context;
        userLoginInfo=new UserLoginInfo(context);

    }

    public Single<Message> signUp(String email, String Pass){
        return signUpRepository.signUp(email, Pass);
    }

    public void saveUserInfo(String email){
        userLoginInfo.saveLoginData(email);
    }
}
