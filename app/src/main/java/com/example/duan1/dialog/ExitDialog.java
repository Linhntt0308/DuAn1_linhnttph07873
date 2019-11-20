package com.example.duan1.dialog;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Typeface;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.duan1.R;


public class ExitDialog extends Dialog implements View.OnClickListener {
    private Typeface mTypeface;

    public ExitDialog(Context context) {
        super(context, R.style.dialog_theme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_exit);
        initView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    private void initView() {
        findViewById(R.id.iv_yes).setOnClickListener(this);
        findViewById(R.id.iv_no).setOnClickListener(this);
        findViewById(R.id.iv_close).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                onBackPressed();
                break;
            case R.id.iv_yes:
                System.exit(0);
                break;
            case R.id.iv_no:
                onBackPressed();
                break;
            default:
                break;
        }
    }

}
