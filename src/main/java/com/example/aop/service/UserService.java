package com.example.aop.service;

import com.example.aop.entity.UserBean;

import java.util.List;

public interface UserService {
    /**
     * @param userBean
     * @return
     */
    int insertUser(UserBean userBean);

    /**
     * @param id
     * @return
     */
    int deleteUserById(int id);

    /**
     * @param userBean
     * @return
     */
    int updateUserById(UserBean userBean);

    /**
     * @return
     */
    List<UserBean> getAllUser();
}
