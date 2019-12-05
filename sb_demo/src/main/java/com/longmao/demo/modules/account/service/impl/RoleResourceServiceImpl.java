package com.longmao.demo.modules.account.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmao.demo.modules.account.dao.RoleResourceDao;
import com.longmao.demo.modules.account.entity.RoleResource;
import com.longmao.demo.modules.account.service.RoleResourceService;
@Service
public class RoleResourceServiceImpl implements RoleResourceService {
	@Autowired
	private RoleResourceDao roleResourceDao;

	@Override
	public RoleResource insertRoleResource(RoleResource roleResource) {
		roleResourceDao.insertRoleResourceByRoleResource(roleResource);
		return roleResource;
	}

	@Override
	public void deleteRoleResourceByRoleId(int roleId) {
		roleResourceDao.deleteRoleResourceByRoleId(roleId);
		
	}

	@Override
	public void deleteRoleResourceByResouceceId(int resourceId) {
		roleResourceDao.deleteRoleResourceByResourceId(resourceId);
		
	}

}
