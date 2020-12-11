package com.once.test.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Date;

public class ModeA {

    //A和B的效果是一样的
    class A {
        @JSONField(name = "type")
        private int type;
    }

    class B {
        private int type;

        @JSONField(name = "type")
        public int getType() {
            return type;
        }

        @JSONField(name = "type")
        public void setType(int type) {
            this.type = type;
        }
    }

    class C {
        //format只针对日期
        @JSONField(format = "yyyy-MM-dd")
        private Date date;

        public Date getDate() {
            return date;
        }

        public void setDate(Date date) {
            this.date = date;
        }
    }

    class D {
        @JSONField(ordinal = 1)
        private int type;

        @JSONField(ordinal = 2)
        private String name;
    }

    class E {
        //type不进行序列化
        @JSONField(serialize = false)
        private int type;
        //name进行序列化（默认为true）
        @JSONField(serialize = true)
        private String name;
    }

    class F {
        //type字段不进行反序列化
        @JSONField(deserialize = false)
        private int type;
        //name字段进行反序列化
        @JSONField(deserialize = true)
        private String name;
    }

    /**
     * G g = new G();
     * g.name = "Mei";
     * String jsonStr = JSON.toJSONString(g);
     * 序列化字符串输出:Mei(hi)
     */
    class G {
        @JSONField(serializeUsing = ValueSerializer.class)
        private String name;
    }

    class ValueSerializer implements ObjectSerializer {
        @Override
        public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
            String value = (String) object;
            String result = value + "(hi)";
            serializer.write(result);
        }
    }

    /**
     * 反序列化时使用多个不同的字段名称(相当于别名)
     * String str="{\"id\":1}";
     * String str1="{\"uid\":2}";
     * String str3 = "{\"pid\":3}";
     * String str4 = "{\"fid\":4}";
     * H h = JSON.parseObject(str,H.class);
     * H h = JSON.parseObject(str1,H.class);
     * H h = JSON.parseObject(str2,H.class);
     */
    class H {
        @JSONField(alternateNames = {"uid", "pid", "fid"})
        private int id;
    }
}
