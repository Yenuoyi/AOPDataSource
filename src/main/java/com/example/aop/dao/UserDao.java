package com.example.aop.dao;

import com.example.aop.entity.UserBean;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserDao {
	/**
	 * @param userBean
	 * @return
	 */
	int insertUser(UserBean userBean);

	/**
	 * @param id
	 * @return
	 */
	int deleteUserById(@Param("id") int id);

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
