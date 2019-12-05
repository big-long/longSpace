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
	@Options(useGeneratedKeys = true, keyColumn = "role_id", keyProperty = "roleId")
	void insertRoleByRole(Role role);

	@Update("update m_role set role_name=#{roleName},name=#{name},create_date=#{createDate} where role_id=#{roleId}")
	void updateRoleByRole(Role role);

	@Delete("delete from m_role where role_id=#{roleId}")
	int deleteRoleByRoleId(int roleId);

	@Select("select * from m_role where role_id=#{roleId}")
	Role selectRoleByRoleId(int roleId);

	@Select("select * from m_role")
	List<Role> selectRoles();

	@Select("select m_role.role_id as role_id,m_role.role_name as role_name,m_role.name as name,m_role.create_date as createDate "
			+ "from m_user inner join m_user_role on m_user.user_id=m_user_role.user_id inner join m_role on m_user_role.role_id=m_role.role_id "
			+ "where m_user.user_id=#{userId}")
	List<Role> selectRoleByUserId(int userId);

	@Select("select m_role.role_id as role_id,m_role.role_name as role_name,m_role.name as name,m_role.create_date as createDate "
			+ "from m_resource inner join m_role_resource on m_resource.resource_id=m_role_resource.resource_id inner join m_role on m_role.role_id=m_role_resource.role_id "
			+ "where m_resource.resource_id=#{resourceId}")
	List<Role> selectRoleByResourceId(Integer resoureId);

}
