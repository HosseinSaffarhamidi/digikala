package ir.adromsh.digikala.Commentes;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.Comment;
import io.reactivex.Single;

public class ApiCommentService {
    private ApiService apiService= ApiProvider.apiProvider();

    public Single<List<Comment>> getComments(String id){
        return apiService.getComments(id);
    }

    public Single<Message> likeComment(String id){
        return apiService.likeComment(id);
    }
    public Single<Message> dislikeComment(String id){
        return apiService.dislikeComment(id);
    }

}
