package com.example.aop.test;

import com.example.aop.AopApplication;
import com.example.aop.entity.UserBean;
import com.example.aop.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AopApplication.class)
public class UserServiceTest {
    @Autowired
    private UserService userService;
    @Test
    public void testDynamicDatasource() {
        UserBean userBean = new UserBean();
        userBean.setName("tudou");
        userBean.setPassword("111111");
        userService.insertUser(userBean);
        System.out.println(userService.getAllUser());
        System.out.println(userService.insertUser(userBean));
        System.out.println(userService.getAllUser());
    }
}
