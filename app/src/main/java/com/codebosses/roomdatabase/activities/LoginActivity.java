package com.codebosses.roomdatabase.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.codebosses.roomdatabase.R;
import com.codebosses.roomdatabase.databinding.ActivityLoginBinding;
import com.codebosses.roomdatabase.pojo.User;
import com.codebosses.roomdatabase.viewmodel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    //    Android fields....
    private ActivityLoginBinding loginBinding;
    private LoginClickHandler loginClickHandler;

    //    View model....
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

//        Setting click handler....
        loginClickHandler = new LoginClickHandler();
        loginBinding.setClickHandler(loginClickHandler);

    }

    public class LoginClickHandler {

        public void onLoginClick(View view) {
            String email = loginBinding.editTextEmail.getText().toString();
            String password = loginBinding.editTextPassword.getText().toString();
            if (!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)) {
                loginViewModel.getUser(email, password).observe(LoginActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user == null) {
                            Toast.makeText(LoginActivity.this, "Invalid user", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(LoginActivity.this, "User exist.", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(LoginActivity.this, "Please enter all fields.", Toast.LENGTH_SHORT).show();
            }
        }

        public void onRegisterClick(View view) {
            startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
        }

        public void onForgotPasswordClick(View view) {
            startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
        }

    }

}
