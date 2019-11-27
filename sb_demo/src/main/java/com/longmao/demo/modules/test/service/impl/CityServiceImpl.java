package com.longmao.demo.modules.test.service.impl;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmao.demo.modules.test.dao.CityDao;
import com.longmao.demo.modules.test.entity.City;
import com.longmao.demo.modules.test.service.CityService;
@Service
public class CityServiceImpl implements CityService {
	@Autowired
	private CityDao citydao;
	@Override
	public List<City> selevtCitiesByCountryId(int countryId) {
		// TODO Auto-generated method stub
		return Optional.ofNullable(citydao.selevtCitiesByCountryId(countryId)).orElse(Collections.emptyList());
	}

}
