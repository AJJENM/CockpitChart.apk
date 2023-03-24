package com.example.myapplication.HttpUtil;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * @since
 * 异步请求会放到线程池中执行
 * 同步请求会阻塞当前线程
 * 一般使用异步请求居多
 */
public class OKHttpUtil {
    private static Request request = null;
    private static int TimeOut = 120;
    //单例获取okhttp3对象
    private static OkHttpClient client = null;

    private static String code = "500";
    /**
     * OkHttpClient的构造方法，通过线程锁的方式构造
     * @return OkHttpClient对象
     */
    private static synchronized OkHttpClient getInstance() {
        if (client == null) {
            client = new OkHttpClient.Builder()
                    .readTimeout(TimeOut, TimeUnit.SECONDS)
                    .connectTimeout(TimeOut, TimeUnit.SECONDS)
                    .writeTimeout(TimeOut, TimeUnit.SECONDS)
                    .build();
        }
        return client;
    }

    /**
     * callback接口
     * 异步请求时使用
     */
    static class MyCallBack implements Callback {
        private OkHttpCallback okHttpCallBack;

        public MyCallBack(OkHttpCallback okHttpCallBack) {
            this.okHttpCallBack = okHttpCallBack;
        }

        @Override
        public void onFailure(Call call, IOException e) {
            okHttpCallBack.onFailure(e);
        }

