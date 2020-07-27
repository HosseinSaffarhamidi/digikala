package ir.adromsh.digikala.Commentes;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Comment;
import io.reactivex.Single;

public class CommentViewModel {
    private CommentRepository commentRepository=new CommentRepository();





    public Single<List<Comment>> getComments(String id) {
        return commentRepository.getComments(id);
    }

    public Single<Message> likeOrDislikeComment(Comment comment, String likeOrDisLike) {
        if (likeOrDisLike.equals("like")) {
            return commentRepository.likeComment(comment.getId());
        } else {
            return commentRepository.dislikeComment(comment.getId());
        }
    }




}
