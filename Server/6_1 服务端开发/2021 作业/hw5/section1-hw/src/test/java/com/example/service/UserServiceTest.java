package com.example.service;


import com.example.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import java.util.Date;

import static org.junit.Assert.*;
// 需要在pom文件中添加相应的依赖
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"/applicationContext.xml"})
public class UserServiceTest extends AbstractJUnit4SpringContextTests {

    @Mock
    private UserService userService;

    @Test
    public void hasMatchUser() {
        when(userService.hasMatchUser("admin", "123456")).thenReturn(true);
        boolean b1 = userService.hasMatchUser("admin", "123456");
        boolean b2 = userService.hasMatchUser("admin", "1111");
        assertTrue(b1);
        assertFalse(b2);
    }


    @Test
    public void findUserByUserName() {
        User user = new User();
        user.setUserName("admin");
        when(userService.findUserByUserName("admin")).thenReturn(user);
        user = userService.findUserByUserName("admin");
        assertEquals(user.getUserName(), "admin");
    }

    @Test
    public void loginSuccess() {
        User user = new User();
        user.setUserName("admin");
        when(userService.findUserByUserName("admin")).thenReturn(user);
        user = userService.findUserByUserName("admin");
        user.setUserId(1);
        user.setUserName("admin");
        user.setLastIp("192.168.12.7");
        user.setLastVisit(new Date());
        when(userService.saveLog(user)).thenReturn(true);
        assertEquals(true, userService.saveLog(user));
    }
}

