package ir.adromsh.digikala.Commentes.AddComment;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.ApiProvider;
import ir.adromsh.digikala.Model.ApiService;
import ir.adromsh.digikala.Model.RatingModel;
import io.reactivex.Single;

public class AddCommentApiService {
    ApiService apiService= ApiProvider.apiProvider();
    public Single<Message> sendPoint(List<RatingModel> ratingModels){
        return apiService.sendPoint(ratingModels);
    }
    public Single<Message> sendParam(String title,String positive,String negative,String passage,String user){
        return apiService.sendParam(title,positive,negative,passage,user);
    }
}
