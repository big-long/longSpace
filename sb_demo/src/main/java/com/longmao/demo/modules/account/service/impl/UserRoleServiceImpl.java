package com.longmao.demo.modules.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmao.demo.modules.account.dao.UserRoleDao;
import com.longmao.demo.modules.account.entity.UserRole;
import com.longmao.demo.modules.account.service.UserRoleService;
@Service
public class UserRoleServiceImpl implements UserRoleService {
	@Autowired
	private UserRoleDao userRoleDao;

	@Override
	public boolean insertUserRoleByUserRole(UserRole userRole) {
		int num=userRoleDao.insertUserRoleByUserRole(userRole);
		if(num>0) {
			return true;
		}
		return false;
	}

	@Override
	public void deleteUserRoleByUserId(int userId) {
		userRoleDao.deleteUserRoleByUserId(userId);
		
	}

}
