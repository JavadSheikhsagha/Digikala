package jvd.ir.digiknew.SignUp;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;

public interface SignUpDataSource {

    Single<Message> signUp(String email, String pass);
}
