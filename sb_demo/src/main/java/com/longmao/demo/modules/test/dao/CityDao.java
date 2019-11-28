package com.longmao.demo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.test.entity.City;

@Repository
@Mapper
public interface CityDao {

	@Select("select * from m_city where country_id=#{contryId}")
	List<City> selectCitiesByCountryId(int countryId);

	@Select("select * from m_city")
	List<City> selectCitiesByPage();

	@Insert("insert into m_city (city_name,local_city_name,country_id,date_created) values (#{cityName},#{localCityName},#{countryId},#{dateCreated})")
	@Options(useGeneratedKeys = true, keyColumn = "city_id", keyProperty = "cityId")
	void insertCityByCity(City city);

	@Update("update m_city set city_name=#{cityName},district=#{district} where city_id=#{cityId}")
	void updateCity(City city);

	@Select("select * from m_city where city_id=#{cityId}")
	City selectCityByCityId(int cityId);

	@Delete("delete from m_city where city_id=#{cityId}")
	int deleteCity(int cityId);
}
