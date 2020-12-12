package com.once.test.fastjson;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.deserializer.ExtraProcessor;
import com.alibaba.fastjson.parser.deserializer.ExtraTypeProvider;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

/**
 * ParseProcess是编程扩展定制反序列化的接口
 * ExtraProcessor 用于处理多余的字段
 * ExtraTypeProvider 用于处理多余字段时提供类型信息
 */
public class ModeB {

    private final String TAG = ModeB.class.getSimpleName();

    class A {

        private int type;
        private Map<String, Object> map = new HashMap<>();

        public void setType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public Map<String, Object> getMap() {
            return map;
        }

        public void setMap(Map<String, Object> map) {
            this.map = map;
        }
    }

    ExtraProcessor extraProcessor = new ExtraProcessor() {
        @Override
        public void processExtra(Object object, String key, Object value) {
            A a = (A) object;
            a.getMap().put(key, value);
        }
    };

    public void test() {
        String str = "{\"type\":\"A\", \"name\":\"Good\"}";
        A a = JSON.parseObject(str, A.class, extraProcessor);
        Log.i(TAG, "[test]A:" + a.getType() + "-" + a.getMap().get("name"));
    }

    class MyExtraProcessor implements ExtraProcessor, ExtraTypeProvider{
        @Override
        public void processExtra(Object object, String key, Object value) {
            A a = (A)object;
            a.getMap().put(key,value);
        }

        @Override
        public Type getExtraType(Object object, String key) {
            if ("id".equals(key)){
                return int.class;
            }
            return null;
        }
    }

    private void test2(){
        MyExtraProcessor myExtraProcessor = new MyExtraProcessor();
        String str = "{\"id\":\"11\", \"type\":\"A\"}";
        A a = JSON.parseObject(str,A.class,myExtraProcessor);
    }

}
