package com.once.test.fastjson;

/**
 * javaBean规范
 * 1.类必须使用public修饰
 * 2.必须保证包含一个公共无参构造函数
 * 3.一个javaBean类不应有公共实例变量,类变量都为private
 * 4.javaBean属性是具有getter/setter方法的成员变量
 */

public class JavaBeanMode {

    private int type;

    public JavaBeanMode(){

    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }
}
