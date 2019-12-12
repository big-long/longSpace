package com.longmao.demo.modules.test.service.impl;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.longmao.demo.modules.test.dao.ScoreDao;
import com.longmao.demo.modules.test.entity.Average;
import com.longmao.demo.modules.test.entity.Score;
import com.longmao.demo.modules.test.service.ScoreService;

@Service
public class ScoreSerciceImpl implements ScoreService {
	@Autowired
	private ScoreDao scoreDao;

	@Override
	public HashMap<String, List> getYearAndScore(String provinces, String level,String discipline) {
		HashMap<String, List> map=new HashMap<String, List>();
		List<Integer> scores=new LinkedList<Integer>();
		List<Integer> years=new LinkedList<Integer>();
		List<Score> scores_db=scoreDao.selectScoreBYProcinceAndLevel(provinces,level,discipline);
		for (int i = scores_db.size()-1; i >=0; i--) {
			years.add(scores_db.get(i).getYear());
			scores.add(scores_db.get(i).getScore());
		}
		map.put("year", years);
		map.put("score", scores);
		return map;
	}

	@Override
	public List<Average> getAverages(String[] provinces, String level, String discipline) {
		List<Average> averages=new LinkedList<Average>();
		Average avge=null;
		for (int i = 0; i < provinces.length; i++) {
			int avg=scoreDao.getAverage(provinces[i],level,discipline);
			avge=new Average(avg, provinces[i]);
			averages.add(avge);
		}
		
//		List<Score> scores=scoreDao.selectScoreByLevelAndDiscipline(level,discipline);
//		for (int i = 0; i < scores.size(); i++) {
//			
//		}
		return averages;
	}

}
