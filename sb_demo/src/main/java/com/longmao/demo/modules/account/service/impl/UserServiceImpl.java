package com.longmao.demo.modules.account.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.dao.UserDao;
import com.longmao.demo.modules.account.entity.User;
import com.longmao.demo.modules.account.service.UserService;
import com.longmao.demo.modules.common.vo.Result;
@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;

	@Override
	public PageInfo<User> selectUsersByPage(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<User> users = Optional.ofNullable(userDao.selectUsers()).orElse(Collections.emptyList());
		return new PageInfo<User>(users);
	}

	@Override
	public List<User> selectUsers() {
		return Optional.ofNullable(userDao.selectUsers()).orElse(Collections.emptyList());
	}

	@Override
	@Transactional
	public Result insertUserByUser(User user) {
		Result result=new Result(200,"success");
		User user_db = userDao.selectUserByUserName(user.getUserName());
		if(user_db!=null) {
			return new Result(500,"the account is exsit !",user);
		}
		user.setCreateDate(new Date(System.currentTimeMillis()));
		Md5Hash hash = new Md5Hash(user.getPassword(), user.getUserName(), 10);
		user.setPassword(hash.toString());
		try {
			userDao.insertUserByUser(user);
		} catch (Exception e) {
			result.setStatus(500);
		}
		result.setObject(user);
		return result;
	}

	@Override
	@Transactional
	public User updateUser(User user) {
		userDao.updateUserByUser(user);
		return userDao.selectUserByUserId(user.getUserId());
	}

	@Override
	@Transactional
	public boolean deleteUser(Integer userId) {
		int num=userDao.deleteUserByUserId(userId);
		if(num>0) {
			return true;
		}
		return false;
	}

	@Override
	public Result selectUserByUserId(Integer userId) {
		User user_db = userDao.selectUserByUserId(userId);
		if(user_db!=null) {
			return new Result(200, "", user_db);
		}
		return new Result(500,"用户名不存在");
	}

	@Override
	public Result selectUserByUserName(String userName) {
		User user_db = userDao.selectUserByUserName(userName);
		if(user_db!=null) {
			return new Result(200, "the account is exist", user_db);
		}
		return new Result(500,"用户名不存在");
	}

	@Override
	public boolean selectUserByuserNameAndPassword(User user) {
		user =userDao.selectUserByUserNameAndPassword(user.getUserName(),user.getPassword());
		if(user!=null) {
			return true;
		}
		return false;
	}

}
