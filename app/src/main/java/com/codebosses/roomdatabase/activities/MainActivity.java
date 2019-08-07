package com.codebosses.roomdatabase.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.codebosses.roomdatabase.R;
import com.codebosses.roomdatabase.endpoints.EndpointKey;
import com.codebosses.roomdatabase.utils.PrefUtils;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (!(Boolean) PrefUtils.getFromPrefs(this, EndpointKey.USER_LOGGED_IN, false)) {
            Intent intent = new Intent(this, LoginActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

        }
    }
}
