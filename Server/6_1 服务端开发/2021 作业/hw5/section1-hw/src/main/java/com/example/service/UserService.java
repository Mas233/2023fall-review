package com.example.service;

import com.example.dao.LoginLogDao;
import com.example.dao.UserDao;
import com.example.domain.LoginLog;
import com.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.stereotype.Service;

@Service
@EnableCaching
public class UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private LoginLogDao loginLogDao;

    public boolean hasMatchUser(String userName, String password) {
        int matchCount = Integer.valueOf(userDao.getMatchCount(userName, password).toString());
        return matchCount > 0;
    }

    @Cacheable(value = "users")
    public User findUserByUserName(String userName) {
        return userDao.findUserByUserName(userName);
    }

    public boolean saveLog(User user) {
        LoginLog loginLog = new LoginLog();
        loginLog.setUserId(user.getUserId());
        loginLog.setIp(user.getLastIp());
        loginLog.setLoginDate(user.getLastVisit());
        loginLogDao.save(loginLog);
        return true;
    }
}
