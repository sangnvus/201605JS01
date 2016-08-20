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
import vn.edu.fu.veazy.core.form.AnswerForm;
import vn.edu.fu.veazy.core.form.ExamPartForm;
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.form.RegisterForm;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.UserModel;
import vn.edu.fu.veazy.core.response.ExamPartResponse;

/**
 *
 * @author Hoang Linh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
public class QuestionBankServiceTest {

    private UserModel user;

    @Autowired
    private QuestionBankService questionBankService;
    @Autowired
    private UserService userService;

    private Integer userId;
    private Integer courseId;
    private Integer skill;
    private List<ExamPartForm> examPart;

    public UserModel setUpUser(String userName) throws Exception {
        RegisterForm form = new RegisterForm();
        form.setUsername(userName);
        form.setEmail(userName);
        form.setPassword(userName);
        userService.saveUser(form);
        UserModel user = userService.findUserByUsername(userName);
        return user;
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
        user = setUpUser("user1");
        userId = user.getId();
        courseId = 1;
        skill = Const.QUESTIONSKILL_LISTENING;
        examPart = setUpExamPartList(1, skill);
    }

    @After
    public void tearDown() {
        try {
            userService.delete(user);
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testGenerateTest() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(userId, courseId, examPart);
        Assert.assertNotNull(generateTest);
    }

    @Test
    public void testGenerateTest2() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(userId, courseId, setUpExamPartList(1, -1));
        for(ExamPartResponse response: generateTest){
            Assert.assertEquals(0, response.getQuestions().size());
        }
    }

    @Test
    public void testGenerateTest3() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(userId, -1, examPart);
        Assert.assertNull(generateTest);
    }

    @Test
    public void testGenerateTest4() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(userId,-1, setUpExamPartList(1, -1));
        Assert.assertNull(generateTest);
    }

    @Test
    public void testGenerateTest5() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(userId,courseId, setUpExamPartList(-1, skill));
        for(ExamPartResponse response: generateTest){
            Assert.assertEquals(0, response.getQuestions().size());
        }
    }

    @Test
    public void testGenerateTest6() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(userId,courseId, setUpExamPartList(-1, -1));
        for(ExamPartResponse response: generateTest){
            Assert.assertEquals(0, response.getQuestions().size());
        }
    }

    @Test
    public void testGenerateTest7() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(userId,-1, setUpExamPartList(-1, skill));
        Assert.assertNull(generateTest);
    }

    @Test
    public void testGenerateTest8() throws Exception {
        List<ExamPartResponse> generateTest = questionBankService.generateTest(userId,-1, setUpExamPartList(-1, -1));
        Assert.assertNull(generateTest);
    }

}
