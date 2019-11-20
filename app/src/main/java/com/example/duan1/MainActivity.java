package com.example.duan1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;

import com.example.duan1.dialog.ExitDialog;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);fullScreen();initView();
    }


    private void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initView() {
        findViewById(R.id.iv_choi_thu).setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_choi_thu:
                goToPlay();
                break;

            default:
                break;
        }
    }

    @Override
    public void onBackPressed() {
        ExitDialog exitDialog = new ExitDialog(this);
        exitDialog.show();
    }

    private void goToPlay() {
        Intent intent = new Intent();
        intent.setClass(this, PlayActivity.class);
        startActivity(intent);
    }
}
