package com.longmao.demo.modules.account.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.dao.ResourceDao;
import com.longmao.demo.modules.account.entity.Resource;
import com.longmao.demo.modules.account.service.ResourceService;

@Service
public class ResourceServiceImpl implements ResourceService {
	@Autowired
	private ResourceDao resourceDao;

	@Override
	public List<Resource> selectResourcesByRoleId(Integer roleId) {
		// TODO Auto-generated method stub
		return resourceDao.selectResourcesByRoleId(roleId);
	}

	@Override
	public boolean deleteResource(Integer resourceId) {
		int num = resourceDao.deleteResourceByResourceId(resourceId);
		if (num > 0) {
			return true;
		}
		return false;
	}

	@Override
	public Resource updateResource(Resource resource) {
		resourceDao.updateResourceByResource(resource);
		return resourceDao.selectResourceByResourceId(resource.getResourceId());
		
	}

	@Override
	public Resource insertResourceByResource(Resource resource) {
		resourceDao.insertResourceByResource(resource);
		return resource;
	}

	@Override
	public PageInfo<Resource> selectResourcesByPage(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<Resource> resources =Optional.ofNullable(resourceDao.selectResources()).orElse(Collections.emptyList());
		return new PageInfo<Resource>(resources);
	}

	@Override
	public List<Resource> selectResources() {
		return Optional.ofNullable(resourceDao.selectResources()).orElse(Collections.emptyList());
	}

	@Override
	public Resource selectResourceByResourceId(Integer resourceId) {
		return resourceDao.selectResourceByResourceId(resourceId);
	}

}
