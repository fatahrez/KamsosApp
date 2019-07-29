package com.lukmo.kamsos.UserInterface;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.lukmo.kamsos.Models.Register.Register;
import com.lukmo.kamsos.Models.Register.Register_;
import com.lukmo.kamsos.Networking.NetworkUtils;
import com.lukmo.kamsos.Networking.UserService;
import com.lukmo.kamsos.R;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.lukmo.kamsos.Utils.Validation.validateEmail;
import static com.lukmo.kamsos.Utils.Validation.validateFields;


public class RegisterFragment extends Fragment {
    public static final String TAG = RegisterFragment.class.getSimpleName();

    private static View view;


    private EditText mEditTextName;
    private EditText mEditTextEmail;
    private EditText mEditTextPassword;
    private EditText mEditTextConfirmPassword;
    private Button mBtnRegister;
    private LinearLayout mTvLogin;
    private TextInputLayout mTextInputname;
    private TextInputLayout mTextInputEmail;
    private TextInputLayout mTextInputPassword;
    private TextInputLayout mTextInputConfirmPassword;
    private ProgressBar mProgressBar;

    private CompositeDisposable mSubscriptions;

    private UserService mUserService;

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
        mEditTextName = v.findViewById(R.id.edittext_name);
        mEditTextEmail = v.findViewById(R.id.edittext_email);
        mEditTextPassword = v.findViewById(R.id.edittext_password);
        mEditTextConfirmPassword = v.findViewById(R.id.edittext_confirmpassword);

        mBtnRegister = v.findViewById(R.id.btn_register);
        mTvLogin = v.findViewById(R.id.tv_login);

        mTextInputname = v.findViewById(R.id.textinput_name);
        mTextInputEmail = v.findViewById(R.id.textinput_email);
        mTextInputPassword = v.findViewById(R.id.textinput_password);
        mTextInputConfirmPassword = v.findViewById(R.id.textinput_confirmpassword);

        mProgressBar = v.findViewById(R.id.progress);

        mUserService = NetworkUtils.ApiInstance();

        mTvLogin.setOnClickListener(view -> goToLogin());
        mBtnRegister.setOnClickListener(view -> register());
    }

    private void register() {

        setError();

        String name = mEditTextName.getText().toString();
        String email = mEditTextEmail.getText().toString().trim();
        String password = mEditTextPassword.getText().toString();
        String confirmPassword = mEditTextConfirmPassword.getText().toString();

        int err = 0;

        if (!validateFields(name) || !validateEmail(email) ||
                !validateFields(password) || !validateFields(confirmPassword)) {
            err++;
            new CustomToast().Show_Toast(getContext(), view,
                    "Fill All Fields");
        } else if (!validateEmail(email)) {
            err++;
            new CustomToast().Show_Toast(getContext(), view,
                    "Enter a valid Email!");
        } else if (!password.equals(confirmPassword)) {
            err++;
            new CustomToast().Show_Toast(getContext(), view,
                    "The passwords do not match!");
        } else {
            mProgressBar.setVisibility(View.VISIBLE);
            registerProcess(email, name, password);
        }

//        if (!validateEmail(email)){
//            err++;
//            mTextInputEmail.setError("Email should be valid!");
//        }

//        if (!validateFields(password)){
//            err++;
//            mTextInputPassword.setError("Password should not be empty!");
//        }

//        if (!validateFields(confirmPassword)){
//            err++;
//            mTextInputConfirmPassword.setError("Password should not be empty!");
//        }
//
//        if (!password.equals(confirmPassword)){
//            err++;
//            mTextInputConfirmPassword.setError("The passwords do not match");
//        }


//        if (err == 0){
//            mProgressBar.setVisibility(View.VISIBLE);
//            registerProcess(email, name, password);
//        } else {
//            showSnackBarMessage("Enter Valid Details!");
//        }
    }

    private void setError() {
        mTextInputname.setError(null);
        mTextInputEmail.setError(null);
        mTextInputPassword.setError(null);
        mTextInputConfirmPassword.setError(null);
    }

    private void registerProcess(String email, String name, String password) {
        Register register = new Register();
        Register_ register_ = new Register_();
        register_.setEmail(email);
        register_.setUsername(name);
        register_.setPassword(password);
        register.setUser(register_);

        mUserService.register("application/json", register)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Register>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Register register) {
                        showSnackBarMessage("User created successfully");
                        goToLogin();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


    private void showSnackBarMessage(String message) {
        if (getView() != null) {
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
