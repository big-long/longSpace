package com.longmao.demo.modules.test.contorller;

import org.apache.ibatis.annotations.Insert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longmao.demo.modules.test.entity.Country;
import com.longmao.demo.modules.test.service.CountryService;

@Controller
@RequestMapping("countryController")
public class CountryController {
	@Autowired
	private CountryService countryService;
	@PostMapping(value="country",consumes="application/json")
	@ResponseBody
	public Country insertCountryByCountry(@RequestBody Country country) {
		return countryService.insertCountryByCountry(country);
	}
	
	/**
	 * 
	 * <p>
	 * 	根据countryId查询Country
	 * </p>
	 * @PathVariable 将路径中的参数赋值给方法中的形参，只支持int
	 * @author zdl
	 * @Date 2019年11月27日
	 * @param int countryId
	 * @return Country
	 */
	@RequestMapping("/country/{countryId}")
	@ResponseBody
	public Country getCountry(@PathVariable int countryId) {
		return countryService.selectCountryByCountryId(countryId);
	}
	/**
	 * 
	 * <p>
	 * 	根据countryName查询Country
	 * </p>
	 * @author zdl
	 * @Date 2019年11月27日
	 * 	@RequestParam 将请求中的参数赋值给对应的变量
	 * @param String countryName
	 * @return Country
	 */
	@RequestMapping("/country")
	@ResponseBody
	public Country getCountryByName(@RequestParam String countryName) {
		return countryService.selectCountryByCountryName(countryName);
	}
}
