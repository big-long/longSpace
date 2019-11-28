package com.longmao.demo.modules.account.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.account.entity.Role;
@Repository
@Mapper
public interface RoleDao {
	@Insert("insert into m_role (role_name,name,create_date) values (#{roleName},#{name},#{createDate})")
	void insertRoleByRole(Role role);
}
