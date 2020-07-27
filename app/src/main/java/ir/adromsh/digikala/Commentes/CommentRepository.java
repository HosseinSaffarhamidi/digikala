package ir.adromsh.digikala.Commentes;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Comment;
import io.reactivex.Single;

public class CommentRepository {
    public ApiCommentService apiCommentService=new ApiCommentService();



    public Single<List<Comment>> getComments(String id){
        return apiCommentService.getComments(id);
    }
    public Single<Message> likeComment(String id){
        return apiCommentService.likeComment(id);
    }
    public Single<Message> dislikeComment(String id){
        return apiCommentService.dislikeComment(id);
    }


}
