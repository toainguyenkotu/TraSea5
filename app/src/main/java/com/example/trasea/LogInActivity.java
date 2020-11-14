package com.example.trasea;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
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
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Arrays;
import java.util.regex.Pattern;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final Pattern PASS_WORD =  Pattern.compile("^(?=.*[0-9])(?=.*[a-zA-Z]).{6,16}");
    ActivityLoginBinding binding;
    LogInWithFB logInWithFB;
    CallbackManager callbackManager;
    private FirebaseAuth mAuth;
    TextView tv_email_alert,tv_password_alert;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());




        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();


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

        // Check if user is signed in (non-null)
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUIWithFBAccount();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            callbackManager.onActivityResult(requestCode, resultCode, data);
        }catch (Exception o){
            // Hạn chế crash khi ko login đc hay back lại login page
            // Do nothing
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tvSignUp:
                showDialog_signUp();
                break;
            case R.id.btnLogIn:
                handleSignIn();
                break;
            case R.id.btnFacebook:
                logInWithFB.signInWithFB();
                break;
            case R.id.btnGmail:

                break;

        }
    }

    private void handleSignIn() {
        String email = binding.txtUser.getText().toString().trim();
        String password = binding.txtPassword.getText().toString().trim();
        if(checkEmailValidForSignIn(email) == true && checkPassValidForSignIn(password) == true){
            mAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = mAuth.getCurrentUser();
                                Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                                startActivity(intent);
                                //updateUI(user);
                            } else {
                                Toast.makeText(LogInActivity.this, "Please Check Your Information",
                                        Toast.LENGTH_SHORT).show();
                                //updateUI(null);
                            }

                        }
                    });
        }else{
            Toast.makeText(LogInActivity.this, "Please Check Your Information",
                    Toast.LENGTH_SHORT).show();
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
                String email = txtSignUser.getText().toString();
                String password = txtPassWord.getText().toString();
                tv_email_alert = dialog.findViewById(R.id.tv_email_alert);
                tv_password_alert = dialog.findViewById(R.id.tv_password_alert);
                //tuong tac dang ky Auth

                // Check Valid Email And Password Must Be > 6 digits
                if(checkEmailValid(email) == true && checkPassValid(password) == true){
                    handleSignUp(email,password);
                }else{
                    /// do something?
                }


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

    public boolean checkEmailValid(String email){
        if(email.isEmpty()) {
            tv_email_alert.setText("You have to fill in");
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tv_email_alert.setText("Invalid Email");
            return false;
        }else
            tv_email_alert.setText(" ");
            return true;
    }

    public boolean checkPassValid(String password){
        if(password.isEmpty()){
            tv_password_alert.setText("You have to fill in");
            return false;
        }if(!PASS_WORD.matcher(password).matches()){
            tv_password_alert.setText("Password must be at least 6 characters and 1 letter");
            return false;
        }else
            tv_password_alert.setText(" ");
            return true;
    }

    public boolean checkEmailValidForSignIn(String email){
        if(email.isEmpty()) {
            return false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            return false;
        }else
        return true;
    }

    public boolean checkPassValidForSignIn(String password){
        if(password.isEmpty()){
            return false;
        }if(!PASS_WORD.matcher(password).matches()){
            return false;
        }else
        return true;
    }

    // Handle Event Sign Up by Email And Password
    private void handleSignUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(LogInActivity.this, "Sign Up Success", Toast.LENGTH_SHORT).show();
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                            startActivity(intent);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LogInActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

}
