package com.study.mongo.client;

/**
 * Created by wangliang on 2016/9/4.
 */
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Column {
    public String name();

    public String text() default "这是一个属性映射";
}