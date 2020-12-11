package com.once.test.fastjson;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.annotation.JSONType;

public class Shape {
    /**
     * fastJson序列化方式
     *
     * @JSONField 序列化
     * @JSONType 序列化
     * SerializeFilter 序列化
     * ParseProcess 反序列化
     */
    //@JSONField配置在字段或者getter/setter方法上
    @JSONField
    private int type;

    /**
     * @JSONField作用在字段上
     */
    class Rect {
        @JSONField(name="Top")
        private int top;
        @JSONField(name="Bottom")
        private int bottom;
        @JSONField(name="Left")
        private int left;
        @JSONField(name="Right")
        private int right;
    }

    /**
     * @JSONFiled作用在方法上（getter和setter）
     */
    class Triangle{
        private int angle;
        private int len;

        @JSONField(name="angle")
        public int getAngle() {
            return angle;
        }

        @JSONField(name="angle")
        public void setAngle(int angle) {
            this.angle = angle;
        }

        @JSONField(name="len")
        public int getLen() {
            return len;
        }

        @JSONField(name="len")
        public void setLen(int len) {
            this.len = len;
        }
    }


    @JSONType
    class Square{
        private int length;
    }

}
