package com.longmao.demo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.test.entity.Country;

@Repository
@Mapper
public interface CountryDao {

	/**
	 * 
	 * <p>
	 * 通过countryId查询country
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年11月27日
	 * @param int countryId
	 * @return Country
	 * 通过@results注解将一个ID映射到不同的实体上，@many中引用的是已有方法，全类名.方法名调用
	 */
	@Select("select * from m_country where country_id=#{countryId}")
	@Results(id = "countryResult", value = { @Result(column = "country_id", property = "countryId"),
			@Result(column = "country_id", property = "cities", javaType = List.class, 
			many = @Many(select = "com.longmao.demo.modules.test.dao.CityDao.selevtCitiesByCountryId")) })
	Country selectCountryByCountryId(int countryId);

	/**
	 * 
	 * <p>
	 * 通过countryName查询country
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年11月27日
	 * @param String countryName
	 * @return Country
	 * @resultMap注解引用已存在的映射关系 通过ID来引用
	 */
	@Select("select * from m_country where country_name=#{countryName}")
	@ResultMap(value = "countryResult")
	Country selectCountryByCountryName(String countryName);

}
