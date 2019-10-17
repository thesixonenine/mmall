package com.simple.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author Simple
 * @create 2017-10-06 15:38
 **/
public class TokenCache {

    public static final String TOKEN_PREFIX = "token_";
    private static final String RETURN = "null";
    /**
     * 声明静态内存块
     * 调用链模式设置参数
     * initialCapacity = 1000 : 缓存的初始化容量
     * maximumSize = 10000 : 缓存的最大容量.当超过这个容量的时候,会使用LRU算法(最少使用算法)来移除缓存项
     * expireAfterAccess = 12h : 有效期
     */
    public static LoadingCache<String, String> loadingCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS).build(new CacheLoader<String, String>() {
        //默认的加载实现,当使用get进行取值的时候,如果key没有对应的value,就使用这个方法进行加载
        @Override
        public String load(String s) throws Exception {
            //避免null去equals
            return RETURN;
        }
    });
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);

    public static void setKey(String key, String value) {
        loadingCache.put(key, value);
    }

    public static String getKey(String key) {
        String value;
        try {
            value = loadingCache.get(key);
            if (RETURN.equals(value)) {
                return null;
            }
            return value;
        } catch (Exception e) {
            logger.error("loadingCache get error", e);
        }
        return null;
    }
}
