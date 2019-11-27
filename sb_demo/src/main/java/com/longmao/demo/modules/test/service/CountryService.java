package com.longmao.demo.modules.test.service;

import com.longmao.demo.modules.test.entity.Country;

public interface CountryService {

	Country selectCountryByCountryId(int countryId);

	Country selectCountryByCountryName(String countryName);

}
