package com.xiaochao.utils;

import java.util.HashMap;

/**
 * @program: graduation
 * @description: 返回前端的数据结果
 * @author: 小超
 * @create: 2020-11-19 11:18
 **/
public final class ResultMap {

    public static HashMap<String, Object> setResult(String customStatus, Object data, String message) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("error_code", customStatus);
        map.put("data", data);
        map.put("message", message);
        return map;
    }

    public static HashMap<String, Object> setResult(String customStatus, Object data, String message, String token) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("custom_status", customStatus);
        map.put("data", data);
        map.put("message", message);
        map.put("token", token);
        return map;
    }

    public static HashMap<String,Object> setResult(Integer errorCode,Object data,String message){
        HashMap<String,Object> map=new HashMap<>();
        map.put("error_code",errorCode);
        map.put("data",data);
        map.put("message",message);
        return map;
    }

    private ResultMap() {}

}
