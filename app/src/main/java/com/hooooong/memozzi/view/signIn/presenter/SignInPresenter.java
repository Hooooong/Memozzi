package com.hooooong.memozzi.view.signIn.presenter;

import android.util.Log;

import com.hooooong.memozzi.data.sign.source.SignRepository;
import com.hooooong.memozzi.data.user.source.UserRepository;
import com.hooooong.memozzi.view.signIn.contract.SignInContract;

import org.androidannotations.annotations.EBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

import static com.hooooong.memozzi.util.JsonUtil.getErrorMessage;

/**
 * Created by Android Hong on 2018-02-22.
 */
@EBean
public class SignInPresenter implements SignInContract.iPresenter {

    private static final String TAG = "SignInPresenter";

    private SignInContract.iView view;
    private SignRepository repository;

    public SignInPresenter(){
        repository = SignRepository.getInstance();
    }

    @Override
    public void attachView(SignInContract.iView view) {
        this.view = view;
    }

    @Override
    public void setSignIn(String email, String password) {
        Log.e(TAG, "setSignIn: email = " + email + ",  password = " + password);

        repository.signIn(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            Log.e(TAG, "setSignIn: Success");
                            // 로그인한 유저 저장시키기.
                            UserRepository.getInstance().setUser(data);
                            // MainPage 로 이동
                            view.goToMain();
                        }, throwable -> {
                            Log.e(TAG, "setSignIn: Error");

                            if (throwable instanceof HttpException) {
                                ResponseBody responseBody = ((HttpException) throwable).response().errorBody();
                                view.showError(getErrorMessage(responseBody));
                            } else {
                                Log.e(TAG, "setSignUp(Throwable): " + throwable.getMessage());
                            }
                        }
                );
    }
}
