package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.response.GetCourseResponse;

public interface CourseService {
	/**
	 * 全部のレベルをとる
	 * @return　全部のレベル
	 * @throws Exception
	 */
	List<GetCourseResponse> getCourses() throws Exception;
}
