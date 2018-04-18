package cn.zknu.l_okhttp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mBtnGet;
    private Button mBtnPost;

    private TextView mShowMsg;
    private Handler mHandler;
    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        init();
    }

    private void init() {
        mBtnGet.setOnClickListener(this);
        mBtnPost.setOnClickListener(this);
        mHandler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                mShowMsg.setText((String)msg.obj);
            }
        };
    }

    private void initView() {
        mBtnGet=(Button)findViewById(R.id.btn_get);
        mBtnPost=(Button)findViewById(R.id.btn_post);
        mShowMsg=(TextView)findViewById(R.id.tv_show_msg);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_get:
                httpGet();
                break;
            case R.id.btn_post:
                httpPost();
                break;
        }
    }

    private void httpPost() {
        OkHttpUtil.requestPost(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String strRet=response.body().string();
                Message msg=mHandler.obtainMessage();
                msg.obj="Post方法获取数据--->"+strRet;
                mHandler.sendMessage(msg);
            }
        });
    }

    private void httpGet() {
        OkHttpUtil.requestGet(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call,Response response) throws IOException {
                String strRet=response.body().string();
                Message msg=mHandler.obtainMessage();
                msg.obj="get方法获取数据--->"+strRet;
                mHandler.sendMessage(msg);
            }
        });
    }

}
