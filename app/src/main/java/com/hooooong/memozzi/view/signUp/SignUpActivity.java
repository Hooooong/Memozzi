package com.hooooong.memozzi.view.signUp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hooooong.memozzi.R;
import com.hooooong.memozzi.view.main.MainActivity_;
import com.hooooong.memozzi.view.signIn.SignInActivity_;
import com.hooooong.memozzi.view.signUp.contract.SignUpContract;
import com.hooooong.memozzi.view.signUp.presenter.SignUpPresenter;

import org.androidannotations.annotations.AfterInject;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import static android.content.Intent.FLAG_ACTIVITY_NO_HISTORY;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity implements SignUpContract.iView{

    private Intent intent;

    @Bean(SignUpPresenter.class)
    SignUpContract.iPresenter presenter;

    @ViewById(R.id.et_email)
    EditText editEmail;
    @ViewById(R.id.et_password)
    EditText editPassword;
    @ViewById(R.id.tv_error_signUp)
    TextView textError;


    @AfterInject
    void initObject() {
        presenter.attachView(this);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {
        textError.setText(error);
    }

    @Override
    public void goToMain() {
        Toast.makeText(this, "회원가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();

        intent = new Intent(this, MainActivity_.class);
        intent.setFlags(FLAG_ACTIVITY_NO_HISTORY);
        startActivity(intent);
    }

    @Click(R.id.btn_signUp)
    public void clickBtnSignUp(){
        editEmail.clearFocus();
        editPassword.clearFocus();

        presenter.setSignUp(editEmail.getText().toString(), editPassword.getText().toString());
    }


    @Click(R.id.tv_signIn)
    public void goToSignIn() {
        intent = new Intent(this, SignInActivity_.class);
        startActivity(intent);
    }
}
