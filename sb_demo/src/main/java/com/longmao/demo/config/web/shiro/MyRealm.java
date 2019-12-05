package com.longmao.demo.config.web.shiro;

import java.util.List;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.longmao.demo.modules.account.entity.Resource;
import com.longmao.demo.modules.account.entity.Role;
import com.longmao.demo.modules.account.entity.User;
import com.longmao.demo.modules.account.service.ResourceService;
import com.longmao.demo.modules.account.service.RoleService;
import com.longmao.demo.modules.account.service.UserService;
import com.longmao.demo.modules.common.vo.Result;

@Component
public class MyRealm extends AuthorizingRealm{
	@Autowired
	private UserService userService;
	@Autowired
	private RoleService roleService;
	@Autowired
	private ResourceService resourceService;

	@Override
	public String getName() {
		
		return "MyRealm";
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		String userName = (String)principals.getPrimaryPrincipal();
		Result userResult = userService.selectUserByUserName(userName);
		if(userResult.getObject()==null){
			return null;
		}
		SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
		User user=(User)userResult.getObject();
		List<Role> roles = roleService.selectRolesByUserId(user.getUserId());
	
		for (int i = 0; i < roles.size(); i++) {
			info.addRole(roles.get(i).getRoleName());
			List<Resource> resources = resourceService.selectResourcesByRoleId(roles.get(i).getRoleId());
			for (int j = 0; j < resources.size(); j++) {
				info.addStringPermission(resources.get(j).getPermission());
			}
		}
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		UsernamePasswordToken PasswordToken=(UsernamePasswordToken)token;
		String userName = PasswordToken.getUsername();
		Result userResult = userService.selectUserByUserName(userName);
		if(userResult.getObject()==null){
			throw new UnknownAccountException("The account do not exist.");
		}
		User user=(User)userResult.getObject();
		SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(user.getUserName(),user.getPassword(),getName());
		return info;
	}

}
