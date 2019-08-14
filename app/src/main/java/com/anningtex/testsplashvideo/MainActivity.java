package com.anningtex.testsplashvideo;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.CountDownTimer;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * @author Administrator
 * desc:开屏页启动视频
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private CustomVideoView videoView;
    private Button btn_start;
    private RelativeLayout rlSplash;
    private TextView tvNumber;

    AlphaAnimation animation;
    MyCount my;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Window window = getWindow();
        //隐藏标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //隐藏状态栏
        //定义全屏参数
        int flag = WindowManager.LayoutParams.FLAG_FULLSCREEN;
        //设置当前窗体为全屏显示
        window.setFlags(flag, flag);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        setContentView(R.layout.activity_main);

        initView();
    }

    private void initView() {
        btn_start = findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);

        rlSplash = findViewById(R.id.rl_splash);
        tvNumber = findViewById(R.id.tv_number);

        videoView = findViewById(R.id.videoView);
        //设置播放加载路径
//        videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.guide_1));
        videoView.setVideoURI(Uri.parse("http://flashmedia.eastday.com/newdate/news/2016-11/shznews1125-19.mp4"));
        //播放
        videoView.start();
        //循环播放
        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                videoView.start();
            }
        });

        animation = new AlphaAnimation(0.5f, 1.0f);
        animation.setDuration(3000);
        my = new MyCount(4000, 1000);
        my.start();
        rlSplash.setAnimation(animation);
    }

    public class MyCount extends CountDownTimer {
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tvNumber.setText("" + millisUntilFinished / 1000 + "s");
        }

        @Override
        public void onFinish() {
            Toast.makeText(MainActivity.this, "跳进入了别的界面", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Toast.makeText(this, "进入了主页", Toast.LENGTH_SHORT).show();
                break;
            case R.id.tv_number:
                Toast.makeText(MainActivity.this, "跳进入了别的界面", Toast.LENGTH_SHORT).show();
                my.cancel();
                break;
            default:
                break;
        }
    }
}
