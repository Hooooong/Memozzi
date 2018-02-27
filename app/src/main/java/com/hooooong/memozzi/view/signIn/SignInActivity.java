package com.hooooong.memozzi.view.signIn;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hooooong.memozzi.R;
import com.hooooong.memozzi.view.main.MainActivity_;
import com.hooooong.memozzi.view.signIn.contract.SignInContract;
import com.hooooong.memozzi.view.signIn.presenter.SignInPresenter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_sign_in)
public class SignInActivity extends AppCompatActivity implements SignInContract.iView{

    private Intent intent;

    @Bean(SignInPresenter.class)
    SignInContract.iPresenter presenter;

    @ViewById(R.id.et_email)
    EditText editEmail;
    @ViewById(R.id.et_password)
    EditText editPassword;
    @ViewById(R.id.tv_error_signIn)
    TextView textError;

    @AfterInject
    void initObject() {
        presenter.attachView(this);
    }

    @Override
    public void showError(String error) {
        textError.setText(error);
    }

    @Override
    public void goToMain() {
        Toast.makeText(this, "로그인이 성공하였습니다.", Toast.LENGTH_SHORT).show();

        intent = new Intent(this, MainActivity_.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }


    @Click(R.id.btn_signIn)
    public void clickBtnSignIn(){
        editEmail.clearFocus();
        editPassword.clearFocus();

        presenter.setSignIn(editEmail.getText().toString(), editPassword.getText().toString());
    }


    @Click(R.id.tv_signUp)
    public void goToSignUp() {
        finish();
    }
}
