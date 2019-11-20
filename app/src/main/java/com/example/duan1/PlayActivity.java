package com.example.duan1;

import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.dialog.AnswerDialog;
import com.example.duan1.dialog.ItemDialog;
import com.example.duan1.dialog.SupportDialog;
import com.example.duan1.dialog.UserDialog;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {
    private static final int LEVEL_MUSIC_ON = 0;
    private static final int LEVEL_MUSIC_OFF = 1;
    private ImageView mIvMusic, mIvPicture;
    private SupportDialog supportDialog;
    private TextView mTvHeart, mTvNumberAnswer, mTvPoint;
    private int heart = 5;
    private int numberAnswer;
    private int point = 0;
    private Button mBtnNext;
    private Typeface mTypeface;
    private TextView mTvSuppost;
    private void initView() {
        mBtnNext = findViewById(R.id.btn_tiep);
        mBtnNext.setOnClickListener(this);
        mTvSuppost = findViewById(R.id.tv_suppost);
        mTvHeart = findViewById(R.id.mTv_heart);
        mTvHeart.setText(String.valueOf(heart));

        mTvPoint = findViewById(R.id.txt_point);
        findViewById(R.id.txt_point).setOnClickListener(this);
        findViewById(R.id.btn_tiep).setOnClickListener(this);
        findViewById(R.id.fm_user).setOnClickListener(this);
        findViewById(R.id.mTv_heart).setOnClickListener(this);
        findViewById(R.id.txt_suggest).setOnClickListener(this);
        findViewById(R.id.iv_back).setOnClickListener(this);

        mTvSuppost = findViewById(R.id.tv_suppost);
        supportDialog = new SupportDialog(this);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        fullScreen();
        final MediaPlayer ring = MediaPlayer.create(PlayActivity.this, R.raw.trolo);
        ring.start();
        mIvMusic = findViewById(R.id.iv_music);
        mIvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setIvMusic();
                if (ring.isPlaying()) {
                    ring.pause();
                } else if (!ring.isPlaying()) {
                    ring.start();
                }

            }
        });
//        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/UTM_Cookies_0.ttf");
        initView();

    }
    private void goToSupportDialog() {

        supportDialog.findViewById(R.id.fl_suppost_1).setOnClickListener(this);
        supportDialog.findViewById(R.id.fl_suppost_2).setOnClickListener(this);
        supportDialog.findViewById(R.id.fl_suppost_3).setOnClickListener(this);
        supportDialog.findViewById(R.id.fl_suppost_4).setOnClickListener(this);
        supportDialog.show();

    }
    private void setIvMusic() {
        int level = mIvMusic.getDrawable().getLevel();
        mIvMusic.setImageLevel(level == LEVEL_MUSIC_ON
                ? LEVEL_MUSIC_OFF : LEVEL_MUSIC_ON);


    }
    private void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tiep:

                break;
            case R.id.fm_user:
                UserDialog userDialog = new UserDialog(this);
                userDialog.show();
                break;
            case R.id.mTv_heart:
                AnswerDialog answerDialog = new AnswerDialog(this);
                answerDialog.show();
                break;
            case R.id.txt_suggest:
                goToSupportDialog();
                break;
            case R.id.txt_point:
                goToItemDialog();
                break;
            case R.id.iv_music:
                setIvMusic();
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.fl_suppost_1:

            case R.id.fl_suppost_2:

            case R.id.fl_suppost_3:
              support();
              break;

            case R.id.fl_suppost_4:
//                 hintKyTuSai();
                break;
            case R.id.fm_freeMoney_x3:
                point += 5;
                mTvPoint.setText(String.valueOf(point));
//                saveDataPoint();
                break;
            case R.id.fm_freeMoney_x10:
                point += 10;
                mTvPoint.setText(String.valueOf(point));
//                saveDataPoint();
                break;
            case R.id.fm_freeHeart_x1:
                heart += 1;
                mTvHeart.setText(String.valueOf(heart));
            default:
                break;
        }
    }

    private void support() {
mTvSuppost.setText("Đàm Vĩnh Hưng");
    }

    private void goToItemDialog() {
        ItemDialog itemDialog = new ItemDialog(this);
        itemDialog.findViewById(R.id.fm_freeMoney_x3).setOnClickListener(this);
        itemDialog.findViewById(R.id.fm_freeMoney_x10).setOnClickListener(this);
        itemDialog.show();
    }
}
