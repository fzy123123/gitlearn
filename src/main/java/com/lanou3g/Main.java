package com.lanou3g;

import com.lanou3g.pojo.Person;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @author FuZhiYuan
 * @date 2019/7/31 9:02
 */
public class Main {
    public static void main(String[] args) {
        /*
        1.反射
        2.git 命令行
        3.spring-cloud 统一配置  config,运行监控
        4.接口文档(测试)工具,swagger2
        5.数据库相关-索引.联合主键,视图-存储过程-执行计划-常用函数.
        6.微服务中的登录
        7.json解析; jackson gson fastjson
        8.数组排序
        9.java8特性
        10.excel  导入导出
         */

        //1.  反射
        /*
        1.有两个类A和B,他们有一些同样的属性,要求实现一个功能,
        把A对象的属性赋值给B对象.
        2.把一个pojo对象转换为Map
        Person
            id
            name
            age
            height
        2.1: 动态的获取到这个对象有多少个属性,然后进行遍历
        2.2: 可以获取到这个属性的名字
        2.3: 获取到某个对象的某个属性的值

         */


        Object obj = new Person(1L, "zhangsan", 18);
        Map map = map(obj);
        System.out.println(map);

        //  获取class的方式
        Class<?>clazz1 = obj.getClass();
        Class clazz2 = Person.class;
        try {
            Class<?> aClass = Class.forName("com.lanou3g.demo");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        //  获取类中的其他属性
        Field[] fields = clazz1.getFields();
        Field f = fields[0];
        Class<?> type = f.getType();//获取属性的类型
        String name = f.getName();//属性的名字
//        f.get(obj) 获取某个对象的属性的值
        int modifiers = f.getModifiers();//修饰符


        Method[] methods = clazz1.getMethods();
        Constructor<?>[] constructors = clazz1.getConstructors();
        Annotation[] annotations = clazz1.getAnnotations();
        Class<?>[] interfaces = clazz1.getInterfaces();

    }

    /**
     * 把一个实体类转换为Map
     *
     * @param obj
     * @return
     */
    public static Map<String, Object> map(Object obj) {
        //  获取某个对象的所有属性
        Class<?> aClass = obj.getClass();
        Field[] fields = aClass.getDeclaredFields();
        Map<String, Object> map = new HashMap<>();
        try {
            for (Field field : fields) {
                String key = field.getName();
                //  获取obj对象的field属性的值
                //  如果某个属性是私有的 而且还想访问
                //  那么就把这个属性设置为可访问的
                field.setAccessible(true);
                Object value = field.get(obj);
                //  一般情况下,用完了 ,在复原
                field.setAccessible(false);
                map.put(key, value);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return map;
    }

    //  把一个Map转换为某个对象
    public static <T> T map(Map<String, Object> map, Class<T> clazz) {
        // 1. 通过class  创建一个对象

        try {
            T obj = clazz.newInstance();
            Set<Map.Entry<String, Object>> set = map.entrySet();
            for (Map.Entry<String, Object> entry : set) {
                String name = entry.getKey();// 属性名
                Object value = entry.getValue();// 属性值
                try {
                    Field field = clazz.getDeclaredField(name);
                    field.setAccessible(true);
                    //  为obj对象的field属性赋值为value
                    field.set(obj, value);
                    field.setAccessible(false);

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                }
            }

            return obj;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 把src对象的所有属性的值,同步到target对象
     *
     * @param src
     * @param target
     */
    public static void copy(Object src, Object target) {

        Class<?> srcClazz = src.getClass();
        Class<?> tarClazz = target.getClass();

        Field[] clazzDeclaredField = srcClazz.getDeclaredField();

    }

}
