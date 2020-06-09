package jvd.ir.digiknew.Login;

import io.reactivex.Single;
import jvd.ir.digiknew.Model.ApiProvider;
import jvd.ir.digiknew.Model.ApiService;

public class LoginApiDataSource implements LoginDataSource {

    ApiService apiService= ApiProvider.apiProvider();

    @Override
    public Single<Message> login(String email,String pass) {
        return apiService.login(email, pass);
    }
}
