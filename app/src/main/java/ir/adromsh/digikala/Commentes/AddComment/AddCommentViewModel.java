package ir.adromsh.digikala.Commentes.AddComment;

import android.content.Context;

import java.util.List;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.RatingModel;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class AddCommentViewModel {
    AddCommentRepository repository;
    Context context;
    public AddCommentViewModel(Context context){
        this.context=context;
        repository=new AddCommentRepository(context);
    }


    public Single<Message> setPoints(List<RatingModel> ratingModels) {
        return repository.sendPoints(ratingModels);
    }
    public Single<Message> sendParam(String title, String positive, String negative, String passage,String user) {
        if(repository.checkIsLogin().equals("")){
            Message message=new Message();
            message.setStatus("error");
            message.setMessage("user has not logged in");

            return Single.just(message)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }else{
            return repository.sendParam(title,positive,negative,passage,repository.checkIsLogin());
        }


    }
}
