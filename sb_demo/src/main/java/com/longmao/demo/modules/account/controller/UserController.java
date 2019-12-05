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
import com.longmao.demo.modules.account.entity.Role;
import com.longmao.demo.modules.account.entity.User;
import com.longmao.demo.modules.account.entity.UserRole;
import com.longmao.demo.modules.account.service.RoleService;
import com.longmao.demo.modules.account.service.UserRoleService;
import com.longmao.demo.modules.account.service.UserService;
import com.longmao.demo.modules.common.vo.Result;

//@Controller
//@RequestMapping("/account")
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	
	@RequestMapping(value="")
	@ResponseBody
	public List<Role> userRole(@RequestParam int userId){
		return roleService.selectRolesByUserId(userId);
	}
	
	@PostMapping(value="/distributeRole",consumes="application/json")
	@ResponseBody
	public boolean distributeRole(@RequestBody UserRole userRole) {
		userRoleService.deleteUserRoleByUserId(userRole.getUserId());
		return userRoleService.insertUserRoleByUserRole(userRole);
	}
	
	@DeleteMapping(value="/deleteUser")
	@ResponseBody
	public boolean deleteUser(@RequestParam Integer userId) {
		return userService.deleteUser(userId);
	}
	@PutMapping(value="/editUser",consumes="application/json")
	@ResponseBody
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}

	@PostMapping(value="/register",consumes="application/x-www-form-urlencoded")
	@ResponseBody
	public Result insertUser(@ModelAttribute User user) {
		return userService.insertUserByUser(user);
	}
	@RequestMapping("/list/{currentPage}/{pageSize}")
	@ResponseBody
	public PageInfo<User> userListByPage(@PathVariable int currentPage,@PathVariable int pageSize) {
		return userService.selectUsersByPage(currentPage,pageSize);
	}
	
	@RequestMapping("/users")
	@ResponseBody
	public List<User> userList() {
		return userService.selectUsers();
	}
	
//	@RequestMapping("userDetail")
//	@ResponseBody
	public Result detailUser(@RequestParam Integer userId) {
		return userService.selectUserByUserId(userId);
	}
	@RequestMapping("/userDetail")
	@ResponseBody
	public Result selectUserByUserName(@RequestParam String userName) {
		return userService.selectUserByUserName(userName);
	}
	
}
