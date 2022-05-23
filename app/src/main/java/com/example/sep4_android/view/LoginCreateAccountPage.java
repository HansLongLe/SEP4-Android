package com.example.sep4_android.view;

import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.sep4_android.R;

public class LoginCreateAccountPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_create_account_page);
    }

    public void setTitleGradient(TextView title) {
        Shader titleShader = new LinearGradient(0, 0, title.getPaint().measureText(title.getText().toString()), title.getTextSize(),
                new int[]{
                        getColor(R.color.main_color),
                        getColor(R.color.gradient_middle)
                }, null, Shader.TileMode.CLAMP);
        title.setTextColor(getColor(R.color.main_color));
        title.getPaint().setShader(titleShader);
    }
}