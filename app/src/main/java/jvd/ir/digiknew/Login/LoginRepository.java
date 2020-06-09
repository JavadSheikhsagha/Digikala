package jvd.ir.digiknew.Login;

import io.reactivex.Single;

public class LoginRepository implements LoginDataSource {

    LoginApiDataSource loginApiDataSource=new LoginApiDataSource();

    @Override
    public Single<Message> login(String email,String pass) {
        return loginApiDataSource.login(email, pass);
    }
}
