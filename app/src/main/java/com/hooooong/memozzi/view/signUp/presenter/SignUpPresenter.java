package com.hooooong.memozzi.view.signUp.presenter;

import android.util.Log;

import com.hooooong.memozzi.data.sign.source.SignRepository;
import com.hooooong.memozzi.data.user.source.UserRepository;
import com.hooooong.memozzi.view.signUp.contract.SignUpContract;

import org.androidannotations.annotations.Background;
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
public class SignUpPresenter implements SignUpContract.iPresenter {

    private static final String TAG = "SignUpPresenter";

    private SignUpContract.iView view;
    private SignRepository repository;

    public SignUpPresenter() {
        repository = SignRepository.getInstance();
    }

    @Override
    public void attachView(SignUpContract.iView view) {
        this.view = view;
    }

    @Background
    @Override
    public void setSignUp(String email, String password) {
        Log.e(TAG, "setSignUp: email = " + email + ",  password = " + password);

        repository.signUp(email, password)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(data -> {
                            Log.e(TAG, "setSignUp: Success");
                            // 로그인한 유저 저장시키기.
                            UserRepository.getInstance().setUser(data);
                            // MainPage 로 이동
                            view.goToMain();
                        }, throwable -> {
                            Log.e(TAG, "setSignUp: Error");

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
