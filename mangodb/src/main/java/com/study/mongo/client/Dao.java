package com.study.mongo.client;

import java.util.List;

/**
 * Created by wangliang on 2016/9/4.
 */
public interface Dao {
    public void save(Object obj);

    public void delete(Object obj);

    public void update(Object obj);

    public Object find(Object obj);

    public List<Object> finds();

    public long total();

    public List<Object> finds(int index, int offset);
}
