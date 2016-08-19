/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vn.edu.fu.veazy.core.service;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.model.ExamModel;
import vn.edu.fu.veazy.core.model.UserModel;

/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:testContext.xml" } )
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class ExamServiceTest {

    private UserModel userModel;
    private UserModel userModel2;
    private ExamModel examModel;

    @Autowired
    private UserService userService;
    @Autowired
    private ExamService examService;

    private Integer userId;
    private Integer zeroExamUserId;
    private Integer examId;
    private ExamModel exam;
    

    private UserModel setUpUser(String userName) throws Exception {
        RegisterForm form = new RegisterForm();
        form.setUsername(userName);
        form.setEmail(userName);
        form.setPassword(userName);
        userService.saveUser(form);
        UserModel user = userService.findUserByUsername(userName);
        return user;
    }

    private ExamModel setUpExam(Integer userId) throws Exception {
        ExamModel exam = new ExamModel();
        exam.setUserId(userId);
        examService.saveExam(exam);
        List<ExamModel> findLearnerExams = examService.findLearnerExams(userId);
        return findLearnerExams.get(0);
    }

    @Before
    public void setUp() throws Exception {
        userModel = setUpUser("abc");
        userId = userModel.getId();

        examModel = setUpExam(userId);
        examId = examModel.getId();

        userModel2 = setUpUser("bbb");
        zeroExamUserId = userModel2.getId();

        exam = new ExamModel();
        exam.setUserId(userId);
        exam.setCourseId(1);
        
        System.out.println("set up data sucessfully");
    }

    @After
    public void tearDown() {
        try {
            userService.delete(userModel);
            userService.delete(userModel2);
            System.out.println("tear down data sucessfully");
        } catch (Exception e) {
            assert false;
        }
	}
	
	@Test
    public void testFindLearnerExams() throws Exception {
        List<ExamModel> exams = examService.findLearnerExams(userId);
        Assert.assertNotNull(exams);
        Assert.assertNotNull(exams.get(0));
        Assert.assertNotEquals(0,exams.size());
    }
    
    @Test
    public void testFindLearnerExams2() throws Exception {
        List<ExamModel> exams = examService.findLearnerExams(zeroExamUserId);
        Assert.assertEquals(new ArrayList<ExamModel>(), exams);
    }
    
    @Test
    public void testFindLearnerExams3() throws Exception {
        List<ExamModel> exams = examService.findLearnerExams(0);
        Assert.assertNull(exams);
    }
    
    @Test
    public void testFindLearnerExams4() throws Exception {
        List<ExamModel> exams = examService.findLearnerExams(null);
        Assert.assertNull(exams);
    }
    
    @Test
    public void testSaveExam() throws Exception {
        examService.saveExam(exam);
        ExamModel foundExam = examService.findExamById(examId);
        Assert.assertNotNull(foundExam);
    }
    
    @Test(expected=Exception.class)
    public void testSaveExam2() throws Exception {
        examService.saveExam(null);
    }
    
    @Test
    public void testFindExamById() throws Exception {
        ExamModel foundExam = examService.findExamById(examId);
        Assert.assertNotNull(foundExam);
    }
    
    @Test
    public void testFindExamById2() throws Exception {
        ExamModel foundExam = examService.findExamById(0);
        Assert.assertNull(foundExam);
    }
    
    @Test
    public void testFindExamById3() throws Exception {
        ExamModel foundExam = examService.findExamById(null);
        Assert.assertNull(foundExam);
	}
}
