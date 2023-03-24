package com.example.myapplication.Post;

import com.example.myapplication.HttpUtil.HttpUrl;
import com.example.myapplication.HttpUtil.OKHttpUtil;
import com.example.myapplication.entity.Info;
import com.example.myapplication.entity.Result;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
/**
 * 增删改查
 * 增：post请求
 * 删：delete请求
 * 改：put请求
 * 查：get请求
 */
public class DatabaseUtil {
    final private static String baseUrl = HttpUrl.getBaseUrl();
    final private static Gson gson = JsonBean.getGson();
//    final private static Gson gson=new GsonBuilder().serializeNulls().create();
//    private static String jsonForm=null;
//    private static String jsonResult=null;
//    private static Result result=null;

    /**
     * 其他关于post请求的
     *
     * @param bean
     * @param args
     * @return
     */
    //                Result result=DatabaseUtil.other(new User(id,pwd.getText().toString()),"user","login");
    public static Result other(Object bean, String... args) {
        Result result = new Result();
        String jsonForm = null;
        String jsonResult = null;
        Gson gson = JsonBean.getGson();
        String baseUrl = HttpUrl.getBaseUrl();
        try {
            jsonForm = gson.toJson(bean);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        jsonResult = OKHttpUtil.postAsyncRequest(baseUrl, jsonForm, args);
        if (jsonResult == null) {//请求失败
            result.setWarn("发送请求错误！", null);
        } else if (jsonResult.equals("500")) {
            result.setErr();
        } else {//请求成功
            result = gson.fromJson(jsonResult, Result.class);
            if (result.getMsg().toString() == "无数据查看权限") {//

            } else if (result.getMsg().toString() == "成功") {//

            }
        }
        return result;
    }

    /**
     * 通过异步post请求
     * 数据库插入操作----增        --例子
     *
     * @param bean bean对象       user
     * @param args 请求地址参数   {"user","insert"}
     * @return
     */
    public static Result insert(Object bean, String... args) {
        Result result = new Result();
        String jsonForm = null;
        String jsonResult = null;
        Gson gson = JsonBean.getGson();
        String baseUrl = HttpUrl.getBaseUrl();
        try {
            jsonForm = gson.toJson(bean);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        jsonResult = OKHttpUtil.postAsyncRequest(baseUrl, jsonForm, args);
        if (jsonResult == null) {//请求失败
            result.setWarn("发送请求错误！", null);
        } else if (jsonResult.equals("500")) {
            result.setErr();
        } else {//请求成功
            result = gson.fromJson(jsonResult, Result.class);
            if (result.getMsg().toString() == "无数据查看权限") {//插入失败

            } else if (result.getMsg().toString() == "成功") {//插入成功

            }
        }
        return result;
    }

    /**
     * 通过异步delete请求
     * 数据库插入操作----通过 id 删除
     *
     * @param args 请求地址参数 args[]={"user","delete",id}
     * @return
     */
    public static Result deleteById(String... args) {
        Result result = new Result();
        String jsonResult = null;
        String jsonForm = "";
        jsonResult = OKHttpUtil.deleteAsyncRequest(baseUrl, jsonForm, args);
        if (jsonResult == null) {//请求失败
            result.setWarn("发送请求错误！", null);
            ;
        } else if (jsonResult.equals("500")) {
            result.setErr();
        } else {//请求成功
            result = gson.fromJson(jsonResult, Result.class);
            if (result.getMsg().toString() == "无数据查看权限") {//删除失败

            } else if (result.getMsg().toString() == "成功") {//删除成功

            }
        }
        return result;
    }

    /**
     * 通过异步put请求
     * 数据库更新操作----通过 id 改    ---例子
     *
     * @param bean user
     * @param args {"user","update"}
     * @return
     */
    public static Result updateById(Object bean, String... args) {
        Result result = new Result();
        String jsonForm = "";
        String jsonResult = null;
        try {
            jsonForm = gson.toJson(bean);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        jsonResult = OKHttpUtil.putAsyncRequest(baseUrl, jsonForm, args);
        if (jsonResult == null) {//请求失败
            result.setWarn("发送请求错误！", null);
            ;
        } else if (jsonResult.equals("500")) {
            result.setErr();
        } else {//请求成功
            result = gson.fromJson(jsonResult, Result.class);
            if (result.getMsg().toString() == "无数据查看权限") {//插入失败

            } else if (result.getMsg().toString() == "成功") {//插入成功

            }
        }
        return result;
    }

    /**
     * 数据库操作--查询，查询单个对象
     *
     * @param args 请求地址参数 {"user","line",id}
     * @return
     */
    //        Result result= DatabaseUtil.selectLineById("userPlace","line",user.getAid());
    public static Result selectLineById(String... args) {
        Result result = new Result();
        String jsonResult = null;
        jsonResult = OKHttpUtil.getAsyncRequest(baseUrl, args);
        if (jsonResult == null) {//请求失败
            result.setWarn("发送请求错误！", null);
        } else if (jsonResult.equals("500")) {
            result.setErr();
        } else {//请求成功
            result = gson.fromJson(jsonResult, Result.class);
            if (result.getMsg().toString() == "无数据查看权限") {//查询失败

            } else if (result.getMsg().toString() == "成功") {//查询成功

            }
        }
        return result;
    }

    /**
     * 数据库操作--查询，查询对象集合
     *
     * @param args 请求地址参数 {"user","list"}
     * @return
     */
    public static Result selectList(String... args) {
        Result result = new Result();
        String jsonResult = null;
        jsonResult = OKHttpUtil.getAsyncRequest(baseUrl, args);
        if (jsonResult == null) {//请求失败
            result.setWarn("发送请求错误！", null);
        } else if (jsonResult.equals("500")) {
            result.setErr();
        } else {//请求成功
            result = gson.fromJson(jsonResult, Result.class);
            if (
//                    result.getCode() == 200
                    result.getMsg().toString() == "无数据查看权限"
            ) {//查询失败

            } else if (
//                    result.getCode() == 400
                    result.getMsg().toString() == "成功"
            ) {//查询成功

            }
        }
        return result;
    }

    /**
     * 通过Result获取bean对象
     *
     * @param result
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T getEntity(Result result, Class<?> cls) {
        T entity = null;
        try {
            entity = (T) gson.fromJson((String.valueOf(result.getResponse())), cls);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return entity;
    }

    /**
     * 通过Result获取对象集合
     *
     * @param result
     * @return
     */
    public static List getList(Result result) {
        List list = new ArrayList<>();

        try {
            String tojson = gson.toJson(result.getResponse());
            list = gson.fromJson((String.valueOf(tojson)), new TypeToken<List<Info>>(){}.getType());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }


//    public static List<T> getList2(Result result,Class<T> cls){
//        List<T> list = new ArrayList<T>();
//        String tojson = gson.toJson(result.getResponse());
//        TypeToken T = new TypeToken<List<cls>>();
//        list = gson.fromJson((String.valueOf(tojson)), new TypeToken<List<T>>(){}.getType());
//        return (T) list;
//    }
//    public static List getList3(Result result,Class<T> cls){
//        List list = new ArrayList<>();
//        String tojson = gson.toJson(result.getResponse());
//        list = gson.fromJson((String.valueOf(tojson)), new TypeToken<List<cls>>(){}.getType());
//        return list;
//    }

//
//    /**
//     * 这是模拟后台请求
//     *
//     * @param listener 用于传递数据的回调
//     * @param <T>      需要转换data 的对象类型
//     */
//    private <T> void request(RequestListener<T> listener) {
//        String serverStr = "这里是后台返回的内容";
////        Type type = new TypeToken<BaseEntity<T>>() {}.getType();
////        BaseEntity<T> baseEntity = new Gson().fromJson(serverStr, type);
//        BaseEntity<T> baseEntity = new Gson().fromJson(serverStr, listener.getType());
//        T data = baseEntity.data;
//        listener.onSuccess(data);
//    }
//    public <T> void request(Result result) {
////        Type type = new TypeToken<BaseEntity<T>>() {}.getType();
////        BaseEntity<T> baseEntity = new Gson().fromJson(serverStr, type);
//        result = new Gson().fromJson(result.getData(), .getType());
//        T data = result.data;
//        listener.onSuccess(data);
//    }
//    public interface RequestListener<T>{
//        void onSuccess(T data);
//    }
}

