package com.longmao.demo.modules.account.service.impl;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.dao.RoleDao;
import com.longmao.demo.modules.account.entity.Role;
import com.longmao.demo.modules.account.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roledao;
	@Override
	public Role insertRoleByRole(Role role) {
		role.setCreateDate(new Date(System.currentTimeMillis()));
		roledao.insertRoleByRole(role);
		return role;
		
	}
	@Override
	public List<Role> selectRolesByUserId(int userId) {
		return Optional.ofNullable(roledao.selectRoleByUserId(userId)).orElse(Collections.emptyList());
	}
	@Override
	public Role updateRoleByRole(Role role) {
		roledao.updateRoleByRole(role);
		return roledao.selectRoleByRoleId(role.getRoleId());
	}
	@Override
	public boolean deleteRoleByRole(int roleId) {
		int num=roledao.deleteRoleByRoleId(roleId);
		if(num>0) {
			return true;
		}
		return false;
	}
	@Override
	public List<Role> selectRoles() {
		return Optional.ofNullable(roledao.selectRoles()).orElse(Collections.emptyList());
	}
	@Override
	public PageInfo<Role> selectRolesByPage(Integer currentPage, Integer pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Role> roles=Optional.ofNullable(roledao.selectRoles()).orElse(Collections.emptyList());
		return new PageInfo<Role>(roles);
	}
	@Override
	public Role selectRoleByRoleId(Integer roleId) {
		return roledao.selectRoleByRoleId(roleId);
	}
	@Override
	public List<Role> selectRoleByResourceId(Integer resoureId) {
		List<Role> roles=Optional.ofNullable(roledao.selectRoleByResourceId(resoureId)).orElse(Collections.emptyList());
		return roles;
	}

}
