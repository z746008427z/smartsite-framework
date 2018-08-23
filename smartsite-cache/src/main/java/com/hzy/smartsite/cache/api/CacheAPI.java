package com.hzy.smartsite.cache.api;

import java.util.List;

import com.hzy.smartsite.cache.entity.CacheBean;

/**
 * 缓存API
 * <p/>
 * 解决问题：
 *
 * @author Hzy
 * @version 1.0
 * @date 2018年8月4日
 * @since 1.7
 */
public interface CacheAPI {
    /**
     * 传入key获取缓存json，需要用fastjson转换为对象
     *
     * @param key
     * @return
     * @author Hzy
     * @date 2018年8月12日
     */
    public String get(String key);

    /**
     * 保存缓存
     *
     * @param key
     * @param value
     * @param expireMin
     * @author Hzy
     * @date 2018年8月12日
     */
    public void set(String key, Object value, int expireMin);

    /**
     * 保存缓存
     *
     * @param key
     * @param value
     * @param expireMin
     * @param desc
     * @author Hzy
     * @date 2018年8月12日
     */
    public void set(String key, Object value, int expireMin, String desc);

    /**
     * 移除单个缓存
     *
     * @param key
     * @return
     * @author Hzy
     * @date 2018年8月12日
     */
    public Long remove(String key);

    /**
     * 移除多个缓存
     *
     * @param keys
     * @return
     * @author Hzy
     * @date 2018年8月12日
     */
    public Long remove(String... keys);

    /**
     * 按前缀移除缓存
     *
     * @param pre
     * @return
     * @author Hzy
     * @date 2018年8月12日
     */
    public Long removeByPre(String pre);

    /**
     * 通过前缀获取缓存信息
     *
     * @param pre
     * @return
     * @author Hzy
     * @date 2018年8月12日
     */
    public List<CacheBean> getCacheBeanByPre(String pre);

    /**
     * 获取所有缓存对象信息
     *
     * @return
     * @author Hzy
     * @date 2018年8月12日
     */
    public List<CacheBean> listAll();

    /**
     * 是否启用缓存
     *
     * @return
     * @author Hzy
     * @date 2018年8月12日
     */
    public boolean isEnabled();

    /**
     * 加入系统标志缓存
     *
     * @param key
     * @return
     * @author Hzy
     * @date 2018年8月12日
     */
    public String addSys(String key);
}
