package com.my.memcached;

/**
 * Created by tianyuzhi on 15/9/30.
 */

import com.whalin.MemCached.MemCachedClient;
import com.whalin.MemCached.SockIOPool;

public class MemCacheTest {
    public static final String HOST = "10.111.0.108:11211";

    public static void main(String[] args) {

        /**
         * 初始化SockIOPool，管理memcached的连接池
         * */

        String[] servers = {HOST};
        SockIOPool pool = SockIOPool.getInstance();
        pool.setServers(servers);
        pool.setFailover(true);
        pool.setInitConn(10);
        pool.setMinConn(5);
        pool.setMaxConn(250);
        pool.setMaintSleep(30);
        pool.setNagle(false);
        pool.setSocketTO(3000);
        pool.setHashingAlg(3);
        pool.setAliveCheck(true);
        pool.initialize();

        /**
         * 建立MemcachedClient实例
         * */

        MemCachedClient memCachedClient = new MemCachedClient();
        for (int i = 0; i < 1000; i++) {
            /**
             * 将对象加入到memcached缓存
             * */

            boolean success = memCachedClient.set("" + i, i + " : Hello!");

            /**
             * 从memcached缓存中按key值取对象
             * */

            String result = (String) memCachedClient.get("" + i);
            System.out.println(String.format("set( %d ): %s", i, success));
            System.out.println(String.format("get( %d ): %s", i, result));
        }
    }
}