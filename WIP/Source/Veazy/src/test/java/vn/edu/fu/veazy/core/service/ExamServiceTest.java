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
import vn.edu.fu.veazy.core.model.ExamModel;

/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:testContext.xml" } )
public class ExamServiceTest {

    @Autowired
    private ExamService examService;
    private Integer userId;
    private Integer zeroExamUserId;
    private Integer examId;
    private ExamModel exam;
    
    @Before
    public void setUp() {
        //need initialed data
        userId = 0;
        zeroExamUserId = 0;
        examId = 0;
        exam = new ExamModel();
    }

    @After
    public void tearDown() {
        try {
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testFindLearnerExams() throws Exception {
        List<ExamModel> exams = examService.findLearnerExams(userId);
        Assert.assertNotNull(exams);
        Assert.assertNotNull(exams.get(0));
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
    }
    
    @Test
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
