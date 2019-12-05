package com.longmao.demo.modules.account.service;

import com.longmao.demo.modules.account.entity.RoleResource;

public interface RoleResourceService {

	RoleResource insertRoleResource(RoleResource roleResource);

	void deleteRoleResourceByRoleId(int roleId);

	void deleteRoleResourceByResouceceId(int resourceId);

}
