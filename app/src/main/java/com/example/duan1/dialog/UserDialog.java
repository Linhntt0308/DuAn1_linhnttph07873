package com.example.duan1.dialog;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.example.duan1.R;



public class UserDialog extends Dialog implements View.OnClickListener {
public  TextView tvcoin, tvtim;

    public UserDialog(Context context) {
        super(context, R.style.dialog_theme);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dialog_user);
        initView();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

tvcoin=findViewById(R.id.txt_coin);
tvtim=findViewById(R.id.tv_timm);

    }

    private void initView() {
        findViewById(R.id.iv_close).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_close:
                onBackPressed();
                break;
            default:
                break;
        }
    }
}
