/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.List;
import org.hibernate.HibernateException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.form.UpdateLessonForm;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.BriefLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonVersionResponse;

/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class LessonServiceTest {

    private UserModel creatorModel;
    private UserModel learnerModel;
    @Autowired
    private LessonService lessonService;
    @Autowired
    private UserService userService;

    private Integer creatorId;
    private Integer learnerId;
    private Integer lessonId;
    private Integer version;
    private CreateLessonForm createForm;
    private UpdateLessonForm updateForm;
    private Integer courseId;

    public UserModel setUpUser(String userName) throws Exception {
        RegisterForm form = new RegisterForm();
        form.setUsername(userName);
        form.setEmail(userName);
        form.setPassword(userName);
        userService.saveUser(form);
        UserModel user = userService.findUserByUsername(userName);
        return user;
    }

    public CreateLessonResponse setUpLesson(Integer creatorId, Integer courseId) throws Exception {
        CreateLessonForm lessonForm = new CreateLessonForm();
        lessonForm.setCourseId(courseId);
        lessonForm.setDescription("des");
        lessonForm.setTitle("title");
        lessonForm.setReading("reading");
        lessonForm.setGrammar("grammar");
        lessonForm.setListening("listening");
        lessonForm.setPractice("practice");
        lessonForm.setConversation("conversation");
        lessonForm.setVocabulary("vocab");
        CreateLessonResponse createLesson = lessonService.createLesson(creatorId, lessonForm);
        return createLesson;
    }

    @Before
    public void setUp() throws Exception {
        creatorModel = setUpUser("user1");
        creatorId = creatorModel.getId();

        learnerModel = setUpUser("user2");
        learnerId = learnerModel.getId();
        courseId = 1;
        version = 1;

        createForm = new CreateLessonForm();

        CreateLessonResponse lesson = setUpLesson(creatorId, courseId);
        lessonId = lesson.getLessonId();

        updateForm = new UpdateLessonForm();
        updateForm.setDescription("des_update");
        updateForm.setLessonId(lessonId);
        updateForm.setCourseId(courseId);
    }

    @After
    public void tearDown() {
        try {
            userService.delete(creatorModel);
            userService.delete(learnerModel);
            lessonService.deleteLesson(lessonId);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testCreateLesson() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(creatorId, createForm);
        Assert.assertNotNull(createLesson);
    }

    @Test(expected = NullPointerException.class)
    public void testCreateLesson2() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(creatorId, null);
    }

    @Test(expected = HibernateException.class)
    public void testCreateLesson3() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(null, createForm);

    }

    @Test(expected = NullPointerException.class)
    public void testCreateLesson4() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(null, null);

    }

