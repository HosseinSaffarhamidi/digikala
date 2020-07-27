package ir.adromsh.digikala.Login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import ir.adromsh.digikala.R;
import ir.adromsh.digikala.SignupActivity.SignupActivity;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class LoginActivity extends AppCompatActivity {
    LoginViewModel loginViewModel ;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    EditText edtEmail, edtPass;
    Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setupViews();

    }


    private void login() {
        loginViewModel.login(edtEmail.getText().toString(), edtPass.getText().toString()).subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<Message>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        compositeDisposable.add(d);
                    }

                    @Override
                    public void onSuccess(Message message) {
                        Log.i("LOG", "onSuccess: " + message.getMessage());
                        if(!message.getMessage().equals("not found")){
                            /*Intent intent=new Intent();
                            intent.putExtra("email",message.getMessage());
                            setResult(RESULT_OK,intent);*/

                            loginViewModel.saveUserEmail(message.getMessage());
                            finish();
                        }else{
                            Toast.makeText(LoginActivity.this, "ایمیل یا کلمه عبور اشتباه است", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.i("LOG", "onError: " + e.toString());
                    }
                });
    }

    private void setupViews() {
        loginViewModel=new LoginViewModel(this);
        btnLogin = findViewById(R.id.btn_login_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        edtEmail = findViewById(R.id.edt_login_email);
        edtPass = findViewById(R.id.edt_login_pass);
        ImageView imgClose = findViewById(R.id.img_login_toolbarColse);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        TextView txtSignup = findViewById(R.id.txt_login_signup);
        txtSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
                finish();

            }
        });
        CheckBox showPass = findViewById(R.id.ch_login_showPass);
        showPass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (!isChecked) {
                    edtPass.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {

                    edtPass.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.dispose();
        super.onDestroy();
    }


}
