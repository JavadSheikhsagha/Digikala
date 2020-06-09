package jvd.ir.digiknew.Properties;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Model.Detail;

public class PropertiesViewModel {

    PropertiesRepository propertiesRepository=new PropertiesRepository();

    public Single<List<Detail>> getDetailss(String id){
        return propertiesRepository.getDetailss(id);
    }

}