//    @Test
//    public void testCreateLesson5() throws Exception {
//        CreateLessonResponse createLesson = lessonService.createLesson(0, createForm);
//        Assert.assertNull(createLesson);
//    }
    @Test(expected = NullPointerException.class)
    public void testCreateLesson6() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(0, null);

    }

    @Test
    public void testGetLessonVersion() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(lessonId, version);
        Assert.assertNotNull(lessonVersion);
    }

    @Test
    public void testGetLessonVersion2() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(lessonId, -1);
        Assert.assertNull(lessonVersion);
    }

    @Test
    public void testGetLessonVersion3() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(lessonId, null);
        Assert.assertNull(lessonVersion);
    }

    @Test(expected = Exception.class)
    public void testGetLessonVersion4() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(-1, version);

    }

    @Test(expected = Exception.class)
    public void testGetLessonVersion5() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(-1, -1);

    }

    @Test(expected = Exception.class)
    public void testGetLessonVersion6() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(-1, null);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetLessonVersion7() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(null, version);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetLessonVersion8() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(null, -1);

    }

    @Test(expected = NumberFormatException.class)
    public void testGetLessonVersion9() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(null, null);

    }

    @Test
    public void testUpdateLesson() throws Exception {
        lessonService.updateLesson(creatorId, updateForm);
        GetLessonResponse lesson = lessonService.getLesson(lessonId, true);
        Assert.assertEquals("des_update", lesson.getDescription());
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateLesson2() throws Exception {
        lessonService.updateLesson(creatorId, null);
    }

//    @Test
//    public void testUpdateLesson3() throws Exception {
//        try {
//            lessonService.updateLesson(-1, updateForm);
//        } catch (Exception ex) {
//            Assert.assertEquals(new Exception("requester isn't creator"), ex);
//        }
//    }
    @Test(expected = NullPointerException.class)
    public void testUpdateLesson4() throws Exception {
        lessonService.updateLesson(-1, null);
    }

    @Test(expected = HibernateException.class)
    public void testUpdateLesson5() throws Exception {
        lessonService.updateLesson(null, updateForm);
    }

    @Test(expected = NullPointerException.class)
    public void testUpdateLesson6() throws Exception {
        lessonService.updateLesson(null, null);
    }

//    @Test
//    public void testUpdateLesson7() throws Exception {
//        try {
//            lessonService.updateLesson(learnerId, updateForm);
//        } catch (Exception ex) {
//            Assert.assertEquals(new Exception("requester isn't creator"), ex);
//        }
//    }
    @Test(expected = NullPointerException.class)
    public void testUpdateLesson8() throws Exception {
        lessonService.updateLesson(learnerId, null);
    }

//    @Test
//    public void testPublishLessonVersion() throws Exception {
//        try {
//            lessonService.publishLessonVersion(creatorId, lessonId);
//        } catch (Exception ex) {
//            Assert.assertEquals(new Exception("don't have version can be published"), ex);
//        }
//    }
    @Test(expected = Exception.class)
    public void testPublishLessonVersion2() throws Exception {
        lessonService.publishLessonVersion(creatorId, -1);
    }

    @Test(expected = Exception.class)
    public void testPublishLessonVersion3() throws Exception {
        lessonService.publishLessonVersion(creatorId, null);
    }

    @Test
    public void testPublishLessonVersion4() throws Exception {
        lessonService.publishLessonVersion(creatorId, lessonId);
        GetLessonResponse lesson = lessonService.getLesson(lessonId, false);
        Assert.assertEquals(Const.PUBLISHED, lesson.getState());
    }

    @Test
    public void testReportLesson() throws Exception {
        lessonService.reportLesson(learnerId, lessonId, "content");
        //TODO need assert
    }

    @Test(expected = Exception.class)
    public void testReportLesson2() throws Exception {
        lessonService.reportLesson(learnerId, -1, "content");
    }

    @Test(expected = Exception.class)
    public void testReportLesson3() throws Exception {
        lessonService.reportLesson(learnerId, null, "content");
    }

    @Test
    public void testGetLessonOfCourse() throws Exception {
        List<BriefLessonResponse> lessonsOfCourse = lessonService.getLessonsOfCourse(courseId);
        Assert.assertNotNull(lessonsOfCourse);
        Assert.assertNotEquals(0, lessonsOfCourse.size());
    }

    @Test
    public void testGetLessonOfCourse2() throws Exception {
        List<BriefLessonResponse> lessonsOfCourse = lessonService.getLessonsOfCourse(-1);
        Assert.assertNull(lessonsOfCourse);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetLessonOfCourse3() throws Exception {
        List<BriefLessonResponse> lessonsOfCourse = lessonService.getLessonsOfCourse(null);
        Assert.assertNull(lessonsOfCourse);
    }

    @Test
    public void testGetLessonOfCourse4() throws Exception {
        List<BriefLessonResponse> lessonsOfCourse = lessonService.getLessonsOfCourse(courseId + 1);
        Assert.assertNull(lessonsOfCourse);
    }

    @Test
    public void testGetAllLesson() throws Exception {
        List<BriefLessonResponse> lessonsOfCourse = lessonService.getAllLesson();
        Assert.assertNotNull(lessonsOfCourse);
    }

//    @Test
//    public void testGetLesson() throws Exception {
//        GetLessonResponse lesson = lessonService.getLesson(lessonId, false);
//        Assert.assertNotNull(lesson);
//    }
//
//    @Test
//    public void testGetLesson2() throws Exception {
//        GetLessonResponse lesson = lessonService.getLesson(-1, false);
//        Assert.assertNull(lesson);
//    }
//
//    @Test
//    public void testGetLesson3() throws Exception {
//        GetLessonResponse lesson = lessonService.getLesson(null, false);
//        Assert.assertNull(lesson);
//    }

    @Test
    public void testGetVersionOfLesson() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(lessonId, version);
        Assert.assertNotNull(lessonVersion);
    }

    @Test
    public void testGetVersionOfLesson2() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(-1, version);
        Assert.assertNull(lessonVersion);
    }

    @Test(expected = NumberFormatException.class)
    public void testGetVersionOfLesson3() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(null, version);
        Assert.assertNull(lessonVersion);
    }
}
