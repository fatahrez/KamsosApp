package com.lukmo.kamsos.UserInterface;

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

import com.google.android.material.textfield.TextInputLayout;
import com.lukmo.kamsos.R;

import io.reactivex.disposables.CompositeDisposable;


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
//        setError();

//        String
    }

}
