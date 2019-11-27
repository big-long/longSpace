package com.longmao.demo.modules.test.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmao.demo.modules.test.dao.CountryDao;
import com.longmao.demo.modules.test.entity.Country;
import com.longmao.demo.modules.test.service.CountryService;

@Service
public class CountryServiceImpl implements CountryService {
@Autowired
private CountryDao countryDao;

	@Override
	public Country selectCountryByCountryId(int countryId) {
		return countryDao.selectCountryByCountryId(countryId);
	}
	
	@Override
	public Country selectCountryByCountryName(String countryName) {
		return countryDao.selectCountryByCountryName(countryName);
	}

}
