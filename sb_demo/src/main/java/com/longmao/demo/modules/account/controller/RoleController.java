package com.longmao.demo.modules.account.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longmao.demo.modules.account.entity.Role;
import com.longmao.demo.modules.account.service.RoleService;

@Controller
public class RoleController {

	@Autowired
	private RoleService roleService;
	@PostMapping(value="/role",consumes="application/json")
	@ResponseBody
	public Role insertRole(Role role) {
		return roleService.insertRoleByRole(role);
	}
}
