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
import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.form.ExamPartForm;
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.response.ExamPartResponse;


/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:testContext.xml" } )
public class QuestionBankServiceTest {
    
    private QuestionModel question;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QuestionBankService questionBankService;

    private Integer courseId;
    private Integer skill;
    private List<ExamPartForm> examPart;

    private QuestionModel setUpQuestion() throws Exception {
        QuestionForm form = new QuestionForm();
        form.setCourseId(courseId);
        form.setQuestionSkill(skill);
        question = new QuestionModel(form);
        return questionService.saveQuestion(question);
    }

    private List<ExamPartForm> setUpExamPartList(Integer numberOfQuestion, Integer skill) {
        ExamPartForm epf = new ExamPartForm();
        epf.setNumberOfQuestion(numberOfQuestion);
        epf.setSkill(skill);
        List<ExamPartForm> forms = new ArrayList<ExamPartForm>();
        forms.add(epf);
        return forms;
    }

	@Before
    public void setUp() throws Exception {
        courseId = 1;
        skill = Const.LISTENING;
        setUpQuestion();
        examPart = setUpExamPartList(1, skill);
    }

    @After
    public void tearDown() {
        try {
            questionService.delete(question);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testGenerateTest() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(courseId, examPart);
        Assert.assertNotNull(generateTest);
    }

    @Test
    public void testGenerateTest2() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(courseId, setUpExamPartList(1, -1));
        Assert.assertNull(generateTest);
    }

    @Test
    public void testGenerateTest3() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(-1, examPart);
        Assert.assertNull(generateTest);
    }
    
    @Test
    public void testGenerateTest4() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(-1, setUpExamPartList(1, -1));
        Assert.assertNull(generateTest);
    }

    @Test
    public void testGenerateTest5() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(courseId, setUpExamPartList(-1, skill));
        Assert.assertNull(generateTest);
    }

    @Test
    public void testGenerateTest6() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(courseId, setUpExamPartList(-1, -1));
        Assert.assertNull(generateTest);
    }

    @Test
    public void testGenerateTest7() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(-1, setUpExamPartList(-1, skill));
        Assert.assertNull(generateTest);
    }

    @Test
    public void testGenerateTest8() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(-1, setUpExamPartList(-1, -1));
        Assert.assertNull(generateTest);
    }
    
}