        @Override
        public void onResponse(Call call, Response response) throws IOException {
            okHttpCallBack.onSuccess(response);
        }
    }
    /**
     * 获得同步get请求对象Response
     * @param url
     * @return Response
     */
    private static Response doSyncGet(String url) {
        //创建OkHttpClient对象
        client = getInstance();
        request = new Request.Builder()
                .url(url)//请求链接
                .build();//创建Request对象
        try {
            //获取Response对象
            Response response = client.newCall(request).execute();
            return response;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 获得异步get请求对象
     * @param url      请求地址
     * @param callback 实现callback接口
     */
    private static void doAsyncGet(String url,OkHttpCallback callback) {
        MyCallBack myCallback = new MyCallBack(callback);
        client = getInstance();
        request = new Request.Builder()
                .url(url)
                .get()
                .addHeader("Authorization","eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjJmN2EzMGMwLWIwMWYtNDc1Ni1hNTk2LTE4NGM0ODQ4ZTAxNCJ9.io4vf1IARGNznUb-tqWe3DUCwSQhipPua0vDjPt04armCBemOBkdtIsRVHxSzKH7Pqdx1M96_aZIYuEgR-Q_pA")
                .build()
//                .put(RequestBody.create(MediaType.parse("application/json"),json))
//                .addHeader("Authorization","eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjJmN2EzMGMwLWIwMWYtNDc1Ni1hNTk2LTE4NGM0ODQ4ZTAxNCJ9.io4vf1IARGNznUb-tqWe3DUCwSQhipPua0vDjPt04armCBemOBkdtIsRVHxSzKH7Pqdx1M96_aZIYuEgR-Q_pA")
//                .header("Authorization","eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjJmN2EzMGMwLWIwMWYtNDc1Ni1hNTk2LTE4NGM0ODQ4ZTAxNCJ9.io4vf1IARGNznUb-tqWe3DUCwSQhipPua0vDjPt04armCBemOBkdtIsRVHxSzKH7Pqdx1M96_aZIYuEgR-Q_pA")

                ;
        client.newCall(request).enqueue(myCallback);
    }
    /**
     * 同步get请求
     * 例如：请求的最终地址为：http://127.0.0.1:8081/user/getUser/123
     * @param url 基本请求地址   例子： http://127.0.0.1:8081
     * @param args 请求的参数    args[]=new String[]{"user","getUser","123"}
     * @return String
     */
    public static String getSyncRequest(String url,String... args) {
        List<String> result=new ArrayList<>();//返回值
        String address=url;
        for(int i=0;i<args.length;i++){
            address=address+"/"+args[i];
        }
        final String finalAddress = address;
        new Thread(new Runnable() {
            @Override
            public void run() {
                Response finalResponse = doSyncGet(finalAddress);
                String res = null;
                try {
                    Log.d("同步get请求请求地址：",finalAddress);
                    if (finalResponse.isSuccessful()) {//请求成功
                        ResponseBody body = finalResponse.body();//拿到响应体
                        res = body.string();
                        result.add(res);
                        Log.d("HttpUtil", "同步get请求成功！");
                        Log.d("请求对象：", res);
                    } else {
                        Log.d("HttpUtil", "同步get请求失败！");
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        /**因为函数返回是立刻执行的，而result要在请求完成之后才能获得
         * 所以需要等待result获得返回值之后再执行return*/
        while(result.size()==0){
            try {
                TimeUnit.MILLISECONDS.sleep(10);//等待xx毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result.get(0);
    }


    /**
     * 异步get请求
     * 例如：请求的最终地址为：http://127.0.0.1:8081/user/getUser/123
     * @param url 基本请求地址   例子： http://127.0.0.1:8081
     * @param args 请求的参数    args[]=new String[]{"user","getUser","123"}
     * @return String
     */
    public static String getAsyncRequest(String url,String... args){
        List<String> result=new ArrayList<>();
        String address=url;
        for(int i=0;i<args.length;i++){
            address=address+"/"+args[i];
        }
        final String finalAddress = address;
        doAsyncGet(finalAddress, new OkHttpCallback() {

            @Override
            public void onFailure(IOException e) {
                Log.d("异步get请求地址：",finalAddress);
                Log.d("HttpUtil", "异步get请求失败！");
            }
            @Override
            public void onSuccess(Response response) {
                Log.d("异步get请求地址：",finalAddress);
                String res = null;
                try {
                    res = response.body().string();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                result.add(res);
                Log.d("HttpUtil", "异步get请求成功！");
                Log.d("请求对象：", res);
            }
        });
        /**因为函数返回是立刻执行的，而result要在请求完成之后才能获得
         * 所以需要等待result获得返回值之后再执行return*/
        while(result.size()==0){
            try {
                TimeUnit.MILLISECONDS.sleep(10);//等待xx毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result.get(0);
    }

    /**
     * 同步post请求
     * 例如：请求的最终地址为：http://127.0.0.1:8081/user/getUser/123
     * @param url 基本请求地址   例子： http://127.0.0.1:8081
     * @param json 提交的json字符串
     * @param args 请求的参数    args[]=new String[]{"user","getUser","123"}
     * @return
     */
    public static String postSyncRequest(String url,String json,String... args){
        List<String> result=new ArrayList<>();
        String address=url;
        for(int i=0;i<args.length;i++){
            address=address+"/"+args[i];
        }
        final String finalAddress = address;
        new Thread(new Runnable() {
            @Override
            public void run() {
                client=getInstance();
                Log.d("同步post请求地址：",finalAddress);
                FormBody.Builder formBody = new FormBody.Builder();
                formBody.add("json",json);
                request=new Request.Builder()
                        .url(finalAddress)
                        .post(formBody.build())
                        .addHeader("device-platform", "android")
                        .build();
                try{
                    Response response=client.newCall(request).execute();
                    String res=response.body().string();
                    result.add(res);
                    Log.d("HttpUtil", "同步post请求成功！");
                    Log.d("请求对象：", res);
                }catch (Exception e){
                    Log.d("HttpUtil", "同步post请求失败！");
                    e.printStackTrace();
                }
            }
        }).start();
        /**因为函数返回是立刻执行的，而result要在请求完成之后才能获得
         * 所以需要等待result获得返回值之后再执行return*/
        while(result.size()==0){
            try {
                TimeUnit.MILLISECONDS.sleep(10);//等待xx毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result.get(0);
    }
    /**
     * 异步post请求
     * 例如：请求的最终地址为：http://127.0.0.1:8081/user/getUser/123
     * @param url 基本请求地址   例子： http://127.0.0.1:8081
     * @param json 提交的json字符串
     * @param args 请求的参数    args[]=new String[]{"user","getUser","123"}
     * @return
     */
    public static String postAsyncRequest(String url,String json,String... args){
        List<String> result=new ArrayList<>();
        String address=url;
        for(int i=0;i<args.length;i++){
            address=address+"/"+args[i];
        }
        final String finalAddress = address;
        Log.d("异步post请求地址：",finalAddress);
        client=getInstance();
        //okhttp上传upjson的时候会默认增加"charset=utf-8"字符集，
        //也就是Content-Type变成了"application/json;charset=utf-8"。导致了接口一直不能通过鉴权。
//        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
//        formBody.add("json",json);
//        Log.d("from-body:", String.valueOf(formBody));
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),json);


        request = new Request.Builder()
                .url(finalAddress)
//                .post(formBody.build())
                .post(requestBody)
//                .addHeader("Content-Type", "application/json")
//                .addHeader("Transfer-Encoding", "chunked")
//                .addHeader("Accept-Encoding","identity")
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("HttpUtil","异步post请求失败！");
                    }
                }).start();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = null;
                        try {
                            res = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        result.add(res);
                        Log.d("HttpUtil","异步post请求成功！");
                        Log.d("请求对象",res);
                    }
                }).start();
            }
        });
        /**因为函数返回是立刻执行的，而result要在请求完成之后才能获得
         * 所以需要等待result获得返回值之后再执行return*/
        while(result.size()==0){
            try {
                TimeUnit.MILLISECONDS.sleep(10);//等待xx毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result.get(0);
    }
    /**
     * 异步delete请求
     * 例如：请求的最终地址为：http://127.0.0.1:8081/user/getUser/123
     * @param url 基本请求地址   例子： http://127.0.0.1:8081
     * @param json 提交的json字符串
     * @param args 请求的参数    args[]=new String[]{"user","getUser","123"}
     * @return
     */
    public static String deleteAsyncRequest(String url,String json,String... args){
        List<String> result=new ArrayList<>();
        String address=url;
        for(int i=0;i<args.length;i++){
            address=address+"/"+args[i];
        }
        final String finalAddress = address;
        Log.d("异步delete请求地址：",finalAddress);
        client=getInstance();
        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("json",json);
        request = new Request.Builder()
                .url(finalAddress)
                .delete(formBody.build())
                .addHeader("device-platform", "android")
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("HttpUtil","异步delete请求失败！");
                    }
                }).start();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = null;
                        try {
                            res = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        result.add(res);
                        Log.d("HttpUtil","异步delete请求成功！");
                        Log.d("请求对象",res);
                    }
                }).start();
            }
        });
        /**因为函数返回是立刻执行的，而result要在请求完成之后才能获得
         * 所以需要等待result获得返回值之后再执行return*/
        while(result.size()==0){
            try {
                TimeUnit.MILLISECONDS.sleep(10);//等待xx毫秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result.get(0);
    }
    /**
     * 异步put请求
     * 例如：请求的最终地址为：http://127.0.0.1:8081/user/getUser/123
     * @param url 基本请求地址   例子： http://127.0.0.1:8081
     * @param json 提交的json字符串
     * @param args 请求的参数    args[]=new String[]{"user","getUser","123"}
     * @return
     */
    public static String putAsyncRequest(String url,String json,String... args){
        List<String> result=new ArrayList<>();
        String address=url;
        for(int i=0;i<args.length;i++){
            address=address+"/"+args[i];
        }
        final String finalAddress = address;
        Log.d("异步put请求地址：",finalAddress);
        client=getInstance();
//        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
//        contentType

        FormBody.Builder formBody = new FormBody.Builder();//创建表单请求体
        formBody.add("json",json);
        Log.d("form-body", String.valueOf(formBody.build()));
        request = new Request.Builder()
                .url(finalAddress)
                .put(formBody.build())
                .put(RequestBody.create(MediaType.parse("application/json"),json))
                .addHeader("Authorization","eyJhbGciOiJIUzUxMiJ9.eyJsb2dpbl91c2VyX2tleSI6IjJmN2EzMGMwLWIwMWYtNDc1Ni1hNTk2LTE4NGM0ODQ4ZTAxNCJ9.io4vf1IARGNznUb-tqWe3DUCwSQhipPua0vDjPt04armCBemOBkdtIsRVHxSzKH7Pqdx1M96_aZIYuEgR-Q_pA")
                //.addHeader("Cookie", "JSESSIONID=7612111e-2529-481d-a818-e97de54f65c0; rememberMe=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")
                //Nice
                .build();
        Call call=client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Log.d("HttpUtil","异步put请求失败！");
                    }
                }).start();
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String res = null;
                        try {
                            res = response.body().string();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        result.add(res);
                        Log.d("HttpUtil","异步put请求成功！");
                        Log.d("请求对象",res);
                    }
                }).start();
            }
        });
        /**因为函数返回是立刻执行的，而result要在请求完成之后才能获得
         * 所以需要等待result获得返回值之后再执行return*/
        int count=1;
        while(result.size()==0){
            try {
                TimeUnit.MILLISECONDS.sleep(10);//等待xx毫秒
                if(count++>100){
                    result.add(code);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return result.get(0);
    }
}
