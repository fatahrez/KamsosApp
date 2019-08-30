package com.lukmo.kamsos.UserInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.lukmo.kamsos.Models.Login.User;
import com.lukmo.kamsos.Models.Login.User_;
import com.lukmo.kamsos.Models.Vet.Vet;
import com.lukmo.kamsos.Networking.NetworkUtils;
import com.lukmo.kamsos.Networking.UserService;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Utils.Constants;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lukmo.kamsos.Utils.Validation.validateEmail;
import static com.lukmo.kamsos.Utils.Validation.validateFields;

public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();


    private static View view;

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mBtnLogin;
    private LinearLayout mTvRegister;
    private TextView mTvForgotPassword;
    private TextView mResponseTv;
    private TextInputLayout mTextInputEmail;
    private TextInputLayout mTextInputPassword;
    private ProgressBar mProgressBar;

    private CompositeDisposable mSubscription;
    private SharedPreferences mSharedPreference;
    private UserService mUserService;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the cursor_drawable for this fragment
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        mSubscription = new CompositeDisposable();
        initViews(view);
        initSharedPreferences();

        return view;
    }

    private void initSharedPreferences() {
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    private void initViews(View v) {
        mEditTextEmail = v.findViewById(R.id.edittext_email);
        mEditTextPassword = v.findViewById(R.id.edittext_password);
        mBtnLogin = v.findViewById(R.id.btn_login);

        mTextInputEmail = v.findViewById(R.id.textinput_email);
        mTextInputPassword = v.findViewById(R.id.textinput_password);

        mProgressBar = v.findViewById(R.id.progress);

        mTvRegister = v.findViewById(R.id.tv_register);
        mTvForgotPassword = v.findViewById(R.id.tv_forgot_password);
        mResponseTv = v.findViewById(R.id.tv_response);

        mUserService = NetworkUtils.ApiInstance();

        mBtnLogin.setOnClickListener(view -> login());
        mTvRegister.setOnClickListener(view -> goToRegister());
        mTvForgotPassword.setOnClickListener(view -> showDialog());
    }

    private void showDialog() {


    }

    private void goToRegister() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        RegisterFragment fragment = new RegisterFragment();
        ft.replace(R.id.fragmentFrame, fragment, RegisterFragment.TAG);
        ft.commit();
    }

    private void login() {
        setError();

        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();

        int err = 0;

        if (!validateEmail(email) || !validateFields(password)) {
            new CustomToast().Show_Toast(getContext(), view,
                    "Enter Valid Details");

        } else if (!validateEmail(email)) {
            err++;
            new CustomToast().Show_Toast(getContext(), view,
                    "Your Email is Invalid.");

        } else if (!validateFields(password)) {
            err++;
            new CustomToast().Show_Toast(getContext(), view,
                    "Enter Password !");
        } else {
            loginProcess(email, password);
            mProgressBar.setVisibility(View.VISIBLE);

            mUserService.getVets().subscribeOn(Schedulers.io()).subscribe(new Observer<List<Vet>>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(List<Vet> vets) {
                    Log.i(TAG, "Vet response: " + vets.get(1).toString());
                }

                @Override
                public void onError(Throwable e) {

                }

                @Override
                public void onComplete() {

                }
            });
        }

//        if (!validateEmail(email)){
//            err++;
//            new CustomToast().Show_Toast(getContext(), view,
//                    "Your Email Id is Invalid.");
//
//        }

//        if (!validateFields(password)){
//            err++;
//            new CustomToast().Show_Toast(getContext(), view,
//                    "Password should not be empty!");
//        }


    }


    private void setError() {
        mTextInputEmail.setError(null);
        mTextInputPassword.setError(null);
    }

    private void loginProcess(String email, String password) {
        User user = new User();
        User_ user_ = new User_();
        user_.setEmail(email);
        user_.setPassword(password);
        user.setUser(user_);
        mUserService.login("application/json", user)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(User user) {
                        showResponse(user.toString());
                        Log.i(TAG, "Response: " + user);
                        mProgressBar.setVisibility(View.GONE);
                        String token = user.getUser().getToken();
                        Log.i(TAG, "Response: " + token);
                        handleResponse(token);
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    private void handleResponse(String response) {
        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(Constants.TOKEN, response);
        editor.putString(Constants.EMAIL, response);
        editor.apply();

        mEditTextEmail.setText(null);
        mEditTextPassword.setText(null);

        Intent intent = new Intent(getActivity(), HomeActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    private void showResponse(String toString) {
        if (mResponseTv.getVisibility() == View.GONE) {
            mResponseTv.setVisibility(View.VISIBLE);
        }
        mResponseTv.setText(toString);
    }

    private void showSnackBarMessage(String message) {
        if (getView() != null) {
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscription.dispose();
    }

}
