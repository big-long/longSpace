package com.longmao.demo.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.test.dao.CityDao;
import com.longmao.demo.modules.test.entity.City;
import com.longmao.demo.modules.test.service.CityService;

@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao cityDao;

	/**
	 * select cities by countryId and return List<City>
	 */
	@Override
	public List<City> selectCitiesByCountryId(int countryId) {
		return Optional.ofNullable(cityDao.selectCitiesByCountryId(countryId)).orElse(Collections.emptyList());
	}

	/**
	 * selct cities for page and return List<city>
	 */

	@Override
	public PageInfo<City> selectCitiesByPage(int currentPage, int pageSize) {
		PageHelper.startPage(currentPage, pageSize);
		List<City> cities = Optional.ofNullable(cityDao.selectCitiesByPage()).orElse(Collections.emptyList());
		return new PageInfo<City>(cities);
	}

	/**
	 * insert city by city and return city.
	 */

	@Override
	@Transactional
	public City insertCityBCity(City city) {
		cityDao.insertCityByCity(city);
		return city;
	}

	/**
	 * update city by city, select city by cityId and return city.
	 */
	@Override
	@Transactional
	public City updateCity(City city) {
		cityDao.updateCity(city);
		return cityDao.selectCityByCityId(city.getCityId());
	}

	/**
	 * delete city by cityId and return boolean
	 */
	@Override
	@Transactional
	public boolean deleteCity(int cityId) {
		int num = cityDao.deleteCity(cityId);
		if (num > 0) {
			return true;
		}
		return false;
	}

}
