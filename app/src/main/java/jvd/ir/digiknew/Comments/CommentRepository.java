package jvd.ir.digiknew.Comments;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.Comments;

public class CommentRepository {

    CommentApiService commentApiService=new CommentApiService();

    public Single<List<Comments>> getComments(String id){
        return commentApiService.getComments(id);
    }

    public Single<Message> getLikeComment(String id){
        return commentApiService.getLikeComment(id);
    }

    public Single<Message> getDislikeComment(String id){
        return commentApiService.getDislikeComment(id);
    }
}
