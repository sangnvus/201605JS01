package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.response.GetCourseResponse;
import vn.edu.fu.veazy.core.response.StatsCourseAvgResponse;
import vn.edu.fu.veazy.core.response.StatsLastExamResponse;
import vn.edu.fu.veazy.core.response.StatsSkillAvgResponse;

public interface StatsService {
	StatsSkillAvgResponse getSkillAvg(Integer userId) throws Exception;
    List<StatsLastExamResponse> getLastExamResult(Integer userId, Integer number) throws Exception;
    List<StatsCourseAvgResponse> getCourseAvg(Integer userId) throws Exception;
}
