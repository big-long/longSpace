package com.longmao.demo.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.entity.Role;

public interface RoleService {
	Role insertRoleByRole(Role role);

	List<Role> selectRolesByUserId(int userId);

	Role updateRoleByRole(Role role);

	boolean deleteRoleByRole(int roleId);

	List<Role> selectRoles();

	PageInfo<Role> selectRolesByPage(Integer currentPage, Integer pageSize);

	Role selectRoleByRoleId(Integer roleId);

	List<Role> selectRoleByResourceId(Integer resoureId);
}
