package com.longmao.demo.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.entity.User;
import com.longmao.demo.modules.common.vo.Result;

public interface UserService {

	PageInfo<User> selectUsersByPage(int currentPage, int pageSize);

	List<User> selectUsers();

	Result insertUserByUser(User user);

	User updateUser(User user);

	boolean deleteUser(Integer userId);

	Result selectUserByUserId(Integer userId);

	Result selectUserByUserName(String userName);

	boolean selectUserByuserNameAndPassword(User user);

	void deleteManyUsers(String[] userIdArr);

	


}
