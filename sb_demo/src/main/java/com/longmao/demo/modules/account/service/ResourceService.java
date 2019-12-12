package com.longmao.demo.modules.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.entity.Resource;

public interface ResourceService {

	List<Resource> selectResourcesByRoleId(Integer roleId);

	boolean deleteResource(Integer resourceId);

	Resource updateResource(Resource resource);

	Resource insertResourceByResource(Resource resource);

	PageInfo<Resource> selectResourcesByPage(int currentPage, int pageSize);

	List<Resource> selectResources();

	Resource selectResourceByResourceId(Integer resourceId);

//	Resource selectResourceByResource(Resource resource);
}
