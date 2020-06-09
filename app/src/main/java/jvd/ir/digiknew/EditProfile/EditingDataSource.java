package jvd.ir.digiknew.EditProfile;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Model.EditProfile;

public interface EditingDataSource {

     Single<List<EditProfile>> editProfile(String email, String name, String code, String home, String mobile, String birthday, String sex, String khabarname);
}
