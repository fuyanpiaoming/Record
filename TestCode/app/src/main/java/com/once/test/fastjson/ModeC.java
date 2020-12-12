package com.once.test.fastjson;

import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.AfterFilter;
import com.alibaba.fastjson.serializer.BeforeFilter;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.NameFilter;
import com.alibaba.fastjson.serializer.PascalNameFilter;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.ValueFilter;

/**
 * SerializeFilter是通过编程扩展的方式定制序列化
 * PropertyPreFilter 根据PropertyName判断是否序列化
 * PropertyFilter 根据PropertyName和PropertyValue来判断是否序列化
 * NameFilter 修改Key
 * ValueFilter 修改Value
 * BeforeFilter 序列化时在最前添加内容
 * AfterFilter 序列化时在最后添加内容
 */
public class ModeC {

    class Mode{

        private int type;
        private String name;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    //根据属性name判断是否需要序列化
    PropertyPreFilter propertyPreFilter = new PropertyPreFilter() {
        @Override
        public boolean apply(JSONSerializer serializer, Object object, String name) {
            return false;
        }
    };

    //根据属性（name）以及值(value)判断是否需要序列化
    PropertyFilter propertyFilter = new PropertyFilter() {
        @Override
        public boolean apply(Object object, String name, Object value) {
            return false;
        }
    };

    //序列化时修改属性
    NameFilter nameFilter = new NameFilter() {
        @Override
        public String process(Object object, String name, Object value) {
            return null;
        }
    };

    //序列化时修改值
    ValueFilter valueFilter = new ValueFilter() {
        @Override
        public Object process(Object object, String name, Object value) {
            return null;
        }
    };

    //序列化时在最前面添加内容
    BeforeFilter beforeFilter = new BeforeFilter() {
        @Override
        public void writeBefore(Object object) {

        }
    };

    //序列化时在最后面添加内容
    AfterFilter afterFilter = new AfterFilter() {
        @Override
        public void writeAfter(Object object) {
            object = "Hello";
        }
    };



    public void test(){
        //String str = "{\"type\":1 ,\"name\":\"Android\"}";
        Mode mode = new Mode();
        mode.setType(1);
        mode.setName("Android");
        PascalNameFilter pascalNameFilter = new PascalNameFilter();
        String jsonStr = JSON.toJSONString(mode,pascalNameFilter);
        Log.i("ModeC","[test]jsonStr=" + jsonStr);
        String jsonStr2 = JSON.toJSONString(mode,afterFilter);
        Log.i("ModeC","[test]jsonStr2=" + jsonStr2);
    }

}
