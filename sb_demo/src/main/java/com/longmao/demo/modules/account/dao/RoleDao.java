package com.longmao.demo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.account.entity.Role;
@Repository
@Mapper
public interface RoleDao {
	@Insert("insert into m_role values (null,#{roleName},#{name},#{createDate})")
	@Options(useGeneratedKeys=true,keyColumn="role_id",keyProperty="roleId")
	void insertRoleByRole(Role role);
	
	@Update("update m_role set role_name=#{roleName},name=#{name},create_date=#{createDate} where role_id=#{roleId}")
	void updateRoleByRole(Role role);
	
	@Delete("delete from m_role where role_id=#{roleId}")
	void deleteRoleByRoleId(int roleId);
	
	@Select("select * from m_role where role_id=#{roleId}")
	Role selectRoleByRoleId(int roleId);
	
	@Select("select * from m_role")
	List<Role> selectRoles();
	

}
