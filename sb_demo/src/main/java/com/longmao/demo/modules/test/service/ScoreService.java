package com.longmao.demo.modules.test.service;

import java.util.HashMap;
import java.util.List;

import com.longmao.demo.modules.test.entity.Average;

public interface ScoreService {

	HashMap<String, List> getYearAndScore(String province, String level,String discipline);

	List<Average> getAverages(String[] provinces, String level, String discipline);

}
