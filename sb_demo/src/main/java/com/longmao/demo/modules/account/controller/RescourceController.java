package com.longmao.demo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.entity.Resource;
import com.longmao.demo.modules.account.service.ResourceService;
//
//@Controller
//@RequestMapping("/res")
public class RescourceController {

	@Autowired
	private ResourceService resourceService;

	@DeleteMapping(value = "deleteResource")
	@ResponseBody
	public boolean deleteResource(@RequestParam Integer resourceId) {
		return resourceService.deleteResource(resourceId);
	}

	@PutMapping(value = "editResource", consumes = "application/json")
	@ResponseBody
	public Resource updateResource(@RequestBody Resource resource) {
		return resourceService.updateResource(resource);
	}

	@PostMapping(value = "addResource", consumes = "application/x-www-form-urlencoded")
	@ResponseBody
	public Resource insertResource(@ModelAttribute Resource resource) {
		return resourceService.insertResourceByResource(resource);
	}

	@RequestMapping("resources/{currentPage}/{pageSize}")
	@ResponseBody
	public PageInfo<Resource> ResourceListByPage(@PathVariable int currentPage, @PathVariable int pageSize) {
		return resourceService.selectResourcesByPage(currentPage, pageSize);
	}

	@RequestMapping("/resources")
	@ResponseBody
	public List<Resource> resourceList() {
		return resourceService.selectResources();
	}

//	@RequestMapping("/detailResource")
//	@ResponseBody
//	public Resource detailResource(Resource resource) {
//		return resourceService.selectResourceByResource(resource);
//	}

}
