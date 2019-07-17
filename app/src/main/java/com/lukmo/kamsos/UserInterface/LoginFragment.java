package com.lukmo.kamsos.UserInterface;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lukmo.kamsos.Models.Response;
import com.lukmo.kamsos.Networking.ServiceGenerator;
import com.lukmo.kamsos.R;
import com.lukmo.kamsos.Utils.Constants;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.HttpException;


import static com.lukmo.kamsos.Utils.Validation.validateFields;
import static com.lukmo.kamsos.Utils.Validation.validateEmail;

public class LoginFragment extends Fragment {
    public static final String TAG = LoginFragment.class.getSimpleName();

    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private Button mBtnLogin;
    private TextView mTvRegister;
    private TextView mTvForgotPassword;
    private TextInputLayout mTextInputEmail;
    private TextInputLayout mTextInputPassword;
    private ProgressBar mProgressBar;

    private CompositeDisposable mSubscription;
    private SharedPreferences mSharedPreference;

    public LoginFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_login, container, false);
        mSubscription = new CompositeDisposable();
        initViews(view);
        initSharedPreferences();
        return view;
    }

    private void initSharedPreferences() {
        mSharedPreference = PreferenceManager.getDefaultSharedPreferences(getActivity());
    }

    private void initViews(View v) {
        mEditTextEmail = (EditText) v.findViewById(R.id.edittext_email);
        mEditTextPassword = (EditText) v.findViewById(R.id.edittext_password);
        mBtnLogin = (Button) v.findViewById(R.id.btn_login);

        mTextInputEmail = (TextInputLayout) v.findViewById(R.id.textinput_email);
        mTextInputPassword = (TextInputLayout) v.findViewById(R.id.textinput_password);

        mProgressBar = (ProgressBar) v.findViewById(R.id.progress);

        mTvRegister = (TextView) v.findViewById(R.id.tv_register);
        mTvForgotPassword = (TextView) v.findViewById(R.id.tv_forgot_password);

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

        if (!validateEmail(email)){
            err++;
            mTextInputEmail.setError("Email should be valid!");
        }

        if (!validateFields(password)){
            err++;
            mTextInputPassword.setError("Password should not be empty!");
        }

        if (err == 0){
            loginProcess(email,password);
            mProgressBar.setVisibility(View.VISIBLE);
        } else {
            showSnackBarMessage("Enter Valid Details");
        }
    }

    private void setError(){
        mTextInputEmail.setError(null);
        mTextInputPassword.setError(null);
    }

    private void loginProcess(String email, String password) {
        mSubscription.add(ServiceGenerator.getUser(email,password).login()
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(Response response){
        mProgressBar.setVisibility(View.GONE);

        SharedPreferences.Editor editor = mSharedPreference.edit();
        editor.putString(Constants.TOKEN, response.getToken());
        editor.putString(Constants.EMAIL, response.getMessage());
        editor.apply();

        mEditTextEmail.setText(null);
        mEditTextPassword.setText(null);

        Intent intent = new Intent(getActivity(), ProfileActivity.class);
        startActivity(intent);
    }

    private void handleError(Throwable error) {
        mProgressBar.setVisibility(View.GONE);

        if (error instanceof HttpException){
            Gson gson = new GsonBuilder().create();

            try {
                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody,Response.class);
                showSnackBarMessage(response.getMessage());
            } catch (IOException e){
                e.printStackTrace();
            }
        } else {
            showSnackBarMessage("Network Error!");
        }
    }

    private void showSnackBarMessage(String message){
        if (getView() != null){
            Snackbar.make(getView(),message,Snackbar.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        mSubscription.dispose();
    }

}
