package ir.adromsh.digikala.SignupActivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import ir.adromsh.digikala.Login.Message;
import ir.adromsh.digikala.R;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SignupActivity extends AppCompatActivity {

    SignupViewModel signupViewModel;
    CompositeDisposable compositeDisposable=new CompositeDisposable();
    EditText edtEmail,edtPass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        setupViews();

    }

    private void signupObserve() {
        signupViewModel.signup(edtEmail.getText().toString(),edtPass.getText().toString())
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Message>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Message message) {
                        Log.i("LOG", "onSuccess: "+message.getMessage());
                        signupViewModel.saveUserInfo(message.getMessage());
                        finish();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: "+e.toString());
                    }
                });
    }

    private void setupViews() {
        signupViewModel=new SignupViewModel(this);
        edtEmail=findViewById(R.id.edt_signup_email);
        edtPass=findViewById(R.id.edt_signup_pass);
        Button btnSignup=findViewById(R.id.btn_signup_signup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtEmail.getText().toString().equals("") ){
                    edtEmail.setError("ایمیل نمی تواند خالی باشد");
                }else if(edtPass.getText().toString().equals("")){

                    edtPass.setError("رمز عبور نمی تواند خالی باشد");
                }else{
                    signupObserve();
                }

            }
        });
        ImageView imgClose=findViewById(R.id.img_signup_toolbarClose);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}
