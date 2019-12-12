package com.longmao.demo.modules.test.contorller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.longmao.demo.modules.test.entity.Average;
import com.longmao.demo.modules.test.service.CityService;
import com.longmao.demo.modules.test.service.ScoreService;


@Controller
@RequestMapping("/test")
public class TestCon {
	@Autowired
	private ScoreService scoreService;
	/**
	 * 
	 * <p>
	 * 	返回柱状图和折线图的数据
	 * </p>	
	 * @author zdl
	 * @Date 2019年12月12日
	 * @param province 省
	 * @param level	一本或二本
	 * @param discipline 理科或文科
	 * @return
	 */
	
	@RequestMapping("/getData")
	@ResponseBody
	public HashMap<String,List> echarts(String province,String level,String discipline){
//		HashMap<String, List> map=new HashMap<String, List>();
		return scoreService.getYearAndScore(province,level,discipline);
		
	}
	/**
	 * 
	 * <p>
	 * 	返回饼图的数据
	 * </p>	
	 * @author zdl
	 * @Date 2019年12月12日
	 * @param provinces 省份的数组
	 * @param level	一本或二本
	 * @param discipline 理科或文科
	 * @return
	 */
	@RequestMapping("/getAverage")
	@ResponseBody
	public List<Average> average(String[] provinces,String level,String discipline){
//		HashMap<String, List> map=new HashMap<String, List>();
		return scoreService.getAverages(provinces,level,discipline);
		
	}
	/**
	 * 
	 * <p>
	 * 	返回到页面 拦截器里面设置了template的值
	 * </p>
	 * @author zdl
	 * @Date 2019年12月12日
	 * @return
	 */
	@RequestMapping("/toPage")
	public String toPage(){
//		HashMap<String, List> map=new HashMap<String, List>();
		return "indexSimple";
		
	}
}
 