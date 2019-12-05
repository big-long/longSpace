package com.longmao.demo.modules.account.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.account.entity.User;

@Repository
@Mapper
public interface UserDao {
	@Insert("insert into m_user values (null,#{userName},#{password},#{createDate})")
	@Options(useGeneratedKeys = true, keyColumn = "user_id", keyProperty = "userId")
	void insertUserByUser(User user);

	@Update("update m_user set user_name=#{userName},password=#{password},create_date=#{createDate} where user_id=#{userId}")
	void updateUserByUser(User user);

	@Delete("delete from m_user where user_id=#{userId}")
	int deleteUserByUserId(int userId);

	@Select("select * from m_user where user_id=#{userId}")
	User selectUserByUserId(int userId);

	@Select("select * from m_user")
	List<User> selectUsers();

	@Select("select count(user_id) from m_user")
	int selectUserNumber();

	@Select("select * from m_user where user_name=#{userName}")
	List<User> selectUsersByUsername(String username);

	@Select("select * from m_user where user_name=#{userName}")
	User selectUserByUserName(String userName);

	@Select("select * from m_user where user_name=#{userName} and password=#{password}")
	User selectUserByUserNameAndPassword(String userName, String password);
}
