package jvd.ir.digiknew.SignUp;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.ApiProvider;
import jvd.ir.digiknew.Model.ApiService;

public class ApiSignUpDataSource implements SignUpDataSource {

    ApiService apiService= ApiProvider.apiProvider();

    @Override
    public Single<Message> signUp(String email, String pass) {
        return apiService.signUp(email,pass);
    }
}
