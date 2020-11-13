package com.example.trasea;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.trasea.databinding.ActivityLoginBinding;
import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    ActivityLoginBinding binding;
    LogInWithFB logInWithFB;
    CallbackManager callbackManager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        binding.btnLogIn.setOnClickListener(this);
        binding.btnFacebook.setOnClickListener(this);
        binding.btnGmail.setOnClickListener(this);
        binding.tvSignUp.setOnClickListener(this);

        logInWithFB = new LogInWithFB(this, callbackManager);

        logInWithFB.registerSignInFBClient();

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUIWithFBAccount();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvSignUp:
                showDialog_signUp();
                break;
            case R.id.btnLogIn:

                break;
            case R.id.btnFacebook:
                logInWithFB.signInWithFB();
                break;
            case R.id.btnGmail:

                break;

        }
    }

    private void showDialog_signUp(){
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_signup);

        final EditText txtSignUser = dialog.findViewById(R.id.txtUserSignUp);
        final EditText txtPassWord = dialog.findViewById(R.id.txtPasswordSignUp);
        Button btnSignUp  = dialog.findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = txtSignUser.getText().toString();
                String password = txtPassWord.getText().toString();
                //tuowng tac dang ky Auth

                binding.txtUser.setText(username);
                binding.txtPassword.setText(password);
                dialog.dismiss();
            }
        });

        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.show();
    }

    public void updateUIWithFBAccount() {
        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (isLoggedIn) {
            logInWithFB.getFacebookAccountInfo(accessToken);
        } else {
            //
        }
    }

}
