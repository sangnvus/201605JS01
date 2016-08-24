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
import org.springframework.transaction.annotation.Transactional;

import vn.edu.fu.veazy.core.common.Const;
import vn.edu.fu.veazy.core.dao.GenericDao;
import vn.edu.fu.veazy.core.form.AnswerForm;
import vn.edu.fu.veazy.core.form.QuestionForm;
import vn.edu.fu.veazy.core.model.AnswerModel;
import vn.edu.fu.veazy.core.model.QuestionModel;
import vn.edu.fu.veazy.core.model.UserModel;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:testContext.xml"})
@Transactional
public class QuestionServiceTest {

    @Autowired
    private QuestionService questionService;
    @Autowired
    private GenericDao<QuestionModel, Integer> questionDao;
    @Autowired
    private GenericDao<UserModel, Integer> userDao;

    private QuestionModel initQuestion;
    private QuestionModel initGroupQuestion;
    private UserModel initUser;

    private QuestionForm setUpQuestionForm(String q, Integer type, List<QuestionForm> listQues)
            throws Exception {
        if (initUser == null) return null;
        QuestionForm form = new QuestionForm();
        form.setCourseId(1);
        form.setCreatorId(initUser.getId());
        form.setQuestion(q);
        form.setQuestionAnswerType(Const.MULTIPLE_CHOICE);
        form.setQuestionSkill(Const.QUESTIONSKILL_GRAMMAR);
        form.setQuestionType(type);
        if (type == Const.QUESTIONTYPE_SINGULAR) {
            List<AnswerForm> listAns = new ArrayList<>();
            AnswerForm answerForm = new AnswerForm("answer1", true);
            AnswerForm answerForm2 = new AnswerForm("answer2", false);
            listAns.add(answerForm);
            listAns.add(answerForm2);
            form.setListAnswers(listAns);
        } else {
            form.setListQuestions(listQues);
        }
        form.setEtaTime(1);
        return form;
    }

    @Before
    public void setUp() throws Exception {
        initUser = new UserModel("init@gmail.com", "init", "123456",
                System.currentTimeMillis());
        userDao.save(initUser);

        QuestionForm form = setUpQuestionForm("Init question", Const.QUESTIONTYPE_SINGULAR, null);
        initQuestion = new QuestionModel(form);
        questionService.saveQuestion(initQuestion);

        QuestionForm form1 = setUpQuestionForm("Q1", Const.QUESTIONTYPE_SINGULAR, null);
        QuestionForm form2 = setUpQuestionForm("Q2", Const.QUESTIONTYPE_SINGULAR, null);
        List<QuestionForm> listQues = new ArrayList<>();
        listQues.add(form1);
        listQues.add(form2);
        QuestionForm groupForm = setUpQuestionForm("Init group question", Const.QUESTIONTYPE_GROUP, listQues);
        initGroupQuestion = new QuestionModel(groupForm);
        questionService.saveQuestion(initGroupQuestion);
    }

    @After
    public void tearDown() {
        try {
            if (initUser != null) userDao.delete(initUser);
            List<QuestionModel> listQuestion = questionDao.getAll();
            if (listQuestion != null && listQuestion.size() > 0) {
                for (QuestionModel m : listQuestion) {
                    questionDao.delete(m);
                }
            }
        } catch (Exception e) {
            assert false;
        }
    }

    @Test
    public void testSaveQuestion() throws Exception {
        QuestionForm form = setUpQuestionForm("sdfsdfsdfsdf", Const.QUESTIONTYPE_SINGULAR, null);
        QuestionModel m = new QuestionModel(form);
        questionService.saveQuestion(m);
        Assert.assertNotNull(m.getId());
    }

    @Test(expected = Exception.class)
    public void testSaveQuestion2() throws Exception {
        questionService.saveQuestion(null);
    }

    @Test
    public void testSaveQuestion3() throws Exception {
        QuestionForm form1 = setUpQuestionForm("Q1", Const.QUESTIONTYPE_SINGULAR, null);
        QuestionForm form2 = setUpQuestionForm("Q2", Const.QUESTIONTYPE_SINGULAR, null);
        List<QuestionForm> listQues = new ArrayList<>();
        listQues.add(form1);
        listQues.add(form2);
        QuestionForm form = setUpQuestionForm("sdfsdfsdfsdf", Const.QUESTIONTYPE_GROUP, listQues);
        QuestionModel m = new QuestionModel(form);
        questionService.saveQuestion(m);
        Assert.assertNotNull(m.getId());
    }

    @Test
    public void testFindQuestionById() throws Exception {
        QuestionModel question = questionService.findQuestionById(initQuestion.getId());
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
        QuestionModel question = questionService.findQuestionByCode(initQuestion.getQuestionCode());
        Assert.assertNotNull(question);
    }

    @Test
    public void testFindQuestionByCode2() throws Exception {
        QuestionModel question = questionService.findQuestionByCode(-1);
        Assert.assertNull(question);
    }

    @Test
    public void testFindAllQuestion() throws Exception {
        List<QuestionModel> questions = questionService.findAllQuestion();
        Assert.assertNotNull(questions);
        Assert.assertTrue(questions.size() > 0);
    }

    @Test
    public void testUpdateQuestion() throws Exception {
        String attach = "attach";
        initQuestion.setAttachment(attach);
        questionService.update(initQuestion);
        Assert.assertEquals(attach, initQuestion.getAttachment());
    }

    @Test
    public void testUpdateQuestionAnswer() throws Exception {
        String ans = "dfsdfsdf34rwerw4342";
        AnswerModel ansM = new AnswerModel();
        ansM.setAnswer(ans);
        ansM.setQuestion(initQuestion);
        
        initQuestion.getListAnswers().add(ansM);
        questionService.update(initQuestion);
        
        List<AnswerModel> listUpdatedAns = initQuestion.getListAnswers();
        AnswerModel newOne = listUpdatedAns.get(listUpdatedAns.size()-1);
        Assert.assertEquals(ans, newOne.getAnswer());
    }

    @Test
    public void testUpdateGroupQuestion() throws Exception {
        QuestionForm form1 = setUpQuestionForm("Q3", Const.QUESTIONTYPE_SINGULAR, null);
        QuestionModel m = new QuestionModel(form1);
        
        initGroupQuestion.getListQuestions().add(m);
        
        questionService.update(initGroupQuestion);
        
        List<QuestionModel> listUpdatedQues = initGroupQuestion.getListQuestions();
        QuestionModel q3 = listUpdatedQues.get(listUpdatedQues.size()-1);
        Assert.assertEquals("Q3", q3.getQuestion());
    }

    @Test
    public void testDeleteQuestion() throws Exception {
        questionService.delete(initQuestion);;
        Assert.assertTrue(initQuestion.isDeleteFlag());
    }
}