package com.example.lpd.imageloadertest.net;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by LPD on 2018/4/25.
 */

public class OkHttpTest {

    private static OkHttpClient client = new OkHttpClient();

    public static byte[] requestBitmapByGet(String URL){
        Request request = new Request.Builder().get().url(URL).build();

        try{
            Response response = client.newCall(request).execute();
//            client.newCall(request).enqueue(new Callback() {
//                @Override
//                public void onFailure(Call call, IOException e) {
//
//                }
//
//                @Override
//                public void onResponse(Call call, Response response) throws IOException {
//
//                }
//            });
            return response.body().bytes();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
