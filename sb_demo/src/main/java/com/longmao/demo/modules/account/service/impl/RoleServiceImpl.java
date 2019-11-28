package com.longmao.demo.modules.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmao.demo.modules.account.dao.RoleDao;
import com.longmao.demo.modules.account.entity.Role;
import com.longmao.demo.modules.account.service.RoleService;
@Service
public class RoleServiceImpl implements RoleService {
	@Autowired
	private RoleDao roleMapper;
	@Override
	public Role insertRoleByRole(Role role) {
		roleMapper.insertRoleByRole(role);
		return role;
		
	}

}
