package com.six.jdcom;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.tv_miao)
    TextView tvMiao;
    private Handler handler = new Handler() {
        int count = 2;

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (count < 0) {// 跳转
                startActivity(new Intent(MainActivity.this,
                        SecondActivity.class));
                finish();
            } else {// 倒计时处理
                tvMiao.setText(count + "s");
                count--;
                handler.sendEmptyMessageDelayed(1, 1000);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        handler.sendEmptyMessageDelayed(1,1000);
    }


}
