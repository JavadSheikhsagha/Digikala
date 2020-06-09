package jvd.ir.digiknew.SignUp;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;

public class SignUpRepository implements SignUpDataSource {

    ApiSignUpDataSource apiSignUpDataSource=new ApiSignUpDataSource();

    @Override
    public Single<Message> signUp(String email, String pass) {
        return apiSignUpDataSource.signUp(email, pass);
    }
}
