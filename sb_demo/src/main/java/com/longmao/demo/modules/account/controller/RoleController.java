package com.longmao.demo.modules.account.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.entity.Resource;
import com.longmao.demo.modules.account.entity.Role;
import com.longmao.demo.modules.account.entity.RoleResource;
import com.longmao.demo.modules.account.service.ResourceService;
import com.longmao.demo.modules.account.service.RoleResourceService;
import com.longmao.demo.modules.account.service.RoleService;

//@Controller
//@RequestMapping("/account")
public class RoleController {

	@Autowired
	private RoleService roleService;
	@Autowired
	private RoleResourceService roleResourceService;
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("")
	@ResponseBody
	public List<Resource> roleResource(@RequestParam Integer roleId){
		
		return resourceService.selectResourcesByRoleId(roleId);
	}
	
	@PostMapping(value="distributeResource",consumes="application/json")
	@ResponseBody
	public RoleResource distributeResource(@RequestBody RoleResource roleResource) {
		roleResourceService.deleteRoleResourceByRoleId(roleResource.getRoleId());
		return roleResourceService.insertRoleResource(roleResource);
	}
	
	@RequestMapping("detailRole")
	@ResponseBody
	public Role detailRole(@RequestParam Integer roleId) {
		return roleService.selectRoleByRoleId(roleId);
		
	}
	
	@RequestMapping("/roles/{currentPage}/{pageSize}")
	@ResponseBody
	public PageInfo<Role> roleListByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize) {
		return roleService.selectRolesByPage(currentPage,pageSize);
	}
	
	@RequestMapping("/roles")
	@ResponseBody
	public List<Role> roleList() {
		return roleService.selectRoles();
	}
	
	@DeleteMapping("/deleteRole")
	@ResponseBody
	public boolean deleteRole(@RequestParam Integer roleId) {
		return roleService.deleteRoleByRole(roleId);
	}
	
	@PutMapping(value="/editRole",consumes="application/json")
	@ResponseBody
	public Role updateRole(@RequestBody Role role) {
		return roleService.updateRoleByRole(role);
	}
	
	@PostMapping(value="/addRole",consumes="application/json")
	@ResponseBody
	public Role insertRole(@RequestBody Role role) {
		return roleService.insertRoleByRole(role);
	}
}
