/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.edu.fu.veazy.core.form.CreateLessonForm;
import vn.edu.fu.veazy.core.response.CreateLessonResponse;
import vn.edu.fu.veazy.core.response.GetLessonVersionResponse;

/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class LessonServiceTest {

    @Autowired
    private LessonService lessonService;
    private Integer userId;
    private CreateLessonForm form;
    private Integer lessonId;
    private Integer version;

    @Before
    public void setUp() {
        //need initialed data
        userId = 0;
        form = new CreateLessonForm();
        lessonId = 0;
        version = 1;
    }

    @After
    public void tearDown() {
        try {
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testCreateLesson() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(userId, form);
        Assert.assertNotNull(createLesson);
    }

    @Test
    public void testCreateLesson2() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(userId, null);
        Assert.assertNull(createLesson);
    }

    @Test
    public void testCreateLesson3() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(null, form);
        Assert.assertNull(createLesson);
    }

    @Test
    public void testCreateLesson4() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(null, null);
        Assert.assertNull(createLesson);
    }

    @Test
    public void testCreateLesson5() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(0, form);
        Assert.assertNull(createLesson);
    }

    @Test
    public void testCreateLesson6() throws Exception {
        CreateLessonResponse createLesson = lessonService.createLesson(0, null);
        Assert.assertNull(createLesson);
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

    @Test
    public void testGetLessonVersion4() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(-1, version);
        Assert.assertNull(lessonVersion);
    }

    @Test
    public void testGetLessonVersion5() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(-1, -1);
        Assert.assertNull(lessonVersion);
    }

    @Test
    public void testGetLessonVersion6() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(-1, null);
        Assert.assertNull(lessonVersion);
    }
   
    @Test
    public void testGetLessonVersion7() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(null, version);
        Assert.assertNull(lessonVersion);
    }

    @Test
    public void testGetLessonVersion8() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(null, -1);
        Assert.assertNull(lessonVersion);
    }

    @Test
    public void testGetLessonVersion9() throws Exception {
        GetLessonVersionResponse lessonVersion = lessonService.getLessonVersion(null, null);
        Assert.assertNull(lessonVersion);
    }
}
