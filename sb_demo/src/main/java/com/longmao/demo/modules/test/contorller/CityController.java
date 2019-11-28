package com.longmao.demo.modules.test.contorller;

import java.util.List;

import org.hibernate.validator.constraints.ParameterScriptAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageInfo;
import com.longmao.demo.modules.test.entity.City;
import com.longmao.demo.modules.test.service.CityService;

@Controller
@RequestMapping("cityController")
public class CityController {

	@Autowired
	private CityService cityService;
	@RequestMapping("page")
	public String pageTest(ModelMap modelMap) {
		modelMap.addAttribute("template", "test/index");
		return "index";
	}

	/**
	 * 
	 * <p>
	 * delete city by cityId
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年11月28日
	 * @param int cityId
	 * @return boolean
	 */
	@DeleteMapping("/city/{cityId}")
	@ResponseBody
	public boolean deleteCity(@PathVariable int cityId) {
		return cityService.deleteCity(cityId);
	}

	/**
	 * 
	 * <p>
	 * update city by city and return updated city info
	 * 接受数据格式是form表单，通过@ModelAttribute注解将参数值赋值给方法的形参
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年11月28日
	 * @param City city
	 * @return city form dataSource
	 */
	@PutMapping(value = "city", consumes = "application/x-www-form-urlencoded")
	@ResponseBody
	public City updateCity(@ModelAttribute City city) {
		return cityService.updateCity(city);
	}

	/**
	 * 
	 * <p>
	 * insert city by object city and return city with cityId get form dataSource
	 * 接受数据格式为json格式，通过@requestBody注解将参数值赋值给方法的形参
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年11月28日
	 * @param City city
	 * @return city (get cityId from dataSource)
	 */
	@PostMapping(value = "/city", consumes = "application/json")
	@ResponseBody
	public City insertCityByCity(@RequestBody City city) {
		return cityService.insertCityBCity(city);

	}

	/**
	 * 
	 * <p>
	 * 通过pagehelper提供的封装类，直接将pageInfo 返回给页面即可完成分页，所需参数为当前页码和页面展示数量
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年11月28日
	 * @param int currentPage 当前页码
	 * @param int pageSize 页面展示数量
	 * @return PageInfo<City>
	 */
	@RequestMapping("/cities/{currentPage}/{pageSize}")
	@ResponseBody
	public PageInfo<City> selectCitiesByPage(@PathVariable int currentPage, @PathVariable int pageSize) {
		return cityService.selectCitiesByPage(currentPage, pageSize);
	}

	/**
	 * 
	 * <p>
	 * 通过countryId查询包含的cities
	 * </p>
	 * 
	 * @author zdl
	 * @Date 2019年11月27日
	 * @param int countryId
	 * @return List<City>
	 */
	@RequestMapping("/cities/{countryId}")
	@ResponseBody
	public List<City> selectCitiesByCountryId(@PathVariable int countryId) {
		return cityService.selectCitiesByCountryId(countryId);
	}

}
