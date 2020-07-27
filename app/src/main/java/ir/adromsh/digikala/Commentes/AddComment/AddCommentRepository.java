package ir.adromsh.digikala.Commentes.AddComment;

import android.content.Context;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Login.UserLoginInfo;
import ir.adromsh.digikala.Model.RatingModel;
import io.reactivex.Single;

public class AddCommentRepository {
    Context context;
    UserLoginInfo userLoginInfo;
    public AddCommentRepository(Context context){
        this.context=context;
        userLoginInfo=new UserLoginInfo(context);
    }
    AddCommentApiService addCommentApiService = new AddCommentApiService();

    public Single<Message> sendPoints(List<RatingModel> ratingModels) {
        return addCommentApiService.sendPoint(ratingModels);
    }
    public String checkIsLogin(){
        return userLoginInfo.getUserEmail();
    }
    public Single<Message> sendParam(String title,String positive,String negative,String passage,String user){
        return addCommentApiService.sendParam(title,positive,negative,passage,user);
    }


}
