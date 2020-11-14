package com.example.trasea;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.widget.Toast;

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

public class LogInWithFB {
    Context context;
    CallbackManager callbackManager;

    public LogInWithFB(Context context, CallbackManager callbackManager) {
        this.context = context;
        this.callbackManager = callbackManager;
    }

    public void registerSignInFBClient(){
        callbackManager = CallbackManager.Factory.create();

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        getFacebookAccountInfo(loginResult.getAccessToken());
                    }

                    @Override
                    public void onCancel() {
                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        Toast.makeText(context, "Login Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void getFacebookAccountInfo(AccessToken accessToken){
        GraphRequest request = GraphRequest.newMeRequest(accessToken, new GraphRequest.GraphJSONObjectCallback() {
            @Override
            public void onCompleted(JSONObject object, GraphResponse response) {
                try {
                    String name = object.getString("name");
                    //
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
        Bundle param = new Bundle();
        param.putString("fields", "id,name,email,picture.width(200)");
        request.setParameters(param);
        request.executeAsync();
    }

    public void signInWithFB(){
        LoginManager.getInstance().logInWithReadPermissions((Activity) context, Arrays.asList("email", "public_profile"));
    }


}
