package jvd.ir.digiknew.EditProfile;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Model.ApiProvider;
import jvd.ir.digiknew.Model.ApiService;
import jvd.ir.digiknew.Model.EditProfile;

public class ApiProfileMainDataSource implements EditingDataSource {

    ApiService apiService= ApiProvider.apiProvider();

    @Override
    public Single<List<EditProfile>> editProfile(String email, String name, String code, String home, String mobile, String birthday, String sex, String khabarname) {
        return apiService.setProfile(email, name, code, home, mobile, birthday, sex, khabarname);
    }
}
