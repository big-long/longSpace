package com.longmao.demo.modules.test.service;

import java.util.List;

import com.longmao.demo.modules.test.entity.City;

public interface CityService {
	List<City> selevtCitiesByCountryId(int countryId);
}
