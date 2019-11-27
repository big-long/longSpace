package com.longmao.demo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.test.entity.City;

@Repository
@Mapper
public interface CityDao {
	/**
	 * 
	 * <p>
	 * 	通过countryId返回city集合
	 * </p>
	 * @author zdl
	 * @Date 2019年11月27日
	 * @param int countryId
	 * @return list<City>
	 */
	@Select("select * from m_city where country_id=#{contryId}")
	List<City> selevtCitiesByCountryId(int countryId);
}
