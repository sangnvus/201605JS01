package vn.edu.fu.veazy.core.service;

import java.util.List;
import java.util.Vector;

import org.springframework.beans.factory.annotation.Autowired;

import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.model.CourseModel;
import vn.edu.fu.veazy.core.response.GetCourseResponse;

public class CourseServiceImpl implements CourseService{
	@Autowired
	private GenericDao<CourseModel, String> courseDao;

	@Override
	public List<GetCourseResponse> getCourses() throws Exception {
		List<CourseModel> listCourse = courseDao.getAll();
		List<GetCourseResponse> listResult = new Vector<GetCourseResponse>();
		for (CourseModel model : listCourse) {
			GetCourseResponse result = new GetCourseResponse(model.getName(), model.getIndex(), model.getDescription());
			listResult.add(result);
		}
		return listResult;
	}
}
