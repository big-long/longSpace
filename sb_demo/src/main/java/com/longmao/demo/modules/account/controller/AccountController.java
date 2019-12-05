package com.longmao.demo.modules.account.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.account.entity.Resource;
import com.longmao.demo.modules.account.entity.Role;
import com.longmao.demo.modules.account.entity.RoleResource;
import com.longmao.demo.modules.account.entity.User;
import com.longmao.demo.modules.account.entity.UserRole;
import com.longmao.demo.modules.account.service.ResourceService;
import com.longmao.demo.modules.account.service.RoleResourceService;
import com.longmao.demo.modules.account.service.RoleService;
import com.longmao.demo.modules.account.service.UserRoleService;
import com.longmao.demo.modules.account.service.UserService;
import com.longmao.demo.modules.common.annotation.PermissionName;
import com.longmao.demo.modules.common.utils.PermissionUtil;
import com.longmao.demo.modules.common.vo.Result;
import com.longmao.demo.utils.VerifyCodeUtil;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private UserRoleService userRoleService;
	@Autowired
	private RoleResourceService roleResourceService;
	@Autowired
	private ResourceService resourceService;
	
	@RequestMapping("/loadResource")
	@PermissionName("加载权限")
	@ResponseBody
	@RequiresRoles("admin")
	@RequiresPermissions("loadResource")
	public Result loadPermission() {
		PermissionUtil.loadPermission();
		return new Result(200,"success");
	}
	@RequestMapping("/checkUserName")
	@ResponseBody
	public Result isExist(String userName) {
		return userService.selectUserByUserName(userName);
	}
	/**
	 * 
	 * <p>
	 * 	生成验证码
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param session
	 * @param response
	 */
	@RequestMapping("/captcha.jpg")
	public void getCaptcha(HttpSession session, HttpServletResponse response) {
		String code = VerifyCodeUtil.generateVerifyCode(4); // 四位随机编码
		session.setAttribute(session.getId(), code); // 放到session
		try {
			VerifyCodeUtil.outputImage(100, 40, response.getOutputStream(), code);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // 生成图片流

	}
	/**
	 * 
	 * <p>
	 * 	登出
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/logout")
	public String logout(ModelMap modelMap) {
		SecurityUtils.getSubject().logout();
		modelMap.addAttribute("template", "account/login");
		return "indexSimple";

	}
	/**
	 * 
	 * <p>
	 * 	跳转到注册页面
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @return
	 */
	@RequestMapping("/register")
	public String register() {
		return "indexSimple";

	}
	/**
	 * 
	 * <p>
	 * 跳转到主页面
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @return
	 */
	@RequestMapping("/dashboard")
	public String dashboard() {
		return "index";

	}
	/**
	 * 
	 * <p>
	 * 	跳转到登陆界面
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @return
	 */
	@RequestMapping("/login")
	public String login() {
		return "indexSimple";

	}
	/**
	 * 
	 * <p>
	 * 	登陆
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param user
	 * @param session
	 * @param captcha
	 * @param rememberMe
	 * @return
	 */
	@PostMapping("/doLogin")
	@ResponseBody
	public Result doLogin(@ModelAttribute User user, HttpSession session, String captcha, boolean rememberMe) {
		Result result = new Result(200, "success");

		String attribute = (String) session.getAttribute(session.getId());
		if (null == captcha || !captcha.equalsIgnoreCase(attribute)) {
			result.setStatus(500);
			result.setMessage("The captcha do not right");
			return result;
		}

		Subject subject = SecurityUtils.getSubject();
		try {
			Md5Hash md5Hash = new Md5Hash(user.getPassword(), user.getUserName(), 10);
			UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(user.getUserName(),
					md5Hash.toString());
			usernamePasswordToken.setRememberMe(rememberMe);
			subject.login(usernamePasswordToken);
			subject.checkRoles();
		} catch (IncorrectCredentialsException e) {
			result = new Result(500, "The password do not right");
		} catch (AuthenticationException e) {
			result = new Result(500, e.getMessage());
		} catch (AuthorizationException e) {
			result = new Result(500, e.getMessage());
		}

		return result;
	}
	/**
	 * 
	 * <p>
	 * 	根据用户的Id查询对应的角色
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param userId
	 * @return
	 */
	@RequestMapping(value = "/user/role/{userId}")
	@RequiresRoles(value = { "admin", "manager" }, logical = Logical.OR)
	@ResponseBody
	@RequiresPermissions("checkRoleByUserID")
	@PermissionName("查询角色")
	public List<Role> userRole(@PathVariable int userId) {
		return roleService.selectRolesByUserId(userId);
	}
	/**
	 * 
	 * <p>
	 * 	为用户分配角色
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param userId
	 * @param arr
	 * @param userRole
	 * @return
	 */
	@PostMapping(value = "/editUser")
	@RequiresRoles(value = "admin")
	@ResponseBody
	@RequiresPermissions("userEdit")
	@PermissionName("修改用户")
	public Result distributeRole(Integer userId, String[] arr, UserRole userRole) {
		userRoleService.deleteUserRoleByUserId(userId);
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				userRole.setRoleId(Integer.parseInt(arr[i]));
				userRoleService.insertUserRoleByUserRole(userRole);
			}
		}
		return new Result(200, "success");
	}
	/**
	 * 
	 * <p>	
	 * 	删除用户
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param userId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/deleteUser/{userId}")
	@RequiresPermissions("userDelete")
	@RequiresRoles(value = "admin")
	@ResponseBody
	@PermissionName("删除用户")
	public Result deleteUser(@PathVariable Integer userId, ModelMap modelMap) {
		userService.deleteUser(userId);
		return new Result(200, "success");
	}
	/**
	 * 
	 * <p>
	 * 	注册
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param user
	 * @return
	 */
	@PostMapping(value = "/doRegister")
	@ResponseBody
	public Result insertUser(User user) {
		return userService.insertUserByUser(user);
	}
	/**
	 * 
	 * <p>
	 * 	返回用户的页面信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param currentPage	当前页
	 * @param pageSize	页面大小
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/users/{currentPage}/{pageSize}")
	@RequiresRoles(value = { "admin", "manager" }, logical = Logical.OR)
	@RequiresPermissions("userList")
	@PermissionName("用户列表")
	public String userListByPage(@PathVariable int currentPage, @PathVariable int pageSize, ModelMap modelMap) {
		PageInfo<User> pageUsers = userService.selectUsersByPage(currentPage, pageSize);
		List<Role> roles = roleService.selectRoles();
		modelMap.addAttribute("roles", roles);
		modelMap.addAttribute("pageUsers", pageUsers);
		modelMap.addAttribute("template", "account/users");
		return "index";
	}
	/**
	 * 	
	 * <p>
	 * 	查询数据库中某角色对应的资源
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param roleId
	 * @return
	 */
	@RequestMapping("/role/resource")
	@ResponseBody
	@RequiresRoles(value = "admin")
	@RequiresPermissions("checkResourceByRoleId")
	@PermissionName("查询资源")
	public List<Resource> roleResource(@RequestParam Integer roleId) {

		return resourceService.selectResourcesByRoleId(roleId);
	}
	/**
	 * 
	 * <p>
	 * 	资源页面分配角色，新增角色资源表信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param resourceId
	 * @return
	 */
	@RequestMapping(value = "roles/resource/{resourceId}")
	@ResponseBody
	@RequiresRoles(value = "admin")
	@RequiresPermissions("roleResourceAdd")
	@PermissionName("新增角色资源")
	public List<Role> distributeResource(@PathVariable Integer resourceId) {
		List<Role> roles = roleService.selectRoleByResourceId(resourceId);
		return roles;

	}

	/**
	 * 
	 * <p>
	 * 	返回角色的分页信息
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param currentPage	当前页码
	 * @param pageSize	页面大小
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/roles/{currentPage}/{pageSize}")
	@RequiresPermissions("roleList")
	@PermissionName("角色列表")
	public String roleListByPage(@PathVariable Integer currentPage, @PathVariable Integer pageSize, ModelMap modelMap) {
		PageInfo<Role> pageRoles = roleService.selectRolesByPage(currentPage, pageSize);
		modelMap.addAttribute("pageRoles", pageRoles);
		modelMap.addAttribute("template", "account/roles");
		return "index";
	}


	/**
	 * 
	 * <p>
	 * 	删除角色
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param roleId
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/deleteRole/{roleId}")
	@RequiresRoles(value = "admin")
	@ResponseBody
	@RequiresPermissions("roleDelete")
	@PermissionName("删除角色")
	public Result deleteRole(@PathVariable Integer roleId, ModelMap modelMap) {
		roleService.deleteRoleByRole(roleId);
		return new Result(200, "success");
	}
	/**
	 * 
	 * <p>
	 * 	新增或者编辑角色
	 * </p>
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param role	角色信息
	 * @return
	 */
	@RequestMapping(value = "/editRole")
	@ResponseBody
	@RequiresRoles(value = "admin")
	@RequiresPermissions("roleEdit")
	@PermissionName("编辑角色")
	public Result updateRole(Role role) {
		Result result = new Result(200, "success");
		if (role.getRoleId() == 0) {
			result.setObject(roleService.insertRoleByRole(role));
			return result;
		}
		result.setObject(roleService.updateRoleByRole(role));
		return result;
	}

	/**
	 * 
	 * <p>
	 * 删除资源
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param resourceId 资源Id
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/deleteResource/{resourceId}")
	@RequiresRoles(value = "admin")
	@ResponseBody
	@RequiresPermissions("resourceDelete")
	@PermissionName("删除资源")
	public Result deleteResource(@PathVariable Integer resourceId, ModelMap modelMap) {
		resourceService.deleteResource(resourceId);
		return new Result(200, "success");
	}

	/**
	 * 
	 * <p>
	 * 新增或者修改资源
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param resource 资源
	 * @param arr      资源对应的角色
	 * @return
	 */
	@RequestMapping(value = "/editResource")
	@ResponseBody
	@RequiresRoles(value = "admin")
	@RequiresPermissions("resourceEdit")
	@PermissionName("编辑资源")
	public Result updateResource(Resource resource, String[] arr) {
		if (resource.getResourceId() == 0) {
			resourceService.insertResourceByResource(resource);
		} else {
			resource = resourceService.updateResource(resource);
			roleResourceService.deleteRoleResourceByResouceceId(resource.getResourceId());
		}
		RoleResource roleResource = new RoleResource();
		roleResource.setResourceId(resource.getResourceId());
		if (arr != null) {
			for (int i = 0; i < arr.length; i++) {
				roleResource.setRoleId(Integer.parseInt(arr[i]));
				roleResourceService.insertRoleResource(roleResource);
			}
		}
		return new Result(200, "success");
	}

	/**
	 * 
	 * <p>
	 * 返回资源的分页信息
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年12月4日
	 * @param currentPage 当前页面
	 * @param pageSize    页面显示资源数
	 * @param modelMap
	 * @return
	 */
	@RequestMapping("/resources/{currentPage}/{pageSize}")
	@RequiresRoles(value = { "admin", "manager" }, logical = Logical.OR)
	@RequiresPermissions("resourcesList")
	@PermissionName("资源列表")
	public String ResourceListByPage(@PathVariable int currentPage, @PathVariable int pageSize, ModelMap modelMap) {
		PageInfo<Resource> pageResources = resourceService.selectResourcesByPage(currentPage, pageSize);
		List<Role> roles = roleService.selectRoles();
		modelMap.addAttribute("roles", roles);
		modelMap.addAttribute("pageResources", pageResources);
		modelMap.addAttribute("template", "account/resources");
		return "index";
	}

}
