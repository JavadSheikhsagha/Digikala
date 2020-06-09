package jvd.ir.digiknew.Login;

import android.content.Context;

import io.reactivex.Single;

public class LoginViewModel {

    LoginRepository loginRepository=new LoginRepository();
    UserLoginInfo userLoginInfo;

    public LoginViewModel(Context context){
        userLoginInfo=new UserLoginInfo(context);
    }

    public Single<Message>login(String email,String pass){

        return loginRepository.login(email, pass);
    }

    public void saveUserEmail(String email){
        userLoginInfo.saveLoginData(email);
    }

}
