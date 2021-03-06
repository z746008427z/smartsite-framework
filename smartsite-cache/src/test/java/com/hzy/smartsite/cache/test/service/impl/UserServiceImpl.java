package com.hzy.smartsite.cache.test.service.impl;

import com.hzy.smartsite.cache.test.service.UserService;
import com.hzy.smartsite.cache.parser.ICacheResultParser;
import com.hzy.smartsite.cache.test.cache.MyKeyGenerator;
import com.hzy.smartsite.cache.test.entity.User;
import com.hzy.smartsite.cache.test.service.UserService;
import com.hzy.smartsite.cache.annotation.Cache;
import com.hzy.smartsite.cache.annotation.CacheClear;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.*;


@Service
public class UserServiceImpl implements UserService {
    private Logger log = Logger.getLogger(UserServiceImpl.class);

    @Override
    @Cache(key = "user{1}")
    public User get(String account) {
        log.debug("从方法内读取....");
        User user = new User("Ace", 24, account);
        return user;
    }

    @Override
    @Cache(key = "user:list")
    public List<User> getLlist() {
        log.debug("从方法内读取....");
        List<User> users = new ArrayList<User>();
        for (int i = 0; i < 20; i++) {
            User user = new User("Ace", i, "ace");
            users.add(user);
        }
        return users;
    }

    @Override
    @Cache(key = "user:set", parser = SetCacheResultParser.class)
    public Set<User> getSet() {
        log.debug("从方法内读取....");
        Set<User> users = new HashSet<User>();
        for (int i = 0; i < 20; i++) {
            User user = new User("Ace", i, "ace");
            users.add(user);
        }
        return users;
    }

    @Override
    @Cache(key = "user:map",parser = UserMapCacheResultParser.class)
    public Map<String, User> getMap() {
        log.debug("从方法内读取....");
        Map<String,User> users = new HashMap<String, User>();
        for (int i = 0; i < 20; i++) {
            User user = new User("Ace", i, "ace");
            users.put(user.getAccount() + i, user);
        }
        return users;
    }

    @Override
    @CacheClear(pre = "user")
    public void save(User user) {

    }

    @Override
    @Cache(key="user",generator = MyKeyGenerator.class)
    public User get(int age) {
        log.debug("从方法内读取....");
        User user = new User("Ace", age, "Ace");
        return user;
    }

    /**
     * 对map返回结果做处理
     *
     * @Created on 2017/5/22.
     */
    public static class UserMapCacheResultParser implements ICacheResultParser {
        @Override
        public Object parse(String value, Type returnType, Class<?>... origins) {
            return JSON.parseObject(value, new TypeReference<HashMap<String, User>>() {
            });
        }
    }
    /**
     * 对set返回结果做处理
     *
     * @Created on 2017/5/22.
     */
    public static class SetCacheResultParser implements ICacheResultParser {
        @Override
        public Object parse(String value, Type returnType, Class<?>... origins) {
//            origins[0]
            return JSON.parseObject(value, new TypeReference<HashSet<User>>() {
            });
        }
    }
}