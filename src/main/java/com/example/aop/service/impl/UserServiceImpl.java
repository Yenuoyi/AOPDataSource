package com.example.aop.service.impl;

import com.example.aop.dao.UserDao;
import com.example.aop.datasource.DataSource;
import com.example.aop.datasource.DataSourceName;
import com.example.aop.entity.UserBean;
import com.example.aop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Max;
import java.util.List;

@Service
@Transactional
@DataSource("SLAVE-DATASOURCE")
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    public int insertUser(UserBean userBean) {
        return userDao.insertUser(userBean);
    }

    @Override
    public int deleteUserById(int id) {
        return userDao.deleteUserById(id);
    }

    @Override
    public int updateUserById(UserBean userBean) {
        return userDao.updateUserById(userBean);
    }

    @Override
    @DataSource("SLAVE-DATASOURCE")
    public List<UserBean> getAllUser() {
        return userDao.getAllUser();
    }
}
