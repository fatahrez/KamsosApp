package com.lukmo.kamsos.UserInterface;


import android.os.Bundle;
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
import com.lukmo.kamsos.Models.User;
import com.lukmo.kamsos.Networking.ServiceGenerator;
import com.lukmo.kamsos.R;

import java.io.IOException;

import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.HttpException;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.disposables.*;

import static com.lukmo.kamsos.Utils.Validation.validateEmail;
import static com.lukmo.kamsos.Utils.Validation.validateFields;


public class RegisterFragment extends Fragment {
    public static final String TAG = RegisterFragment.class.getSimpleName();

    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private  EditText mEditTextConfirmPassword;
    private Button mBtnRegister;
    private TextView mTvLogin;
    private TextInputLayout mTextInputname;
    private TextInputLayout mTextInputEmail;
    private TextInputLayout mTextInputPassword;
    private TextInputLayout mTextInputConfirmPassword;
    private ProgressBar mProgressBar;

    private CompositeDisposable mSubscriptions;

    public RegisterFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mSubscriptions = new CompositeDisposable();
        initViews(view);
        return view;
    }

    private void initViews(View v) {
        mEditTextName = (EditText) v.findViewById(R.id.edittext_name);
        mEditTextEmail = (EditText) v.findViewById(R.id.edittext_email);
        mEditTextPassword = (EditText) v.findViewById(R.id.edittext_password);
        mEditTextConfirmPassword = (EditText) v.findViewById(R.id.edittext_confirmpassword);

        mBtnRegister = (Button) v.findViewById(R.id.btn_register);
        mTvLogin = (TextView) v.findViewById(R.id.tv_login);

        mTextInputname = (TextInputLayout) v.findViewById(R.id.textinput_name);
        mTextInputEmail = (TextInputLayout) v.findViewById(R.id.textinput_email);
        mTextInputPassword = (TextInputLayout) v.findViewById(R.id.textinput_password);
        mTextInputConfirmPassword = (TextInputLayout) v.findViewById(R.id.textinput_confirmpassword);

        mProgressBar = (ProgressBar) v.findViewById(R.id.progress);


        mTvLogin.setOnClickListener(view -> goToLogin());
        mBtnRegister.setOnClickListener(view -> register());
    }

    private void register() {

        setError();

        String name = mEditTextName.getText().toString();
        String email = mEditTextEmail.getText().toString();
        String password = mEditTextPassword.getText().toString();
        String confirmPassword = mEditTextConfirmPassword.getText().toString();

        int err = 0;

        if (!validateFields(name)){
            err++;
            mTextInputname.setError("Name should not be empty!");
        }

        if (!validateEmail(email)){
            err++;
            mTextInputEmail.setError("Email should be valid!");
        }

        if (!validateFields(password)){
            err++;
            mTextInputPassword.setError("Password should not be empty!");
        }

        if (!validateFields(confirmPassword)){
            err++;
            mTextInputConfirmPassword.setError("Password should not be empty!");
        }

        if (!password.equals(confirmPassword)){
            err++;
            mTextInputConfirmPassword.setError("The passwords do not match");
        }


        if (err == 0){
            User user = new User();
            user.setEmail(email);
            user.setUsername(name);
            user.setPassword(password);

            mProgressBar.setVisibility(View.VISIBLE);
            registerProcess(user);
        } else {
            showSnackBarMessage("Enter Valid Details!");
        }
    }

    private void setError(){
        mTextInputname.setError(null);
        mTextInputEmail.setError(null);
        mTextInputPassword.setError(null);
        mTextInputConfirmPassword.setError(null);
    }

    private void registerProcess(User user){
        mSubscriptions.add(ServiceGenerator.getUser().register(user)
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeOn(Schedulers.io())
        .subscribe(this::handleResponse, this::handleError));
    }

    private void handleResponse(Response response){
        mProgressBar.setVisibility(View.GONE);
        showSnackBarMessage(response.getMessage());
    }

    private void handleError(Throwable error) {
        mProgressBar.setVisibility(View.GONE);

        if (error instanceof HttpException) {
            Gson gson = new GsonBuilder().create();

            try {
                String errorBody = ((HttpException) error).response().errorBody().string();
                Response response = gson.fromJson(errorBody, Response.class);
                showSnackBarMessage(response.getMessage());
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            showSnackBarMessage("Network Error!");
        }
    }

    private void showSnackBarMessage(String message){
        if (getView() != null){
            Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
        }
    }

    private void goToLogin() {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        LoginFragment fragment = new LoginFragment();
        ft.replace(R.id.fragmentFrame, fragment, LoginFragment.TAG);
        ft.commit();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mSubscriptions.dispose();
    }
}
