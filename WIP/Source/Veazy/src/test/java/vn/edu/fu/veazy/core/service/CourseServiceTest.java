package vn.edu.fu.veazy.core.service;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import vn.edu.fu.veazy.core.response.GetCourseResponse;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:testContext.xml" } )
public class CourseServiceTest {

    @Autowired
    private CourseService courseService;
    
    private List<GetCourseResponse> listCoursesStd;
    
    @Before
    public void setUp() {
        listCoursesStd = new ArrayList<GetCourseResponse>();
        // listCoursesStd.add(new GetCourseResponse("name", "desc"));
        // Dang le fix cung data se setup data o day
        // Nhung data gio dang ko quan tam (name, description) nen khoi setup
    }

    @After
    public void tearDown() {
        try {
            listCoursesStd = null;
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testGetCourse() throws Exception {
        List<GetCourseResponse> courses = courseService.getCourses();
        assertNotNull(courses);
    }

}
