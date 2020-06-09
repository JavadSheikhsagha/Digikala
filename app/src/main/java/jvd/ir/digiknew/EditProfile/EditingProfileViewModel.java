package jvd.ir.digiknew.EditProfile;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Model.EditProfile;

public class EditingProfileViewModel {

    ProfileRepository profileRepository=new ProfileRepository();

    public Single<List<EditProfile>> editProfile(String email, String name, String code, String home, String mobile, String birthday, String sex, String khabarname) {

        return profileRepository.editProfile(email,name,code,home,mobile,birthday,sex,khabarname);
    }
}
