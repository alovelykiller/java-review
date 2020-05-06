package com.chenms.datastructuresandalgorithms;

import java.util.LinkedHashMap;
import java.util.Map;

public class UseLinkedHashMapCache<k, v> extends LinkedHashMap<k, v> {
    private int cacheSize;

    public UseLinkedHashMapCache(int cacheSize) {
        //构造函数一定要放在第一行
        super(16, 0.75f, true);    //那个f如果不加  就是double类型，然后该构造没有该类型的入参。 然后最为关键的就是那个入参 true
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<k, v> eldest) {   //重写LinkedHashMap原方法
        return size() > cacheSize;  //临界条件不能有等于，否则会让缓存尺寸小1
    }
}
