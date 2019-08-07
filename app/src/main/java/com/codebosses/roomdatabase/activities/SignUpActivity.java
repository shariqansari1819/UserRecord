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
import com.codebosses.roomdatabase.databinding.ActivitySignUpBinding;
import com.codebosses.roomdatabase.endpoints.EndpointKey;
import com.codebosses.roomdatabase.pojo.User;
import com.codebosses.roomdatabase.utils.PrefUtils;
import com.codebosses.roomdatabase.viewmodel.LoginViewModel;

public class SignUpActivity extends AppCompatActivity {

    //    Android fields....
    private ActivitySignUpBinding signUpBinding;
    private SignUpClickHandler signUpClickHandler;

    //    View model....
    LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        signUpBinding = DataBindingUtil.setContentView(this, R.layout.activity_sign_up);

        loginViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);

//        Setting click handler....
        signUpClickHandler = new SignUpClickHandler();
        signUpBinding.setClickHandler(signUpClickHandler);

    }

    public class SignUpClickHandler {

        public void onRegisterClick(View view) {
            final String name = signUpBinding.editTextFullName.getText().toString();
            final String email = signUpBinding.editTextEmail.getText().toString();
            final String password = signUpBinding.editTextPassword.getText().toString();
            final String phoneNumber = signUpBinding.editTextPhone.getText().toString();

            if (!TextUtils.isEmpty(name) && !TextUtils.isEmpty(email) && !TextUtils.isEmpty(password) && !TextUtils.isEmpty(phoneNumber)) {
                loginViewModel.getUserByEmail(email).observe(SignUpActivity.this, new Observer<User>() {
                    @Override
                    public void onChanged(User user) {
                        if (user == null) {
                            User newUser = new User(name, email, password, phoneNumber);
                            loginViewModel.insertUser(newUser);
                            Toast.makeText(SignUpActivity.this, "User created successfully.", Toast.LENGTH_SHORT).show();
                            PrefUtils.saveToPrefs(SignUpActivity.this, EndpointKey.USER_LOGGED_IN, true);
                            Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                            startActivity(intent);
                        } else {
                            Toast.makeText(SignUpActivity.this, "Old User", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            } else {
                Toast.makeText(SignUpActivity.this, "Please fill all the fields.", Toast.LENGTH_SHORT).show();
            }
        }

        public void onLogInClick(View view) {
            onBackPressed();
        }
    }
}