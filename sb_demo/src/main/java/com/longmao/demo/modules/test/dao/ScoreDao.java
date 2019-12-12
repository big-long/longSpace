package com.longmao.demo.modules.test.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import com.longmao.demo.modules.test.entity.Score;

@Repository
@Mapper
public interface ScoreDao {
	@Select("select id,province,level,discipline,year,score from score where province=#{province} and level=#{level} and discipline=#{discipline}")
	List<Score> selectScoreBYProcinceAndLevel(String province, String level,String discipline);
	@Select("select avg(score) from score where province=#{province} and level=#{level} and discipline=#{discipline}")
	int getAverage(String province, String level, String discipline);
	@Select("select id,province,level,discipline,year,score from score where level=#{level} and discipline=#{discipline}")
	List<Score> selectScoreByLevelAndDiscipline(String level, String discipline);
	

}
