package com.study.mongo.client;

import java.util.List;

/**
 * Created by wangliang on 2016/9/4.
 */
public class DaoImpl implements Dao{
    @Override
    public void save(Object obj) {

    }

    @Override
    public void delete(Object obj) {

    }

    @Override
    public void update(Object obj) {

    }

    @Override
    public Object find(Object obj) {
        return null;
    }

    @Override
    public List<Object> finds() {
        return null;
    }

    @Override
    public long total() {
        return 0;
    }

    @Override
    public List<Object> finds(int index, int offset) {
        return null;
    }
//    private MongoTemplate template;
//    private MongoSession session;
//    private String className;
//    public void setClassName(String className) {
//        this.className = className;
//    }
//    public MongoSession getSession() {
//        return session;
//    }
//    public DaoImpl(){}
//    public DaoImpl(MongoTemplate template,String className){
//        this.template = template;
//        this.className=className;
//        try {
//            this.session=template.session(Class.forName(className));
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//    public void setTemplate(MongoTemplate template) {
//        this.template = template;
//        try {
//            this.session=template.session(Class.forName(className));
//        } catch (ClassNotFoundException e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//    @Override
//    public void save(Object obj) {
//        this.session.save(obj);
//    }
//
//    @Override
//    public void delete(Object obj) {
//        try {
//            this.session.delete(obj);
//        } catch (Exception e) {
//            // TODO Auto-generated catch block
//            e.printStackTrace();
//        }
//    }
//
//    @Override
//    public void update(Object obj) {
//        this.session.update(obj);
//    }
//
//    @Override
//    public Object find(Object obj) {
//        // TODO Auto-generated method stub
//        return this.session.find(obj);
//    }
//
//    @Override
//    public List<Object> finds() {
//        // TODO Auto-generated method stub
//        return this.session.finds();
//    }
//
//    @Override
//    public long total() {
//        // TODO Auto-generated method stub
//        return this.session.count();
//    }
//
//    @Override
//    public List<Object> finds(int index, int offset) {
//        // TODO Auto-generated method stub
//        return this.session.query(null, null, offset, index);
//    }

}
