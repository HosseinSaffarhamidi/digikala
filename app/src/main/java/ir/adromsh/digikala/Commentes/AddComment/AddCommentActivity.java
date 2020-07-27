package ir.adromsh.digikala.Commentes.AddComment;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ir.adromsh.digikala.Login.LoginActivity;
import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Comment;
import ir.adromsh.digikala.Model.RatingModel;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AddCommentActivity extends AppCompatActivity {

    Comment comment;
    ImageView imgBack;
    RecyclerView recyclerView;
    Button btnSetPoint,btnWriteComment;
    List<RatingModel> ratingModelList;
    AddCommentAdapter addCommentAdapter;
    AddCommentViewModel viewModel;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_comment);
        setupViews();
        comment =getIntent().getParcelableExtra("comment");
        getParams(comment);

    }

    private void setupViews() {
        viewModel=new AddCommentViewModel(this);
        ratingModelList=new ArrayList<>();
        imgBack=findViewById(R.id.img_addComment_back);
        recyclerView=findViewById(R.id.rv_addComment);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        btnWriteComment=findViewById(R.id.btn_addComment_writeComment);
        btnWriteComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddCommentActivity.this,WriteCommentActivity.class);
                startActivityForResult(intent,100);
            }
        });
        btnSetPoint=findViewById(R.id.btn_addComment_setPoint);
        btnSetPoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.setPoints(addCommentAdapter.getRatingModels())
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new SingleObserver<Message>() {
                            @Override
                            public void onSubscribe(Disposable d) {
                                compositeDisposable.add(d);
                            }

                            @Override
                            public void onSuccess(Message message) {
                                finish();
                                Toast.makeText(AddCommentActivity.this, message.getMessage(), Toast.LENGTH_SHORT).show();
                            }

                            @Override
                            public void onError(Throwable e) {
                                Log.i("LOG", "onError: "+e.toString());
                            }
                        });

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==100 && resultCode==RESULT_OK){
            String title=data.getExtras().getString("title");
            String positive=data.getExtras().getString("positive");
            String negative=data.getExtras().getString("negative");
            String passage=data.getExtras().getString("passage");
            viewModel.sendParam(title,positive,negative,passage,viewModel.repository.checkIsLogin())
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new SingleObserver<Message>() {
                        @Override
                        public void onSubscribe(Disposable d) {
                            compositeDisposable.add(d);
                        }

                        @Override
                        public void onSuccess(Message message) {
                            Log.i("LOG", "status:"+message.getStatus()+" message:"+message.getMessage());
                            if(message.getStatus().equals("error")){
                                Intent intent=new Intent(getApplicationContext(), LoginActivity.class);
                                startActivity(intent);
                            }else{
                                Toast.makeText(AddCommentActivity.this, "نظر شما با موفقیت ثبت شد", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.i("LOG", "onError: "+e.toString());
                        }
                    });

        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    public void getParams(Comment comment){
        String param = comment.getParam();
        try {
            JSONArray jsonArray = new JSONArray(param);
            for (int j = 0; j < jsonArray.length(); j++) {
                RatingModel ratingModel = new RatingModel();
                JSONObject jsonObject = jsonArray.getJSONObject(j);
                ratingModel.setTitle(jsonObject.getString("title"));
                ratingModel.setValue(jsonObject.getString("value"));
                ratingModelList.add(ratingModel);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        addCommentAdapter=new AddCommentAdapter(this,ratingModelList);
        recyclerView.setAdapter(addCommentAdapter);

    }
}
