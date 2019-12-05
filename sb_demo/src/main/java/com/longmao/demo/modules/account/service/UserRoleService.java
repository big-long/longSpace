package com.longmao.demo.modules.account.service;

import com.longmao.demo.modules.account.entity.UserRole;

public interface UserRoleService {

	boolean insertUserRoleByUserRole(UserRole userRole);

	void deleteUserRoleByUserId(int userId);

}
