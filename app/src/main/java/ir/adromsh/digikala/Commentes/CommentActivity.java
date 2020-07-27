package ir.adromsh.digikala.Commentes;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import ir.adromsh.digikala.Commentes.AddComment.AddCommentActivity;
import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.Model.Comment;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class CommentActivity extends AppCompatActivity {
    TextView txtTitle;
    RecyclerView recyclerView;
    CommentViewModel commentViewModel = new CommentViewModel();
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    String id;
    CommentAdapter commentAdapter;
    FrameLayout progressFrame;
    Comment intentComment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);
        setupViews();
        /*if(Build.VERSION.SDK_INT>=21){
            getWindow().setStatusBarColor(ContextCompat.getColor(CommentActivity.this,R.color.colorPrimary));

        }*/
        id = getIntent().getExtras().getString("id");
        txtTitle.setText(getIntent().getExtras().getString("name"));
        observeForComments(id);


    }

    private void observeForComments(String id) {
        commentViewModel.getComments(id)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Comment>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(List<Comment> comments) {

                        if(comments.size()>0){
                            intentComment=comments.get(0);
                            progressFrame.setVisibility(View.GONE);
                            commentAdapter = new CommentAdapter(comments, CommentActivity.this, new CommentAdapter.OnLikeOrDislikeClick() {
                                @Override
                                public void onClikc(final Comment comment, final String likeOrDislike) {
                                    commentViewModel.likeOrDislikeComment(comment,likeOrDislike)
                                            .subscribeOn(Schedulers.newThread())
                                            .observeOn(AndroidSchedulers.mainThread())
                                            .subscribe(new SingleObserver<Message>() {
                                                @Override
                                                public void onSubscribe(Disposable d) {
                                                    compositeDisposable.add(d);
                                                }

                                                @Override
                                                public void onSuccess(Message message) {
                                                    if(message.getStatus().equals("error")){
                                                        Toast.makeText(CommentActivity.this, "خطای ناشناخته", Toast.LENGTH_SHORT).show();
                                                    }else {
                                                        commentAdapter.changeLikeOrDislikeCount(comment,likeOrDislike);

                                                    }
                                                }

                                                @Override
                                                public void onError(Throwable e) {
                                                    Log.i("LOG", "onError: "+e.toString());
                                                }
                                            });
                                }
                            });

                            recyclerView.setAdapter(commentAdapter);
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: " + e.toString());
                    }
                });
    }

    private void setupViews() {
        progressFrame=findViewById(R.id.frame_comment_progressFrame);
        ImageView imgClose = findViewById(R.id.img_comment_close);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtTitle = findViewById(R.id.txt_comment_productName);
        recyclerView = findViewById(R.id.rv_comment_comments);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(CommentActivity.this, AddCommentActivity.class);
                intent.putExtra("comment",intentComment);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
