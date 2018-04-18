package cn.zknu.l_okhttp;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by Administrator on 2018\4\10 0010.
 */

public abstract class OkHttpUtil {

    public static void requestGet(Callback callback){
        String BASE_URL="http://10.0.2.2/get.php?key=get";
        OkHttpClient okHttpClient=new OkHttpClient();
        Request request=new Request.Builder()
                .url(BASE_URL)
                .get()
                .build();

        Call call=okHttpClient.newCall(request);
        call.enqueue(callback);
    }
    public static void requestPost(Callback callback){
        String BASE_URL="http://10.0.2.2/post.php";
        OkHttpClient okHttpClient=new OkHttpClient();
        FormBody formBody=new FormBody.Builder()
                .add("key","Post")
                .build();
        Request request=new Request.Builder()
                .url(BASE_URL)
                .post(formBody)
                .build();

        Call call=okHttpClient.newCall(request);
        call.enqueue(callback);
    }
}
