package com.my.memcached;

import java.net.InetSocketAddress;
import java.util.concurrent.Future;
import net.spy.memcached.MemcachedClient;


public class TestSpyMemcache {
    public static InetSocketAddress _ADDRESS = new InetSocketAddress("10.111.0.108", 11211);

    public static void main(String[] args) {

        // 保存对象

        try {

           /* 建立MemcachedClient 实例，并指定memcached服务的IP地址和端口号 */

            MemcachedClient mc = new MemcachedClient(_ADDRESS);
            Future<Boolean> b = null;

           /* 将key值，过期时间(秒)和要缓存的对象set到memcached中 */

            b = mc.set("neea:testDaF:ksIdno", 9, "someObject");

            if (b.get().booleanValue() == true) {

                mc.shutdown();

            }

        } catch (Exception ex) {

            ex.printStackTrace();

        }

        // 取得对象

        try {

           /* 建立MemcachedClient 实例，并指定memcached服务的IP地址和端口号 */

            MemcachedClient mc = new MemcachedClient(_ADDRESS);

           /* 按照key值从memcached中查找缓存，不存在则返回null */

            Object b = mc.get("neea:testDaF:ksIdno");

            System.out.println(b.toString());

            Thread.sleep(1000);
            // the key should expired
            b = mc.get("neea:testDaF:ksIdno");
            System.out.println(b);

            Thread.sleep(8000);
            // the key should expired
            b = mc.get("neea:testDaF:ksIdno");
            System.out.println(b);

            mc.shutdown();

        } catch (Exception ex) {

            ex.printStackTrace();

        }

    }

}
