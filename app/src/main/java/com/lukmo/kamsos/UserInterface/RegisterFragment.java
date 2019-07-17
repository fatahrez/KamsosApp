package com.lukmo.kamsos.UserInterface;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.textfield.TextInputLayout;
import com.lukmo.kamsos.R;

import rx.subscriptions.CompositeSubscription;


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

    private CompositeSubscription mSubscriptions;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        mSubscriptions = new CompositeSubscription();
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
    }

    private void goToLogin() {
    }
}
