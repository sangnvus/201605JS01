package vn.edu.fu.veazy.core.service;

import java.util.List;

import vn.edu.fu.veazy.core.response.GetCourseResponse;

public interface CourseService {
	List<GetCourseResponse> getCourses() throws Exception;
}
