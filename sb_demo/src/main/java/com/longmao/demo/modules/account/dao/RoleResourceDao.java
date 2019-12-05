package com.longmao.demo.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.account.entity.RoleResource;

@Repository
@Mapper
public interface RoleResourceDao {
	@Insert("insert into m_role_resource values (null,${roleId},${resourceId})")
	void insertRoleResourceByRoleResource(RoleResource roleResource);

	@Delete("delete from m_role_resource where role_id=#{roleId}")
	void deleteRoleResourceByRoleId(int roleId);
	@Delete("delete from m_role_resource where resource_id=#{resourceId}")
	void deleteRoleResourceByResourceId(int resourceId);

}
