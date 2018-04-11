package cn.zknu.l_okhttp;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2018\4\10 0010.
 */

public abstract class OkHttpUtil {

    public static void requestGet(ResponseData responseData){
        final ResponseData resData=responseData;
        String BASE_URL="http://10.0.2.2/get.php?key=key";
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(BASE_URL)
                .get()
                .build();

        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                resData._onResponse(response.body().string());
            }
        });
    }
    public static void requestPost(ResponseData responseData){
        final ResponseData resData=responseData;
        String BASE_URL="http://10.0.2.2/post.php";
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody formBody=new FormBody.Builder()
                .add("key","key")
                .build();
        Request request=new Request.Builder()
                .url(BASE_URL)
                .post(formBody)
                .build();

        Call call=okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                resData._onResponse(response.body().string());
            }
        });
    }
}
