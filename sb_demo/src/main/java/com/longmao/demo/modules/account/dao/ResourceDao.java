package com.longmao.demo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.account.entity.Resource;

@Repository
@Mapper
public interface ResourceDao {
	@Insert("insert into m_resource (resource_url,resource_name,permission,create_date) values (#{resourceUrl},#{resourceName},#{permission},#{createDate})")
	@Options(useGeneratedKeys=true,keyColumn="resource_id",keyProperty="resourceId")
	void insertResourceByResource(Resource resource);

	@Update("update m_resource set resource_url=#{resourceUrl},resource_name=#{resourceName},permission=#{permission} where resource_id=#{resourceId}")
	void updateResourceByResource(Resource resource);

	@Delete("delete from m_resource where resource_id=#{resourceId}")
	int deleteResourceByResourceId(int resourceId);

	@Select("select * from m_resource where resource_id=#{resourceId}")
	Resource selectResourceByResourceId(int resourceId);
	
	@Select("select * from m_resource")
	List<Resource> selectResources();
@Select("select m_resource.resource_id as resource_id,m_resource.resource_url as resource_url,m_resource.resource_name as resource_name,m_resource.permission as permission,m_resource.create_date as create_date"
		+ " from m_role inner join m_role_resource on m_role.role_id=m_role_resource.role_id"
		+ " inner join m_resource on m_role_resource.resource_id=m_resource.resource_id"
		+ " where m_role.role_id=#{roleId}")
	List<Resource> selectResourcesByRoleId(Integer roleId);

}
