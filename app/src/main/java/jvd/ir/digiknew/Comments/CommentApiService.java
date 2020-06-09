package jvd.ir.digiknew.Comments;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.ApiProvider;
import jvd.ir.digiknew.Model.ApiService;
import jvd.ir.digiknew.Model.Comments;

public class CommentApiService {

    ApiService apiService= ApiProvider.apiProvider();

    public Single<List<Comments>> getComments(String id){
        return apiService.getComments(id);
    }

    public Single<Message> getLikeComment(String id){
        return  apiService.likeComment(id);
    }

    public Single<Message> getDislikeComment(String id){
        return apiService.dislikeComment(id);
    }
}
