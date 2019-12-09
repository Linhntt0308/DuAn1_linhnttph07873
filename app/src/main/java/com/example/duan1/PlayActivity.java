package com.example.duan1;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

import com.example.duan1.dialog.AnswerDialog;
import com.example.duan1.dialog.ItemDialog;
import com.example.duan1.dialog.ItemHeartDialog;
import com.example.duan1.dialog.SupportDialog;
import com.example.duan1.dialog.UserDialog;
import com.example.duan1.module.AppData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class PlayActivity extends AppCompatActivity implements View.OnClickListener {

    public static final String SHARED_PREFERENCES_COIN = "SHARED_PREFERENCES_COIN";
    public static final String SHARED_PREFERENCES_HEART = "SHARED_PREFERENCES_HEART";
    private static final int MAX_KYTU = 16;
    private static final String chars = "ABCDEGHIKLMNOPQRSTUVXY";
    private static final String TAG = "TAG";
    private static final int LEVEL_MUSIC_ON = 0;
    private static final int LEVEL_MUSIC_OFF = 1;
    Random random = new Random();
    private SupportDialog supportDialog;
    private String kq = "";
    private int size;

    private int heart = 5;
    private int numberAnswer;
    private int point = 0;
    private String dapan = "";
    private Button mBtnNext;
    private Typeface mTypeface;
    private String suggest;

    private ImageView mIvMusic, mIvPicture;
    private LinearLayout mAnswer1, mAnswer2, mPlan1, mPlan2, mAnswer;
    private TextView mTvHeart, mTvNumberAnswer, mTvPoint;
    private TextView mTvSuppost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        mTypeface = Typeface.createFromAsset(getAssets(), "fonts/UTM_Cookies_0.ttf");
        initView();
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

    private void initView() {
        readData();


        findViewById(R.id.iv_back).setOnClickListener(this);
        mIvPicture = findViewById(R.id.iv_picture);
        mAnswer1 = findViewById(R.id.anwser1);
        mAnswer2 = findViewById(R.id.anwser2);
        mAnswer = findViewById(R.id.anwser);
        mPlan1 = findViewById(R.id.plan1);
        mPlan2 = findViewById(R.id.plan2);

        mTvSuppost = findViewById(R.id.tv_suppost);
        mBtnNext = findViewById(R.id.btn_tiep);
        mBtnNext.setOnClickListener(this);

        mTvHeart = findViewById(R.id.mTv_heart);
        mTvHeart.setText(String.valueOf(heart));

        mTvPoint = findViewById(R.id.txt_point);
        findViewById(R.id.txt_point).setOnClickListener(this);

        mTvNumberAnswer = findViewById(R.id.tv_numberAnswer);


        findViewById(R.id.btn_tiep).setOnClickListener(this);
        findViewById(R.id.fm_user).setOnClickListener(this);
        findViewById(R.id.mTv_heart).setOnClickListener(this);
        findViewById(R.id.txt_suggest).setOnClickListener(this);


        mTvNumberAnswer.setText(String.valueOf(numberAnswer));
        mTvPoint.setText(String.valueOf(point));


        supportDialog = new SupportDialog(this);
        showInfor();

    }

    private void readData() {


        SharedPreferences spCoin = getSharedPreferences(SHARED_PREFERENCES_COIN, Context.MODE_PRIVATE);
        if (spCoin != null) {
            int coin = spCoin.getInt("coin", 0);
            point = coin;
        }

        SharedPreferences spHeart = getSharedPreferences(SHARED_PREFERENCES_HEART, Context.MODE_PRIVATE);
        if (spHeart != null) {
            int heartttt = spHeart.getInt("heart", 5);
            heart = heartttt;


        }

    }

    private void showInfor() {
        try {
            AppData appData = new AppData(this);
            appData.toNextQuestion();
            mIvPicture.setImageDrawable(appData.getImageDrawable());
            ArrayList<String> answer = appData.getShortAnswer();
            suggest = appData.getSuggest();
            Log.i(TAG, suggest);
            kq = appData.getKQ();
            size = answer.size();
            showAnswer(size);
            // Thêm các ký tự trong 16 ký tự
            for (int i = 0; i < MAX_KYTU - size; i++) {
                int numberRandom = random.nextInt(20);
                String kytu = RandomString(numberRandom);
                answer.add(kytu);
            }
            // Hiển thị 16 ký tự
            Collections.shuffle(answer);
            viewAnswer(answer);
//            Toast.makeText(this, appData.getSuggest(), Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showAnswer(int size) {
        for (int i = 0; i < size; i++) {
            final Button button = new Button(this);

            button.setBackgroundResource(R.drawable.tile_empty);

            button.setText("");
            button.setId(i);
            button.setTypeface(mTypeface);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, 75);
            button.setLayoutParams(params);
            if (i < 8) {
                mAnswer1.addView(button);
                setvisible(button, mAnswer1);
            } else {
                mAnswer2.addView(button);
                setvisible(button, mAnswer2);
            }
//            Toast.makeText(this, "Answer count = "+mAnswer.getChildCount(), Toast.LENGTH_SHORT).show();
        }
    }

    private Button setvisible(final Button button, final LinearLayout mAnswer) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (button.getText() != "") {
                    String bt = (String) button.getText();
                    button.setText("");

                    for (int i = 0; i < mAnswer.getChildCount(); i++) {
                        Button button1 = (Button) mAnswer.getChildAt(i);
                        button1.setBackgroundResource(R.drawable.tile_empty);
                    }
                    for (int i = 0; i < mPlan1.getChildCount(); i++) {
                        Button button1 = (Button) mPlan1.getChildAt(i);
                        findViewById(button1.getId());
                        if (bt == button1.getText()) {
                            button1.setVisibility(View.VISIBLE);
                        }
                    }

                    for (int i = 0; i < mPlan2.getChildCount(); i++) {
                        Button button1 = (Button) mPlan2.getChildAt(i);
                        if (bt == button1.getText()) {
                            button1.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }
        });
        return button;
    }


    private String RandomString(int length) {
        return chars.substring(length, length + 1);
    }

    private void viewAnswer(final ArrayList<String> list) {
        for (int i = 0; i < list.size(); i++) {
            final Button button = new Button(this);

            button.setBackgroundResource(R.drawable.tile_hover);

            button.setText(list.get(i));
            button.setId(i);
            button.setTypeface(mTypeface);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(70, 105);
            button.setLayoutParams(params);
            if (i < 8) {
                mPlan1.addView(button);
                setInvisible(button);
            } else {
                mPlan2.addView(button);
                setInvisible(button);
            }
        }
    }


    private Button setInvisible(final Button button) {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < mAnswer1.getChildCount(); i++) {
                    Button button1 = (Button) mAnswer1.getChildAt(i);
                    if (button1.getText() == "") {
                        dapan = button.getText().toString();
                        button1.setText(dapan);
                        button.setVisibility(View.INVISIBLE);
                        if (i == (mAnswer1.getChildCount() - 1)) {
                            checkAnswer(mAnswer1);
                        }
                        return;
                    }
                }
                for (int i = 0; i < mAnswer2.getChildCount(); i++) {
                    Button button1 = (Button) mAnswer2.getChildAt(i);
                    if (button1.getText() == "") {
                        dapan = button.getText().toString();
                        button1.setText(dapan);
                        button.setVisibility(View.INVISIBLE);
                        if (i == (mAnswer2.getChildCount() - 1)) {
                            checkAnswer(mAnswer2);
                        }
                        return;
                    }
                }
            }
        });
        return button;
    }


    private void checkAnswer(LinearLayout answer) {
        //lay dap an
        StringBuilder da = new StringBuilder();
        if (answer == mAnswer1) {
            getAnswer(da, answer);
        } else {
            getAnswer(da, mAnswer1);
            getAnswer(da, mAnswer2);
        }
        //check KQ
        if (da.length() == size) {
            if (da.toString().equals(kq)) {
                Toast.makeText(this, "Chúc mừng!!!", Toast.LENGTH_SHORT).show();
                numberAnswer++;
                point += 100;
                mTvNumberAnswer.setText(String.valueOf(numberAnswer));
                mTvPoint.setText(String.valueOf(point));
                mBtnNext.setVisibility(View.VISIBLE);

                saveData();

            } else {
                Toast.makeText(this, "Bạn đã trả lời sai!", Toast.LENGTH_SHORT).show();
                for (int i = 0; i < answer.getChildCount(); i++) {
                    Button button1 = (Button) answer.getChildAt(i);
                    button1.setBackgroundResource(R.drawable.tile_false);
                }
                heart -= 1;
                mTvHeart.setText(String.valueOf(heart));
                saveDataHeart();

                if (heart <= 0) {
//                    heart = 0;
//                    mTvHeart.setText(String.valueOf(heart));
                    Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
                    goToItemHeartDialog();
                }
            }
        }
    }

    private void saveDataHeart() {
        SharedPreferences spHeart = getSharedPreferences(SHARED_PREFERENCES_HEART, Context.MODE_PRIVATE);
        SharedPreferences.Editor editorrrr = spHeart.edit();
        if (heart >= 0) {
            editorrrr.putInt("heart", heart);
            editorrrr.apply();
        }

    }

    private void saveData() {

        SharedPreferences spCoin = getSharedPreferences(SHARED_PREFERENCES_COIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = spCoin.edit();
        editor1.putInt("coin", point);
        editor1.apply();


    }

    private void getAnswer(StringBuilder da, LinearLayout answer) {
        for (int k = 0; k < answer.getChildCount(); k++) {
            Button btn = (Button) answer.getChildAt(k);
            String text = btn.getText().toString();
            if (!text.isEmpty()) {
                da.append(text);
            }
        }
    }

    private void newQuestion() {
        mAnswer1.removeAllViews();
        mAnswer2.removeAllViews();
        mPlan1.removeAllViews();
        mPlan2.removeAllViews();
        mTvSuppost.setText("");
        mBtnNext.setVisibility(View.GONE);
        showInfor();
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_tiep:
                newQuestion();
                break;
            case R.id.fm_user:


                UserDialog userDialog = new UserDialog(this);
                userDialog.tvcoin.setText(point + "");
                userDialog.tvtim.setText(heart + "");
                userDialog.show();


                break;
            case R.id.mTv_heart:
                AnswerDialog answerDialog = new AnswerDialog(this);
                answerDialog.tv_timm.setText(" x "+ heart);
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
                if (point < 5) {
                    Toast.makeText(this, "Bạn không đủ tiền", Toast.LENGTH_LONG).show();
                } else {
                    showKyTu(0);
                }

                break;
            case R.id.fl_suppost_2:
                if (point < 5) {
                    Toast.makeText(this, "Bạn không đủ tiền", Toast.LENGTH_LONG).show();
                } else {
                    int indext = random.nextInt(size);
                    showKyTu(indext);
                }

                break;
            case R.id.fl_suppost_3:
                if (point < 5) {
                    Toast.makeText(this, "Bạn không đủ tiền", Toast.LENGTH_LONG).show();
                } else {
                    support();
                }
                break;
            case R.id.fl_suppost_4:
                if (point < 20) {
                    Toast.makeText(this, "Bạn không đủ tiền", Toast.LENGTH_LONG).show();
                } else {
                    hintKyTuSai();
                }

                break;
            case R.id.fm_freeMoney_x3:
                point += 5;
                mTvPoint.setText(String.valueOf(point));
                saveDataPoint();
                break;
            case R.id.fm_freeMoney_x10:
                point += 10;
                mTvPoint.setText(String.valueOf(point));
                saveDataPoint();
                break;
            case R.id.fm_freeHeart_x1:
                heart += 1;
                mTvHeart.setText(String.valueOf(heart));
            default:
                break;
        }
    }


    private void support() {
        if (point > 0) {
            point -= 5;

            mTvPoint.setText(String.valueOf(point));
            mTvSuppost.setText(suggest);
            mTvSuppost.setTypeface(mTypeface);
            supportDialog.cancel();
            checkAnswer(mAnswer1);
            checkAnswer(mAnswer2);
        } else {
            Toast.makeText(this, "Bạn không đủ tiền!", Toast.LENGTH_SHORT).show();
        }
    }

    private void goToItemHeartDialog() {
        ItemHeartDialog itemHeartDialog = new ItemHeartDialog(this);
        itemHeartDialog.findViewById(R.id.fm_freeHeart_x1).setOnClickListener(this);
        itemHeartDialog.show();
    }

    private void goToItemDialog() {
        ItemDialog itemDialog = new ItemDialog(this);
        itemDialog.findViewById(R.id.fm_freeMoney_x3).setOnClickListener(this);
        itemDialog.findViewById(R.id.fm_freeMoney_x10).setOnClickListener(this);
        itemDialog.show();
    }


    // Make DialogSupport
    private void goToSupportDialog() {
        supportDialog.findViewById(R.id.fl_suppost_1).setOnClickListener(this);
        supportDialog.findViewById(R.id.fl_suppost_2).setOnClickListener(this);
        supportDialog.findViewById(R.id.fl_suppost_3).setOnClickListener(this);
        supportDialog.findViewById(R.id.fl_suppost_4).setOnClickListener(this);
        supportDialog.show();

    }

    private void hintKyTuSai() {
        if (point > 0) {
            point -= 20;
            mTvPoint.setText(String.valueOf(point));
            saveDataPoint();

            forHint(mPlan1);
            forHint(mPlan2);
            checkAnswer(mAnswer1);
            checkAnswer(mAnswer2);
            supportDialog.cancel();

        } else {
            Toast.makeText(this, "Bạn không đủ tiền!", Toast.LENGTH_SHORT).show();
            mTvSuppost.setEnabled(false);
        }

    }

    private void saveDataPoint() {
        SharedPreferences spCoin = getSharedPreferences(SHARED_PREFERENCES_COIN, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor1 = spCoin.edit();
        if (point >= 0) {
            editor1.putInt("coin", point);
            editor1.apply();
        }

    }


    private void showKyTu(int indext) {
        try {
            for (int i = 0; i < kq.length(); i++) {
                String KyTuDau = String.valueOf(kq.charAt(indext));
                if (point > 0) {
                    point -= 5;
                    mTvPoint.setText(String.valueOf(point));
                    for (int k = 0; k < mAnswer1.getChildCount(); k++) {
                        Button button = (Button) mAnswer1.getChildAt(indext);
                        button.setText(KyTuDau);
//                    button.setBackgroundResource(R.drawable.tile_hint);
                        button.setEnabled(false);
                        break;
                    }
                    forMplan(KyTuDau, mPlan1);
                    forMplan(KyTuDau, mPlan2);
                    checkAnswer(mAnswer1);
                    checkAnswer(mAnswer2);
                    supportDialog.cancel();
                } else {
                    Toast.makeText(this, "Bạn không đủ tiền để mở gợi ý", Toast.LENGTH_SHORT).show();
                }
                break;
            }
        } catch (Exception e) {
            Log.i(TAG, "Exception : " + e);
        }

    }

    private void forHint(LinearLayout mPlan) {
        for (int k = 0; k < mPlan.getChildCount(); k++) {
            Button button = (Button) mPlan.getChildAt(k);
            button.setVisibility(View.INVISIBLE);
            for (int i = 0; i < kq.length(); i++) {
                String KyTuDau = String.valueOf(kq.charAt(i));
                if (KyTuDau.equals(button.getText())) {
                    button.setVisibility(View.VISIBLE);
                }
            }
        }
    }

    public void forMplan(String KyTuDau, LinearLayout mPlan) {
        for (int k = 0; k < mPlan.getChildCount(); k++) {
            Button button = (Button) mPlan.getChildAt(k);
            if (KyTuDau.equals(button.getText())) {
                button.setVisibility(View.INVISIBLE);
                return;
            }
        }
    }


}
