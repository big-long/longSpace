package com.longmao.demo.modules.test.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.test.entity.City;

public interface CityService {

	List<City> selectCitiesByCountryId(int countryId);

	PageInfo<City> selectCitiesByPage(int currentPage, int pageSize);

	City insertCityBCity(City city);

	City updateCity(City city);

	boolean deleteCity(int cityId);
}
