package com.example.duan1;

import android.content.Intent;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.dialog.AnswerDialog;
import com.example.duan1.dialog.ItemDialog;
import com.example.duan1.dialog.SupportDialog;
import com.example.duan1.dialog.UserDialog;
import com.example.duan1.module.appData;
import com.example.duan1.module.questionpresenter;

import java.util.ArrayList;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener, appData {
    private static final int LEVEL_MUSIC_ON = 0;
    private static final int LEVEL_MUSIC_OFF = 1;
    private ImageView mIvMusic, mIvPicture;
    private SupportDialog supportDialog;
    private TextView mTvHeart, mTvNumberAnswer, mTvPoint;
    private int heart = 5;
    private int numberAnswer;
    public int point = 0;
    private int t = 0;
    private questionpresenter question;
    private Button mBtnNext;
    private Typeface mTypeface;
    private TextView mTvSuppost;
    Button daT, daO, daH, daO2, daA, daI, tlT, tlO, tlH, tlO2, tlA, tlI, tlL, tlP, tlC, tlB;

    private void initView() {
        daT = findViewById(R.id.btnda1);
        daO = findViewById(R.id.btnda2);
        daH = findViewById(R.id.btnda3);
        daO2 = findViewById(R.id.btnda4);
        daA = findViewById(R.id.btnda5);
        daI = findViewById(R.id.btnda6);
        tlT = findViewById(R.id.tl1);
        tlO = findViewById(R.id.tl2);
        tlH = findViewById(R.id.tl3);
        tlO2 = findViewById(R.id.tl4);
        tlA = findViewById(R.id.tl5);
        tlI = findViewById(R.id.tl6);
        tlB = findViewById(R.id.btn_tlb);
        tlL = findViewById(R.id.btn_tlL);
        tlP = findViewById(R.id.btn_tlP);
        tlC = findViewById(R.id.btn_tlC);

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
        mIvPicture = findViewById(R.id.iv_picture);
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
                question.music();
                if (ring.isPlaying()) {
                    ring.pause();
                } else if (!ring.isPlaying()) {
                    ring.start();
                }

            }
        });
//        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/UTM_Cookies_0.ttf");
        initView();
        mIvPicture.setImageResource(R.drawable.tohoai);
        tl();
        question = new questionpresenter(this);

    }

    @Override
    public void toNextQuestion() {

    }

    @Override
    public Drawable getImageDrawable() {
        mIvPicture.setImageResource(R.drawable.cattuong);
        return null;
    }

    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getSuggest() {
        mTvSuppost.setText("Dế mèn phiêu lưu kí");
        supportDialog.cancel();
        return null;
    }

    @Override
    public String getKQ() {
        return null;
    }

    @Override
    public ArrayList<String> getShortAnswer() {
        return null;
    }

    @Override
    public void setT() {

        tlT.setText("T");
        tlO.setText("O");
        tlO2.setText("O");
        tlA.setText("A");
        tlH.setText("H");
        tlI.setText("I");
        tlB.setText("B");
        tlC.setText("C");
        tlP.setText("P");
        tlL.setText("L");
        tlT.setText("T");
        daT.setText("");
        daO2.setText("");
        daO.setText("");
        daA.setText("");
        daH.setText("");
        daI.setText("");


    }

    @Override
    public void tl() {


        tlT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daT.setText("T");
                tlT.setText("");
                t = t + 1;
            }
        });

        tlO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daO.setText("O");
                tlO.setText("");
                t = t + 1;
            }
        });
        tlH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daH.setText("H");
                tlH.setText("");
                t = t + 1;
            }
        });
        tlO2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daO2.setText("O");
                tlO2.setText("");
                t = t + 1;
            }
        });
        tlA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daA.setText("A");
                tlA.setText("");
                t = t + 1;
            }
        });
        tlI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                daI.setText("I");
                tlI.setText("");
                t = t + 1;
                if (t >= 6) {
                    Toast.makeText(PlayActivity.this, "Chúc mừng bạn đã trả lời đúng.", Toast.LENGTH_LONG).show();
                    question.getIMG();
                    setT();
                }
            }
        });


    }

    @Override
    public void goToSupportDialog() {


        supportDialog.findViewById(R.id.fl_suppost_1).setOnClickListener((View.OnClickListener) this);
        supportDialog.findViewById(R.id.fl_suppost_2).setOnClickListener((View.OnClickListener) this);
        supportDialog.findViewById(R.id.fl_suppost_3).setOnClickListener((View.OnClickListener) this);
        supportDialog.findViewById(R.id.fl_suppost_4).setOnClickListener((View.OnClickListener) this);
        supportDialog.show();


    }

    @Override
    public void setIvMusic() {

        int level = mIvMusic.getDrawable().getLevel();
        mIvMusic.setImageLevel(level == LEVEL_MUSIC_ON
                ? LEVEL_MUSIC_OFF : LEVEL_MUSIC_ON);


    }


    public void fullScreen() {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
    }


    @Override
    public void goToItemDialog() {

        ItemDialog itemDialog = new ItemDialog(this);
        itemDialog.findViewById(R.id.fm_freeMoney_x3).setOnClickListener((View.OnClickListener) this);
        itemDialog.findViewById(R.id.fm_freeMoney_x10).setOnClickListener((View.OnClickListener) this);
        itemDialog.show();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btn_tiep:
                mIvPicture.setImageResource(R.drawable.aomua);
                mBtnNext.setVisibility(View.GONE);
                setT();
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
                question.supDia();
                break;
            case R.id.txt_point:
                question.itemDia(this);
                break;
            case R.id.iv_music:
                question.music();
                break;
            case R.id.iv_back:
                onBackPressed();
                break;
            case R.id.fl_suppost_1:

            case R.id.fl_suppost_2:

            case R.id.fl_suppost_3:
                question.support();
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


}
