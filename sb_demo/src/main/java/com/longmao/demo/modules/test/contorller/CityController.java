package com.longmao.demo.modules.test.contorller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longmao.demo.modules.test.entity.City;
import com.longmao.demo.modules.test.service.CityService;
@Controller
@RequestMapping("cityController")
public class CityController {
	
	@Autowired
	private CityService cityService;
	/**
	 * 
	 * <p>
	 * 	通过countryId查询包含的cities
	 * </p>
	 * @author zdl
	 * @Date 2019年11月27日
	 * @param int countryId
	 * @return List<City>
	 */
	@RequestMapping("/cities/{countryId}")
	@ResponseBody
	public List<City> selectCitiesByCountryId(@PathVariable int countryId){
		return cityService.selevtCitiesByCountryId(countryId);
	}

}
