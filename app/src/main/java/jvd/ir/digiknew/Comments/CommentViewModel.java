package jvd.ir.digiknew.Comments;

import java.util.List;

import io.reactivex.Single;
import jvd.ir.digiknew.Login.Message;
import jvd.ir.digiknew.Model.Comments;

public class CommentViewModel {

    CommentRepository commentRepository=new CommentRepository();

    public Single<List<Comments>> getComments(String id){
        return commentRepository.getComments(id);
    }

    public Single<Message> getLikeComment( Comments comments){
        return commentRepository.getLikeComment(comments.getId());
    }

    public Single<Message> getDislikeComments( Comments comments){
        return commentRepository.getDislikeComment(comments.getId());
    }
}
