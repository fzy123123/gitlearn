package com.lanou3g;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lanou3g.pojo.Person;

import java.io.IOException;

/**
 * @author FuZhiYuan
 * @date 2019/7/31 15:19
 */
public class JacksonMain {
    public static void main(String[] args) {
        //  什么叫解析
        //  解析就是从一个有特定格式的字符串中提取数据
        //  常用的格式:xml json
        //  json格式的数据有两种类型
        //  {}  括起来的部分叫对象  Object
        //  []  括起来的叫做数组    Array
        //  序列化:  java对象  ->  json 数据
        //  反序列化:json数据 ->  java对象
        //  完成序列化和反序列化的过程使用框架来完成
        //  jackson gson  fastjson



        String json = "{\"username\":\"zhangsan\"}";

        //  1.创建一个与json数据结构一样的一个类
        //  2.使用框架进行解析

        ObjectMapper mapper = new ObjectMapper();
        try {
           mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            User user = mapper.readValue(json, User.class);
            System.out.println(user.getUsername());
        } catch (IOException e) {
            e.printStackTrace();
        }

        Person p = new Person(1L, "wangwu",28 );
        try {
            String s = mapper.writeValueAsString(p);
            System.out.println(s);

            mapper.writeValue(System.out,p );
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
