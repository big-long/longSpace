package com.longmao.demo.modules.account.dao;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.account.entity.UserRole;

@Repository
@Mapper
public interface UserRoleDao {
	@Insert("insert into m_user_role values(null, #{userId}, #{roleId})")
	int insertUserRoleByUserRole(UserRole userRole);
	
	@Delete("delete from m_user_role where user_id=#{userId}")
	void deleteUserRoleByUserId(int userId);
	
}
