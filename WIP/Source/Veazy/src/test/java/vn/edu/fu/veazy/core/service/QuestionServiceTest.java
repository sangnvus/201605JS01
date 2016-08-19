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
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.model.QuestionModel;

/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( locations = { "classpath:testContext.xml" } )
public class QuestionServiceTest {
    
    private QuestionModel question;
    private QuestionModel groupQuestion;
    @Autowired
    private QuestionService questionService;

    private Integer questionId;
    private Integer questionCode;
    private Integer courseId;
    private Integer skill;

    private QuestionForm setUpQuestionForm(String question) throws Exception {
        QuestionForm form = new QuestionForm();
        form.setCourseId(courseId);
        form.setQuestionSkill(skill);
        form.setQuestion(question);
        return form;
    }

    private QuestionModel setUpQuestion() throws Exception {
        QuestionModel question = new QuestionModel(setUpQuestionForm("sample question"));
        return questionService.saveQuestion(question);
    }

    private QuestionModel setUpGroupQuestion() throws Exception {
        List<QuestionForm> forms = new ArrayList();
        forms.add(setUpQuestionForm("single question 1"));
        forms.add(setUpQuestionForm("single question 2"));

        QuestionForm form = setUpQuestionForm("sample question");
        form.setListQuestions(forms);
        QuestionModel question = new QuestionModel(form);
        return questionService.saveQuestion(question);
    }
	
    @Before
    public void setUp() throws Exception {
        courseId = 1;
        skill = Const.LISTENING;

        question = setUpQuestion();
        groupQuestion = setUpGroupQuestion();

        questionId = question.getId();
        questionCode = question.getQuestionCode();
    }

    @After
    public void tearDown() {
        try {
            questionService.delete(question);
            questionService.delete(groupQuestion);
        } catch (Exception e) {
            assert false;
        }
    }
	
    @Test
    public void testSaveExam() throws Exception {
        QuestionModel saveQuestion = questionService.saveQuestion(question);
        Assert.assertNotNull(saveQuestion);
    }

    @Test
    public void testSaveExam2() throws Exception {
        QuestionModel saveQuestion = questionService.saveQuestion(null);
        Assert.assertNull(saveQuestion);
    }

    @Test
    public void testSaveExam3() throws Exception {
        QuestionModel saveQuestion = questionService.saveQuestion(groupQuestion);
        Assert.assertNotNull(saveQuestion);
    }

    @Test
    public void testFindQuestionById() throws Exception {
        QuestionModel question = questionService.findQuestionById(questionId);
        Assert.assertNotNull(question);
    }

    @Test
    public void testFindQuestionById2() throws Exception {
        QuestionModel question = questionService.findQuestionById(null);
        Assert.assertNull(question);
    }

    @Test
    public void testFindQuestionById3() throws Exception {
        QuestionModel question = questionService.findQuestionById(-1);
        Assert.assertNull(question);
    }

    @Test
    public void testFindQuestionByCode() throws Exception {
        QuestionModel question = questionService.findQuestionByCode(questionCode);
        Assert.assertNotNull(question);
    }

    @Test
    public void testFindQuestionByCode2() throws Exception {
        QuestionModel question = questionService.findQuestionByCode(null);
        Assert.assertNull(question);
    }

    @Test
    public void testFindQuestionByCode3() throws Exception {
        QuestionModel question = questionService.findQuestionByCode(-1);
        Assert.assertNull(question);
    }

    @Test
    public void testFindAllQuestion() throws Exception {
        List<QuestionModel> questions = questionService.findAllQuestion();
        Assert.assertNotNull(questions);
        Assert.assertNotEquals(0, questions.size());
    }

    @Test
    public void testUpdateQuestion() throws Exception {
        questionService.update(question);
    }

    @Test
    public void testUpdateQuestion2() throws Exception {
        questionService.update(groupQuestion);
    }

    @Test
    public void tesDeleteQuestion() throws Exception {
        questionService.delete(question);
    }
}
